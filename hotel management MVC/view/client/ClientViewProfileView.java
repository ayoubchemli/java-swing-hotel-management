package view.client;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.client.ClientViewProfileController;
import model.Client;

public class ClientViewProfileView extends JFrame {
    private ClientViewProfileController controller;
    private Client client;

    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JTextField addressField;

    public ClientViewProfileView(ClientViewProfileController controller, Client client) {
        this.controller = controller;
        this.client = client;

        // Set frame properties
        setTitle("Client Profile");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("model/images/client_view.png").getImage());

        // Enable anti-aliasing for text
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");

        getContentPane().setBackground(new Color(250, 240, 230));
        setLayout(new BorderLayout());

        // Header panel
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBackground(new Color(20, 30, 48));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel titleLabel = new JLabel("Client Profile");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 36));
        titleLabel.setForeground(new Color(255, 215, 0));
        headerPanel.add(titleLabel);

        add(headerPanel, BorderLayout.NORTH);

        // Profile content panel
        JPanel profilePanel = new JPanel();
        profilePanel.setBackground(new Color(250, 240, 230));
        profilePanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        profilePanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Client image
        JLabel clientImageLabel = new JLabel(new ImageIcon("model/images/client_profile.png"));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        profilePanel.add(clientImageLabel, gbc);

        // Client details labels
        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = 0;
        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setFont(new Font("Serif", Font.PLAIN, 24));
        nameLabel.setForeground(new Color(20, 30, 48));
        profilePanel.add(nameLabel, gbc);

        gbc.gridy = 1;
        JLabel emailLabel = new JLabel("Email: ");
        emailLabel.setFont(new Font("Serif", Font.PLAIN, 24));
        emailLabel.setForeground(new Color(20, 30, 48));
        profilePanel.add(emailLabel, gbc);

        gbc.gridy = 2;
        JLabel phoneLabel = new JLabel("Phone: ");
        phoneLabel.setFont(new Font("Serif", Font.PLAIN, 24));
        phoneLabel.setForeground(new Color(20, 30, 48));
        profilePanel.add(phoneLabel, gbc);

        gbc.gridy = 3;
        JLabel addressLabel = new JLabel("Address: ");
        addressLabel.setFont(new Font("Serif", Font.PLAIN, 24));
        addressLabel.setForeground(new Color(20, 30, 48));
        profilePanel.add(addressLabel, gbc);

        // Client details text fields
        gbc.gridx = 2;
        gbc.gridy = 0;
        nameField = new JTextField(client.getName(), 20);
        nameField.setFont(new Font("Serif", Font.PLAIN, 24));
        profilePanel.add(nameField, gbc);

        gbc.gridy = 1;
        emailField = new JTextField(client.getEmail(), 20);
        emailField.setFont(new Font("Serif", Font.PLAIN, 24));
        profilePanel.add(emailField, gbc);

        gbc.gridy = 2;
        phoneField = new JTextField(client.getPhoneNumber(), 20);
        phoneField.setFont(new Font("Serif", Font.PLAIN, 24));
        profilePanel.add(phoneField, gbc);

        gbc.gridy = 3;
        addressField = new JTextField(client.getAddress(), 20);
        addressField.setFont(new Font("Serif", Font.PLAIN, 24));
        profilePanel.add(addressField, gbc);

        // Update Profile button
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        JButton updateProfileButton = new JButton("Update Profile");
        updateProfileButton.setFont(new Font("Serif", Font.BOLD, 20));
        updateProfileButton.setBackground(new Color(20, 30, 48));
        updateProfileButton.setForeground(new Color(255, 215, 0));
        updateProfileButton.setPreferredSize(new Dimension(200, 40));
        updateProfileButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        updateProfileButton.addActionListener(e -> controller.handleUpdateProfileAction());
        profilePanel.add(updateProfileButton, gbc);

        add(profilePanel, BorderLayout.CENTER);

        // Footer panel
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setBackground(new Color(20, 30, 48));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JButton backButton = new JButton("Back to Dashboard");
        backButton.setFont(new Font("Serif", Font.BOLD, 18));
        backButton.setBackground(new Color(255, 215, 0));
        backButton.setForeground(new Color(20, 30, 48));
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
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

    // Method to show success message
    public void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}
