
import javax.swing.*;
import java.awt.*;

public class InvoiceGUI extends JFrame {

    private JTextField txtTitle;
    private JTextField txtAddress;
    private JTextField txtProduct;
    private JTextField txtPrice;
    private JTextField txtQuantity;

    private JTextArea txtInvoice;

    private Invoice invoice;

    public InvoiceGUI() {
        setTitle("Software Engineering Camp - Invoice");
        setSize(650, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createGUI();
    }

    private void createGUI() {

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Invoice information
        JPanel inputPanel = new JPanel(new GridLayout(0, 2, 10, 10));

        inputPanel.add(new JLabel("Invoice Title:"));
        txtTitle = new JTextField("Invoice");
        inputPanel.add(txtTitle);

        inputPanel.add(new JLabel("Customer Address:"));
        txtAddress = new JTextField();
        inputPanel.add(txtAddress);

        inputPanel.add(new JLabel("Product Name:"));
        txtProduct = new JTextField();
        inputPanel.add(txtProduct);

        inputPanel.add(new JLabel("Unit Price:"));
        txtPrice = new JTextField();
        inputPanel.add(txtPrice);

        inputPanel.add(new JLabel("Quantity:"));
        txtQuantity = new JTextField();
        inputPanel.add(txtQuantity);

        // Buttons
        JPanel buttonPanel = new JPanel();

        JButton btnAdd = new JButton("Add Item");
        JButton btnDisplay = new JButton("Display Invoice");
        JButton btnClear = new JButton("Clear");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnDisplay);
        buttonPanel.add(btnClear);

        // Invoice display
        txtInvoice = new JTextArea(12, 45);
        txtInvoice.setEditable(false);
        txtInvoice.setFont(new Font("Monospaced", Font.PLAIN, 13));

        JScrollPane scrollPane = new JScrollPane(txtInvoice);

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        add(mainPanel);

        // Add item button
        btnAdd.addActionListener(e -> addItem());

        // Display invoice button
        btnDisplay.addActionListener(e -> displayInvoice());

        // Clear button
        btnClear.addActionListener(e -> clearFields());
    }

    private void addItem() {
        try {
            String title = txtTitle.getText().trim();
            String address = txtAddress.getText().trim();
            String name = txtProduct.getText().trim();

            double price = Double.parseDouble(txtPrice.getText());
            int quantity = Integer.parseInt(txtQuantity.getText());

            if (title.isEmpty() || address.isEmpty() || name.isEmpty()
                    || price < 0 || quantity <= 0) {
                JOptionPane.showMessageDialog(this,
                        "Enter all information. Price cannot be negative and quantity must be positive.");
                return;
            }

            if (invoice == null) {
                invoice = new Invoice(title, address);
            }

            Product product = new Product(name, price);
            LineItem item = new LineItem(product, quantity);

            invoice.addLineItem(item);

            JOptionPane.showMessageDialog(this, "Item added successfully!");

            txtProduct.setText("");
            txtPrice.setText("");
            txtQuantity.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Please enter a valid price and quantity.");
        }
    }

    private void displayInvoice() {
        if (invoice == null) {
            JOptionPane.showMessageDialog(this,
                    "Please add at least one item first.");
            return;
        }

        txtInvoice.setText(invoice.displayInvoice());
    }

    private void clearFields() {
        txtTitle.setText("Invoice");
        txtAddress.setText("");
        txtProduct.setText("");
        txtPrice.setText("");
        txtQuantity.setText("");
        txtInvoice.setText("");
        invoice = null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InvoiceGUI gui = new InvoiceGUI();
            gui.setVisible(true);
        });
    }
}
