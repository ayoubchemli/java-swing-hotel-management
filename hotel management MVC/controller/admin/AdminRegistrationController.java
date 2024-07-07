package controller.admin;

import javax.swing.JOptionPane;

import controller.client.ClientRegistrationController;
import model.Admin;
import model.Hotel;

public class AdminRegistrationController {

    public AdminRegistrationController() {
    }

    public void registerAdmin(String username, String password, String name, String phoneNumber, String email, String address, String department, String role) {
        if (!Hotel.clientExistsByUsername(username) && !Hotel.adminExistsByUsername(username)) {
            String hashedPassword = ClientRegistrationController.hashPassword(password);
            if (hashedPassword != null) {
                Admin newAdmin = new Admin(name, phoneNumber, email, address, username, hashedPassword,department, role);

                Hotel.addAdmin(newAdmin);

                JOptionPane.showMessageDialog(null, "Admin Registration successful!");
            } else {
                JOptionPane.showMessageDialog(null, "Error hashing password!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Username already exists!");
        }
    }


}
