package controller.admin;

import model.Client;
import model.Hotel;
import view.admin.ManageClientsView;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;

import controller.client.ClientRegistrationController;

public class ManageClientsController {
    private ManageClientsView view;

    public ManageClientsController() {
        view = new ManageClientsView(this);
        view.setVisible(true);
    }

    public void handleAddClient() {
        // Create a new JDialog or JFrame to prompt the user for client details
        JDialog dialog = new JDialog(view, "Add Client", true);
        JPanel panel = new JPanel(new GridLayout(6, 2));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        panel.add(nameLabel);
        panel.add(nameField);

        JLabel phoneLabel = new JLabel("Phone Number:");
        JTextField phoneField = new JTextField();
        panel.add(phoneLabel);
        panel.add(phoneField);

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        panel.add(emailLabel);
        panel.add(emailField);

        JLabel addressLabel = new JLabel("Address:");
        JTextField addressField = new JTextField();
        panel.add(addressLabel);
        panel.add(addressField);

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        panel.add(usernameLabel);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        panel.add(passwordLabel);
        panel.add(passwordField);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String name = nameField.getText();
            String phoneNumber = phoneField.getText();
            String email = emailField.getText();
            String address = addressField.getText();

            if (name.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() || address.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Please fill in all the required fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            if (!email.matches("^[\\w-\\.+]*[\\w-\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")) {
                JOptionPane.showMessageDialog(view, "Please enter a valid email address.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            if (!phoneNumber.matches("\\d+")) {
                JOptionPane.showMessageDialog(view, "Please enter a valid phone number.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            ClientRegistrationController controller = new ClientRegistrationController();
            controller.registerClient(username, password, name, phoneNumber, email, address);

            dialog.dispose();
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(registerButton);

        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.pack();
        dialog.setLocationRelativeTo(view);
        dialog.setVisible(true);
    }

    public void handleEditClient() {
        int selectedRow = view.getClientTable().getSelectedRow();
        if (selectedRow != -1) {
            int option = JOptionPane.showConfirmDialog(view, "Are you sure you want to edit the selected client?", "Confirm edit", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {

                String clientId = (String) view.getClientTable().getValueAt(selectedRow, 0);
                Client selectedClient = Hotel.searchClient(clientId);
           
                // Create a JFrame to edit client details
                JFrame editFrame = new JFrame("Edit Client");
                editFrame.setSize(300, 250);
                editFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                editFrame.setLocationRelativeTo(view);
    
                JPanel panel = new JPanel(new GridLayout(7, 2));
    
                JLabel nameLabel = new JLabel("Name:");
                JTextField nameField = new JTextField(selectedClient.getName());
                panel.add(nameLabel);
                panel.add(nameField);
    
                JLabel phoneLabel = new JLabel("Phone Number:");
                JTextField phoneField = new JTextField(selectedClient.getPhoneNumber());
                panel.add(phoneLabel);
                panel.add(phoneField);
    
                JLabel emailLabel = new JLabel("Email:");
                JTextField emailField = new JTextField(selectedClient.getEmail());
                panel.add(emailLabel);
                panel.add(emailField);
    
                JLabel addressLabel = new JLabel("Address:");
                JTextField addressField = new JTextField(selectedClient.getAddress());
                panel.add(addressLabel);
                panel.add(addressField);
    
                JLabel usernameLabel = new JLabel("Username:");
                JTextField usernameField = new JTextField(selectedClient.getUsername());
                panel.add(usernameLabel);
                panel.add(usernameField);
    
                JLabel passwordLabel = new JLabel("Password:");
                JPasswordField passwordField = new JPasswordField(selectedClient.getPassword());
                panel.add(passwordLabel);
                panel.add(passwordField);

                if (nameField.getText().isEmpty() || emailField.getText().isEmpty() || phoneField.getText().isEmpty() || addressField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Please fill in all the required fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
        
                if (!emailField.getText().matches("^[\\w-\\.+]*[\\w-\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")) {
                    JOptionPane.showMessageDialog(view, "Please enter a valid email address.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
        
                if (!phoneField.getText().matches("\\d+")) {
                    JOptionPane.showMessageDialog(view, "Please enter a valid phone number.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
    
                JButton saveButton = new JButton("Save");
                saveButton.addActionListener(e -> {
                    // Update the client details
                    selectedClient.setName(nameField.getText());
                    selectedClient.setPhoneNumber(phoneField.getText());
                    selectedClient.setEmail(emailField.getText());
                    selectedClient.setAddress(addressField.getText());
                    selectedClient.setUsername(usernameField.getText());
                    selectedClient.setPassword(new String(passwordField.getPassword()));
    
                    // Inform the user
                    JOptionPane.showMessageDialog(editFrame, "Client details updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    editFrame.dispose();
                });
    
                panel.add(saveButton);
    
                editFrame.add(panel);
                editFrame.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(view, "Failed to retrieve client details.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(view, "Please select a client to edit.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void handleRemoveClient() {
        int selectedRow = view.getClientTable().getSelectedRow();
        if (selectedRow != -1) {
            int option = JOptionPane.showConfirmDialog(view, "Are you sure you want to remove the selected client?", "Confirm Removal", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                String clientId = (String) view.getClientTable().getValueAt(selectedRow, 0);
                Client client = Hotel.searchClient(clientId);
                if (client != null) {
                    Hotel.removeClient(client);
                    view.getTableModel().removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(view, "Error finding the selected client.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(view, "Please select a client to remove.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}