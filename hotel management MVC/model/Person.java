package model;

// Represents a generic person with basic attributes
public abstract class Person {
    
    private String name; // Person's name
    private String phoneNumber; // Person's phone number
    private String email; // Person's email address
    private String address; // Person's address
    private String username; // Person's username
    private String password; // Person's password

    // Constructor
    public Person(String name, String phoneNumber, String email, String address, String username, String password) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.username = username;
        this.password = password;
    }

    // Getters and setters
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}
