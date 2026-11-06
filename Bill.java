package model;

public class Bill {
    private double roomCharge;
    private double discount;
    private double tax;
    private double totalAmount;

    public Bill(double roomCharge, double discount) {
        if (roomCharge <= 0 || discount < 0 || discount > roomCharge) {
            throw new IllegalArgumentException("Invalid billing details!");
        }
        this.roomCharge = roomCharge;
        this.discount = discount;
        double taxableAmount = roomCharge - discount;
        this.tax = taxableAmount * 0.05; // GST: 5% demonstration rate
        this.totalAmount = taxableAmount + tax;
    }

    public double getTotalAmount() { return totalAmount; }

    public void displayBill() {
        System.out.println("\n========== HOTEL BILL ==========");
        System.out.printf("Room Charge       : Rs.%.2f%n", roomCharge);
        System.out.printf("Discount          : Rs.%.2f%n", discount);
        System.out.printf("GST (5%%)           : Rs.%.2f%n", tax);
        System.out.println("--------------------------------");
        System.out.printf("Total Amount      : Rs.%.2f%n", totalAmount);
        System.out.println("================================");
    }
}
