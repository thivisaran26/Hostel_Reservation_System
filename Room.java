package model;

public class Room {
    private int roomNumber;
    private String roomType;
    private double pricePerNight;
    private boolean available;

    public Room(int roomNumber, String roomType, double pricePerNight) {
        if (roomNumber <= 0 || pricePerNight <= 0 ||
                roomType == null || roomType.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid room details!");
        }
        this.roomNumber = roomNumber;
        this.roomType = roomType.trim();
        this.pricePerNight = pricePerNight;
        this.available = true;
    }

    public int getRoomNumber() { return roomNumber; }
    public String getRoomType() { return roomType; }
    public double getPricePerNight() { return pricePerNight; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    public void displayRoom() {
        System.out.println("Room Number: " + roomNumber);
        System.out.println("Room Type: " + roomType);
        System.out.printf("Price Per Night: Rs.%.2f%n", pricePerNight);
        System.out.println("Availability: " + (available ? "Available" : "Booked"));
    }
}
