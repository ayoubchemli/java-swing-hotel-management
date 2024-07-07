package controller.client;

import javax.swing.JOptionPane;

import model.Client;
import controller.application.MyDate;
import view.client.ClientCheckOutView;

public class ClientCheckOutController {
    private ClientCheckOutView view;
    private Client client;

    public ClientCheckOutController(Client client, String roomName, MyDate checkInDate, MyDate checkOutDate) {
        this.client = client;
        view = new ClientCheckOutView(client, roomName, checkInDate, checkOutDate);
        view.addCheckOutButtonListener(e -> handleCheckOut());
    }

    private void handleCheckOut() {
        // Perform check-out logic
        JOptionPane.showMessageDialog(view, "Check-out successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
        view.dispose(); // Close the view
    }

    public void showView() {
        view.setVisible(true);
    }
}