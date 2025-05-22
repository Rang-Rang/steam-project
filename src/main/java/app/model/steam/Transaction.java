<<<<<<< HEAD:src/main/java/app/steam/Transaction.java
package app.steam;
import app.payments.*;
import app.users.*;
=======
package app.model.steam;

>>>>>>> c4d3518d23422da2bbeb692f55f13ca230b41739:src/main/java/app/model/steam/Transaction.java
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

