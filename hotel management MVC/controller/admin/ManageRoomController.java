package controller.admin;

import model.Hotel;
import model.Room;
import model.RoomService;
import model.RoomType;
import view.admin.ManageRoomView;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.*;

public class ManageRoomController {
    private ManageRoomView view;
    private Room room;
    AdminReservationAndRoomController adminReservationAndRoomController;

    public ManageRoomController(Room room,AdminReservationAndRoomController adminReservationAndRoomController) {
        this.adminReservationAndRoomController = adminReservationAndRoomController;
        this.room = room;
        this.view = new ManageRoomView(room);
        initController();
    }

    private void initController() {
        view.getDeleteButton().addActionListener(e -> handleDeleteRoomAction());
        view.getUpdateButton().addActionListener(e -> handleUpdateRoomAction());
        view.getCheckButton().addActionListener(e -> handleCheckRoomAction());
    }

    public void showView() {
        view.setVisible(true);
    }

    private void handleDeleteRoomAction() {
        int choice = JOptionPane.showConfirmDialog(view, "Are you sure you want to delete this room?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            // Perform room deletion logic here
            Hotel.getRooms().remove(room);
            JOptionPane.showMessageDialog(view, "Room deleted successfully.");
            view.dispose();
        }
        adminReservationAndRoomController.populateRoomList();
    }

    private void handleUpdateRoomAction() {
        // Open a new screen or dialog to update room details
        JFrame addRoomFrame = new JFrame("update Room");
        addRoomFrame.setSize(400, 300);
        addRoomFrame.setLocationRelativeTo(null); // Center the frame
        addRoomFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel roomTypeLabel = new JLabel("Room Type:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(roomTypeLabel, gbc);

        String[] roomTypes = {"Single", "Double", "Suite", "Executive Suite", "Deluxe Room", "Family Room", "Penthouse Suite", "Standard Room"};
        JComboBox<String> roomTypeComboBox = new JComboBox<>(roomTypes);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        contentPanel.add(roomTypeComboBox, gbc);

        JLabel occupancyLabel = new JLabel("Occupancy:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(occupancyLabel, gbc);

        String[] occupancyOptions = {"1", "2", "3", "4"};
        JComboBox<String> occupancyComboBox = new JComboBox<>(occupancyOptions);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        contentPanel.add(occupancyComboBox, gbc);

        // Create and customize room services panel
        JPanel servicesPanel = new JPanel(new GridLayout(0, 2));
        servicesPanel.setBorder(BorderFactory.createTitledBorder("Room Services"));

        // Create checkboxes for each room service
        RoomService[] services = RoomService.values();
        JCheckBox[] serviceCheckboxes = new JCheckBox[services.length];
        for (int i = 0; i < services.length; i++) {
            serviceCheckboxes[i] = new JCheckBox(services[i].name());
            servicesPanel.add(serviceCheckboxes[i]);
        }

        // Add the services panel to the content panel
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(servicesPanel, gbc);

        JButton createRoomButton = new JButton("update Room");
        createRoomButton.addActionListener(e -> {
            RoomType selectedRoomType = RoomType.valueOf(roomTypeComboBox.getSelectedItem().toString().replace(" ", "_").toUpperCase());
            int selectedOccupancy = Integer.parseInt(occupancyComboBox.getSelectedItem().toString());
            // Room newRoom = new Room(selectedRoomType, selectedOccupancy);
            room.setRoomType(selectedRoomType);
            room.setoccupancy(selectedOccupancy);

            // Add selected services to the room
            for (int i = 0; i < services.length; i++) {
                if (serviceCheckboxes[i].isSelected()) {
                    room.addRoomService(services[i]);
                }
            }

            adminReservationAndRoomController.populateRoomList();

            addRoomFrame.dispose(); // Close the add room frame
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(createRoomButton, gbc);

        addRoomFrame.add(contentPanel);
        addRoomFrame.setVisible(true);

        adminReservationAndRoomController.populateRoomList();

    }
    

    private void handleCheckRoomAction() {
        StringBuilder roomDetails = new StringBuilder();
        roomDetails.append("Room Number: ").append(room.getRoomNumber()).append("\n");
        roomDetails.append("Room Type: ").append(room.getRoomTypeString()).append("\n");
        roomDetails.append("Rate Per Night: $").append(room.getRatePerNight()).append("\n");
        roomDetails.append("Description: ").append(room.getDescription()).append("\n");
        roomDetails.append("Occupancy: ").append(room.getOccupancyString()).append("\n");
        roomDetails.append("Available Services: ").append(room.getAvailableServices()).append("\n");

        JOptionPane.showMessageDialog(view, roomDetails.toString(), "Room Details", JOptionPane.INFORMATION_MESSAGE);
    }
}