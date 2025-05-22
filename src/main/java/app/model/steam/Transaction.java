package app.model.steam;

<<<<<<< HEAD
import app.model.payments.*;
import app.model.users.*;
=======
import app.model.users.*;
import app.model.payments.*;
>>>>>>> e49aaed2b324de8c84e3f814f46d89b70000db61

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

<<<<<<< HEAD
    public String generateReceipt(){
        return "";
    }   
=======
    public String getTransactionId() {
        return transactionId;
    }

    public Game getGame() {
        return game;
    }

    public Customer getUser() {
        return user;
    }

    public Payment getPayment() {
        return payment;
    }
>>>>>>> e49aaed2b324de8c84e3f814f46d89b70000db61
}

