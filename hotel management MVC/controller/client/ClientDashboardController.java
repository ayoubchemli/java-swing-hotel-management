package controller.client;

import model.Client;
import view.client.ClientDashboardView;

public class ClientDashboardController {
    private ClientDashboardView view;

    public ClientDashboardController(Client client) {
        view = new ClientDashboardView(this, client );
    }

    public void showView() {
        view.setVisible(true);
    }

    public void handleViewReservationsAction() {
        // Implement the logic to handle the "View Reservations" action
        // For example: new ViewReservationsScreen().setVisible(true);
    }

    public void handleBookRoomAction() {
        // Implement the logic to handle the "Book a Room" action
        // For example: new BookRoomScreen().setVisible(true);
    }

    public void handleManageProfileAction() {
        // Implement the logic to handle the "Manage Profile" action
        // For example: new ManageProfileScreen().setVisible(true);
    }
}