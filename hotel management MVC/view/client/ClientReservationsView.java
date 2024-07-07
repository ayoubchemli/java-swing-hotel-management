package view.client;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

import controller.client.ClientReservationsController;
import model.Client;
import model.Room;

public class ClientReservationsView extends JFrame {
    private JPanel contentPane;
    private JList<Room> roomList;
    private DefaultListModel<Room> roomListModel;
    private JButton checkInButton;
    private JButton checkOutButton;

    public ClientReservationsView(Client client, ClientReservationsController controller) {
        setTitle("Client Reservations");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPane.setBackground(new Color(255, 250, 240));

        JLabel titleLabel = new JLabel("Your Reservations");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 28));
        titleLabel.setForeground(new Color(51, 26, 0));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(titleLabel, BorderLayout.NORTH);

        roomListModel = new DefaultListModel<>();
        List<Room> bookedRooms = client.getBookedRooms();
        for (Room room : bookedRooms) {
            roomListModel.addElement(room);
        }

        roomList = new JList<>(roomListModel);
        roomList.setFont(new Font("Serif", Font.PLAIN, 16));
        roomList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        roomList.setCellRenderer(new RoomListCellRenderer());
        JScrollPane scrollPane = new JScrollPane(roomList);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(204, 153, 102), 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        checkInButton = new JButton("Check In");
        checkOutButton = new JButton("Check Out");
        checkInButton.setPreferredSize(new Dimension(120, 40));
        checkOutButton.setPreferredSize(new Dimension(120, 40));
        checkInButton.setBackground(new Color(51, 102, 0));
        checkInButton.setForeground(Color.WHITE);
        checkOutButton.setBackground(new Color(153, 51, 0));
        checkOutButton.setForeground(Color.WHITE);
        buttonPanel.add(checkInButton);
        buttonPanel.add(checkOutButton);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(contentPane);
    }

    public JList<Room> getRoomList() {
        return roomList;
    }

    public JButton getCheckInButton() {
        return checkInButton;
    }

    public JButton getCheckOutButton() {
        return checkOutButton;
    }

    private static class RoomListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (renderer instanceof JLabel && value instanceof Room) {
                Room room = (Room) value;
                JLabel label = (JLabel) renderer;
                label.setText("room num" + room.getRoomNumber() + " - " + room.getRoomType() + " - $" + room.getRatePerNight() + "/night");
                label.setFont(new Font("Serif", Font.PLAIN, 16));
                label.setForeground(isSelected ? Color.WHITE : new Color(51, 26, 0));
                label.setBackground(isSelected ? new Color(204, 153, 102) : Color.WHITE);
                label.setOpaque(true);
            }
            return renderer;
        }
    }
}