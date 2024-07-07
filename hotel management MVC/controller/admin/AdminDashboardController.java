package controller.admin;

import model.Admin;
import view.admin.AdminDashboardView;

public class AdminDashboardController {
    private AdminDashboardView view;
    private Admin admin;

    public AdminDashboardController(Admin admin) {
        this.admin = admin;
        view = new AdminDashboardView(this, admin);
    }

    public void showView() {
        view.setVisible(true);
    }

    public void handleManageReservationsAction() {
        // Implement the logic to handle the "Manage Reservations" action
        // For example: new ManageReservationsScreen().setVisible(true);
    }

    public void handleManageRoomsAction() {
        // Implement the logic to handle the "Manage Rooms" action
        // For example: new ManageRoomsScreen().setVisible(true);
    }

    public void handleManageClientsAction() {
        // Implement the logic to handle the "Manage Clients" action
        // For example: new ManageClientsScreen().setVisible(true);
    }
}