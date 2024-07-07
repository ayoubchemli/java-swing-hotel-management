package model;

import java.util.*;

// Represents a client extending Person
public class Client extends Person {
    
    private static int idCounter = 0; // Counter for generating unique client IDs
    private String clientId; // Unique client ID
    private List<Room> bookedRooms; // List of rooms booked by the client

    // Constructor
    public Client(String name, String phoneNumber, String email, String address, String username, String password) {
        super(name, phoneNumber, email, address, username, password);
        idCounter++;
        this.clientId = "C" + idCounter; // Generate client ID
        this.bookedRooms = new ArrayList<>(); // Initialize booked rooms list
    }

    // Getters and setters
    public String getClientId() { return clientId; }
    public void setClientId(String clientId) { this.clientId = clientId; }
    public List<Room> getBookedRooms() { return bookedRooms; }

    //*some methods */
    
    // Book a room and mark it as occupied
    public void bookRoom(Room room) {
        bookedRooms.add(room);
        room.setOccupancy(true);
    }

    // Cancel a booked room and mark it as available
    public void cancelRoom(Room room) {
        bookedRooms.remove(room);
        room.setOccupancy(false);
    }

    

    // Check if a room is booked by the client
    public boolean hasBookedRoom(Room room) {
        return bookedRooms.contains(room);
    }

    // Get the number of booked rooms
    public int getNumberOfBookedRooms() {
        return bookedRooms.size();
    }

    
}
