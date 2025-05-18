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
        if (cardNumber == null || cardNumber.length() != 16) return false;
        for (char c : cardNumber.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }

        if (expiryDate == null || expiryDate.length() != 5 || expiryDate.charAt(2) != '/') return false;
        try {
            int month = Integer.parseInt(expiryDate.substring(0, 2));
            Integer.parseInt(expiryDate.substring(3)); // cek YY
            if (month < 1 || month > 12) return false;
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
}
