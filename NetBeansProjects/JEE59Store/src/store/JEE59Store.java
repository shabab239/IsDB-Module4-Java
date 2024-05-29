package store;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import model.Product;
import model.ProductTableModel;
import model.Sales;
import model.SalesTableModel;
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
            }
        });
        productUnitPriceField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                calculateTotalPrice();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                calculateTotalPrice();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                calculateTotalPrice();
            }

        });
        productQuantityField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                calculateTotalPrice();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                calculateTotalPrice();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                calculateTotalPrice();
            }

        });
        salesProductCombo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedProductName = (String) e.getItem();
                    getSalesPrice(selectedProductName);
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
                Integer quantity = rs.getInt("quantity");
                Double salesPrice = rs.getDouble("salesPrice");

                Product product = new Product(id, name, unitPrice, quantity, salesPrice);
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

    public void addProduct() { //TODO duplicate name check
        try {
            String productName;
            Double unitPrice = null;
            Integer quantity;
            Double salesPrice = null;

            if (productNameField.getText().isBlank()) {
                JOptionPane.showMessageDialog(rootPane, "Product Name is Required");
                return;
            }

            if (productQuantityField.getText().isBlank()) {
                JOptionPane.showMessageDialog(rootPane, "Product Quantity is Required");
                return;
            }

            productName = productNameField.getText().trim();

            try {
                if (!productUnitPriceField.getText().isBlank()) {
                    unitPrice = Double.valueOf(productUnitPriceField.getText().trim());
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(rootPane, "Invalid Unit Price");
                return;
            }

            try {
                quantity = Integer.valueOf(productQuantityField.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(rootPane, "Invalid Quantity");
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

            String sql = "INSERT INTO product(name, unitPrice, quantity, salesPrice) VALUES (?, ?, ?, ?);";
            PreparedStatement ps = dBUtil.getConnection().prepareStatement(sql);
            ps.setString(1, productName);

            if (unitPrice != null) {
                ps.setDouble(2, unitPrice);
            } else {
                ps.setNull(2, java.sql.Types.DOUBLE);
            }

            ps.setInt(3, quantity);

            if (salesPrice != null) {
                ps.setDouble(4, salesPrice);
            } else {
                ps.setNull(4, java.sql.Types.DOUBLE);
            }

            ps.executeUpdate();
            ps.close();

            JOptionPane.showMessageDialog(rootPane, "Product Added Successfully");
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

    public void updateProduct() { //TODO duplicate name check
        if (productIdField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Please Select a Product First");
            return;
        }
        try {
            if (productNameField.getText().isBlank()) {
                JOptionPane.showMessageDialog(rootPane, "Product Name is Required");
                return;
            }

            if (productQuantityField.getText().isBlank()) {
                JOptionPane.showMessageDialog(rootPane, "Product Quantity is Required");
                return;
            }

            long productId = Long.parseLong(productIdField.getText().trim());
            String productName = productNameField.getText().trim();
            Integer quantity;

            try {
                quantity = Integer.valueOf(productQuantityField.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(rootPane, "Invalid Quantity");
                return;
            }

            Double unitPrice = null;
            if (!productUnitPriceField.getText().trim().isEmpty()) {
                try {
                    if (!productUnitPriceField.getText().isBlank()) {
                        unitPrice = Double.valueOf(productUnitPriceField.getText().trim());
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Invalid Unit Price");
                    return;
                }
            }
            Double salesPrice = null;
            if (!productSalesPriceField.getText().trim().isEmpty()) {
                try {
                    if (!productSalesPriceField.getText().isBlank()) {
                        salesPrice = Double.valueOf(productSalesPriceField.getText().trim());
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Invalid Sales Price");
                    return;
                }
            }

            String sql = "update product set name = ?, unitPrice = ?, quantity = ?, salesPrice = ? where id = ?;";
            PreparedStatement ps = dBUtil.getConnection().prepareStatement(sql);

            ps.setString(1, productName);
            if (unitPrice != null) {
                ps.setDouble(2, unitPrice);
            } else {
                ps.setNull(2, java.sql.Types.DOUBLE);
            }
            ps.setInt(3, quantity);
            if (salesPrice != null) {
                ps.setDouble(4, salesPrice);
            } else {
                ps.setNull(4, java.sql.Types.DOUBLE);
            }
            ps.setLong(5, productId);

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

    public void calculateTotalPrice() {
        try {
            if (!productUnitPriceField.getText().isBlank() && !productQuantityField.getText().isBlank()) {
                Double totalPrice = Double.valueOf(productUnitPriceField.getText()) * Double.valueOf(productQuantityField.getText());
                productTotalPriceField.setText(String.valueOf(totalPrice));
            }
        } catch (Exception e) {
            productTotalPriceField.setText("");
        }
    }

    public void calculateTotalSalesPrice() {
        try {
            if (!salesQuantityField.getText().isBlank() && !salesPriceField.getText().isBlank()) {
                Double totalPrice = Integer.valueOf(salesQuantityField.getText()) * Double.valueOf(salesPriceField.getText());
                salesTotalPriceField.setText(String.valueOf(totalPrice));
            }
        } catch (Exception e) {
            salesTotalPriceField.setText("");
        }
    }

    public void resetProductFields() {
        productIdField.setText("");
        productNameField.setText("");
        productUnitPriceField.setText("");
        productQuantityField.setText("");
        productSalesPriceField.setText("");
        productTotalPriceField.setText("");
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

    public void getSalesPrice(String productName) {
        String sql = "select salesPrice from product where name = ?;";
        PreparedStatement ps;
        ResultSet rs;

        try {
            ps = dBUtil.getConnection().prepareStatement(sql);
            ps.setString(1, productName);

            rs = ps.executeQuery();

            while (rs.next()) {
                String salesPrice = rs.getString("salesPrice");
                salesPriceField.setText(salesPrice);
                if (salesPrice == null || salesPrice.isBlank()) {
                    JOptionPane.showMessageDialog(rootPane, "This Product has no sales price!");
                }
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

    public void addSales() {
        try {
            Integer quantity = null;
            if (salesQuantityField.getText().trim().isEmpty()) {
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

            Date date = salesDateField.getDate();
            java.sql.Date sqlDate = utilDateToSqlDate(date);
            if (sqlDate == null) {
                JOptionPane.showMessageDialog(rootPane, "Date is Required");
                return;
            }

            String sql = "insert into sales(name, salesPrice, quantity, totalPrice, date) values(?, ?, ?, ?, ?)";

            PreparedStatement ps;
            ps = dBUtil.getConnection().prepareStatement(sql);

            ps.setString(1, salesProductCombo.getSelectedItem().toString());
            ps.setDouble(2, Double.parseDouble(salesPriceField.getText()));
            ps.setDouble(3, Integer.parseInt(salesQuantityField.getText()));
            ps.setDouble(4, Double.parseDouble(salesTotalPriceField.getText()));
            ps.setDate(5, sqlDate);

            ps.executeUpdate();

            ps.close();
            resetSalesFields();
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tabbedPane = new javax.swing.JTabbedPane();
        productPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        productIdField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        productNameField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        productUnitPriceField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        productQuantityField = new javax.swing.JTextField();
        productSalesPriceField = new javax.swing.JTextField();
        productTotalPriceField = new javax.swing.JTextField();
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
        editSalesBtn = new javax.swing.JButton();
        resetSalesBtn = new javax.swing.JButton();
        deleteSalesBtn = new javax.swing.JButton();
        salesProductCombo = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        salesListTable = new javax.swing.JTable();
        salesDateField = new com.toedter.calendar.JDateChooser();
        stockPanel = new javax.swing.JPanel();
        reportPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(850, 700));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Cambria", 1, 30)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("JEE 59 Store");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 788, -1));

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
        jLabel5.setText("Product Name");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Unit Price");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Total Price");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Quantity");

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
                        .addGap(40, 40, 40)
                        .addGroup(productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, productPanelLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(productIdField, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, productPanelLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(productNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, productPanelLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, productPanelLayout.createSequentialGroup()
                                        .addComponent(addProductBtn)
                                        .addGap(18, 18, 18)
                                        .addComponent(editProductBtn))
                                    .addComponent(productUnitPriceField, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(productPanelLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(productSalesPriceField, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(productPanelLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(productTotalPriceField, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(productPanelLayout.createSequentialGroup()
                                .addComponent(resetProductBtn)
                                .addGap(18, 18, 18)
                                .addComponent(deleteProductBtn))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, productPanelLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(productQuantityField, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 23, Short.MAX_VALUE))
                    .addGroup(productPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
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
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(productQuantityField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(productNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(productSalesPriceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(productUnitPriceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(productTotalPriceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addProductBtn)
                    .addComponent(editProductBtn)
                    .addComponent(resetProductBtn)
                    .addComponent(deleteProductBtn))
                .addGap(36, 36, 36)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE))
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

        editSalesBtn.setText("Edit");

        resetSalesBtn.setText("Reset");

        deleteSalesBtn.setText("Delete");

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
                .addGroup(salesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, salesPanelLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(salesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, salesPanelLayout.createSequentialGroup()
                                .addComponent(addSalesBtn)
                                .addGap(18, 18, 18)
                                .addComponent(editSalesBtn))
                            .addComponent(salesQuantityField, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addGap(18, 18, 18)
                .addGroup(salesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, salesPanelLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(186, 186, 186))
                    .addGroup(salesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(salesPanelLayout.createSequentialGroup()
                            .addComponent(jLabel15)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(salesTotalPriceField, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(salesPanelLayout.createSequentialGroup()
                            .addComponent(resetSalesBtn)
                            .addGap(18, 18, 18)
                            .addComponent(deleteSalesBtn))
                        .addComponent(salesPriceField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                    .addComponent(salesDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGap(33, 33, 33)
                .addGroup(salesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addSalesBtn)
                    .addComponent(editSalesBtn)
                    .addComponent(resetSalesBtn)
                    .addComponent(deleteSalesBtn))
                .addGap(36, 36, 36)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE))
        );

        tabbedPane.addTab("Sales", salesPanel);

        javax.swing.GroupLayout stockPanelLayout = new javax.swing.GroupLayout(stockPanel);
        stockPanel.setLayout(stockPanelLayout);
        stockPanelLayout.setHorizontalGroup(
            stockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 610, Short.MAX_VALUE)
        );
        stockPanelLayout.setVerticalGroup(
            stockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 569, Short.MAX_VALUE)
        );

        tabbedPane.addTab("Stock", stockPanel);

        javax.swing.GroupLayout reportPanelLayout = new javax.swing.GroupLayout(reportPanel);
        reportPanel.setLayout(reportPanelLayout);
        reportPanelLayout.setHorizontalGroup(
            reportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 610, Short.MAX_VALUE)
        );
        reportPanelLayout.setVerticalGroup(
            reportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 569, Short.MAX_VALUE)
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

        jButton2.setText("Sales");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Stock");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Report");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 92, -1, 552));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void productIdFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productIdFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_productIdFieldActionPerformed

    private void addSalesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSalesBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addSalesBtnActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        tabbedPane.setSelectedIndex(1);
    }//GEN-LAST:event_jButton2MouseClicked

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
            productNameField.setText(selectedProduct.getName());
            productUnitPriceField.setText(String.valueOf(selectedProduct.getUnitPrice()));
            productQuantityField.setText(String.valueOf(selectedProduct.getQuantity()));
            productSalesPriceField.setText(String.valueOf(selectedProduct.getSalesPrice()));
        }
    }//GEN-LAST:event_productListTableMouseClicked

    private void editProductBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editProductBtnMouseClicked
        updateProduct();
    }//GEN-LAST:event_editProductBtnMouseClicked

    private void resetProductBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resetProductBtnMouseClicked
        resetProductFields();
    }//GEN-LAST:event_resetProductBtnMouseClicked

    private void deleteProductBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteProductBtnMouseClicked
        deleteProduct();
    }//GEN-LAST:event_deleteProductBtnMouseClicked

    private void addSalesBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addSalesBtnMouseClicked
        addSales();
    }//GEN-LAST:event_addSalesBtnMouseClicked

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
    private javax.swing.JButton deleteSalesBtn;
    private javax.swing.JButton editProductBtn;
    private javax.swing.JButton editSalesBtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField productIdField;
    private javax.swing.JTable productListTable;
    private javax.swing.JTextField productNameField;
    private javax.swing.JPanel productPanel;
    private javax.swing.JTextField productQuantityField;
    private javax.swing.JTextField productSalesPriceField;
    private javax.swing.JTextField productTotalPriceField;
    private javax.swing.JTextField productUnitPriceField;
    private javax.swing.JPanel reportPanel;
    private javax.swing.JButton resetProductBtn;
    private javax.swing.JButton resetSalesBtn;
    private com.toedter.calendar.JDateChooser salesDateField;
    private javax.swing.JTable salesListTable;
    private javax.swing.JPanel salesPanel;
    private javax.swing.JTextField salesPriceField;
    private javax.swing.JComboBox<String> salesProductCombo;
    private javax.swing.JTextField salesQuantityField;
    private javax.swing.JTextField salesTotalPriceField;
    private javax.swing.JPanel stockPanel;
    private javax.swing.JTabbedPane tabbedPane;
    // End of variables declaration//GEN-END:variables

}
