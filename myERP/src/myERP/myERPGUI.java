package myERP;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.util.Map;

public class myERPGUI extends JFrame {
    private CustomerManager customerManager;
    private ProductManager productManager;
    private OrderManager orderManager;

    private JTable customerTable;
    private DefaultTableModel customerTableModel;

    private JTable productTable;
    private DefaultTableModel productTableModel;

    private JTable orderTable;
    private DefaultTableModel orderTableModel;

    public myERPGUI() {
        customerManager = new CustomerManager();
        productManager = new ProductManager();
        orderManager = new OrderManager();

        setTitle("My ERP System");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        // --- Customers Tab ---
        JPanel customerPanel = new JPanel(new BorderLayout());
        customerTableModel = new DefaultTableModel(new String[]{"ID", "First Name", "Last Name", "Email"}, 0);
        customerTable = new JTable(customerTableModel);
        customerPanel.add(new JScrollPane(customerTable), BorderLayout.CENTER);

        JPanel customerControl = new JPanel();
        JButton addCustomerBtn = new JButton("Add Customer");
        JButton removeCustomerBtn = new JButton("Remove Customer");
        customerControl.add(addCustomerBtn);
        customerControl.add(removeCustomerBtn);
        customerPanel.add(customerControl, BorderLayout.SOUTH);

        addCustomerBtn.addActionListener(e -> addCustomer());
        removeCustomerBtn.addActionListener(e -> removeCustomer());

        tabbedPane.add("Customers", customerPanel);

        // --- Products Tab ---
        JPanel productPanel = new JPanel(new BorderLayout());
        productTableModel = new DefaultTableModel(new String[]{"ID", "Name", "Price", "Stock"}, 0);
        productTable = new JTable(productTableModel);
        productPanel.add(new JScrollPane(productTable), BorderLayout.CENTER);

        JPanel productControl = new JPanel();
        JButton addProductBtn = new JButton("Add Product");
        JButton removeProductBtn = new JButton("Remove Product");
        productControl.add(addProductBtn);
        productControl.add(removeProductBtn);
        productPanel.add(productControl, BorderLayout.SOUTH);

        addProductBtn.addActionListener(e -> addProduct());
        removeProductBtn.addActionListener(e -> removeProduct());

        tabbedPane.add("Products", productPanel);

        // --- Orders Tab ---
        JPanel orderPanel = new JPanel(new BorderLayout());
        orderTableModel = new DefaultTableModel(new String[]{"ID", "Customer", "Status", "Total"}, 0);
        orderTable = new JTable(orderTableModel);
        orderPanel.add(new JScrollPane(orderTable), BorderLayout.CENTER);

        JPanel orderControl = new JPanel();
        JButton addOrderBtn = new JButton("Add Order");
        JButton completeOrderBtn = new JButton("Complete Order");
        JButton cancelOrderBtn = new JButton("Cancel Order");
        orderControl.add(addOrderBtn);
        orderControl.add(completeOrderBtn);
        orderControl.add(cancelOrderBtn);
        orderPanel.add(orderControl, BorderLayout.SOUTH);

        addOrderBtn.addActionListener(e -> addOrder());
        completeOrderBtn.addActionListener(e -> completeOrder());
        cancelOrderBtn.addActionListener(e -> cancelOrder());

        tabbedPane.add("Orders", orderPanel);

        add(tabbedPane);
    }

