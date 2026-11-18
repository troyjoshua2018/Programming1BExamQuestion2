import java.awt.GridLayout;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.*;

public class ProductApp extends JFrame {
    private final JTextField txtName = new JTextField();
    private final JTextField txtPrice = new JTextField();
    private final JComboBox<String> cmbProduct = new JComboBox<>(
            new String[]{"Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"});
    private final JButton btnProcess = new JButton("PROCESS");

    public ProductApp() {
        super("Product App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(420, 240);
        setLocationRelativeTo(null);

        // Menu bar
        JMenuBar bar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        JMenuItem miExit = new JMenuItem("Exit");
        JMenuItem miProcess = new JMenuItem("Process");
        JMenuItem miClear = new JMenuItem("Clear");
        file.add(miExit);
        edit.add(miProcess);
        edit.add(miClear);
        bar.add(file);
        bar.add(edit);
        setJMenuBar(bar);

        // Form
        setLayout(new GridLayout(4, 2, 8, 8));
        add(new JLabel("  Customer Name:"));
        add(txtName);
        add(new JLabel("  Select Product:"));
        add(cmbProduct);
        add(new JLabel("  Enter Price:"));
        add(txtPrice);
        add(new JLabel());
        add(btnProcess);

        // Q2.5 menu logic
        miExit.addActionListener(e -> System.exit(0));
        miProcess.addActionListener(e -> processSale());
        miClear.addActionListener(e -> clearForm());
        btnProcess.addActionListener(e -> processSale());
    }

    private void clearForm() {
        txtName.setText("");
        txtPrice.setText("");
        cmbProduct.setSelectedIndex(0);
    }

    private void processSale() {
        String name = txtName.getText().trim();
        String product = (String) cmbProduct.getSelectedItem();
        double price;
        try {
            price = Double.parseDouble(txtPrice.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric price.");
            return;
        }
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the customer name.");
            return;
        }

        IProductCalculations calc = new ProductCalculations(price);
        double vat = calc.GetVat();
        double commission = calc.GetCommission();
        double total = calc.GetTotal();

        JOptionPane.showMessageDialog(this,
                "Customer Name: " + name + "\n"
                        + "Product: " + product + "\n"
                        + "VAT: R " + vat + "\n"
                        + "COMMISSION: R " + commission + "\n"
                        + "TOTAL: R " + total);

        saveToFile(name, product, vat, commission, total);
    }

    private void saveToFile(String name, String product, double vat, double commission, double total) {
        try (PrintWriter out = new PrintWriter(new FileWriter("data.txt", true))) {
            out.println("DATA LOG");
            out.println("**************************************");
            out.println("Customer Name: " + name);
            out.println("Product: " + product);
            out.println("VAT: R " + vat);
            out.println("COMMISSION: R " + commission);
            out.println("TOTAL: R " + total);
            out.println("**************************************");
            out.println();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Could not write to data.txt: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProductApp().setVisible(true));
    }
}