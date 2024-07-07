package view.application;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.application.MainController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame {
    private MainController controller;
    private LoginView loginView; 


    public MainView() {
        controller = new MainController();
        initComponents();
    }

    private void initComponents() {
        loginView = new LoginView(this);
        
        setTitle("Luxury Hotel Experience");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create a panel for the content
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(new Color(250, 240, 230));
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Create a panel for the header
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBackground(new Color(20, 30, 48));
        JLabel headerLabel = new JLabel("Welcome to The Grand Palace Hotel");
        headerLabel.setFont(new Font("Serif", Font.BOLD, 36));
        headerLabel.setForeground(new Color(255, 215, 0));
        headerPanel.add(headerLabel);
        contentPanel.add(headerPanel, BorderLayout.NORTH);

        // Create a panel for the main content
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(250, 240, 230));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Add a description panel
        JPanel descriptionPanel = new JPanel(new GridBagLayout());
        descriptionPanel.setBackground(new Color(250, 240, 230));
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel descriptionLabel = new JLabel("<html><center>Experience the ultimate in luxury and comfort at our prestigious hotel.<br>Indulge in world-class amenities and exceptional service.</center></html>");
        descriptionLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        descriptionLabel.setForeground(new Color(20, 30, 48));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(20, 20, 20, 20);
        descriptionPanel.add(descriptionLabel, constraints);

        mainPanel.add(descriptionPanel, BorderLayout.NORTH);

        // Add a main image
        ImageIcon mainImageIcon = new ImageIcon(getClass().getResource("/model/images/hotel-main.jpg"));
        JLabel mainImageLabel = new JLabel(mainImageIcon);
        mainImageLabel.setBorder(BorderFactory.createLineBorder(new Color(20, 30, 48), 2));
        mainPanel.add(mainImageLabel, BorderLayout.CENTER);

        contentPanel.add(mainPanel, BorderLayout.CENTER);

        // Add a login button
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Serif", Font.BOLD, 18));
        loginButton.setBackground(new Color(20, 30, 48));
        loginButton.setForeground(new Color(255, 215, 0));
        loginButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show the LoginView
                loginView.setVisible(true);
                // Hide the MainView
                setVisible(false);
            }
        });
        contentPanel.add(loginButton, BorderLayout.SOUTH);

        add(contentPanel);
    }

}