package app.payments;

public class CreditCard extends PaymentMethod {
    private String cardNumber;
    private String expiryDate;

    public CreditCard(String methodId, String cardNumber, String expiryDate) {
        super(methodId, "CreditCard");
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
    }

    public boolean charge(double amount) {
        System.out.println("Charging " + amount + " to credit card: " + cardNumber);
        return true; 
    }

    @Override
    public boolean validate() {
        return cardNumber != null && cardNumber.length() == 16 && expiryDate.matches("\\d{2}/\\d{2}");
    }
}
