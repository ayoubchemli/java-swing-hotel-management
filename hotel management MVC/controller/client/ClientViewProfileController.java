package controller.client;

import model.Client;
import view.client.ClientDashboardView;
import view.client.ClientViewProfileView;

import javax.swing.*;

public class ClientViewProfileController {
    private final ClientViewProfileView view;
    private final Client client;
    private final ClientDashboardView dashboardView;

    public ClientViewProfileController(Client client, ClientDashboardView dashboardView) {
        this.client = client;
        this.view = new ClientViewProfileView(this, client);
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

        // Update the client instance with the new information
        client.setName(newName);
        client.setEmail(newEmail);
        client.setPhoneNumber(newPhone);
        client.setAddress(newAddress);

        // Update the welcome label in the dashboard view
        dashboardView.updateWelcomeLabel(newName);

        // Show success message
        view.showSuccessMessage("Profile updated successfully!");
    }
}
