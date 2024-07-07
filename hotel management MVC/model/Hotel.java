package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Represents a hotel
public class Hotel {
    
    private String name; // Name of the hotel
    private String location; // Location of the hotel
    private static List<Room> rooms = new ArrayList<>(); // List of rooms in the hotel
    private static List<Client> clients = new ArrayList<>(); // List of clients of the hotel
    private static List<Admin> admins = new ArrayList<>(); // List of admins of the hotel

    // Private constructor to prevent instantiation from outside
    private Hotel(String name, String location) {
        this.name = name;
        this.location = location;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public static List<Room> getRooms() { return rooms; }
    public static List<Client> getClients() { return clients; }
    public static List<Admin> getAdmins() { return admins; }



    //?**************************************************************************************************
    // Methods for manipulation of rooms
    public static void addRoom(Room room) { rooms.add(room); }

    public static void removeRoom(Room room) { rooms.remove(room); }

    public static Room searchRoom(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null; // Room not found
    }

    //defining a custom comparison logic for sorting Room objects based on their room numbers
    public static void sortRoomsByNumber() { Collections.sort(rooms, (r1, r2) -> r1.getRoomNumber() - r2.getRoomNumber()); }


    //?**************************************************************************************************
    // Methods for manipulation of clients
    public static void addClient(Client client) { clients.add(client); }

    public static void removeClient(Client client) { clients.remove(client); }

    public static Client searchClient(String clientId) {
        for (Client client : clients) {
            if (client.getClientId().equals(clientId)) {
                return client;
            }
        }
        return null; // Client not found
    }

    public static Client getClientByUsername(String username) {
        for (Client client : clients) {
            if (client.getUsername().equals(username)) {
                return client;
            }
        }
        return null; // Client not found
    }

    public static boolean clientExistsByUsername(String username) {
        for (Client client : clients) {
            if (client.getUsername().equals(username)) {
                return true; // Client found
            }
        }
        return false; // Client not found
    }

    public static void sortClientsByName() { Collections.sort(clients, (c1, c2) -> c1.getName().compareTo(c2.getName())); }



    //?**************************************************************************************************
    // Methods for manipulation of admins
    public static void addAdmin(Admin admin) { admins.add(admin); }

    public static void removeAdmin(Admin admin) { admins.remove(admin); }

    public static Admin searchAdmin(String adminId) {
        for (Admin admin : admins) {
            if (admin.getAdminId().equals(adminId)) {
                return admin;
            }
        }
        return null; // Admin not found
    }

    public static boolean adminExistsByUsername(String username) {
        for (Admin admin : admins) {
            if (admin.getUsername().equals(username)) {
                return true; // Admin found
            }
        }
        return false; // Admin not found
    }

    public static Admin getAdminByUsername(String username) {
        for (Admin admin : admins) {
            if (admin.getUsername().equals(username)) {
                return admin;
            }
        }
        return null; // Client not found
    }

    public static void sortAdminsByName() { Collections.sort(admins, (a1, a2) -> a1.getName().compareTo(a2.getName())); }
}
