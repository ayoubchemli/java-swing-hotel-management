package view.application;


import javax.swing.*;

import controller.application.LoginController;
import view.admin.AdminRegistrationView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SecurityView extends JFrame {
    private LoginController controller;
    private JButton[] numButtons;
    private JTextField pinField;
    private JLabel pinDisplayLabel;
    private int attempts = 0;
    private AdminRegistrationView adminRegistrationView;

    public SecurityView(LoginController controller) {
        this.controller = controller;
        setTitle("Security Access");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("Security Access");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        titlePanel.add(titleLabel);
        mainPanel.add(titlePanel, BorderLayout.NORTH);

        JPanel pinDisplayPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pinDisplayLabel = new JLabel();
        pinDisplayLabel.setFont(new Font("Courier New", Font.BOLD, 24));
        pinDisplayPanel.add(pinDisplayLabel);
        mainPanel.add(pinDisplayPanel, BorderLayout.CENTER);

        JPanel pinPanel = new JPanel(new BorderLayout());
        pinField = new JTextField(10);
        pinField.setFont(new Font("Serif", Font.BOLD, 20));
        pinField.setHorizontalAlignment(JTextField.CENTER);
        pinField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        pinPanel.add(pinField, BorderLayout.CENTER);
        mainPanel.add(pinPanel, BorderLayout.SOUTH);

        JPanel numPadPanel = new JPanel(new GridLayout(4, 3, 10, 10));
        numPadPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        numButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numButtons[i] = new JButton(String.valueOf(i));
            numButtons[i].setFont(new Font("Serif", Font.BOLD, 20));
            numButtons[i].addActionListener(new NumButtonListener(i));
            numPadPanel.add(numButtons[i]);
        }

        JButton enterButton = new JButton("Enter");
        enterButton.setFont(new Font("Serif", Font.BOLD, 20));
        enterButton.addActionListener(new EnterButtonListener());

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(enterButton, BorderLayout.EAST);
        buttonPanel.add(numPadPanel, BorderLayout.CENTER);

        mainPanel.add(buttonPanel, BorderLayout.AFTER_LAST_LINE);

        add(mainPanel);
    }

    private class NumButtonListener implements ActionListener {
        private int num;

        public NumButtonListener(int num) {
            this.num = num;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            pinField.setText(pinField.getText() + num);
            updatePinDisplay();
        }
    }

    private class EnterButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String pin = pinField.getText();
            boolean isValid = controller.validateSecurityPin(pin);
            if (isValid) {
                adminRegistrationView = new AdminRegistrationView(controller);
                adminRegistrationView.setVisible(true);
                dispose();
            } else {
                attempts++;
                if (attempts < 3) {
                    showWarningDialog("Invalid pin. Attempts remaining: " + (3 - attempts));
                    pinField.setText("");
                    updatePinDisplay();
                } else {
                    showErrorDialog("Maximum attempts reached. Program will exit.");
                    System.exit(0);
                }
            }
        }
    }

    private void updatePinDisplay() {
        String pin = pinField.getText();
        String maskedPin = pin.replaceAll("\\d", "*");
        pinDisplayLabel.setText(maskedPin);
    }

    private void showWarningDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Access Denied", JOptionPane.WARNING_MESSAGE);
    }

    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Access Denied", JOptionPane.ERROR_MESSAGE);
    }
}