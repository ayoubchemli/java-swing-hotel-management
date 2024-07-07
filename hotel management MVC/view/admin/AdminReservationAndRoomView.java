package view.admin;

import javax.swing.*;
import java.awt.*;

public class AdminReservationAndRoomView extends JFrame {

    private JList<String> roomList;
    private DefaultListModel<String> roomListModel;
    private JButton manageRoomButton;
    private JComboBox<String> priceRangeComboBox;
    private JComboBox<String> roomTypeComboBox;
    private JCheckBox showOccupiedRoomsCheckBox;
    private JButton addRoomButton;

    public AdminReservationAndRoomView() {
        setTitle("Room Reservation");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create and customize room list
        roomListModel = new DefaultListModel<>();
        roomList = new JList<>(roomListModel);

        // Set background color
        getContentPane().setBackground(new Color(240, 240, 240));

        // Create a panel with GridBagLayout for more flexibility
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create and customize welcome label
        JLabel welcomeLabel = new JLabel("Select a Room to manage");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
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

        showOccupiedRoomsCheckBox = new JCheckBox("Show Occupied Rooms");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(showOccupiedRoomsCheckBox, gbc);

        roomList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        roomList.setFont(new Font("Arial", Font.PLAIN, 16));
        roomList.setCellRenderer(new RoomListRenderer());
        roomList.setVisibleRowCount(5);
        JScrollPane scrollPane = new JScrollPane(roomList);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(scrollPane, gbc);

        // Create and customize reservation button
        manageRoomButton = new JButton("Manage Selected Room");
        manageRoomButton.setPreferredSize(new Dimension(200, 50));
        manageRoomButton.setBackground(new Color(100, 180, 255));
        manageRoomButton.setForeground(Color.WHITE);
        manageRoomButton.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridy = 5;
        contentPanel.add(manageRoomButton, gbc);

        addRoomButton = new JButton("Add Room");
        addRoomButton.setPreferredSize(new Dimension(150, 40));
        addRoomButton.setBackground(new Color(100, 180, 255));
        addRoomButton.setForeground(Color.WHITE);
        addRoomButton.setFont(new Font("Arial", Font.BOLD, 14));

        // Add the button to the content panel
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(addRoomButton, gbc);

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
    public JButton getManageRoomButton() {
        return manageRoomButton;
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

    public JCheckBox getShowOccupiedRoomsCheckBox() {
        return showOccupiedRoomsCheckBox;
    }

    public JButton getAddRoomButton() {
        return addRoomButton;
    }
}