package view.client;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ClientReservationRequestView extends JFrame {
    private JTextArea detailsArea;
    private JButton approveButton;
    private JButton declineButton;

    public ClientReservationRequestView() {
        setTitle("Reservation Request");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Create panel with BorderLayout
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPanel.setBackground(new Color(255, 250, 240)); // Fancy beige background

        // Create and customize welcome label
        JLabel titleLabel = new JLabel("Room Information");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 28));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(new Color(137, 76, 38)); // Fancy brown color

        contentPanel.add(titleLabel, BorderLayout.NORTH);

        // Create panel for room information
        JPanel roomPanel = new JPanel(new BorderLayout());
        roomPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(204, 153, 102), 2),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)));
        roomPanel.setBackground(new Color(255, 245, 230)); 

        // Room details
        detailsArea = new JTextArea();
        detailsArea.setEditable(false);
        detailsArea.setFont(new Font("Serif", Font.PLAIN, 16));
        detailsArea.setLineWrap(true);
        detailsArea.setWrapStyleWord(true);
        detailsArea.setBackground(new Color(255, 245, 230)); 
        detailsArea.setForeground(new Color(137, 76, 38)); 
        JScrollPane scrollPane = new JScrollPane(detailsArea);
        roomPanel.add(scrollPane, BorderLayout.CENTER);

        // Add room panel to content panel
        contentPanel.add(roomPanel, BorderLayout.CENTER);

        // Create and customize button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        approveButton = new JButton("Approve");
        declineButton = new JButton("Decline");
        approveButton.setPreferredSize(new Dimension(120, 40));
        declineButton.setPreferredSize(new Dimension(120, 40));
        approveButton.setBackground(new Color(51, 102, 0));
        approveButton.setForeground(new Color(255, 245, 230)); // Fancy light beige color
        declineButton.setBackground(new Color(153, 51, 0));
        declineButton.setForeground(new Color(255, 245, 230)); // Fancy light beige color
        buttonPanel.add(approveButton);
        buttonPanel.add(declineButton);

        // Add button panel to content panel
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add content panel to frame
        add(contentPanel);
    }

    public JButton getApproveButton() {
        return approveButton;
    }

    public JButton getDeclineButton() {
        return declineButton;
    }

    public void updateRoomInfo(String roomName, String roomDescription) {
        detailsArea.setText(roomDescription);
        setTitle("Reservation Request - " + roomName);
    }
}