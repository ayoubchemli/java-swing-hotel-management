package view.client;

import javax.swing.*;
import java.awt.*;
import model.RoomService;
import java.util.ArrayList;
import java.util.List;

public class ClientReservationAndRoomView extends JFrame {

    private JList<String> roomList;
    private DefaultListModel<String> roomListModel;
    private JButton reserveRoomButton;
    private JComboBox<String> priceRangeComboBox;
    private JComboBox<String> roomTypeComboBox;
    private JComboBox<String> occupancyComboBox;
    private JPanel servicesPanel;
    public JCheckBox[] serviceCheckboxes;

    public ClientReservationAndRoomView() {
        setTitle("Room Selection and Reservation");
        setSize(1100, 900);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create and customize room list
        roomListModel = new DefaultListModel<>();
        roomList = new JList<>(roomListModel);

        // Set background color
        getContentPane().setBackground(new Color(240, 240, 240));

        // Create a panel with GridBagLayout for more flexibility
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        // Create and customize welcome label
        JLabel welcomeLabel = new JLabel("Select a Room for Reservation");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 28));
        welcomeLabel.setForeground(Color.BLACK);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 20, 0);
        contentPanel.add(welcomeLabel, gbc);

        // Create and customize filter and search options
        JLabel priceRangeLabel = new JLabel("Price Range:");
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(priceRangeLabel, gbc);

        String[] priceRanges = {"Any", "Low", "Medium", "High"};
        priceRangeComboBox = new JComboBox<>(priceRanges);
        priceRangeComboBox.setSelectedIndex(0);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        contentPanel.add(priceRangeComboBox, gbc);

        JLabel roomTypeLabel = new JLabel("Room Type:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(roomTypeLabel, gbc);

        String[] roomTypes = {"Any", "Single", "Double", "Suite", "Executive Suite", "Deluxe Room", "Family Room", "Penthouse Suite", "Standard Room"};
        roomTypeComboBox = new JComboBox<>(roomTypes);
        roomTypeComboBox.setSelectedIndex(0);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        contentPanel.add(roomTypeComboBox, gbc);

        JLabel occupancyLabel = new JLabel("Occupancy:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(occupancyLabel, gbc);

        String[] occupancies = {"Any", "1 Person", "2 Persons", "3 Persons", "4 Persons"};
        occupancyComboBox = new JComboBox<>(occupancies);
        occupancyComboBox.setSelectedIndex(0);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        contentPanel.add(occupancyComboBox, gbc);

        // Create and customize room services panel
        servicesPanel = new JPanel(new GridLayout(0, 2));
        servicesPanel.setBorder(BorderFactory.createTitledBorder("Room Services"));

        // Create checkboxes for each room service
        RoomService[] services = RoomService.values();
        serviceCheckboxes = new JCheckBox[services.length];
        for (int i = 0; i < services.length; i++) {
            serviceCheckboxes[i] = new JCheckBox(services[i].name());
            servicesPanel.add(serviceCheckboxes[i]);
        }

        // Add the services panel to the content panel
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(servicesPanel, gbc);

        roomList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        roomList.setFont(new Font("Arial", Font.PLAIN, 16));
        roomList.setCellRenderer(new RoomListRenderer());
        roomList.setVisibleRowCount(5);
        JScrollPane scrollPane = new JScrollPane(roomList);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(scrollPane, gbc);

        // Create and customize reservation button
        reserveRoomButton = new JButton("Reserve Selected Room");
        reserveRoomButton.setPreferredSize(new Dimension(200, 50));
        reserveRoomButton.setBackground(new Color(100, 180, 255));
        reserveRoomButton.setForeground(Color.WHITE);
        reserveRoomButton.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridy = 6;
        contentPanel.add(reserveRoomButton, gbc);

        // Add the content panel to the frame
        add(contentPanel);
    }

    // Custom cell renderer for room list to apply color coding
    private static class RoomListRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (index % 2 == 0) {
                c.setBackground(new Color(255, 255, 204));
            } else {
                c.setBackground(Color.WHITE);
            }
            return c;
        }
    }

    // Getter methods for the view components
    public JButton getReserveRoomButton() {
        return reserveRoomButton;
    }

    public JList<String> getRoomList() {
        return roomList;
    }

    public JComboBox<String> getPriceRangeComboBox() {
        return priceRangeComboBox;
    }

    public JComboBox<String> getRoomTypeComboBox() {
        return roomTypeComboBox;
    }

    public JComboBox<String> getOccupancyComboBox() {
        return occupancyComboBox;
    }

    public List<RoomService> getSelectedRoomServices() {
        List<RoomService> selectedServices = new ArrayList<>();
        for (int i = 0; i < serviceCheckboxes.length; i++) {
            if (serviceCheckboxes[i].isSelected()) {
                selectedServices.add(RoomService.values()[i]);
            }
        }
        return selectedServices;
    }
}