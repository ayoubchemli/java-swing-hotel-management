package controller.client;

import model.Client;
import model.Hotel;
import model.Room;
import model.RoomService;
import view.client.ClientDashboardView;
import view.client.ClientReservationAndRoomView;

import javax.swing.*;
import java.util.List;

public class ClientReservationAndRoomController {
    private ClientReservationAndRoomView view;
    private ClientDashboardView parentView;
    private ClientReservationRequestController requestController;

    public ClientReservationAndRoomController(ClientDashboardView parentView, Client client) {
        this.parentView = parentView;
        this.view = new ClientReservationAndRoomView();
        this.requestController = new ClientReservationRequestController(client, this);
        populateRoomList();
        initController();
    }

    public void populateRoomList() {
        DefaultListModel<String> roomListModel = (DefaultListModel<String>) view.getRoomList().getModel();
        roomListModel.clear(); // Clear the list before populating

        List<RoomService> selectedServices = view.getSelectedRoomServices();
        String selectedPriceRange = (String) view.getPriceRangeComboBox().getSelectedItem();
        String selectedRoomType = (String) view.getRoomTypeComboBox().getSelectedItem();
        String selectedOccupancy = (String) view.getOccupancyComboBox().getSelectedItem();

        for (Room room : Hotel.getRooms()) {
            // Check if the room contains all selected services
            if (selectedServices.isEmpty() || room.getAvailableServices().containsAll(selectedServices)) {
                // Check if the room matches the selected price range, room type, and occupancy
                if (matchesPriceRange(room, selectedPriceRange) &&
                        matchesRoomType(room, selectedRoomType) &&
                        matchesOccupancy(room, selectedOccupancy) && !room.checkOccupancy()){
                    String roomInfo = "Room " + room.getRoomNumber() + " - " + room.getRoomType() + " - $" + room.getRatePerNight() + "/night";
                    roomListModel.addElement(roomInfo);
                }
            }
        }
    }

    // Helper method to check if a room matches the selected price range
    private boolean matchesPriceRange(Room room, String selectedPriceRange) {
        switch (selectedPriceRange) {
            case "Low":
                return room.getRatePerNight() >= 0 && room.getRatePerNight() <= 300;
            case "Medium":
                return room.getRatePerNight() >= 301 && room.getRatePerNight() <= 600;
            case "High":
                return room.getRatePerNight() > 600;
            default:
                return true; // Any price range selected
        }
    }

    // Helper method to check if a room matches the selected room type
    private boolean matchesRoomType(Room room, String selectedRoomType) {
        if (selectedRoomType.equals("Any")) {
            return true; // Any room type is selected
        } else {
            // Convert enum constant name to lowercase and replace underscores with spaces
            String roomTypeName = room.getRoomTypeString().toLowerCase().replace("_", " ");
            return roomTypeName.equals(selectedRoomType.toLowerCase()); // Case-insensitive comparison
        }
    }
    

    // Helper method to check if a room matches the selected occupancy
    private boolean matchesOccupancy(Room room, String selectedOccupancy) {
        return selectedOccupancy.equals("Any") || room.getOccupancyString().equals(selectedOccupancy);
    }

    private void initController() {
        view.getReserveRoomButton().addActionListener(e -> handleReserveRoomAction());
        
        // Add event listener for service checkboxes
        for (JCheckBox checkbox : view.serviceCheckboxes) {
            checkbox.addItemListener(e -> populateRoomList());
        }

        // Add event listener for price range combobox
        view.getPriceRangeComboBox().addActionListener(e -> populateRoomList());

        // Add event listener for room type combobox
        view.getRoomTypeComboBox().addActionListener(e -> populateRoomList());

        // Add event listener for occupancy combobox
        view.getOccupancyComboBox().addActionListener(e -> populateRoomList());
    }

    public void showView() {
        view.setVisible(true);
    }

    private void handleReserveRoomAction() {
        int selectedIndex = view.getRoomList().getSelectedIndex();
        if (selectedIndex != -1) {
            String selectedRoomInfo = view.getRoomList().getModel().getElementAt(selectedIndex);
            int roomNumber = Integer.parseInt(selectedRoomInfo.split(" ")[1]);
            Room selectedRoom = Hotel.searchRoom(roomNumber);
            if (selectedRoom != null) {
                // Open the reservation request screen
                String roomName = selectedRoom.getRoomTypeString();
                String roomDescription = selectedRoom.getDescription();
                requestController.updateRoomInfo(roomName, roomDescription, selectedRoom);
                requestController.showView();
            } else {
                JOptionPane.showMessageDialog(view,
                        "Error finding the selected room.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(view,
                    "Please select a room to reserve.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
