// AdminViewProfileView.java
package view.admin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

import controller.admin.AdminViewProfileController;
import model.Admin;

public class AdminViewProfileView extends JFrame {
    private AdminViewProfileController controller;
    private Admin admin;

    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JTextField addressField;
    private JComboBox<String> roleComboBox;
    private JComboBox<String> departmentComboBox;

    public AdminViewProfileView(AdminViewProfileController controller, Admin admin) {
        this.controller = controller;
        this.admin = admin;

        // Set frame properties
        setTitle("Admin Profile");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("model/images/admin_view.png").getImage());

        // Enable anti-aliasing for text
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");

        getContentPane().setBackground(new Color(45, 45, 45)); // Dark background color
        setLayout(new BorderLayout());

        // Header panel
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBackground(new Color(30, 30, 30)); // Darker header panel color
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel titleLabel = new JLabel("Admin Profile");
        titleLabel.setFont(new Font("Montserrat", Font.BOLD, 36)); // Modern font
        titleLabel.setForeground(new Color(255, 215, 0)); // Accent color
        headerPanel.add(titleLabel);

        add(headerPanel, BorderLayout.NORTH);

        // Profile content panel
        JPanel profilePanel = new JPanel();
        profilePanel.setBackground(new Color(45, 45, 45)); // Dark background color
        profilePanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        profilePanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Admin image
        JLabel adminImageLabel = new JLabel(new ImageIcon("model/images/admin_profile.png"));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 5;
        profilePanel.add(adminImageLabel, gbc);

        // Admin details labels
        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = 0;
        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setFont(new Font("Montserrat", Font.PLAIN, 24)); // Modern font
        nameLabel.setForeground(new Color(200, 200, 200)); // Light text color
        profilePanel.add(nameLabel, gbc);

        gbc.gridy = 1;
        JLabel emailLabel = new JLabel("Email: ");
        emailLabel.setFont(new Font("Montserrat", Font.PLAIN, 24));
        emailLabel.setForeground(new Color(200, 200, 200));
        profilePanel.add(emailLabel, gbc);

        gbc.gridy = 2;
        JLabel phoneLabel = new JLabel("Phone: ");
        phoneLabel.setFont(new Font("Montserrat", Font.PLAIN, 24));
        phoneLabel.setForeground(new Color(200, 200, 200));
        profilePanel.add(phoneLabel, gbc);

        gbc.gridy = 3;
        JLabel addressLabel = new JLabel("Address: ");
        addressLabel.setFont(new Font("Montserrat", Font.PLAIN, 24));
        addressLabel.setForeground(new Color(200, 200, 200));
        profilePanel.add(addressLabel, gbc);

        gbc.gridy = 4;
        JLabel roleLabel = new JLabel("Role: ");
        roleLabel.setFont(new Font("Montserrat", Font.PLAIN, 24));
        roleLabel.setForeground(new Color(200, 200, 200));
        profilePanel.add(roleLabel, gbc);

        gbc.gridy = 5;
        JLabel departmentLabel = new JLabel("Department: ");
        departmentLabel.setFont(new Font("Montserrat", Font.PLAIN, 24));
        departmentLabel.setForeground(new Color(200, 200, 200));
        profilePanel.add(departmentLabel, gbc);

        // Admin details text fields
        gbc.gridx = 2;
        gbc.gridy = 0;
        nameField = new JTextField(admin.getName(), 20);
        nameField.setFont(new Font("Montserrat", Font.PLAIN, 24));
        nameField.setForeground(new Color(200, 200, 200));
        nameField.setBackground(new Color(60, 60, 60)); // Darker text field background
        nameField.setCaretColor(new Color(255, 215, 0)); // Accent color for caret
        profilePanel.add(nameField, gbc);

