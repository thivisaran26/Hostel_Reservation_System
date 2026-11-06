package model;

public class Customer {
    private int customerId;
    private String name;
    private String phoneNumber;

    public Customer(int customerId, String name, String phoneNumber) {
        if (customerId <= 0 || name == null || name.trim().isEmpty()
                || phoneNumber == null || !phoneNumber.matches("[0-9]{10}")) {
            throw new IllegalArgumentException(
                    "Invalid customer details! Use a name and a 10-digit phone number.");
        }
        this.customerId = customerId;
        this.name = name.trim();
        this.phoneNumber = phoneNumber;
    }

    public int getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getPhoneNumber() { return phoneNumber; }

    public void displayCustomer() {
        System.out.println("Customer ID: " + customerId);
        System.out.println("Name: " + name);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Customer Type: Regular");
    }
}
