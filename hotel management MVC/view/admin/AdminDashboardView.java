package view.admin;

import javax.swing.*;

import controller.admin.AdminDashboardController;
import controller.admin.AdminReservationAndRoomController;
import controller.admin.AdminViewProfileController;
import controller.admin.ManageClientsController;
import controller.client.ClientReservationAndRoomController;
import model.Admin;

import java.awt.*;

public class AdminDashboardView extends JFrame {
    private AdminReservationAndRoomController reservationController;
    private ManageClientsController manageClientsController;

    public AdminDashboardView(AdminDashboardController controller, Admin admin) {
        // Enable anti-aliasing for text
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");

        // Set text rendering hints
        UIManager.put("swing.aatext", true);
        UIManager.put("swing.aalines", true);

        setTitle("Admin Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("model/images/admin_view.png").getImage());

        getContentPane().setBackground(new Color(255, 250, 240));

        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Admin Dashboard", 0, 0, new Font("Serif", Font.BOLD, 24), new Color(51, 26, 0)));

        JLabel welcomeLabel = new JLabel("Welcome, "+ admin.getName()+"!");
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 32));
        welcomeLabel.setForeground(new Color(51, 26, 0));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 40, 0);
        contentPanel.add(welcomeLabel, gbc);

        // Scale icons to 24x24 pixels
        ImageIcon reservationsIcon = new ImageIcon(new ImageIcon("model/images/reserved.png").getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH));
        JButton manageReservationsButton = new JButton("Manage Reservations");
        manageReservationsButton.setHorizontalAlignment(SwingConstants.LEFT);
        manageReservationsButton.setIcon(reservationsIcon);
        manageReservationsButton.setPreferredSize(new Dimension(250, 60));
        manageReservationsButton.setBackground(new Color(204, 102, 0));
        manageReservationsButton.setForeground(Color.WHITE);
        manageReservationsButton.setFont(new Font("Serif", Font.BOLD, 18));
        manageReservationsButton.addActionListener(e -> {
            AdminDashboardController resevationController = new AdminDashboardController(admin);
            resevationController.handleManageReservationsAction();
        });
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 20, 0);
        contentPanel.add(manageReservationsButton, gbc);

        ImageIcon roomsIcon = new ImageIcon(new ImageIcon("model/images/hotel.png").getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH));
        JButton manageRoomsButton = new JButton("Manage Rooms");
        manageRoomsButton.setHorizontalAlignment(SwingConstants.LEFT);
        manageRoomsButton.setIcon(roomsIcon);
        manageRoomsButton.setPreferredSize(new Dimension(250, 60));
        manageRoomsButton.setBackground(new Color(255, 165, 0));
        manageRoomsButton.setForeground(Color.WHITE);
        manageRoomsButton.setFont(new Font("Serif", Font.BOLD, 18));
        manageRoomsButton.addActionListener(e -> {
            reservationController = new AdminReservationAndRoomController(this );
            reservationController.showView();
        });
        gbc.gridy = 2;
        contentPanel.add(manageRoomsButton, gbc);

        ImageIcon clientsIcon = new ImageIcon(new ImageIcon("model/images/client_view.png").getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH));
        JButton manageClientsButton = new JButton("Manage Clients");
        manageClientsButton.setHorizontalAlignment(SwingConstants.LEFT);
        manageClientsButton.setIcon(clientsIcon);
        manageClientsButton.setPreferredSize(new Dimension(250, 60));
        manageClientsButton.setBackground(new Color(153, 76, 0));
        manageClientsButton.setForeground(Color.WHITE);
        manageClientsButton.setFont(new Font("Serif", Font.BOLD, 18));
        manageClientsButton.addActionListener(e -> {
            ManageClientsController manageClientsController = new ManageClientsController();
        });
        gbc.gridy = 3;
        contentPanel.add(manageClientsButton, gbc);

        manageReservationsButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        manageRoomsButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        manageClientsButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add logout button
        JButton logoutButton = new JButton("Logout");
        logoutButton.setPreferredSize(new Dimension(100, 30));
        logoutButton.setBackground(new Color(153, 51, 0));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFont(new Font("Serif", Font.BOLD, 14));
        logoutButton.addActionListener(e -> {
            // Implement logout logic here
            // For example, you can dispose of the current frame
            dispose();
        });
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = new Insets(20, 0, 0, 0); // Adjust top insets to create space
        gbc.anchor = GridBagConstraints.SOUTHWEST; // Anchor the button to the bottom-left
        contentPanel.add(logoutButton, gbc);

        ImageIcon profileIcon = new ImageIcon(new ImageIcon("model/images/account-manager.png").getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH));
        JButton manageProfileButton = new JButton("Manage Profile");
        manageProfileButton.setHorizontalAlignment(SwingConstants.LEFT);
        manageProfileButton.setIcon(profileIcon);
        manageProfileButton.setPreferredSize(new Dimension(250, 60));
        manageProfileButton.setBackground(new Color(102, 51, 0));
        manageProfileButton.setForeground(Color.WHITE);
        manageProfileButton.setFont(new Font("Serif", Font.BOLD, 18));
        manageProfileButton.addActionListener(e -> {
            AdminViewProfileController viewProfileController = new AdminViewProfileController(admin, this);
            viewProfileController.showView();
        });
        gbc.gridy = 4;
        contentPanel.add(manageProfileButton, gbc);

        manageProfileButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add the content panel to the frame
        add(contentPanel);
    }
    

}