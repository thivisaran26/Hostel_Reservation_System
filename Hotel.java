package service;

import java.util.ArrayList;
import model.*;
import exception.InvalidBookingException;

public class Hotel {
    private ArrayList<Room> rooms = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Booking> bookings = new ArrayList<>();
    private int nextBookingId = 1;
    private int nextPaymentId = 1;

    public void addRoom(Room room) {
        for (Room existing : rooms) {
            if (existing.getRoomNumber() == room.getRoomNumber()) {
                throw new IllegalArgumentException("Room number already exists!");
            }
        }
        rooms.add(room);
        System.out.println("Room added successfully!");
    }

    public void addCustomer(Customer customer) {
        for (Customer existing : customers) {
            if (existing.getCustomerId() == customer.getCustomerId()) {
                throw new IllegalArgumentException("Customer ID already exists!");
            }
        }
        customers.add(customer);
        System.out.println("Customer added successfully!");
    }

    private Room findRoom(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) return room;
        }
        return null;
    }

    private Customer findCustomer(int customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId() == customerId) return customer;
        }
        return null;
    }

    public void makeReservation(int roomNumber, int customerId, int nights,
                                String paymentMethod) throws InvalidBookingException {
        Room room = findRoom(roomNumber);
        Customer customer = findCustomer(customerId);

        if (room == null) throw new InvalidBookingException("Room not found!");
        if (customer == null) throw new InvalidBookingException("Customer not found!");
        if (nights <= 0) throw new InvalidBookingException("Number of nights must be positive!");
        if (!room.isAvailable()) throw new InvalidBookingException("Room is already booked!");

        double roomCharge = room.getPricePerNight() * nights;
        double discount = 0.0;

        if (customer instanceof PremiumCustomer) {
            PremiumCustomer premium = (PremiumCustomer) customer;
            discount = roomCharge * premium.getDiscountRate() / 100.0;
        }

        Bill bill = new Bill(roomCharge, discount);
        Payment payment = new Payment(nextPaymentId, bill.getTotalAmount(), paymentMethod);
        payment.processPayment();

        Booking booking = new Booking(nextBookingId, room, customer, nights);
        booking.setPayment(payment);
        bookings.add(booking);

        nextBookingId++;
        nextPaymentId++;

        if (customer instanceof PremiumCustomer) {
            PremiumCustomer premium = (PremiumCustomer) customer;
            premium.addLoyaltyPoints((int) (bill.getTotalAmount() / 100));
        }

        System.out.println("\n*** RESERVATION CONFIRMED ***");
        booking.displayBooking();
        bill.displayBill();
        System.out.println("Room availability updated successfully!");
    }

    public void cancelReservation(int bookingId) throws InvalidBookingException {
        for (Booking booking : bookings) {
            if (booking.getBookingId() == bookingId && booking.isActive()) {
                booking.cancelBooking();
                System.out.println("Reservation cancelled successfully!");
                System.out.println("Room " + booking.getRoom().getRoomNumber()
                        + " is now available.");
                return;
            }
        }
        throw new InvalidBookingException("Active booking not found!");
    }

    public void displayRooms() {
        if (rooms.isEmpty()) {
            System.out.println("No rooms have been added yet.");
            return;
        }
        System.out.println("\n========== ROOM DETAILS ==========");
        for (Room room : rooms) {
            room.displayRoom();
            System.out.println("----------------------------------");
        }
    }
}
