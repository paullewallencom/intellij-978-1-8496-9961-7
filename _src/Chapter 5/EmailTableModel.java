package org.example.desktop;

import org.example.ws.EntityEmail;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: brevleq
 * Date: 24/06/13
 * Time: 19:07
 * To change this template use File | Settings | File Templates.
 */
public class EmailTableModel extends AbstractTableModel {

    List<EntityEmail> emails;

    public EmailTableModel(List<EntityEmail> emails) {
        this.emails = emails;
    }

    public void addRow(EntityEmail phone) {
        emails.add(phone);
        fireTableRowsInserted(emails.size(), emails.size());
    }

    public void removeRow(int index) {
        if (!emails.isEmpty()) {
            emails.remove(index);
            fireTableRowsDeleted(index, index);
        }
    }

    @Override
    public int getRowCount() {
        return emails.size();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return emails.get(rowIndex).getEmail();
    }

    @Override
    public String getColumnName(int column) {
        return "E-mails";
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        EntityEmail phone = emails.get(rowIndex);
        phone.setEmail((String) value);
        fireTableCellUpdated(rowIndex, columnIndex);
    }
}
