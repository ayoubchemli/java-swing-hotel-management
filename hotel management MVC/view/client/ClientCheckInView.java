package view.client;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

import controller.client.ClientCheckInController;
import model.Client;
import controller.application.MyDate;

public class ClientCheckInView extends JFrame {
    private JComboBox<Integer> dayComboBox, monthComboBox, yearComboBox;
    private JTextField durationField;
    private JButton checkInButton;
    private ClientCheckInController controller;

    public ClientCheckInView(ClientCheckInController controller, Client client) {
        this.controller = controller;

        // Set frame properties
        setTitle("Check-In");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("model/images/client_view.png").getImage());

        // Enable anti-aliasing for text
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");

        getContentPane().setBackground(new Color(250, 240, 230));
        setLayout(new BorderLayout());

        // Header panel
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBackground(new Color(20, 30, 48));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel titleLabel = new JLabel("Check-In");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 36));
        titleLabel.setForeground(new Color(255, 215, 0));
        headerPanel.add(titleLabel);

        add(headerPanel, BorderLayout.NORTH);

        // Check-in content panel
        JPanel checkInPanel = new JPanel();
        checkInPanel.setBackground(new Color(250, 240, 230));
        checkInPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        checkInPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Client image
        JLabel clientImageLabel = new JLabel(new ImageIcon("model/images/check_in.png"));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        checkInPanel.add(clientImageLabel, gbc);

        // Check-in date labels
        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = 0;
        JLabel dateLabel = new JLabel("Check-In Date: ");
        dateLabel.setFont(new Font("Serif", Font.PLAIN, 24));
        dateLabel.setForeground(new Color(20, 30, 48));
        checkInPanel.add(dateLabel, gbc);

        gbc.gridy = 1;
        JLabel durationLabel = new JLabel("Duration (days): ");
        durationLabel.setFont(new Font("Serif", Font.PLAIN, 24));
        durationLabel.setForeground(new Color(20, 30, 48));
        checkInPanel.add(durationLabel, gbc);

        // Check-in date input fields
        gbc.gridx = 2;
        gbc.gridy = 0;
        dayComboBox = new JComboBox<>(createRange(1, 31));
        monthComboBox = new JComboBox<>(createRange(1, 12));
        yearComboBox = new JComboBox<>(createRange(2022, 2030));
        JPanel dateInputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        dateInputPanel.add(dayComboBox);
        dateInputPanel.add(new JLabel("Day"));
        dateInputPanel.add(monthComboBox);
        dateInputPanel.add(new JLabel("Month"));
        dateInputPanel.add(yearComboBox);
        dateInputPanel.add(new JLabel("Year"));
        checkInPanel.add(dateInputPanel, gbc);

        gbc.gridy = 1;
        durationField = new JTextField(10);
        durationField.setFont(new Font("Serif", Font.PLAIN, 24));
        checkInPanel.add(durationField, gbc);

        // Check-In button
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        checkInButton = new JButton("Check-In");
        checkInButton.setFont(new Font("Serif", Font.BOLD, 20));
        checkInButton.setBackground(new Color(20, 30, 48));
        checkInButton.setForeground(new Color(255, 215, 0));
        checkInButton.setPreferredSize(new Dimension(200, 40));
        checkInButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        checkInButton.addActionListener(e -> controller.handleCheckInAction());
        checkInPanel.add(checkInButton, gbc);

        add(checkInPanel, BorderLayout.CENTER);

        // Footer panel
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setBackground(new Color(20, 30, 48));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JButton backButton = new JButton("Back to Dashboard");
        backButton.setFont(new Font("Serif", Font.BOLD, 18));
        backButton.setBackground(new Color(255, 215, 0));
        backButton.setForeground(new Color(20, 30, 48));
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        backButton.addActionListener(e -> dispose());

        footerPanel.add(backButton);
        add(footerPanel, BorderLayout.SOUTH);
    }

    public MyDate getSelectedDate() {
        int day = (int) dayComboBox.getSelectedItem();
        int month = (int) monthComboBox.getSelectedItem();
        int year = (int) yearComboBox.getSelectedItem();
        return new MyDate(day, month, year);
    }

    public int getDuration() {
        return Integer.parseInt(durationField.getText());
    }

    private Integer[] createRange(int start, int end) {
        Integer[] range = new Integer[end - start + 1];
        for (int i = 0; i < range.length; i++) {
            range[i] = start++;
        }
        return range;
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}