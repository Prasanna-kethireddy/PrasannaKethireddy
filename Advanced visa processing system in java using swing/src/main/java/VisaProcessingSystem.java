import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class VisaProcessingSystem extends JFrame {
    private JTextField nameField, passportField, nationalityField, dobField, visaTypeField, statusField;
    private JTextArea logArea;
    private DefaultTableModel tableModel;
    private JTable table;

    public VisaProcessingSystem() {
        setTitle("Visa Processing System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE); // Set background color

        setContentPane(mainPanel);

        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding
        formPanel.setBackground(Color.WHITE); // Set background color
        mainPanel.add(formPanel, BorderLayout.NORTH);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Name:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        nameField = new JTextField(20);
        formPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Passport:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        passportField = new JTextField(20);
        formPanel.add(passportField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Nationality:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        nationalityField = new JTextField(20);
        formPanel.add(nationalityField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("DOB (yyyy-MM-dd):"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        dobField = new JTextField(20);
        formPanel.add(dobField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(new JLabel("Visa Type:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        visaTypeField = new JTextField(20);
        formPanel.add(visaTypeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(new JLabel("Status:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        statusField = new JTextField(20);
        formPanel.add(statusField, gbc);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.WHITE); // Set background color
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        JButton addButton = new JButton("Add");
        addButton.setBackground(new Color(30, 144, 255)); // Set background color
        addButton.setForeground(Color.WHITE); // Set text color
        addButton.setFocusPainted(false); // Remove focus border
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addApplication();
            }
        });
        buttonPanel.add(addButton);

        JButton removeButton = new JButton("Remove");
        removeButton.setBackground(new Color(255, 69, 0)); // Set background color
        removeButton.setForeground(Color.WHITE); // Set text color
        removeButton.setFocusPainted(false); // Remove focus border
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeApplication();
            }
        });
        buttonPanel.add(removeButton);

        JButton updateButton = new JButton("Update");
        updateButton.setBackground(new Color(60, 179, 113)); // Set background color
        updateButton.setForeground(Color.WHITE); // Set text color
        updateButton.setFocusPainted(false); // Remove focus border
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateApplication();
            }
        });
        buttonPanel.add(updateButton);

        // Table panel
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding
        tablePanel.setBackground(Color.WHITE); // Set background color
        mainPanel.add(tablePanel, BorderLayout.SOUTH);

        // Table to display applications
        tableModel = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return String.class; // Ensure all columns are treated as Strings
            }
        };
        table = new JTable(tableModel);
        table.setBackground(Color.WHITE); // Set background color

        // Set renderer for column headers to have colored backgrounds
        TableCellRenderer headerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setBackground(new Color(70, 130, 180)); // Set background color for headers
                label.setForeground(Color.WHITE); // Set text color for headers
                label.setHorizontalAlignment(JLabel.CENTER); // Center align text
                return label;
            }
        };

        // Apply renderer to each column header
        for (int i = 0; i < tableModel.getColumnCount(); i++) {
            table.getTableHeader().getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // Add columns to the table
        String[] columnNames = {"Name", "Passport", "Nationality", "DOB", "Visa Type", "Status"};
        tableModel.setColumnIdentifiers(columnNames);

        // Log area
        logArea = new JTextArea(10, 40);
        logArea.setEditable(false);
        logArea.setBackground(Color.WHITE); // Set background color
        JScrollPane logScrollPane = new JScrollPane(logArea);
        tablePanel.add(logScrollPane, BorderLayout.EAST);

        setVisible(true);
    }

    private void addApplication() {
        String name = nameField.getText();
        String passport = passportField.getText();
        String nationality = nationalityField.getText();
        String dob = dobField.getText();
        String visaType = visaTypeField.getText();
        String status = statusField.getText();

        if (!name.isEmpty() && !passport.isEmpty() && !nationality.isEmpty() && !dob.isEmpty() && !visaType.isEmpty() && !status.isEmpty()) {
            Vector<String> row = new Vector<>();
            row.add(name);
            row.add(passport);
            row.add(nationality);
            row.add(dob);
            row.add(visaType);
            row.add(status);
            tableModel.addRow(row);

            clearFields();
            log("Application added to table:");
            log("Name: " + name);
            log("Passport: " + passport);
            log("Nationality: " + nationality);
            log("DOB: " + dob);
            log("Visa Type: " + visaType);
            log("Status: " + status);
        } else {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeApplication() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            tableModel.removeRow(selectedRow);
            log("Application removed from table.");
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to remove.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateApplication() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            tableModel.setValueAt(nameField.getText(), selectedRow, 0);
            tableModel.setValueAt(passportField.getText(), selectedRow, 1);
            tableModel.setValueAt(nationalityField.getText(), selectedRow, 2);
            tableModel.setValueAt(dobField.getText(), selectedRow, 3);
            tableModel.setValueAt(visaTypeField.getText(), selectedRow, 4);
            tableModel.setValueAt(statusField.getText(), selectedRow, 5);

            log("Application updated in table:");
            log("Name: " + nameField.getText());
            log("Passport: " + passportField.getText());
            log("Nationality: " + nationalityField.getText());
            log("DOB: " + dobField.getText());
            log("Visa Type: " + visaTypeField.getText());
            log("Status: " + statusField.getText());

            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to update.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        nameField.setText("");
        passportField.setText("");
        nationalityField.setText("");
        dobField.setText("");
        visaTypeField.setText("");
        statusField.setText("");
    }

    private void log(String message) {
        logArea.append(message + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new VisaProcessingSystem();
            }
        });
    }
}
