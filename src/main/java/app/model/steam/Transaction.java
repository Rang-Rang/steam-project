package app.model.steam;

import app.model.users.*;
import app.model.payments.*;

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

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Customer getUser() {
        return user;
    }

    public void setUser(Customer user) {
        this.user = user;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}

