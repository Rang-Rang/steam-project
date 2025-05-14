package app.payments;

public class Payment {
    private String paymentId;
    private double amount;
    private String status;
    private PaymentMethod paymentMethod;

    public Payment(String paymentId, double amount, String status, PaymentMethod paymentMethod) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.status = status;
        this.paymentMethod = paymentMethod;
    }

    public boolean processPayment() {
        if (paymentMethod.isValid()) {
            this.status = "Processed";
            return true;
        } else {
            this.status = "Failed";
            return false;
        }
    }
}
