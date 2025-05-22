package app.steam;
import app.payments.*;
import app.users.*;
public class Transaction {
    private String transactionId;
    private Game game;
    private Customer user;
    private Payment payment;

    public Transaction(String transactionId, Game game, Customer user, Payment payment) {
        this.transactionId = transactionId;
        this.game = game;
        this.user = user;
        this.payment = payment;
    }

    public String generateReceipt(){
        return"hi";
    }
}

