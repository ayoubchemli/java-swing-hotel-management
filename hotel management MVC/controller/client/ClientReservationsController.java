package controller.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.Client;
import model.Room;
import view.client.ClientCheckInView;
import view.client.ClientCheckOutView;
import view.client.ClientReservationsView;

public class ClientReservationsController {
    private ClientReservationsView view;
    private Client client;

    public ClientReservationsController(Client client) {
        this.client = client;
        view = new ClientReservationsView(client, this);
        setupListeners();
    }

    private void setupListeners() {
        view.getCheckInButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCheckIn();
            }
        });

        view.getCheckOutButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCheckOut();
            }
        });
    }

    private void handleCheckIn() {
        Room selectedRoom = view.getRoomList().getSelectedValue();
        if (selectedRoom != null) {
            // Open the check-in view
            ClientCheckInController checkInController = new ClientCheckInController(client,selectedRoom);
            checkInController.showView();
        } else {
            JOptionPane.showMessageDialog(view, "Please select a room to check in.", "Check In", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void handleCheckOut() {
        Room selectedRoom = view.getRoomList().getSelectedValue();
        if (selectedRoom != null) {
            // Open the check-out view
            ClientCheckOutController checkOutController = new ClientCheckOutController(client, String.valueOf(selectedRoom.getRoomNumber()) , selectedRoom.getCheckInDate(), selectedRoom.getCheckOutDate());
            checkOutController.showView();
        } else {
            JOptionPane.showMessageDialog(view, "Please select a room to check out.", "Check Out", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void showView() {
        view.setVisible(true);
    }
}