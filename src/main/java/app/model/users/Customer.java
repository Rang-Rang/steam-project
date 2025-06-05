package app.model.users;

import java.util.ArrayList;
import java.util.List;

import app.model.refunds.Refund;
import app.model.refunds.RefundRequester;
import app.model.steam.Game;
import app.model.steam.Library;

public class Customer extends User implements RefundRequester {
    private Library library;
    private List<Game> cart;
    private List<Refund> refundRequests = new ArrayList<>();
    private static List<Customer> allCustomers = new ArrayList<>();

    public Customer(String userId, String name, String email, String password, Library library, List<Game> cart) {
        super(userId, name, email, password);
        this.library = library;
        this.cart = cart;
        allCustomers.add(this);
    }

    public void addRefundRequest(Game game, String reason) {
        boolean alreadyRequested = refundRequests.stream()
                .anyMatch(r -> r.getGame().equals(game) && !r.isApproved() && !r.isRejected());

        if (!alreadyRequested) {
            Refund refund = new Refund(game, this.getUserId(), this.getName(), reason);
            refundRequests.add(refund);
        }
    }

    public void removeRefundRequest(Refund refund) {
        refundRequests.remove(refund);
    }

    public List<Refund> getRefundRequests() {
        return refundRequests;
    }

    public void removeFromLibrary(Game game) {
        library.removeGame(game);
    }

    public Library getLibrary() {
        return library;
    }

    public boolean ownsGame(Game game) {
        return library.isOwned(game);
    }

    @Override
    public void requestRefund(Game game, String reason) {
        addRefundRequest(game, reason);
    }

    public List<Game> getCart() {
        return cart;
    }

    public static List<Customer> getAllCustomers() {
        return allCustomers;
    }

    public static Customer getCustomerById(String id) {
        for (Customer c : allCustomers) {
            if (c.getUserId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    public static Customer findByCredential(String nameOrEmail, String pass) {
        for (Customer c : allCustomers) {
            if (c.isLoginMatch(nameOrEmail, pass)) {
                return c;
            }
        }
        return null;
    }

    static {
        allCustomers.add(new Customer(
            "C001",
            "user",
            "user@email.com",
            "123",
            new Library(),
            new ArrayList<>()
        ));
    }
}
