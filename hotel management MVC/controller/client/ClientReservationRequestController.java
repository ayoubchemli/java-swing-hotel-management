package controller.client;

import model.Client;
import model.Room;
import view.client.ClientReservationRequestView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientReservationRequestController {
    private ClientReservationRequestView view;
    private ClientReservationAndRoomController controller;
    private Room selectedRoom;

    public ClientReservationRequestController(Client client,ClientReservationAndRoomController controller) {
        view = new ClientReservationRequestView();
        this.controller = controller;
        initController(client);
    }

    private void initController(Client client) {
        view.getApproveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement approve action
                // Perform reservation for the selected room
                reserveRoom(client);
                JOptionPane.showMessageDialog(view,
                        "Reservation request approved!", "Success", JOptionPane.INFORMATION_MESSAGE);
                view.dispose(); // Close the screen
            }
        });

        view.getDeclineButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement decline action
                JOptionPane.showMessageDialog(view,
                        "Reservation request declined.", "Declined", JOptionPane.INFORMATION_MESSAGE);
                view.dispose(); // Close the screen
            }
        });
    }

    public void updateRoomInfo(String roomName, String roomDescription, Room selectedRoom) {
        this.selectedRoom = selectedRoom;
        view.updateRoomInfo(roomName, roomDescription);
    }

    private void reserveRoom(Client client) {
        client.bookRoom(selectedRoom);
        System.out.println("Reserving room: " + selectedRoom.getRoomNumber());
        controller.populateRoomList();
    }

    public void showView() {
        view.setVisible(true);
    }
}