package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class HotelDatabase {
    // Method to populate the rooms list with all possible combinations
    public static void populateRoomsList() {
        // Clear the existing rooms list
        Hotel.getRooms().clear();

        // Create a list of all room types
        List<RoomType> roomTypes = new ArrayList<>();
        Collections.addAll(roomTypes, RoomType.values());

        // Create a list of all possible occupancies (from 1 to 4 persons)
        List<Integer> occupancies = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            occupancies.add(i);
        }

        // Create a list of all room services
        List<RoomService> allServices = new ArrayList<>();
        Collections.addAll(allServices, RoomService.values());

        Random random = new Random();

        // Generate rooms
        int roomNumber = 1;
        while (roomNumber <= 1500) { // Ensure rooms are generated until the maximum room number limit
            for (RoomType type : roomTypes) {
                for (int occupancy : occupancies) {
                    // Create a new room with the current type and occupancy
                    Room room = new Room(type, occupancy);

                    // Randomly select a subset of room services for the room
                    List<RoomService> selectedServices = new ArrayList<>();
                    int numServices = random.nextInt(RoomService.values().length) + 1; // Random number of services from 1 to all services
                    for (int i = 0; i < numServices; i++) {
                        RoomService service = allServices.get(random.nextInt(allServices.size()));
                        selectedServices.add(service);
                    }

                    // Add the selected services to the room
                    for (RoomService service : selectedServices) {
                        room.addRoomService(service);
                    }

                    System.out.println("Room " + roomNumber + " - " + type + " - " + occupancy + " person(s) - $" + room.getRatePerNight() + "/night" + " - Services: " + selectedServices);
                    // Add the room to the hotel's rooms list
                    Hotel.addRoom(room);

                    // Increment room number
                    roomNumber++;

                    // Check if maximum room number limit is reached
                    if (roomNumber > 1500) {
                        break; // Stop generating rooms
                    }
                }
                // Check if maximum room number limit is reached
                if (roomNumber > 1500) {
                    break; // Stop generating rooms
                }
            }
        }
    }
}
