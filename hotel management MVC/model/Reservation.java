package model;

import java.util.*;

// Represents a reservation made by a client
public class Reservation {
    
    private static int nextReservationId = 1; // Static variable to track the next reservation ID
    
    private int reservationId; // Unique reservation ID
    private int clientId; // ID of the client making the reservation
    private Room room; // Room being reserved
    private Date checkInDate; // Date of check-in for the reservation
    private Date checkOutDate; // Date of check-out for the reservation

    // Constructor
    public Reservation(int clientId, Room room, Date checkInDate, Date checkOutDate) {
        this.reservationId = nextReservationId++; // Assign and increment reservation ID
        this.clientId = clientId; // Assign client ID
        this.room = room; // Assign room
        this.checkInDate = checkInDate; // Assign check-in date
        this.checkOutDate = checkOutDate; // Assign check-out date
    }

    // Getters and setters
    public int getReservationId() { return reservationId; } // Get reservation ID
    public int getClientId() { return clientId; } // Get client ID
    public Room getRoom() { return room; } // Get reserved room
    public Date getCheckInDate() { return checkInDate; } // Get check-in date
    public void setCheckInDate(Date checkInDate) { this.checkInDate = checkInDate; } // Set check-in date
    public Date getCheckOutDate() { return checkOutDate; } // Get check-out date
    public void setCheckOutDate(Date checkOutDate) { this.checkOutDate = checkOutDate; } // Set check-out date
}
