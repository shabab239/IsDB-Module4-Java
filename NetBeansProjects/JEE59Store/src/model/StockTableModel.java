package model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Shabab Ahmed
 */
public class StockTableModel extends AbstractTableModel {

    private final List<Stock> stockList;
    private final String[] columnNames = {"SL", "Name", "Quantity", "Date"};

    public StockTableModel() {
        this.stockList = new ArrayList<>();
    }

    public void addProduct(Stock stock) {
        stockList.add(stock);
        fireTableRowsInserted(stockList.size() - 1, stockList.size() - 1);
    }

    public void removeProduct(int rowIndex) {
        stockList.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public Stock getProductAt(int rowIndex) {
        return stockList.get(rowIndex);
    }

    public Long getProductId(int rowIndex) {
        return stockList.get(rowIndex).getId();
    }

    @Override
    public int getRowCount() {
        return stockList.size();
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
        Stock stock = stockList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return stock.getName();
            case 2:
                return stock.getQuantity();
            case 3:
                return stock.getDate();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Stock stock = stockList.get(rowIndex);
        switch (columnIndex) {
            case 1:
                stock.setName((String) aValue);
            case 2:
                stock.setQuantity((Integer) aValue);
            case 3:
                stock.setDate((Date) aValue);
                break;
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
