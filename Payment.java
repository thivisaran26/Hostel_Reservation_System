package model;

public class Payment {
    private int paymentId;
    private double amount;
    private String paymentMethod;
    private boolean paid;

    public Payment(int paymentId, double amount, String paymentMethod) {
        if (paymentId <= 0 || amount <= 0 || paymentMethod == null
                || paymentMethod.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid payment details!");
        }
        this.paymentId = paymentId;
        this.amount = amount;
        this.paymentMethod = paymentMethod.trim();
        this.paid = false;
    }

    public void processPayment() { paid = true; }
    public boolean isPaid() { return paid; }

    public void displayPayment() {
        System.out.println("Payment ID: " + paymentId);
        System.out.printf("Amount: Rs.%.2f%n", amount);
        System.out.println("Payment Method: " + paymentMethod);
        System.out.println("Payment Status: " + (paid ? "Successful (simulation)" : "Pending"));
    }
}
