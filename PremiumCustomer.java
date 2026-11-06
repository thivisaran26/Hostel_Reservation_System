package model;

public class PremiumCustomer extends Customer {
    private final double discountRate;
    private int loyaltyPoints;

    public PremiumCustomer(int customerId, String name, String phoneNumber) {
        super(customerId, name, phoneNumber);
        this.discountRate = 10.0;
        this.loyaltyPoints = 0;
    }

    public double getDiscountRate() { return discountRate; }
    public int getLoyaltyPoints() { return loyaltyPoints; }

    public void addLoyaltyPoints(int points) {
        if (points > 0) loyaltyPoints += points;
    }

    @Override
    public void displayCustomer() {
        super.displayCustomer();
        System.out.println("Customer Type: Premium");
        System.out.println("Discount: " + discountRate + "%");
        System.out.println("Loyalty Points: " + loyaltyPoints);
    }
}
