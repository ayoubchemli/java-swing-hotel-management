package view.admin;

import model.Client;
import model.Hotel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controller.admin.ManageClientsController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ManageClientsView extends JFrame {
    private JTable clientTable;
    private DefaultTableModel tableModel;
    private JButton addClientButton;
    private JButton editClientButton;
    private JButton removeClientButton;
    private ManageClientsController controller;

    public ManageClientsView(ManageClientsController controller) {
        this.controller = controller;
        setTitle("Manage Clients");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the table model and client table
        tableModel = new DefaultTableModel(new Object[]{"Client ID", "Name", "Phone Number", "Email", "Address", "Username"}, 0);
        clientTable = new JTable(tableModel);
        clientTable.setDefaultEditor(Object.class, null); // Disable editing directly in the table

        // Populate the table with client data
        List<Client> clients = Hotel.getClients();
        for (Client client : clients) {
            Object[] row = {client.getClientId(), client.getName(), client.getPhoneNumber(), client.getEmail(), client.getAddress(), client.getUsername()};
            tableModel.addRow(row);
        }

        // Create a scroll pane and add the table to it
        JScrollPane scrollPane = new JScrollPane(clientTable);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Create buttons
        addClientButton = new JButton("Add Client");
        addClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.handleAddClient();
            }
        });

        editClientButton = new JButton("Edit Client");
        editClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.handleEditClient();
            }
        });

        removeClientButton = new JButton("Remove Client");
        removeClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.handleRemoveClient();
            }
        });

        // Create a button panel and add the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addClientButton);
        buttonPanel.add(editClientButton);
        buttonPanel.add(removeClientButton);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }


    

    public JTable getClientTable() {
        return clientTable;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }
}
