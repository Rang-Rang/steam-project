package app.payments;

public class Payment {
    private String paymentId;
    private double amount;
    private String status;
    private PaymentMethod paymentMethod;

    public Payment(String paymentId, double amount, PaymentMethod paymentMethod) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = "PENDING";
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
}