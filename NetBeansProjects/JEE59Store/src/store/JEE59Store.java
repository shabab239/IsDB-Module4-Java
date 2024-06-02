package store;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import model.Product;
import model.ProductTableModel;
import model.Sales;
import model.SalesTableModel;
import model.Stock;
import model.StockTableModel;
import util.DBUtil;

/**
 *
 * @author Shabab Ahmed
 */
public class JEE59Store extends javax.swing.JFrame {

    DBUtil dBUtil = new DBUtil();

    /**
     * Creates new form JEE59Store
     */
    public JEE59Store() {
        initComponents();
        loadProductTableData();
        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (tabbedPane.getSelectedIndex() == 0) {
                    loadProductTableData();
                }
                if (tabbedPane.getSelectedIndex() == 1) {
                    loadComboProducts();
                    loadSalesTableData();
                    salesDateField.setDate(new Date());
                }
                if (tabbedPane.getSelectedIndex() == 2) {
                    loadStockData();
                    loadStockComboProducts();
                    stockDateField.setDate(new Date());
                }
                if (tabbedPane.getSelectedIndex() == 3) {
                    reportTableTitle.setText("Report");

                    DefaultTableModel model = new DefaultTableModel();
                    model.setColumnIdentifiers(new Object[]{"", "", "", ""});
                    model.addRow(new Object[]{});
                    model.addRow(new Object[]{});
                    model.addRow(new Object[]{});
                    model.addRow(new Object[]{});
                    reportTable.setModel(model);

                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(new Date());
                    calendar.add(Calendar.DAY_OF_MONTH, -7);
                    reportFromDate.setDate(calendar.getTime());
                    reportToDate.setDate(new Date());
                }
            }
        });
