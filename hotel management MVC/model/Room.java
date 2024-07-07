package model;

import java.util.*;
import controller.application.MyDate;



// Represents a room in the hotel
public class Room {
    private int roomNumber; // Room number
    private RoomType roomType; // Type of room
    private List<RoomService> availableServices; // List of available room services
    private int occupancy; // Maximum occupancy of the room
    public int getOccupancy() {
        return occupancy;
    }

    private double ratePerNight; // Rate per night for the room
    private boolean isOccupied; // Flag indicating whether the room is occupied
    private Client currentClient; // Current client occupying the room
    private static int roomNumbers = 0; // Static variable to generate unique room numbers

    // Constructor
    public Room(RoomType roomType,int occupancy) {
        roomNumbers++;
        this.roomNumber = roomNumbers;
        this.roomType = roomType;
        this.occupancy = occupancy;
        this.isOccupied = false; // Initially, room is not occupied
        this.currentClient = null; // No client initially
        this.availableServices = new ArrayList<>(); // Initialize available services list
    }

    // Method to add a room service
    public void addRoomService(RoomService service) {
        availableServices.add(service);
        calculateRatePerNight(); // Recalculate ratePerNight after adding the service
    }

    // Method to remove a room service
    public void removeRoomService(RoomService service) {
        availableServices.remove(service);
        calculateRatePerNight(); // Recalculate ratePerNight after removing the service
    }

    // Getters and setters
    public int getRoomNumber() { return roomNumber; }
    public void setRoomNumber(int roomNumber) { this.roomNumber = roomNumber; }
    public int getoccupancy() { return occupancy; }
    public void setoccupancy(int occupancy) { this.occupancy = occupancy; }
    public double getRatePerNight() { return ratePerNight; }
    public void setRatePerNight(double ratePerNight) { this.ratePerNight = ratePerNight; }
    public boolean checkOccupancy() { return isOccupied; }
    public void setOccupancy(boolean occupied) { isOccupied = occupied; }
    public Client getCurrentClient() { return currentClient; }
    public void setCurrentClient(Client currentClient) { this.currentClient = currentClient; }
    public RoomType getRoomType() { return roomType; }
    public void setRoomType(RoomType roomType) { this.roomType = roomType; }
    public List<RoomService> getAvailableServices() {    return new ArrayList<>(availableServices);    }
    // Getters and setters
    public String getOccupancyString() { 
        if (occupancy == 1){
        return occupancy + " Person";    
        }
        else{
            return occupancy + " Persons";
        }
    }
    public String getRoomTypeString() {
        return roomType.name(); // Returns the name of the enum constant as a String
    }

    

    // Method to check-in a client to the room
    public void checkIn(Client client) {
        if (!isOccupied) {
            currentClient = client;
            isOccupied = true;
            System.out.println("Client " + client.getName() + " checked into Room " + roomNumber);
        } else {
            System.out.println("Room " + roomNumber + " is already occupied.");
        }
    }

    // Method to check-out the current client from the room
    public void checkOut() {
        if (isOccupied) {
            System.out.println("Client " + currentClient.getName() + " checked out from Room " + roomNumber);
            currentClient = null;
            isOccupied = false;
        } else {
            System.out.println("Room " + roomNumber + " is not occupied.");
        }
    }

    // Method to display room details
    public void displayRoomDetails() {
        System.out.println("Room Details:");
        System.out.println("Room Number: " + roomNumber);
        System.out.println("Room Type: " + roomType);
        System.out.println("Rate Per Night: $" + ratePerNight);
        System.out.println("Occupied: " + isOccupied);
        if (isOccupied) {
            System.out.println("Current Client: " + currentClient.getName());
        }
    }

    // Method to calculate ratePerNight based on RoomType and RoomService
    public void calculateRatePerNight() {
        double baseRate = 0.0;
        switch (roomType) {
            case SINGLE:
                baseRate = 100.0;
                break;
            case DOUBLE:
                baseRate = 150.0;
                break;
            case SUITE:
                baseRate = 200.0;
                break;
            case EXECUTIVE_SUITE:
                baseRate = 300.0;
                break;
            case DELUXE_ROOM:
                baseRate = 250.0;
                break;
            case FAMILY_ROOM:
                baseRate = 200.0;
                break;
            case PENTHOUSE_SUITE:
                baseRate = 500.0;
                break;
            case STANDARD_ROOM:
                baseRate = 80.0;
                break;
        }

        // Add additional charges for selected services
        for (RoomService service : availableServices) {
            switch (service) {
                case BREAKFAST:
                    baseRate += 20.0;
                    break;
                case LUNCH:
                    baseRate += 30.0;
                    break;
                case DINNER:
                    baseRate += 40.0;
                    break;
                case LAUNDRY:
                    baseRate += 15.0;
                    break;
                case CLEANING:
                    baseRate += 20.0;
                    break;
                case MASSAGE:
                    baseRate += 50.0;
                    break;
                case ROOM_SERVICE:
                    baseRate += 25.0;
                    break;
                case AIRPORT_SHUTTLE:
                    baseRate += 10.0;
                    break;
                case SPA:
                    baseRate += 60.0;
                    break;
                case CONCIERGE:
                    baseRate += 15.0;
                    break;
                case FITNESS_CENTER:
                    baseRate += 20.0;
                    break;
                case WIFI_ACCESS:
                    baseRate += 10.0;
                    break;
                case CAR_RENTAL:
                    baseRate += 35.0;
                    break;
            }
        }

        // Set the calculated ratePerNight
        ratePerNight = baseRate;
    }

    public String getDescription() {
        StringBuilder description = new StringBuilder();
        description.append("Room Number: ").append(roomNumber).append("\n")
                   .append("Room Type: ").append(roomType).append("\n")
                   .append("Occupancy: ").append(occupancy).append(" persons\n")
                   .append("Rate per Night: $").append(ratePerNight).append("\n")
                   .append("Status: ").append(isOccupied ? "Occupied" : "Available").append("\n")
                   .append("Available Services: ").append("\n");
                   
        for (RoomService service : availableServices) {
            description.append(" - ").append(service.getDescription()).append("\n");
        }
        
        return description.toString();
    }

    private MyDate checkInDate;
    public MyDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(MyDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    private MyDate checkOutDate;
    public MyDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(MyDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
}
