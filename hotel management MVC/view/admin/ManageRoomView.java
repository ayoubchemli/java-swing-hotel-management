package view.admin;

import model.Room;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ManageRoomView extends JFrame {
    private JLabel roomNumberLabel;
    private JLabel roomTypeLabel;
    private JLabel ratePerNightLabel;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton checkButton;

    public ManageRoomView(Room room) {
        setTitle("Manage Room");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Enable anti-aliasing for text
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");

        getContentPane().setBackground(new Color(45, 45, 45)); // Dark background color
        setLayout(new BorderLayout());

        // Header panel
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBackground(new Color(30, 30, 30)); // Darker header panel color
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel titleLabel = new JLabel("Manage Room");
        titleLabel.setFont(new Font("Montserrat", Font.BOLD, 36)); // Modern font
        titleLabel.setForeground(new Color(255, 215, 0)); // Accent color
        headerPanel.add(titleLabel);

        add(headerPanel, BorderLayout.NORTH);

        // Room content panel
        JPanel roomPanel = new JPanel();
        roomPanel.setBackground(new Color(45, 45, 45)); // Dark background color
        roomPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        roomPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Room image
        JLabel roomImageLabel = new JLabel(new ImageIcon("model/images/room_image.png"));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        roomPanel.add(roomImageLabel, gbc);

        // Room details labels
        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = 0;
        roomNumberLabel = new JLabel("Room Number: " + room.getRoomNumber());
        roomNumberLabel.setFont(new Font("Montserrat", Font.PLAIN, 24)); // Modern font
        roomNumberLabel.setForeground(new Color(200, 200, 200)); // Light text color
        roomPanel.add(roomNumberLabel, gbc);

        gbc.gridy = 1;
        roomTypeLabel = new JLabel("Room Type: " + room.getRoomTypeString());
        roomTypeLabel.setFont(new Font("Montserrat", Font.PLAIN, 24));
        roomTypeLabel.setForeground(new Color(200, 200, 200));
        roomPanel.add(roomTypeLabel, gbc);

        gbc.gridy = 2;
        ratePerNightLabel = new JLabel("Rate Per Night: $" + room.getRatePerNight());
        ratePerNightLabel.setFont(new Font("Montserrat", Font.PLAIN, 24));
        ratePerNightLabel.setForeground(new Color(200, 200, 200));
        roomPanel.add(ratePerNightLabel, gbc);

        // Create and customize buttons
        deleteButton = new JButton("Delete Room");
        updateButton = new JButton("Update Room");
        checkButton = new JButton("Check Details");

        // Set button styles
        setButtonStyle(deleteButton, new Color(153, 102, 0));
        setButtonStyle(updateButton, new Color(153, 102, 0));
        setButtonStyle(checkButton, new Color(153, 102, 0));

        // Add buttons to the room panel
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(30, 0, 0, 0);
        roomPanel.add(deleteButton, gbc);

        gbc.gridx = 2;
        roomPanel.add(updateButton, gbc);

        gbc.gridx = 3;
        roomPanel.add(checkButton, gbc);

        // Add the room panel to the frame
        add(roomPanel, BorderLayout.CENTER);

        // Footer panel
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setBackground(new Color(30, 30, 30));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Montserrat", Font.BOLD, 18));
        backButton.setBackground(new Color(255, 215, 0));
        backButton.setForeground(new Color(30, 30, 30));
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        backButton.setFocusPainted(false); // Remove focus rectangle
        backButton.addActionListener(e -> dispose());
        footerPanel.add(backButton);
        add(footerPanel, BorderLayout.SOUTH);
    }

    private void setButtonStyle(JButton button, Color color) {
        button.setPreferredSize(new Dimension(200, 60));
        button.setBackground(color);
        button.setForeground(Color.darkGray);
        button.setFont(new Font("Serif", Font.BOLD, 18));
        button.setBorder(new EmptyBorder(5, 10, 5, 10));
        button.setFocusPainted(false);
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public JButton getCheckButton() {
        return checkButton;
    }
}