package model;

// Enum representing different room services
public enum RoomService {
    BREAKFAST("Breakfast - Enjoy a delicious morning meal to start your day."),
    LUNCH("Lunch - Savor a midday meal with a variety of options."),
    DINNER("Dinner - Indulge in a sumptuous evening feast prepared by our chefs."),
    LAUNDRY("Laundry - Have your clothes cleaned and pressed for ultimate convenience."),
    CLEANING("Cleaning - Keep your room tidy and fresh with our thorough cleaning service."),
    MASSAGE("Massage - Relax and rejuvenate with a soothing massage session."),
    ROOM_SERVICE("Room Service - Order from our comprehensive room service menu for dining in the comfort of your room."),
    AIRPORT_SHUTTLE("Airport Shuttle - Arrive and depart hassle-free with our convenient airport shuttle service."),
    SPA("Spa - Pamper yourself with a range of spa treatments for complete relaxation."),
    CONCIERGE("Concierge - Our knowledgeable concierge staff is available to assist you with any requests or arrangements."),
    FITNESS_CENTER("Fitness Center - Stay active and energized with state-of-the-art fitness equipment."),
    WIFI_ACCESS("Wi-Fi Access - Stay connected with high-speed internet access throughout the hotel."),
    CAR_RENTAL("Car Rental - Explore the area at your own pace with our convenient car rental service.");

    private String description;

    RoomService(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}