package org.example.desktop;

import org.example.ws.*;

import javax.swing.*;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created with IntelliJ IDEA.
 * User: brevleq
 * Date: 20/06/13
 * Time: 15:22
 * To change this template use File | Settings | File Templates.
 */
public class MainForm {
    private JPanel panel1;
    private JTextField completeNameTextField;
    private JTextField placeOfBirthTextField;
    private JSpinner birthDateSpinner;
    private JTable phoneTable;
    private JTable emailTable;
    private JButton addPhoneButton;
    private JButton removePhoneButton;
    private JButton addEmailButton;
    private JButton removeEmailButton;
    private JButton saveButton;
    private JButton clearButton;

    private EntityPerson person;

    public MainForm() {
        addPhoneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((PhoneTableModel) phoneTable.getModel()).addRow(new EntityPhone());
            }
        });
        removePhoneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = phoneTable.getSelectedRow();
                ((PhoneTableModel) phoneTable.getModel()).removeRow(selectedIndex);
                if (phoneTable.getRowCount()==0)
                    return;
                if (selectedIndex == phoneTable.getRowCount())
                    phoneTable.setRowSelectionInterval(phoneTable.getRowCount() - 1, phoneTable.getRowCount() - 1);
                else if (selectedIndex < phoneTable.getRowCount())
                    phoneTable.setRowSelectionInterval(selectedIndex, selectedIndex);

            }
        });
        addEmailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((EmailTableModel) emailTable.getModel()).addRow(new EntityEmail());
            }
        });
        removeEmailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = emailTable.getSelectedRow();
                ((EmailTableModel) emailTable.getModel()).removeRow(selectedIndex);
                if (emailTable.getRowCount()==0)
                    return;
                if (selectedIndex == emailTable.getRowCount())
                    emailTable.setRowSelectionInterval(emailTable.getRowCount() - 1, emailTable.getRowCount() - 1);
                else if (selectedIndex < emailTable.getRowCount())
                    emailTable.setRowSelectionInterval(selectedIndex, selectedIndex);
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ContactsPersisterService service=new ContactsPersisterService();
                ContactsPersister client=service.getContactsPersisterPort();
                if(client.create(getData()))
                    JOptionPane.showMessageDialog(null,"The contact was sucessfully saved!");
                else
                    JOptionPane.showMessageDialog(null,"Sorry but was not possible save the contact!");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainForm");
        MainForm mainForm = new MainForm();
        mainForm.setData(new EntityPerson());
        frame.setContentPane(mainForm.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        Date date = new Date();
        SpinnerDateModel dateModel = new SpinnerDateModel();
        dateModel.setValue(date);
        birthDateSpinner = new JSpinner(dateModel);
    }

    public void setData(EntityPerson data) {
        this.person = data;
        placeOfBirthTextField.setText(person.getPlaceOfBirth());
        completeNameTextField.setText(person.getCompleteName());
        phoneTable.setModel(new PhoneTableModel(person.getPhonesById()));
        emailTable.setModel(new EmailTableModel(person.getEmailsById()));
    }

    public EntityPerson getData() {
        person.setPlaceOfBirth(placeOfBirthTextField.getText());
        person.setCompleteName(completeNameTextField.getText());
        try {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime((Date) birthDateSpinner.getValue());
            XMLGregorianCalendar convertedDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
            person.setBirthDate(convertedDate);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return person;
    }
}
