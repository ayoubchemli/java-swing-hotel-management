package controller.client;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JOptionPane;

import model.Hotel;
import model.Client;

public class ClientRegistrationController {

    public ClientRegistrationController() {
    }

    public void registerClient(String username, String password, String name, String phoneNumber, String email, String address) {
        if (!Hotel.clientExistsByUsername(username) && !Hotel.adminExistsByUsername(username)) {
            String hashedPassword = hashPassword(password);
            if (hashedPassword != null) {
                Client newClient = new Client(name, phoneNumber, email, address, username, hashedPassword);

                Hotel.addClient(newClient);


                JOptionPane.showMessageDialog(null, "Client Registration successful!");
            } else {
                JOptionPane.showMessageDialog(null, "Error hashing password!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Username already exists!");
        }
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error hashing password: " + e.getMessage());
            return null;
        }
    }

    
}