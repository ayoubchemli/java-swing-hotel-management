package controller.client;

import javax.swing.JOptionPane;

import model.Client;
import model.Room;
import view.client.ClientCheckInView;
import controller.application.MyDate;

public class ClientCheckInController {
    private ClientCheckInView view;
    private Client client;
    private Room room;

    public ClientCheckInController(Client client, Room room) {
        this.client = client;
        this.room = room;
        view = new ClientCheckInView(this, client);
    }

    public void handleCheckInAction() {
        try {
            MyDate selectedDate = view.getSelectedDate();
            int duration = view.getDuration();

            if (selectedDate.isValidDate()) {
                if (duration > 0) {
                    if (!selectedDate.isBeforeCurrentDate()) {
                        room.setCheckInDate(selectedDate);
                        room.setCheckOutDate(selectedDate.addDays(duration));
                        System.out.println("Check-in successful for " + duration + " days starting from " + room.getCheckInDate() + "to : " + room.getCheckOutDate() + "!");
                        // Perform other check-in logic
                        JOptionPane.showMessageDialog(view,
                                "Check-in successful for " + duration + " days starting from " + selectedDate.toString() + ".. you have to pay $" + duration * room.getRatePerNight(),
                                "Success", JOptionPane.INFORMATION_MESSAGE);
                        view.dispose(); // Close the view
                    } else {
                        view.showErrorMessage("Check-in date cannot be before the current date.");
                    }
                } else {
                    view.showErrorMessage("Please enter a valid duration.");
                }
            } else {
                view.showErrorMessage("Please enter a valid date.");
            }
        } catch (NumberFormatException e) {
            view.showErrorMessage("Invalid duration format. Please enter a valid number.");
        }
    }

    public void showView() {
        view.setVisible(true);
    }
}