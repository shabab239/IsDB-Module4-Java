package model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Shabab Ahmed
 */
public class SalesTableModel extends AbstractTableModel {

    private final List<Sales> salesList;
    private final String[] columnNames = {"SL", "Name", "Sales Price", "Quantity", "Total Price", "Date"};

    public SalesTableModel() {
        this.salesList = new ArrayList<>();
    }

    public void addProduct(Sales sales) {
        salesList.add(sales);
        fireTableRowsInserted(salesList.size() - 1, salesList.size() - 1);
    }

    public void removeProduct(int rowIndex) {
        salesList.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public Sales getProductAt(int rowIndex) {
        return salesList.get(rowIndex);
    }

    public Long getProductId(int rowIndex) {
        return salesList.get(rowIndex).getId();
    }

    @Override
    public int getRowCount() {
        return salesList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Sales sales = salesList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return sales.getName();
            case 2:
                return sales.getSalesPrice();
            case 3:
                return sales.getQuantity();
            case 4:
                return sales.getTotalPrice();
            case 5:
                return sales.getDate();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Sales sales = salesList.get(rowIndex);
        switch (columnIndex) {
            case 1:
                sales.setName((String) aValue);
            case 2:
                sales.setSalesPrice((Double) aValue);
            case 3:
                sales.setQuantity((Integer) aValue);
            case 4:
                sales.setTotalPrice((Double) aValue);
            case 5:
                sales.setDate((Date) aValue);
                break;
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
