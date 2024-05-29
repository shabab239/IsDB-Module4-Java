package model;

/**
 *
 * @author shaba
 */
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ProductTableModel extends AbstractTableModel {

    private final List<Product> products;
    private final String[] columnNames = {"Name", "Unit Price", "Quantity", "Sales Price"};

    public ProductTableModel() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
        fireTableRowsInserted(products.size() - 1, products.size() - 1);
    }

    public void removeProduct(int rowIndex) {
        products.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public Product getProduct(int rowIndex) {
        return products.get(rowIndex);
    }

    public Long getProductId(int rowIndex) {
        return products.get(rowIndex).getId();
    }

    @Override
    public int getRowCount() {
        return products.size();
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
        Product product = products.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return product.getName();
            case 1:
                return product.getUnitPrice();
            case 2:
                return product.getQuantity();
            case 3:
                return product.getSalesPrice();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Product product = products.get(rowIndex);
        switch (columnIndex) {
            case 0:
                product.setName((String) aValue);
                break;
            case 1:
                product.setUnitPrice((Double) aValue);
                break;
            case 2:
                product.setQuantity((Integer) aValue);
                break;
            case 3:
                product.setSalesPrice((Double) aValue);
                break;
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
}
