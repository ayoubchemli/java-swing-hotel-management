# Hotel Management System

## Overview
This Java-based Hotel Management System is designed to streamline hotel operations, providing separate interfaces for administrators and clients. The application follows the Model-View-Controller (MVC) architecture, ensuring a clear separation of concerns and maintainable code structure.

## Features

### For Administrators
- Dashboard for managing hotel operations
- Room management (add, update, delete rooms)
- Client management
- Reservation oversight
- Staff management (different roles and departments)

### For Clients
- User-friendly dashboard
- Room browsing and booking
- Reservation management
- Profile management

## Project Structure

The project is organized into four main packages:

1. **Model**: Contains the core business logic and data structures
   - `Admin.java`: Represents hotel staff with various roles and departments
   - `Client.java`: Represents hotel guests with booking capabilities
   - Other entity classes (Room, Reservation, etc.)

2. **View**: User interface components
   - `admin`: Admin-specific views
   - `client`: Client-specific views
   - `application`: Shared application views

3. **Controller**: Handles user input and manages interactions between Model and View

4. **Application**: Contains main application logic and entry points

## Key Classes

### Admin
- Extends the `Person` class
- Includes properties like adminId, salary, department, and role
- Departments include FrontOffice, Housekeeping, FoodAndBeverage, etc.
- Roles include Administrator, Manager, and Supervisor

### Client
- Extends the `Person` class
- Manages room bookings with methods to book, cancel, and check reservations

## Technology Stack
- Java
- Swing for GUI components