        gbc.gridy = 1;
        emailField = new JTextField(admin.getEmail(), 20);
        emailField.setFont(new Font("Montserrat", Font.PLAIN, 24));
        emailField.setForeground(new Color(200, 200, 200));
        emailField.setBackground(new Color(60, 60, 60));
        emailField.setCaretColor(new Color(255, 215, 0));
        profilePanel.add(emailField, gbc);

        gbc.gridy = 2;
        phoneField = new JTextField(admin.getPhoneNumber(), 20);
        phoneField.setFont(new Font("Montserrat", Font.PLAIN, 24));
        phoneField.setForeground(new Color(200, 200, 200));
        phoneField.setBackground(new Color(60, 60, 60));
        phoneField.setCaretColor(new Color(255, 215, 0));
        profilePanel.add(phoneField, gbc);

        gbc.gridy = 3;
        addressField = new JTextField(admin.getAddress(), 20);
        addressField.setFont(new Font("Montserrat", Font.PLAIN, 24));
        addressField.setForeground(new Color(200, 200, 200));
        addressField.setBackground(new Color(60, 60, 60));
        addressField.setCaretColor(new Color(255, 215, 0));
        profilePanel.add(addressField, gbc);

        gbc.gridy = 4;
        roleComboBox = new JComboBox<>(new String[]{"Administrator", "Manager", "Supervisor"});
        roleComboBox.setSelectedItem(admin.getRole());
        roleComboBox.setFont(new Font("Montserrat", Font.PLAIN, 24));
        roleComboBox.setForeground(new Color(200, 200, 200));
        roleComboBox.setBackground(new Color(60, 60, 60));
        profilePanel.add(roleComboBox, gbc);

        gbc.gridy = 5;
        departmentComboBox = new JComboBox<>(new String[]{"FrontOffice", "Housekeeping", "FoodAndBeverage", "Maintenance", "SalesAndMarketing", "FinanceAndAccounting", "HumanResources", "Security"});
        departmentComboBox.setSelectedItem(admin.getDepartment());
        departmentComboBox.setFont(new Font("Montserrat", Font.PLAIN, 24));
        departmentComboBox.setForeground(new Color(200, 200, 200));
        departmentComboBox.setBackground(new Color(60, 60, 60));
        profilePanel.add(departmentComboBox, gbc);

        // Update Profile button
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        JButton updateProfileButton = new JButton("Update Profile");
        updateProfileButton.setFont(new Font("Montserrat", Font.BOLD, 20));
        updateProfileButton.setBackground(new Color(30, 30, 30));
        updateProfileButton.setForeground(new Color(255, 215, 0));
        updateProfileButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        updateProfileButton.setFocusPainted(false); // Remove focus rectangle
        updateProfileButton.addActionListener(e -> controller.handleUpdateProfileAction());
        profilePanel.add(updateProfileButton, gbc);

        add(profilePanel, BorderLayout.CENTER);

        // Footer panel
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setBackground(new Color(30, 30, 30));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JButton backButton = new JButton("Back to Dashboard");
        backButton.setFont(new Font("Montserrat", Font.BOLD, 18));
        backButton.setBackground(new Color(255, 215, 0));
        backButton.setForeground(new Color(30, 30, 30));
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        backButton.setFocusPainted(false); // Remove focus rectangle
        backButton.addActionListener(e -> dispose());
        footerPanel.add(backButton);
        add(footerPanel, BorderLayout.SOUTH);
    }

    // Getter methods to retrieve text field values
    public String getNameFieldText() {
        return nameField.getText();
    }

    public String getEmailFieldText() {
        return emailField.getText();
    }

    public String getPhoneFieldText() {
        return phoneField.getText();
    }

    public String getAddressFieldText() {
        return addressField.getText();
    }

    public String getRoleComboBoxText() {
        return (String) roleComboBox.getSelectedItem();
    }

    public String getDepartmentComboBoxText() {
        return (String) departmentComboBox.getSelectedItem();
    }

    // Method to show success message
    public void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}