    // --- Customer Methods ---
    private void addCustomer() {
        JTextField firstNameField = new JTextField();
        JTextField lastNameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField companyField = new JTextField();
        JTextField vatField = new JTextField();
        JTextField addressField = new JTextField();
        JTextField notesField = new JTextField();

        Object[] message = {
                "First Name:", firstNameField,
                "Last Name:", lastNameField,
                "Email:", emailField,
                "Phone:", phoneField,
                "Company:", companyField,
                "VAT Number:", vatField,
                "Address:", addressField,
                "Notes:", notesField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add Customer", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            Customer c = new Customer(UUID.randomUUID(), firstNameField.getText(), lastNameField.getText(),
                    emailField.getText(), phoneField.getText(), companyField.getText(), vatField.getText(),
                    addressField.getText(), notesField.getText());
            c.setStatus(CustomerStatus.PROSPECT);
            customerManager.addCustomer(c);
            customerTableModel.addRow(new Object[]{c.getId(), c.getFirstName(), c.getLastName(), c.getEmail()});
        }
    }

    private void removeCustomer() {
        int selectedRow = customerTable.getSelectedRow();
        if (selectedRow >= 0) {
            UUID id = UUID.fromString(customerTableModel.getValueAt(selectedRow, 0).toString());
            customerManager.removeCustomer(id);
            customerTableModel.removeRow(selectedRow);
        }
    }

    // --- Product Methods ---
    private void addProduct() {
        JTextField nameField = new JTextField();
        JTextField descField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField stockField = new JTextField();

        Object[] message = {
                "Name:", nameField,
                "Description:", descField,
                "Price:", priceField,
                "Stock:", stockField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add Product", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                double price = Double.parseDouble(priceField.getText());
                int stock = Integer.parseInt(stockField.getText());
                Product p = new Product(UUID.randomUUID(), nameField.getText(), descField.getText(), price, stock);
                p.setStatus(ProductStatus.ACTIVE);
                productManager.addProduct(p);
                productTableModel.addRow(new Object[]{p.getId(), p.getName(), p.getPrice(), p.getStock()});
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid price or stock!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void removeProduct() {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow >= 0) {
            UUID id = UUID.fromString(productTableModel.getValueAt(selectedRow, 0).toString());
            productManager.removeProduct(id);
            productTableModel.removeRow(selectedRow);
        }
    }

    // --- Order Methods ---
    private void addOrder() {
        ArrayList<Customer> customers = customerManager.getAllCustomers();
        if (customers.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No customers available!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JComboBox<String> customerBox = new JComboBox<>();
        for (Customer c : customers) {
            customerBox.addItem(c.getId() + " - " + c.getFullName());
        }

        JPanel orderPanel = new JPanel(new GridLayout(0, 2));
        orderPanel.add(new JLabel("Select Customer:"));
        orderPanel.add(customerBox);

        ArrayList<Product> products = productManager.getAllProducts();
        if (products.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No products available!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Map<Product, JTextField> productFields = new HashMap<>();
        for (Product p : products) {
            orderPanel.add(new JLabel(p.getName() + " (Stock: " + p.getStock() + ")"));
            JTextField qtyField = new JTextField("0");
            productFields.put(p, qtyField);
            orderPanel.add(qtyField);
        }

        int option = JOptionPane.showConfirmDialog(this, orderPanel, "Add Order", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String selectedCustomer = (String) customerBox.getSelectedItem();
            UUID customerId = UUID.fromString(selectedCustomer.split(" - ")[0]);
            Customer customer = customerManager.getCustomerById(customerId);
            Map<Product, Integer> items = new HashMap<>();
            for (Map.Entry<Product, JTextField> entry : productFields.entrySet()) {
                try {
                    int qty = Integer.parseInt(entry.getValue().getText());
                    if (qty > 0) items.put(entry.getKey(), qty);
                } catch (NumberFormatException ignored) {}
            }

            if (items.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No products selected!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Order order = new Order(UUID.randomUUID(), customer, items);
            order.setStatus(OrderStatus.PENDING);
            orderManager.addOrder(order);
            updateOrderTable();
        }
    }

    private void completeOrder() {
        int selectedRow = orderTable.getSelectedRow();
        if (selectedRow >= 0) {
            UUID id = UUID.fromString(orderTableModel.getValueAt(selectedRow, 0).toString());
            Order order = orderManager.getOrderById(id);
            if (order != null && order.getStatus() == OrderStatus.PENDING) {
                // Μείωση stock προϊόντων
                for (Map.Entry<Product, Integer> entry : order.getItems().entrySet()) {
                    Product p = entry.getKey();
                    int qty = entry.getValue();
                    p.setStock(p.getStock() - qty);
                }
                orderManager.completeOrder(id);
                updateOrderTable();
                updateProductTable();
            }
        }
    }

    private void cancelOrder() {
        int selectedRow = orderTable.getSelectedRow();
        if (selectedRow >= 0) {
            UUID id = UUID.fromString(orderTableModel.getValueAt(selectedRow, 0).toString());
            orderManager.cancelOrder(id);
            updateOrderTable();
        }
    }

    private void updateOrderTable() {
        orderTableModel.setRowCount(0);
        for (Order o : orderManager.getAllOrders()) {
            orderTableModel.addRow(new Object[]{
                    o.getId(),
                    o.getCustomer().getFullName(),
                    o.getStatus(),
                    o.calculateTotal()
            });
        }
    }

    private void updateProductTable() {
        productTableModel.setRowCount(0);
        for (Product p : productManager.getAllProducts()) {
            productTableModel.addRow(new Object[]{p.getId(), p.getName(), p.getPrice(), p.getStock()});
        }
    }
}
