package app.model.payments;

public class DanaPayment extends PaymentMethod{
    public DanaPayment(String methodId) {
        super(methodId, "Dana");
    }
    
    @Override
    public boolean validate() {
        return true; 
    }
}
