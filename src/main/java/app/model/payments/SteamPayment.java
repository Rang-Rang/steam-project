package app.model.payments;

public class SteamPayment extends PaymentMethod {
    public SteamPayment(String methodId) {
        super(methodId, "SteamWallet");
    }
    
    @Override
    public boolean validate() {
        return true; 
    }
}
