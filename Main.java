package main;

import java.util.Scanner;
import model.*;
import service.Hotel;
import exception.InvalidBookingException;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    private static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a whole number.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = Double.parseDouble(sc.nextLine().trim());
                if (Double.isFinite(value)) return value;
                System.out.println("Enter a valid finite number.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid amount.");
            }
        }
    }

    private static String readText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = sc.nextLine().trim();
            if (!value.isEmpty()) return value;
            System.out.println("This field cannot be empty.");
        }
    }

    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        int choice;

        System.out.println("======================================");
        System.out.println("       HOTEL RESERVATION SYSTEM");
        System.out.println("======================================");

        do {
            System.out.println("\n1. Add Room");
            System.out.println("2. Add Customer");
            System.out.println("3. Make Reservation");
            System.out.println("4. Cancel Reservation");
            System.out.println("5. Display Rooms");
            System.out.println("6. Exit");

            choice = readInt("Enter your choice: ");

            try {
                switch (choice) {
                    case 1: {
                        int roomNumber = readInt("Enter Room Number: ");
                        String roomType = readText("Enter Room Type (Single/Double/Deluxe): ");
                        double price = readDouble("Enter Price Per Night: Rs.");
                        hotel.addRoom(new Room(roomNumber, roomType, price));
                        break;
                    }
                    case 2: {
                        int customerId = readInt("Enter Customer ID: ");
                        String name = readText("Enter Customer Name: ");
                        String phone = readText("Enter 10-digit Phone Number: ");
                        String type = readText("Customer Type (Normal/Premium): ");

                        Customer customer;
                        if (type.equalsIgnoreCase("Premium")) {
                            customer = new PremiumCustomer(customerId, name, phone);
                        } else if (type.equalsIgnoreCase("Normal")) {
                            customer = new Customer(customerId, name, phone);
                        } else {
                            throw new IllegalArgumentException(
                                    "Customer type must be Normal or Premium.");
                        }
                        hotel.addCustomer(customer);
                        break;
                    }
                    case 3: {
                        int roomNumber = readInt("Enter Room Number: ");
                        int customerId = readInt("Enter Customer ID: ");
                        int nights = readInt("Enter Number of Nights: ");
                        String method = readText("Payment Method (Cash/UPI/Card): ");
                        hotel.makeReservation(roomNumber, customerId, nights, method);
                        break;
                    }
                    case 4: {
                        int bookingId = readInt("Enter Booking ID: ");
                        hotel.cancelReservation(bookingId);
                        break;
                    }
                    case 5:
                        hotel.displayRooms();
                        break;
                    case 6:
                        System.out.println("Thank you for using Hotel Reservation System!");
                        break;
                    default:
                        System.out.println("Invalid choice! Select a number from 1 to 6.");
                }
            } catch (InvalidBookingException | IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (choice != 6);

        sc.close();
    }
}
