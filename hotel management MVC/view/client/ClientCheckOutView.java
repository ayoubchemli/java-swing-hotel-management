package view.client;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

import controller.client.ClientCheckOutController;
import model.Client;
import controller.application.MyDate;

public class ClientCheckOutView extends JFrame {
    private JTextArea detailsArea;
    private JButton checkOutButton;

    public ClientCheckOutView(Client client, String roomName, MyDate checkInDate, MyDate checkOutDate) {
        setTitle("Check-Out");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPane.setBackground(new Color(255, 250, 240));

        JLabel titleLabel = new JLabel("Check-Out");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 28));
        titleLabel.setForeground(new Color(51, 26, 0));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(titleLabel, BorderLayout.NORTH);

        detailsArea = new JTextArea();
        detailsArea.setEditable(false);
        detailsArea.setFont(new Font("Serif", Font.PLAIN, 16));
        detailsArea.setLineWrap(true);
        detailsArea.setWrapStyleWord(true);
        detailsArea.setBackground(Color.WHITE);
        detailsArea.setForeground(new Color(51, 26, 0));
        detailsArea.setText(buildDetailsText(roomName, checkInDate, checkOutDate));

        JScrollPane scrollPane = new JScrollPane(detailsArea);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(204, 153, 102), 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        contentPane.add(scrollPane, BorderLayout.CENTER);

        checkOutButton = new JButton("Check-Out");
        checkOutButton.setPreferredSize(new Dimension(120, 40));
        checkOutButton.setBackground(new Color(153, 51, 0));
        checkOutButton.setForeground(Color.WHITE);
        checkOutButton.setFont(new Font("Serif", Font.BOLD, 16));
        contentPane.add(checkOutButton, BorderLayout.SOUTH);

        setContentPane(contentPane);
    }

    public void addCheckOutButtonListener(ActionListener listener) {
        checkOutButton.addActionListener(listener);
    }

    private String buildDetailsText(String roomName, MyDate checkInDate, MyDate checkOutDate) {
        StringBuilder sb = new StringBuilder();
        sb.append("Room: ").append(roomName).append("\n");
        sb.append("Check-In Date: ").append(checkInDate.toString()).append("\n");
        sb.append("Check-Out Date: ").append(checkOutDate.toString()).append("\n");
        return sb.toString();
    }
}