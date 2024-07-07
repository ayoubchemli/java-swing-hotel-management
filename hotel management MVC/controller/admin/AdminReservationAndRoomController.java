package controller.admin;

import model.Hotel;
import model.Room;
import model.RoomService;
import model.RoomType;
import view.admin.AdminDashboardView;
import view.admin.AdminReservationAndRoomView;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.*;

public class AdminReservationAndRoomController {
    private AdminReservationAndRoomView view;
    private AdminDashboardView parentView;
    //private AdminReservationRequestController requestController;

    public AdminReservationAndRoomController(AdminDashboardView parentView) {
        this.parentView = parentView;
        this.view = new AdminReservationAndRoomView();
        //this.requestController = new AdminReservationRequestController(this);
        populateRoomList();
        initController();
    }

    public void populateRoomList() {
        DefaultListModel<String> roomListModel = (DefaultListModel<String>) view.getRoomList().getModel();
        roomListModel.clear(); // Clear the list before populating

        boolean showOccupiedRooms = view.getShowOccupiedRoomsCheckBox().isSelected();
        String selectedPriceRange = (String) view.getPriceRangeComboBox().getSelectedItem();
        String selectedRoomType = (String) view.getRoomTypeComboBox().getSelectedItem();

        for (Room room : Hotel.getRooms()) {
            // Check if the room matches the occupancy status filter
            if (showOccupiedRooms == room.checkOccupancy()) {
                // Check if the room matches the selected price range and room type
                if (matchesPriceRange(room, selectedPriceRange) &&
                    matchesRoomType(room, selectedRoomType)) {
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

    private void initController() {
        view.getManageRoomButton().addActionListener(e -> handleManageRoomAction());

        // Add event listener for occupancy checkbox
        view.getShowOccupiedRoomsCheckBox().addItemListener(e -> populateRoomList());

        // Add event listener for price range combobox
        view.getPriceRangeComboBox().addActionListener(e -> populateRoomList());

        // Add event listener for room type combobox
        view.getRoomTypeComboBox().addActionListener(e -> populateRoomList());

        view.getAddRoomButton().addActionListener(e -> handleAddRoomAction());
    }

    public JButton getAddRoomButton() {
        return view.getAddRoomButton();
    }

    public void showView() {
        view.setVisible(true);
    }

    private void handleManageRoomAction() {
        int selectedIndex = view.getRoomList().getSelectedIndex();
        if (selectedIndex != -1) {
            String selectedRoomInfo = view.getRoomList().getModel().getElementAt(selectedIndex);
            int roomNumber = Integer.parseInt(selectedRoomInfo.split(" ")[1]);
            Room selectedRoom = Hotel.searchRoom(roomNumber);
            if (selectedRoom != null) {
                ManageRoomController manageRoomController = new ManageRoomController(selectedRoom, this);
                manageRoomController.showView();
            } else {
                JOptionPane.showMessageDialog(view,
                        "Error finding the selected room.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(view,
                    "Please select a room to manage.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        populateRoomList();
    }

    private void handleAddRoomAction() {
        JFrame addRoomFrame = new JFrame("Add Room");
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

        JButton createRoomButton = new JButton("Create Room");
        createRoomButton.addActionListener(e -> {
            RoomType selectedRoomType = RoomType.valueOf(roomTypeComboBox.getSelectedItem().toString().replace(" ", "_").toUpperCase());
            int selectedOccupancy = Integer.parseInt(occupancyComboBox.getSelectedItem().toString());
            Room newRoom = new Room(selectedRoomType, selectedOccupancy);

            // Add selected services to the room
            for (int i = 0; i < services.length; i++) {
                if (serviceCheckboxes[i].isSelected()) {
                    newRoom.addRoomService(services[i]);
                }
            }

            Hotel.addRoom(newRoom);
            populateRoomList(); // Update the room list after adding the new room
            addRoomFrame.dispose(); // Close the add room frame
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(createRoomButton, gbc);

        addRoomFrame.add(contentPanel);
        addRoomFrame.setVisible(true);

        populateRoomList();
    }

}