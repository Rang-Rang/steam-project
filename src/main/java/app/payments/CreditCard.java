package app.payments;

public class CreditCard extends PaymentMethod{
    private String cardNumber;
    private String expirationDate;

    public CreditCard(String methodId, String type, String cardNumber, String expirationDate) {
        super(methodId, type);
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
    }
    
    public boolean charge(double amount) {
        return true;
    }
}
