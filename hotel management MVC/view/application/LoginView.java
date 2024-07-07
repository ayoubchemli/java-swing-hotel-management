package view.application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.application.LoginController;


public class LoginView extends JFrame {
    private LoginController controller;
    private MainView mainView; 


    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerClientButton;
    private JButton registerAdminButton;
    private JButton resetButton;
    private JButton backButton;

    public LoginView(MainView mainView) {

        this.mainView = mainView; // Initialize the mainView instance variable
        controller = new LoginController();

        setTitle("Login");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int w = getWidth();
                int h = getHeight();
                Color color1 = controller.generateRandomColor();
                Color color2 = controller.generateRandomColor();
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        formPanel.setOpaque(false);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.WHITE);
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        loginButton = new JButton("Login");
        registerClientButton = new JButton("Client Register");
        registerAdminButton = new JButton("Admin Register");
        resetButton = new JButton("Reset");
        backButton = new JButton("Back");
        resetButton.setPreferredSize(new Dimension(80, 30));

        loginButton.setBackground(new Color(46, 204, 113));
        loginButton.setForeground(Color.WHITE);
        registerClientButton.setBackground(new Color(52, 152, 219));
        registerClientButton.setForeground(Color.WHITE);
        registerAdminButton.setBackground(new Color(52, 152, 219));
        registerAdminButton.setForeground(Color.WHITE);
        resetButton.setBackground(new Color(231, 76, 60));
        resetButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.WHITE);
        backButton.setForeground(Color.BLACK);
        Font buttonFont = new Font("Arial", Font.BOLD, 12);
        loginButton.setFont(buttonFont);
        registerClientButton.setFont(buttonFont);
        registerAdminButton.setFont(buttonFont);
        resetButton.setFont(buttonFont);
        backButton.setFont(buttonFont);

        formPanel.add(usernameLabel);
        formPanel.add(usernameField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);
        formPanel.add(resetButton);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        buttonPanel.add(loginButton);
        buttonPanel.add(registerClientButton);
        buttonPanel.add(registerAdminButton);
        buttonPanel.setOpaque(false);

        JPanel buttonContainer = new JPanel(new BorderLayout());
        buttonContainer.setBackground(Color.WHITE);
        buttonContainer.add(buttonPanel, BorderLayout.CENTER);
        buttonContainer.add(backButton, BorderLayout.SOUTH);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonContainer, BorderLayout.SOUTH);
        add(mainPanel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        });

        registerClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showClientRegistrationScreen();
            }
        });

        registerAdminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.showAdminRegistrationScreen();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.resetFields(usernameField, passwordField);
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show the MainView
                mainView.setVisible(true);
                // Hide the LoginView
                setVisible(false);
            }
        });
    }

    public void performLogin() {
        // Get the username and password from the text fields
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // Delegate the login logic to the controller
        controller.login(username, password);
    }

    public void showClientRegistrationScreen() {
        // Delegate the logic to the controller
        controller.showClientRegistrationScreen();
    }




}