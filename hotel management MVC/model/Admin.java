package model;

import java.util.*;

// Enum for admin Department
enum Department {
    FrontOffice,
    Housekeeping,
    FoodAndBeverage,
    Maintenance,
    SalesAndMarketing,
    FinanceAndAccounting,
    HumanResources,
    Security,
}

// Enum for the admin Role in department
enum Role {
    Administrator,
    Manager,
    Supervisor,
}


// Represents an admin extending Person
public class Admin extends Person {
    
    private static int idCounter = 0; // Counter for generating unique admin IDs
    private String adminId; // Unique admin ID
    private double salary; // Salary of the admin
    private String department; // Department of the admin
    private String role; // Role of the admin

    // Constructor
    public Admin(String name, String phoneNumber, String email, String address, String username, String password, String department, String role) {
        super(name, phoneNumber, email, address, username, password);
        idCounter++;
        this.adminId = "A" + idCounter; // Generate admin ID
        // Calculate salary
        this.department = department;
        this.role = role;
    }

    // Getters and setters
    public String getAdminId() { return adminId; }
    public void setAdminId(String adminId) { this.adminId = adminId; }
    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    //*  Other methods  */

    

    
}
