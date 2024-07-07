package controller.application;

import java.awt.Color;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import controller.admin.AdminDashboardController;
import controller.client.ClientDashboardController;
import controller.client.ClientRegistrationController;
import model.Admin;
import model.Client;
import model.Hotel;
import view.application.SecurityView;
import view.client.ClientRegistrationView;

public class LoginController {
    private Random random;

    private static final String SECURITY_PIN = "2005";

    public LoginController() {
        // Initialize the user data maps
        random = new Random();
        // You can populate the user maps with some initial data if needed
    }

    public void login(String username, String password) {
    
        
        // Check if the provided credentials match a registered client
        if (Hotel.clientExistsByUsername(username)) {
            Client client = Hotel.getClientByUsername(username);
            String hashedPassword = ClientRegistrationController.hashPassword(password);
            if (client.getPassword().equals(hashedPassword)) {
                System.out.println("Client Login successful!");
                // Open the ClientDashboardView
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        ClientDashboardController clientDashboardController = new ClientDashboardController(client);
                        clientDashboardController.showView();
                    }
                });
            } else {
                System.out.println("Invalid password!");
                // Show an error message or perform any other necessary actions
            }
        } else {
            System.out.println("Invalid username!");
            // Show an error message or perform any other necessary actions
        }

        // Check for admin login
        if (Hotel.adminExistsByUsername(username)) {
            Admin admin = Hotel.getAdminByUsername(username);
            String hashedPassword = ClientRegistrationController.hashPassword(password);
            if (admin.getPassword().equals(hashedPassword)) {
                System.out.println("Admin Login successful!");
                // Perform any additional actions for Admin login
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        AdminDashboardController adminDashboardController = new AdminDashboardController(admin);
                        adminDashboardController.showView();
                    }
                });
            } else {
                System.out.println("Invalid password!");
                // Show an error message or perform any other necessary actions
            }
        } else {
            System.out.println("Invalid username!");
            // Show an error message or perform any other necessary actions
        }
    
    }


    public void showClientRegistrationScreen() {
        ClientRegistrationView clientRegistrationView = new ClientRegistrationView(this);
        clientRegistrationView.setVisible(true);
    } 

    public void showAdminRegistrationScreen() {
        SecurityView securityView = new SecurityView(this);
        securityView.setVisible(true);
    }

    public void resetFields(JTextField usernameField, JPasswordField passwordField) {
        usernameField.setText("");
        passwordField.setText("");
    }

    public Color generateRandomColor() {
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return new Color(r, g, b);
    }

    public boolean validateSecurityPin(String pin) {
        return pin.equals(SECURITY_PIN);
    }

    
}