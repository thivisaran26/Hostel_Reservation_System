package model;

public class Booking {
    private int bookingId;
    private Room room;
    private Customer customer;
    private int numberOfNights;
    private boolean active;
    private Payment payment;

    public Booking(int bookingId, Room room, Customer customer, int numberOfNights) {
        if (bookingId <= 0 || room == null || customer == null
                || numberOfNights <= 0 || !room.isAvailable()) {
            throw new IllegalArgumentException("Invalid booking details or room unavailable!");
        }
        this.bookingId = bookingId;
        this.room = room;
        this.customer = customer;
        this.numberOfNights = numberOfNights;
        this.active = true;
        room.setAvailable(false);
    }

    public int getBookingId() { return bookingId; }
    public Room getRoom() { return room; }
    public Customer getCustomer() { return customer; }
    public int getNumberOfNights() { return numberOfNights; }
    public boolean isActive() { return active; }
    public void setPayment(Payment payment) { this.payment = payment; }

    public void cancelBooking() {
        if (active) {
            active = false;
            room.setAvailable(true);
        }
    }

    public void displayBooking() {
        System.out.println("\n========== BOOKING DETAILS ==========");
        System.out.println("Booking ID: " + bookingId);
        System.out.println("Customer: " + customer.getName());
        System.out.println("Room Number: " + room.getRoomNumber());
        System.out.println("Number of Nights: " + numberOfNights);
        System.out.println("Booking Status: " + (active ? "Confirmed" : "Cancelled"));
        if (payment != null) payment.displayPayment();
    }
}
