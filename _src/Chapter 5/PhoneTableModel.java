package org.example.desktop;

import org.example.ws.EntityPhone;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: brevleq
 * Date: 24/06/13
 * Time: 19:07
 * To change this template use File | Settings | File Templates.
 */
public class PhoneTableModel extends AbstractTableModel {

    List<EntityPhone> phones;

    public PhoneTableModel(List<EntityPhone> phones) {
        this.phones = phones;
    }

    public void addRow(EntityPhone phone) {
        phones.add(phone);
        fireTableRowsInserted(phones.size(), phones.size());
    }

    public void removeRow(int index) {
        if (!phones.isEmpty()) {
            phones.remove(index);
            fireTableRowsDeleted(index, index);
        }
    }

    @Override
    public int getRowCount() {
        return phones.size();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return phones.get(rowIndex).getNumber();
    }

    @Override
    public String getColumnName(int column) {
        return "Phones";
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        EntityPhone phone = phones.get(rowIndex);
        phone.setNumber((String) value);
        fireTableCellUpdated(rowIndex, columnIndex);
    }
}
