package app.model.payments;

import java.util.ArrayList;

import app.model.steam.Game;

public class Payment {
    private String paymentId;
    private double amount;
    private String status;
    private PaymentMethod paymentMethod;
    private ArrayList<Game> purchasedGames = new ArrayList<Game>();
    private static int paymentCounter = 1;

    public Payment(double amount, PaymentMethod paymentMethod, ArrayList<Game> purchasedGames) {
        this.paymentId = generatePaymentId();
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = "PENDING";
        this.purchasedGames = purchasedGames;
    }

    public boolean process() {
        if (paymentMethod.validate()) {
            if (paymentMethod instanceof CreditCard) {
                boolean success = ((CreditCard) paymentMethod).charge(amount);
                status = success ? "SUCCESS" : "FAILED";
                return success;
            }
        }
        status = "FAILED";
        return false;
    }

    public String getStatus() {
        return status;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    private String generatePaymentId() {
        return String.format("PAY%03d", paymentCounter++); 
    }
}
