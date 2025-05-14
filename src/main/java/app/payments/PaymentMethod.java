package app.payments;

public abstract class PaymentMethod {
    private String methodId;
    private String type;

    public PaymentMethod(String methodId, String type) {
        this.methodId = methodId;
        this.type = type;
    }

    public boolean isValid() {
        return true;
        
    }
}
