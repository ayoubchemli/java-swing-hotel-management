package view.admin;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

import controller.admin.AdminRegistrationController;
import controller.application.LoginController;

public class AdminRegistrationView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JTextField addressField;
    private JComboBox<String> roleComboBox;
    private JComboBox<String> departmentComboBox;

    private AdminRegistrationController adminRegistrationController;

    public AdminRegistrationView(LoginController loginController) {
        this.adminRegistrationController = new AdminRegistrationController();

        setTitle("Admin Registration");
        setSize(800, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(30, 50, 30, 50));
        mainPanel.setBackground(new Color(255, 250, 240));

        JLabel titleLabel = new JLabel("Admin Registration");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 28));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(new Color(51, 26, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 30, 0);
        mainPanel.add(titleLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 20, 10);
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(createLabeledComponent("Username:", usernameField = new JTextField(20)), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 10, 20, 0);
        mainPanel.add(createLabeledComponent("Password:", passwordField = new JPasswordField(20)), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 20, 10);
        mainPanel.add(createLabeledComponent("Name:", nameField = new JTextField(20)), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 10, 20, 0);
        mainPanel.add(createLabeledComponent("Email:", emailField = new JTextField(20)), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 20, 10);
        mainPanel.add(createLabeledComponent("Phone:", phoneField = new JTextField(20)), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 10, 20, 0);
        mainPanel.add(createLabeledComponent("Address:", addressField = new JTextField(20)), gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = new Insets(0, 0, 20, 10);
        mainPanel.add(createLabeledComponent("Role:", roleComboBox = new JComboBox<>(new String[]{"Administrator", "Manager", "Supervisor"})), gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.insets = new Insets(0, 10, 20, 0);
        mainPanel.add(createLabeledComponent("Department:", departmentComboBox = new JComboBox<>(new String[]{"FrontOffice", "Housekeeping", "FoodAndBeverage", "Maintenance", "SalesAndMarketing", "FinanceAndAccounting", "HumanResources", "Security"})), gbc);

        JButton registerButton = new JButton("Register");
        JButton cancelButton = new JButton("Cancel");

        registerButton.setFont(new Font("Serif", Font.BOLD, 16));
        cancelButton.setFont(new Font("Serif", Font.BOLD, 16));
        registerButton.setBackground(new Color(204, 102, 0));
        cancelButton.setBackground(new Color(153, 76, 0));
        registerButton.setForeground(Color.WHITE);
        cancelButton.setForeground(Color.WHITE);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(30, 0, 0, 10);
        mainPanel.add(registerButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.insets = new Insets(30, 10, 0, 0);
        mainPanel.add(cancelButton, gbc);

        add(mainPanel);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerAdmin();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    private JPanel createLabeledComponent(String labelText, JComponent component) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Serif", Font.PLAIN, 16));
        label.setForeground(new Color(51, 26, 0));
        panel.add(label);
        panel.add(component);
        return panel;
    }

    private boolean validateInput() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();
        String address = addressField.getText().trim();

        if (username.isEmpty() || password.isEmpty() || name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all the required fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!email.matches("^[\\w-\\.+]*[\\w-\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid email address.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!phone.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid phone number.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private void registerAdmin() {
        if (validateInput()) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String name = nameField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            String address = addressField.getText();
            String role = (String) roleComboBox.getSelectedItem();String department = (String) departmentComboBox.getSelectedItem();

            adminRegistrationController.registerAdmin(username, password, name, phone, email, address, role, department);
        }
    }
}