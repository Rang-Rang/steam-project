package app.model.users;

import java.util.ArrayList;
import java.util.List;

import app.model.refunds.Refund;
import app.model.refunds.RefundRequester;
import app.model.steam.Game;

public class Customer extends User implements RefundRequester {
private List<Game> library;
private List<Game> cart;
private List<Refund> refundRequests = new ArrayList<>();

private static List<Customer> allCustomers = new ArrayList<>();

// Constructor
public Customer(String userId, String name, String email, String password, List<Game> library, List<Game> cart) {
    super(userId, name, email, password);
    this.library = library;
    this.cart = cart;
    allCustomers.add(this);
}

// Tambah permintaan refund jika belum ada yang aktif
public void addRefundRequest(Game game, String reason) {
    boolean alreadyRequested = refundRequests.stream()
            .anyMatch(r -> r.getGame().equals(game) && !r.isApproved());

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

// Hapus game dari library berdasarkan ID
public void removeFromLibrary(Game game) {
    library.removeIf(g -> g.getGameId().equals(game.getGameId()));
}

public List<Game> getLibrary() {
    return library;
}

public List<Game> getCart() {
    return cart;
}

@Override
public void requestRefund(Game game, String reason) {
    addRefundRequest(game, reason);
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

// Login verifikasi dengan nama/email + password
public static Customer findByCredential(String nameOrEmail, String pass) {
    for (Customer c : allCustomers) {
        if (c.isLoginMatch(nameOrEmail, pass)) {
            return c;
        }
    }
    return null;
}

// Dummy user untuk login ke store
static {
    allCustomers.add(new Customer(
            "C001", "user", "user@email.com", "123",
            new ArrayList<>(), new ArrayList<>()
    ));
}


}