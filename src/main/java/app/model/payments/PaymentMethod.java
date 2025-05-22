package app.model.payments;

public abstract class PaymentMethod {
    protected String methodId;
    protected String type;

    public PaymentMethod(String methodId, String type) {
        this.methodId = methodId;
        this.type = type;
    }

    public String getMethodId() {
        return methodId;
    }

    public String getType() {
        return type;
    }

    public abstract boolean validate();
}
