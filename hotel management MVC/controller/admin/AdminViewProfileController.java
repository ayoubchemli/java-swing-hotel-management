// AdminViewProfileController.java
package controller.admin;

import model.Admin;
import view.admin.AdminDashboardView;
import view.admin.AdminViewProfileView;

import javax.swing.*;

public class AdminViewProfileController {
    private final AdminViewProfileView view;
    private final Admin admin;
    private final AdminDashboardView dashboardView;

    public AdminViewProfileController(Admin admin, AdminDashboardView dashboardView) {
        this.admin = admin;
        this.view = new AdminViewProfileView(this, admin);
        this.dashboardView = dashboardView;
    }

    public void showView() {
        view.setVisible(true);
    }

    public void handleUpdateProfileAction() {
        String newName = view.getNameFieldText().trim();
        String newEmail = view.getEmailFieldText().trim();
        String newPhone = view.getPhoneFieldText().trim();
        String newAddress = view.getAddressFieldText().trim();
        String newRole = view.getRoleComboBoxText();
        String newDepartment = view.getDepartmentComboBoxText();

        if (newName.isEmpty() || newEmail.isEmpty() || newPhone.isEmpty() || newAddress.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please fill in all the required fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!newEmail.matches("^[\\w-\\.+]*[\\w-\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")) {
            JOptionPane.showMessageDialog(view, "Please enter a valid email address.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!newPhone.matches("\\d+")) {
            JOptionPane.showMessageDialog(view, "Please enter a valid phone number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Update the admin instance with the new information
        admin.setName(newName);
        admin.setEmail(newEmail);
        admin.setPhoneNumber(newPhone);
        admin.setAddress(newAddress);
        admin.setRole(newRole);
        admin.setDepartment(newDepartment);

        // Show success message
        view.showSuccessMessage("Profile updated successfully!");
    }
}