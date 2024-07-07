package view.client;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

import controller.application.LoginController;
import controller.client.ClientRegistrationController;

public class ClientRegistrationView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JTextField addressField;

    private ClientRegistrationController clientRegistrationController;

    public ClientRegistrationView(LoginController loginController) {
        this.clientRegistrationController = new ClientRegistrationController();

        setTitle("Client Registration");
        setSize(800, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setIconImage(new ImageIcon("path/to/your/icon.png").getImage());

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(30, 50, 30, 50));
        mainPanel.setBackground(new Color(255, 250, 240));

        JLabel titleLabel = new JLabel("Client Registration");
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

        JButton registerButton = new JButton("Register");
        JButton cancelButton = new JButton("Cancel");

        registerButton.setFont(new Font("Serif", Font.BOLD, 16));
        cancelButton.setFont(new Font("Serif", Font.BOLD, 16));
        registerButton.setBackground(new Color(204, 102, 0));
        cancelButton.setBackground(new Color(153, 76, 0));
        registerButton.setForeground(Color.WHITE);
        cancelButton.setForeground(Color.WHITE);

        registerButton.setIcon(new ImageIcon("path/to/your/register_icon.png"));
        cancelButton.setIcon(new ImageIcon("path/to/your/cancel_icon.png"));

        registerButton.addMouseListener(new HoverEffectListener(registerButton, new Color(179, 89, 0)));
        cancelButton.addMouseListener(new HoverEffectListener(cancelButton, new Color(128, 64, 0)));

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(30, 0, 0, 10);
        mainPanel.add(registerButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.insets = new Insets(30, 10, 0, 0);
        mainPanel.add(cancelButton, gbc);

        add(mainPanel);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerClient();
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

    private void registerClient() {
        if (validateInput()) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String name = nameField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            String address = addressField.getText();

            clientRegistrationController.registerClient(username, password, name, phone, email, address);
        }
    }

    private static class HoverEffectListener extends MouseAdapter {
        private final JButton button;
        private final Color hoverColor;

        public HoverEffectListener(JButton button, Color hoverColor) {
            this.button = button;
            this.hoverColor = hoverColor;
        }@Override
        public void mouseEntered(MouseEvent e) {
            button.setBackground(hoverColor);
        }
        
        @Override
        public void mouseExited(MouseEvent e) {
            button.setBackground(new Color(204, 102, 0));
        }
    }
}