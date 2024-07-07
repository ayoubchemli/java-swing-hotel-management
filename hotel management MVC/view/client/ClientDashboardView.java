package view.client;

import javax.swing.*;
import controller.client.ClientDashboardController;
import controller.client.ClientReservationAndRoomController;
import controller.client.ClientReservationsController;
import controller.client.ClientViewProfileController;

import java.awt.*;
import model.Client;

public class ClientDashboardView extends JFrame {
    private ClientDashboardController controller;
    private Client client;
    private JLabel welcomeLabel;
    private ClientReservationAndRoomController reservationController;

    public ClientDashboardView(ClientDashboardController controller, Client client) {
        this.controller = controller;
        this.client = client;

        // Enable anti-aliasing for text
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");

        // Set text rendering hints
        UIManager.put("swing.aatext", true);
        UIManager.put("swing.aalines", true);

        setTitle("Client Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("model/images/client_view.png").getImage());

        getContentPane().setBackground(new Color(255, 250, 240));

        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Client Dashboard", 0, 0, new Font("Serif", Font.BOLD, 24), new Color(51, 26, 0)));

        this.welcomeLabel = new JLabel("Welcome, " + client.getName() + " !");

        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 32));
        welcomeLabel.setForeground(new Color(51, 26, 0));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 40, 0);
        contentPanel.add(welcomeLabel, gbc);

        // Scale icons to 24x24 pixels
        ImageIcon reservedIcon = new ImageIcon(new ImageIcon("model/images/reserved.png").getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH));
        JButton viewReservationsButton = new JButton("View Reservations");
        viewReservationsButton.setHorizontalAlignment(SwingConstants.LEFT);
        viewReservationsButton.setIcon(reservedIcon);
        viewReservationsButton.setPreferredSize(new Dimension(250, 60));
        viewReservationsButton.setBackground(new Color(204, 102, 0));
        viewReservationsButton.setForeground(Color.WHITE);
        viewReservationsButton.setFont(new Font("Serif", Font.BOLD, 18));
        viewReservationsButton.addActionListener(e -> {
        ClientReservationsController reservationsController = new ClientReservationsController(client);
        reservationsController.showView();
        });
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 20, 0);
        contentPanel.add(viewReservationsButton, gbc);

        ImageIcon reserveIcon = new ImageIcon(new ImageIcon("model/images/reserve.png").getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH));
        JButton bookRoomButton = new JButton("Book a Room");
        bookRoomButton.setHorizontalAlignment(SwingConstants.LEFT);
        bookRoomButton.setIcon(reserveIcon);
        bookRoomButton.setPreferredSize(new Dimension(250, 60));
        bookRoomButton.setBackground(new Color(255, 165, 0));
        bookRoomButton.setForeground(Color.WHITE);
        bookRoomButton.setFont(new Font("Serif", Font.BOLD, 18));
        bookRoomButton.addActionListener(e -> {
            reservationController = new ClientReservationAndRoomController(this, client);
            reservationController.showView();
        });
        gbc.gridy = 2;
        contentPanel.add(bookRoomButton, gbc);

        ImageIcon accountManagerIcon = new ImageIcon(new ImageIcon("model/images/account-manager.png").getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH));
        JButton manageProfileButton = new JButton("Manage Profile");
        manageProfileButton.setHorizontalAlignment(SwingConstants.LEFT);
        manageProfileButton.setIcon(accountManagerIcon);
        manageProfileButton.setPreferredSize(new Dimension(250, 60));
        manageProfileButton.setBackground(new Color(153, 76, 0));
        manageProfileButton.setForeground(Color.WHITE);
        manageProfileButton.setFont(new Font("Serif", Font.BOLD, 18));
        manageProfileButton.addActionListener(e -> {
            ClientViewProfileController viewProfileController = new ClientViewProfileController(client, this);
            viewProfileController.showView();
        });
        gbc.gridy = 3;
        contentPanel.add(manageProfileButton, gbc);

        viewReservationsButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bookRoomButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        manageProfileButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

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

        
        viewReservationsButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bookRoomButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        manageProfileButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add the content panel to the frame
        add(contentPanel);
    }
    public void updateWelcomeLabel(String newName) {
        welcomeLabel.setText("Welcome, " + newName + " !");
    }
}