//        productUnitPriceField.getDocument().addDocumentListener(new DocumentListener() {
//            @Override
//            public void insertUpdate(DocumentEvent e) {
//                calculateTotalPrice();
//            }
//
//            @Override
//            public void removeUpdate(DocumentEvent e) {
//                calculateTotalPrice();
//            }
//
//            @Override
//            public void changedUpdate(DocumentEvent e) {
//                calculateTotalPrice();
//            }
//
//        });
//        productQuantityField.getDocument().addDocumentListener(new DocumentListener() {
//            @Override
//            public void insertUpdate(DocumentEvent e) {
//                calculateTotalPrice();
//            }
//
//            @Override
//            public void removeUpdate(DocumentEvent e) {
//                calculateTotalPrice();
//            }
//
//            @Override
//            public void changedUpdate(DocumentEvent e) {
//                calculateTotalPrice();
//            }
//
//        });
        salesProductCombo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedProductName = (String) e.getItem();
                    getSalesPriceAndQuantity(selectedProductName);
                }
            }
        });
        salesQuantityField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                calculateTotalSalesPrice();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                calculateTotalSalesPrice();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                calculateTotalSalesPrice();
            }

        });
        salesPriceField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                calculateTotalSalesPrice();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                calculateTotalSalesPrice();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                calculateTotalSalesPrice();
            }

        });
    }

    public void loadProductTableData() {
        ProductTableModel productTableModel = new ProductTableModel();

        try {
            String sql = "SELECT * FROM product;";
            PreparedStatement ps = dBUtil.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                Double unitPrice = rs.getDouble("unitPrice");
//                if (rs.wasNull()) {
//                    unitPrice = null;
//                }
                Double salesPrice = rs.getDouble("salesPrice");
//                if (rs.wasNull()) {
//                    salesPrice = null;
//                }

                Product product = new Product(id, name, unitPrice, salesPrice);
                productTableModel.addProduct(product);
            }

            productListTable.setModel(productTableModel);
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dBUtil.getConnection().close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void addProduct() {
        try {
            String productName;
            Double unitPrice = null;
            Double salesPrice = null;

            if (productUnitPriceField.getText().isBlank()) {
                JOptionPane.showMessageDialog(rootPane, "Product Name is Required");
                return;
            }

            productName = productUnitPriceField.getText().trim();

            try {
                if (!productNameField.getText().isBlank()) {
                    unitPrice = Double.valueOf(productNameField.getText().trim());
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(rootPane, "Invalid Unit Price");
                return;
            }

            try {
                if (!productSalesPriceField.getText().isBlank()) {
                    salesPrice = Double.valueOf(productSalesPriceField.getText().trim());
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(rootPane, "Invalid Sales Price");
                return;
            }

            String sql = "select count(*) as count from product where lower(name) = lower(?);";
            PreparedStatement ps = dBUtil.getConnection().prepareStatement(sql);
            ps.setString(1, productName);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("count") > 0) {
                    JOptionPane.showMessageDialog(rootPane, "Product Already Exists!");
                    return;
                }
            }

            Connection connection = dBUtil.getConnection();
            connection.setAutoCommit(false);

            sql = "INSERT INTO product(name, unitPrice, salesPrice) VALUES (?, ?, ?);";
            ps = connection.prepareStatement(sql);
            ps.setString(1, productName);

            if (unitPrice != null) {
                ps.setDouble(2, unitPrice);
            } else {
                ps.setNull(2, java.sql.Types.DOUBLE);
            }

            if (salesPrice != null) {
                ps.setDouble(3, salesPrice);
            } else {
                ps.setNull(3, java.sql.Types.DOUBLE);
            }

            ps.executeUpdate();

            sql = "INSERT INTO stock(name, quantity, date) VALUES (?, ?, ?);";
            ps = connection.prepareStatement(sql);
            ps.setString(1, productName);
            ps.setInt(2, 0);
            ps.setDate(3, utilDateToSqlDate(new Date()));

            ps.executeUpdate();
            connection.commit();
            ps.close();

            JOptionPane.showMessageDialog(rootPane, "Product Added Successfully. Stock Inititated.");
            loadProductTableData();
            resetProductFields();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dBUtil.getConnection().close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void updateProduct() {
        if (productIdField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Please Select a Product First");
            return;
        }
        try {
            if (productUnitPriceField.getText().isBlank()) {
                JOptionPane.showMessageDialog(rootPane, "Product Name is Required");
                return;
            }

            long productId = Long.parseLong(productIdField.getText().trim());
            String productName = productUnitPriceField.getText().trim();
            Integer quantity;

            Double unitPrice = null;
            if (!productNameField.getText().trim().isEmpty()) {
                try {
                    if (!productNameField.getText().isBlank()) {
                        unitPrice = Double.valueOf(productNameField.getText().trim());
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Invalid Unit Price");
                    return;
                }
            }
            Double salesPrice = null;
            if (!productSalesPriceField.getText().isBlank()) {
                try {
                    if (!productSalesPriceField.getText().isBlank()) {
                        salesPrice = Double.valueOf(productSalesPriceField.getText().trim());
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Invalid Sales Price");
                    return;
                }
            }
            String sql = "update product set name = ?, unitPrice = ?, salesPrice = ? where id = ?;";
            PreparedStatement ps = dBUtil.getConnection().prepareStatement(sql);

            ps.setString(1, productName);
            if (unitPrice != null) {
                ps.setDouble(2, unitPrice);
            } else {
                ps.setNull(2, java.sql.Types.DOUBLE);
            }
            if (salesPrice != null) {
                ps.setDouble(3, salesPrice);
            } else {
                ps.setNull(3, java.sql.Types.DOUBLE);
            }
            ps.setLong(4, productId);

            ps.executeUpdate();
            ps.close();

            loadProductTableData();
            resetProductFields();
            JOptionPane.showMessageDialog(rootPane, "Product Updated Successfully");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dBUtil.getConnection().close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void deleteProduct() {
        if (productIdField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Please Select a Product First");
            return;
        }
        int result = JOptionPane.showConfirmDialog(rootPane, "Are you sure? Related Sales and Stock History will NOT be deleted.");
        if (result != 0) {
            return;
        }
        
        try {
            Long productId = Long.parseLong(productIdField.getText().trim());

            String sql = "DELETE FROM product WHERE id = ?;";
            PreparedStatement ps = dBUtil.getConnection().prepareStatement(sql);

            ps.setLong(1, productId);

            ps.executeUpdate();
            ps.close();

            loadProductTableData();
            resetProductFields();
            JOptionPane.showMessageDialog(rootPane, "Product Deleted Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (dBUtil.getConnection() != null) {
                    dBUtil.getConnection().close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

//    public void calculateTotalPrice() {
//        try {
//            if (!productUnitPriceField.getText().isBlank() && !productQuantityField.getText().isBlank()) {
//                Double totalPrice = Double.valueOf(productUnitPriceField.getText().trim()) * Double.valueOf(productQuantityField.getText().trim());
//                productTotalPriceField.setText(String.valueOf(totalPrice));
//            }
//        } catch (Exception e) {
//            productTotalPriceField.setText("");
//        }
//    }
    public void calculateTotalSalesPrice() {
        try {
            if (!salesQuantityField.getText().isBlank() && !salesPriceField.getText().isBlank()) {
                Double totalPrice = Integer.valueOf(salesQuantityField.getText().trim()) * Double.valueOf(salesPriceField.getText().trim());
                salesTotalPriceField.setText(String.valueOf(totalPrice));
            }
        } catch (Exception e) {
            salesTotalPriceField.setText("");
        }
    }

    public void resetProductFields() {
        productIdField.setText("");
        productUnitPriceField.setText("");
        productNameField.setText("");
        productSalesPriceField.setText("");
        //productTotalPriceField.setText("");
    }

    public void loadComboProducts() {
        String sql = "select name from product;";
        PreparedStatement ps;
        ResultSet rs;

        salesProductCombo.removeAllItems();

        try {
            ps = dBUtil.getConnection().prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {
                String productName = rs.getString("name");
                salesProductCombo.addItem(productName);
            }
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (dBUtil.getConnection() != null) {
                    dBUtil.getConnection().close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void getSalesPriceAndQuantity(String productName) {
        String sqlProduct = "select salesPrice from product where name = ?;";
        String sqlStock = "select quantity from stock where name = ?;";
        PreparedStatement ps;
        ResultSet rs;

        try {
            ps = dBUtil.getConnection().prepareStatement(sqlProduct);
            ps.setString(1, productName);

            rs = ps.executeQuery();

            while (rs.next()) {
                String salesPrice = rs.getString("salesPrice");
                salesPriceField.setText(salesPrice);
                if (salesPrice == null || salesPrice.isBlank()) {
                    JOptionPane.showMessageDialog(rootPane, "This Product has no sales price!");
                }
            }
            ps = dBUtil.getConnection().prepareStatement(sqlStock);
            ps.setString(1, productName);

            rs = ps.executeQuery();
            while (rs.next()) {
                Integer quantity = rs.getInt("quantity");
                salesAvailableField.setText(String.valueOf(quantity));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (dBUtil.getConnection() != null) {
                    dBUtil.getConnection().close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void loadSalesTableData() {
        SalesTableModel salesTableModel = new SalesTableModel();

        try {
            String sql = "SELECT * FROM sales;";
            PreparedStatement ps = dBUtil.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                Double salesPrice = rs.getDouble("salesPrice");
                Integer quantity = rs.getInt("quantity");
                Double totalPrice = rs.getDouble("totalPrice");
                Date date = rs.getDate("date");

                Sales sales = new Sales(id, name, salesPrice, quantity, totalPrice, date);
                salesTableModel.addProduct(sales);
            }

            salesListTable.setModel(salesTableModel);
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dBUtil.getConnection().close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void addSales() {
        try {
            Integer quantity = null;
            if (salesQuantityField.getText().isBlank()) {
                JOptionPane.showMessageDialog(rootPane, "Quantity is Required");
                return;
            } else {
                try {
                    quantity = Integer.valueOf(salesQuantityField.getText().trim());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Invalid Quantity");
                    return;
                }
            }

            if (Integer.parseInt(salesAvailableField.getText()) < Integer.parseInt(salesQuantityField.getText())) {
                JOptionPane.showMessageDialog(rootPane, "Product is Out of Stock");
                return;
            }

            Date date = salesDateField.getDate();
            java.sql.Date sqlDate = utilDateToSqlDate(date);
            if (sqlDate == null) {
                JOptionPane.showMessageDialog(rootPane, "Date is Required");
                return;
            }

            String sql = "insert into sales(name, salesPrice, quantity, totalPrice, date) values(?, ?, ?, ?, ?)";

            Connection connection = dBUtil.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, salesProductCombo.getSelectedItem().toString());
            ps.setDouble(2, Double.parseDouble(salesPriceField.getText().trim()));
            ps.setDouble(3, Integer.parseInt(salesQuantityField.getText().trim()));
            ps.setDouble(4, Double.parseDouble(salesTotalPriceField.getText().trim()));
            ps.setDate(5, sqlDate);

            ps.executeUpdate();

            sql = "update stock set quantity = quantity - ? where name = ?;";
            ps = dBUtil.getConnection().prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(salesQuantityField.getText().trim()));
            ps.setString(2, salesProductCombo.getSelectedItem().toString());

            ps.executeUpdate();

            connection.commit();

            ps.close();
            resetSalesFields();
            loadSalesTableData();
            JOptionPane.showMessageDialog(this, "Sales Saved Successfully");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (dBUtil.getConnection() != null) {
                    dBUtil.getConnection().close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void loadStockData() {
        StockTableModel stockTableModel = new StockTableModel();

        try {
            String sql = "SELECT * FROM stock;";
            PreparedStatement ps = dBUtil.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                Integer quantity = rs.getInt("quantity");
                Date date = rs.getDate("date");

                Stock stock = new Stock(id, name, quantity, date);
                stockTableModel.addProduct(stock);
            }

            stockHistoryTable.setModel(stockTableModel);
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dBUtil.getConnection().close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void updateStock() {
        try {
            String productName;
            Integer quantity = null;

            Date date = stockDateField.getDate();
            java.sql.Date sqlDate = utilDateToSqlDate(date);
            if (sqlDate == null) {
                JOptionPane.showMessageDialog(rootPane, "Date is Required");
                return;
            }

            if (stockQuantityField.getText().isBlank()) {
                JOptionPane.showMessageDialog(rootPane, "Product Quantity is Required");
                return;
            }

            productName = stockProductCombo.getSelectedItem().toString();
            quantity = Integer.parseInt(stockQuantityField.getText().trim());

            String sql = "update stock set quantity = quantity + ? where lower(name) = lower(?);";
            PreparedStatement ps = dBUtil.getConnection().prepareStatement(sql);
            ps.setInt(1, quantity);
            ps.setString(2, productName);

            ps.executeUpdate();
            ps.close();

            JOptionPane.showMessageDialog(rootPane, "Stock Updated Successfully");
            loadStockData();
            stockQuantityField.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dBUtil.getConnection().close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void loadStockComboProducts() {
        String sql = "select name from product;";
        PreparedStatement ps;
        ResultSet rs;

        stockProductCombo.removeAllItems();

        try {
            ps = dBUtil.getConnection().prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {
                String productName = rs.getString("name");
                stockProductCombo.addItem(productName);
            }
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (dBUtil.getConnection() != null) {
                    dBUtil.getConnection().close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void viewSalesReport() {
        try {
            Date fromDate = reportFromDate.getDate();
            Date toDate = reportToDate.getDate();
            java.sql.Date sqlFromDate = utilDateToSqlDate(fromDate);
            java.sql.Date sqlToDate = utilDateToSqlDate(toDate);
            if (sqlFromDate == null || sqlToDate == null) {
                JOptionPane.showMessageDialog(rootPane, "Both Dates are Required");
                return;
            }

            String sql = "select * from sales where date between ? and ?;";
            PreparedStatement ps = dBUtil.getConnection().prepareStatement(sql);

            ps.setDate(1, sqlFromDate);
            ps.setDate(2, sqlToDate);

            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(new Object[]{"SL", "Name", "Sales Price", "Quantity", "Total Price", "Date"});

            int sl = 0;
            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                Double salesPrice = rs.getDouble("salesPrice");
                Integer quantity = rs.getInt("quantity");
                Double totalPrice = rs.getDouble("totalPrice");
                Date date = rs.getDate("date");

                model.addRow(new Object[]{sl, name, salesPrice, quantity, totalPrice, date});
                sl++;
            }
            reportTable.setModel(model);

            ps.close();
            reportTableTitle.setText("Sales Report");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (dBUtil.getConnection() != null) {
                    dBUtil.getConnection().close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void viewStockReport() {
        try {
            Date fromDate = reportFromDate.getDate();
            Date toDate = reportToDate.getDate();
            java.sql.Date sqlFromDate = utilDateToSqlDate(fromDate);
            java.sql.Date sqlToDate = utilDateToSqlDate(toDate);
            if (sqlFromDate == null || sqlToDate == null) {
                JOptionPane.showMessageDialog(rootPane, "Both Dates are Required");
                return;
            }

            String sql = "select * from stock where date between ? and ?;";
            PreparedStatement ps = dBUtil.getConnection().prepareStatement(sql);

            ps.setDate(1, sqlFromDate);
            ps.setDate(2, sqlToDate);

            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(new Object[]{"SL", "Name", "Quantity", "Date"});

            int sl = 0;
            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                Integer quantity = rs.getInt("quantity");
                Date date = rs.getDate("date");

                model.addRow(new Object[]{sl, name, quantity, date});
                sl++;
            }
            reportTable.setModel(model);

            ps.close();
            reportTableTitle.setText("Stock Report");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (dBUtil.getConnection() != null) {
                    dBUtil.getConnection().close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static java.util.Date sqlDateToUtilDate(java.sql.Date sqlDate) {
        if (sqlDate != null) {
            return new java.util.Date(sqlDate.getTime());
        }
        return null;
    }

    public static java.sql.Date utilDateToSqlDate(java.util.Date utilDate) {
        if (utilDate != null) {
            return new java.sql.Date(utilDate.getTime());
        }
        return null;
    }

    public void resetSalesFields() {
        salesQuantityField.setText("");
        salesTotalPriceField.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titlePane = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tabbedPane = new javax.swing.JTabbedPane();
        productPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        productIdField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        productUnitPriceField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        productNameField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        productSalesPriceField = new javax.swing.JTextField();
        addProductBtn = new javax.swing.JButton();
        editProductBtn = new javax.swing.JButton();
        resetProductBtn = new javax.swing.JButton();
        deleteProductBtn = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        productListTable = new javax.swing.JTable();
        salesPanel = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        salesQuantityField = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        salesPriceField = new javax.swing.JTextField();
        salesTotalPriceField = new javax.swing.JTextField();
        addSalesBtn = new javax.swing.JButton();
        salesProductCombo = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        salesListTable = new javax.swing.JTable();
        salesDateField = new com.toedter.calendar.JDateChooser();
        jLabel18 = new javax.swing.JLabel();
        salesAvailableField = new javax.swing.JTextField();
        stockPanel = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        stockQuantityField = new javax.swing.JTextField();
        updateStockBtn = new javax.swing.JButton();
        stockProductCombo = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        stockHistoryTable = new javax.swing.JTable();
        stockDateField = new com.toedter.calendar.JDateChooser();
        reportPanel = new javax.swing.JPanel();
        reportTableTitle = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        reportSalesBtn = new javax.swing.JButton();
        reportFromDate = new com.toedter.calendar.JDateChooser();
        jLabel26 = new javax.swing.JLabel();
        reportToDate = new com.toedter.calendar.JDateChooser();
        jScrollPane3 = new javax.swing.JScrollPane();
        reportTable = new javax.swing.JTable();
        jLabel25 = new javax.swing.JLabel();
        reportStockBtn = new javax.swing.JButton();
        menuPane = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        menuSalesBtn = new javax.swing.JButton();
        menuStockBtn = new javax.swing.JButton();
        menuReportBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(850, 700));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Cambria", 1, 30)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("JEE 59 Store");

        javax.swing.GroupLayout titlePaneLayout = new javax.swing.GroupLayout(titlePane);
        titlePane.setLayout(titlePaneLayout);
        titlePaneLayout.setHorizontalGroup(
            titlePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titlePaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        titlePaneLayout.setVerticalGroup(
            titlePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlePaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        getContentPane().add(titlePane, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 788, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Manage Product");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Product Id");

        productIdField.setEditable(false);
        productIdField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productIdFieldActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Unit Price");

        productUnitPriceField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productUnitPriceFieldActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Product Name");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Sales Price");

        addProductBtn.setText("Add");
        addProductBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addProductBtnMouseClicked(evt);
            }
        });

        editProductBtn.setText("Update");
        editProductBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editProductBtnMouseClicked(evt);
            }
        });

        resetProductBtn.setText("Reset");
        resetProductBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                resetProductBtnMouseClicked(evt);
            }
        });

        deleteProductBtn.setText("Delete");
        deleteProductBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteProductBtnMouseClicked(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Product List");

        productListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        productListTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                productListTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(productListTable);

        javax.swing.GroupLayout productPanelLayout = new javax.swing.GroupLayout(productPanel);
        productPanel.setLayout(productPanelLayout);
        productPanelLayout.setHorizontalGroup(
            productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(productPanelLayout.createSequentialGroup()
                .addGroup(productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(productPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(productPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(productPanelLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(productPanelLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                                .addComponent(productIdField, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, productPanelLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(productUnitPriceField)))
                        .addGap(30, 30, 30)
                        .addGroup(productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(productPanelLayout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(productSalesPriceField, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(productPanelLayout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(productNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 48, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, productPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(addProductBtn)
                .addGap(18, 18, 18)
                .addComponent(editProductBtn)
                .addGap(18, 18, 18)
                .addComponent(resetProductBtn)
                .addGap(18, 18, 18)
                .addComponent(deleteProductBtn)
                .addGap(112, 112, 112))
        );
        productPanelLayout.setVerticalGroup(
            productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(productIdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(productNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(productUnitPriceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(productSalesPriceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addProductBtn)
                    .addComponent(editProductBtn)
                    .addComponent(resetProductBtn)
                    .addComponent(deleteProductBtn))
                .addGap(32, 32, 32)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE))
        );

        tabbedPane.addTab("Product", productPanel);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Manage Sales");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Date");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("Select Product");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("Quantity");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("Total Price");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setText("Sales Price");

        salesPriceField.setEditable(false);

        salesTotalPriceField.setEditable(false);

        addSalesBtn.setText("Save");
        addSalesBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addSalesBtnMouseClicked(evt);
            }
        });
        addSalesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSalesBtnActionPerformed(evt);
            }
        });

        salesProductCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Sales List");

        salesListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(salesListTable);

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel18.setText("Available");

        salesAvailableField.setEditable(false);

        javax.swing.GroupLayout salesPanelLayout = new javax.swing.GroupLayout(salesPanel);
        salesPanel.setLayout(salesPanelLayout);
        salesPanelLayout.setHorizontalGroup(
            salesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(salesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(salesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(salesPanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(salesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(salesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, salesPanelLayout.createSequentialGroup()
                            .addComponent(jLabel14)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(salesQuantityField, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, salesPanelLayout.createSequentialGroup()
                            .addGroup(salesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, salesPanelLayout.createSequentialGroup()
                                    .addComponent(jLabel13)
                                    .addGap(18, 18, 18))
                                .addGroup(salesPanelLayout.createSequentialGroup()
                                    .addComponent(jLabel12)
                                    .addGap(73, 73, 73)))
                            .addGroup(salesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(salesProductCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(salesDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(addSalesBtn))
                .addGap(18, 18, 18)
                .addGroup(salesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(salesPanelLayout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(salesAvailableField))
                    .addGroup(salesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, salesPanelLayout.createSequentialGroup()
                            .addComponent(jLabel17)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(salesPriceField, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(salesPanelLayout.createSequentialGroup()
                            .addComponent(jLabel15)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(salesTotalPriceField, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        salesPanelLayout.setVerticalGroup(
            salesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(salesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(salesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(salesDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(salesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(salesAvailableField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(salesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(salesPriceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(salesProductCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(salesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(salesQuantityField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(salesTotalPriceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(addSalesBtn)
                .addGap(51, 51, 51)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE))
        );

        tabbedPane.addTab("Sales", salesPanel);

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Manage Stock");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel22.setText("Date");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel23.setText("Select Product");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel24.setText("Add Quantity");

        updateStockBtn.setText("Update");
        updateStockBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateStockBtnMouseClicked(evt);
            }
        });

        stockProductCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Stock History");

        stockHistoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(stockHistoryTable);

        javax.swing.GroupLayout stockPanelLayout = new javax.swing.GroupLayout(stockPanel);
        stockPanel.setLayout(stockPanelLayout);
        stockPanelLayout.setHorizontalGroup(
            stockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stockPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(stockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(stockPanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(stockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, stockPanelLayout.createSequentialGroup()
                        .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(stockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(updateStockBtn, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(stockQuantityField, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, stockPanelLayout.createSequentialGroup()
                        .addGroup(stockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, stockPanelLayout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addGap(18, 18, 18))
                            .addGroup(stockPanelLayout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addGap(73, 73, 73)))
                        .addGroup(stockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(stockProductCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(stockDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        stockPanelLayout.setVerticalGroup(
            stockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stockPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(stockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stockDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(stockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stockProductCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(stockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stockQuantityField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(updateStockBtn)
                .addGap(27, 27, 27)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE))
        );

        tabbedPane.addTab("tab4", stockPanel);

        reportTableTitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        reportTableTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        reportTableTitle.setText("Report Table");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel20.setText("From");

        reportSalesBtn.setText("View Sales");
        reportSalesBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reportSalesBtnMouseClicked(evt);
            }
        });
        reportSalesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportSalesBtnActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel26.setText("To");

        reportTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(reportTable);

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Report");

        reportStockBtn.setText("View Stock");
        reportStockBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reportStockBtnMouseClicked(evt);
            }
        });
        reportStockBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportStockBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout reportPanelLayout = new javax.swing.GroupLayout(reportPanel);
        reportPanel.setLayout(reportPanelLayout);
        reportPanelLayout.setHorizontalGroup(
            reportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportPanelLayout.createSequentialGroup()
                .addGroup(reportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(reportTableTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE)
                    .addGroup(reportPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(reportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)
                            .addGroup(reportPanelLayout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(reportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(reportPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(reportToDate, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(reportPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(reportFromDate, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(reportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(reportSalesBtn)
                                    .addComponent(reportStockBtn))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE))))
                .addContainerGap())
        );
        reportPanelLayout.setVerticalGroup(
            reportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(reportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reportFromDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reportSalesBtn))
                .addGap(18, 18, 18)
                .addGroup(reportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(reportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(reportToDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(reportStockBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(reportTableTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabbedPane.addTab("Report", reportPanel);

        getContentPane().add(tabbedPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(184, 44, 610, 600));

        jButton1.setText("Product");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Menu");

        menuSalesBtn.setText("Sales");
        menuSalesBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuSalesBtnMouseClicked(evt);
            }
        });
        menuSalesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSalesBtnActionPerformed(evt);
            }
        });

        menuStockBtn.setText("Stock");
        menuStockBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuStockBtnMouseClicked(evt);
            }
        });
        menuStockBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuStockBtnActionPerformed(evt);
            }
        });

        menuReportBtn.setText("Report");
        menuReportBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuReportBtnMouseClicked(evt);
            }
        });
        menuReportBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuReportBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout menuPaneLayout = new javax.swing.GroupLayout(menuPane);
        menuPane.setLayout(menuPaneLayout);
        menuPaneLayout.setHorizontalGroup(
            menuPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(menuSalesBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                    .addComponent(menuStockBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                    .addComponent(menuReportBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
                .addContainerGap())
        );
        menuPaneLayout.setVerticalGroup(
            menuPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(menuSalesBtn)
                .addGap(18, 18, 18)
                .addComponent(menuStockBtn)
                .addGap(18, 18, 18)
                .addComponent(menuReportBtn)
                .addContainerGap(357, Short.MAX_VALUE))
        );

        getContentPane().add(menuPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 92, -1, 552));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menuSalesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSalesBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuSalesBtnActionPerformed

    private void menuStockBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuStockBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuStockBtnActionPerformed

    private void productIdFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productIdFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_productIdFieldActionPerformed

    private void addSalesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSalesBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addSalesBtnActionPerformed

    private void menuSalesBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuSalesBtnMouseClicked
        tabbedPane.setSelectedIndex(1);
    }//GEN-LAST:event_menuSalesBtnMouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        tabbedPane.setSelectedIndex(0);
    }//GEN-LAST:event_jButton1MouseClicked

    private void addProductBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addProductBtnMouseClicked
        addProduct();
    }//GEN-LAST:event_addProductBtnMouseClicked

    private void productListTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productListTableMouseClicked
        int selectedRowIndex = productListTable.getSelectedRow();

        if (selectedRowIndex != -1) {
            ProductTableModel model = (ProductTableModel) productListTable.getModel();
            Product selectedProduct = model.getProductAt(selectedRowIndex);

            productIdField.setText(String.valueOf(selectedProduct.getId()));
            productUnitPriceField.setText(selectedProduct.getName());
            productNameField.setText(String.valueOf(selectedProduct.getUnitPrice()));
            productSalesPriceField.setText(String.valueOf(selectedProduct.getSalesPrice()));
        }
    }//GEN-LAST:event_productListTableMouseClicked

    private void editProductBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editProductBtnMouseClicked
        updateProduct();
    }//GEN-LAST:event_editProductBtnMouseClicked

    private void addSalesBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addSalesBtnMouseClicked
        addSales();
    }//GEN-LAST:event_addSalesBtnMouseClicked

    private void reportSalesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportSalesBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_reportSalesBtnActionPerformed

    private void reportSalesBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportSalesBtnMouseClicked
        viewSalesReport();
    }//GEN-LAST:event_reportSalesBtnMouseClicked

    private void menuStockBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuStockBtnMouseClicked
        tabbedPane.setSelectedIndex(2);
    }//GEN-LAST:event_menuStockBtnMouseClicked

    private void menuReportBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuReportBtnMouseClicked
        tabbedPane.setSelectedIndex(3);
    }//GEN-LAST:event_menuReportBtnMouseClicked

    private void menuReportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuReportBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuReportBtnActionPerformed

    private void updateStockBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateStockBtnMouseClicked
        updateStock();
    }//GEN-LAST:event_updateStockBtnMouseClicked

    private void productUnitPriceFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productUnitPriceFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_productUnitPriceFieldActionPerformed

    private void reportStockBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportStockBtnMouseClicked
        viewStockReport();
    }//GEN-LAST:event_reportStockBtnMouseClicked

    private void reportStockBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportStockBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_reportStockBtnActionPerformed

    private void deleteProductBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteProductBtnMouseClicked
        deleteProduct();
    }//GEN-LAST:event_deleteProductBtnMouseClicked

    private void resetProductBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resetProductBtnMouseClicked
        resetProductFields();
    }//GEN-LAST:event_resetProductBtnMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JEE59Store.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JEE59Store.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JEE59Store.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JEE59Store.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JEE59Store().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addProductBtn;
    private javax.swing.JButton addSalesBtn;
    private javax.swing.JButton deleteProductBtn;
    private javax.swing.JButton editProductBtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel menuPane;
    private javax.swing.JButton menuReportBtn;
    private javax.swing.JButton menuSalesBtn;
    private javax.swing.JButton menuStockBtn;
    private javax.swing.JTextField productIdField;
    private javax.swing.JTable productListTable;
    private javax.swing.JTextField productNameField;
    private javax.swing.JPanel productPanel;
    private javax.swing.JTextField productSalesPriceField;
    private javax.swing.JTextField productUnitPriceField;
    private com.toedter.calendar.JDateChooser reportFromDate;
    private javax.swing.JPanel reportPanel;
    private javax.swing.JButton reportSalesBtn;
    private javax.swing.JButton reportStockBtn;
    private javax.swing.JTable reportTable;
    private javax.swing.JLabel reportTableTitle;
    private com.toedter.calendar.JDateChooser reportToDate;
    private javax.swing.JButton resetProductBtn;
    private javax.swing.JTextField salesAvailableField;
    private com.toedter.calendar.JDateChooser salesDateField;
    private javax.swing.JTable salesListTable;
    private javax.swing.JPanel salesPanel;
    private javax.swing.JTextField salesPriceField;
    private javax.swing.JComboBox<String> salesProductCombo;
    private javax.swing.JTextField salesQuantityField;
    private javax.swing.JTextField salesTotalPriceField;
    private com.toedter.calendar.JDateChooser stockDateField;
    private javax.swing.JTable stockHistoryTable;
    private javax.swing.JPanel stockPanel;
    private javax.swing.JComboBox<String> stockProductCombo;
    private javax.swing.JTextField stockQuantityField;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JPanel titlePane;
    private javax.swing.JButton updateStockBtn;
    // End of variables declaration//GEN-END:variables

}
