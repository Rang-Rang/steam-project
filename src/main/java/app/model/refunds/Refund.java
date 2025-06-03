package app.model.refunds;

import app.model.steam.Game;

public class Refund {
    private Game game;
    private String userId;
    private String userName;
    private boolean approved;
    private String reason; // ✅ Tambahkan ini

    public Refund(Game game, String userId, String userName, String reason) {
        this.game = game;
        this.userId = userId;
        this.userName = userName;
        this.reason = reason; // ✅ Inisialisasi
        this.approved = false;
    }

    public Game getGame() {
        return game;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getReason() {
        return reason; // ✅ Getter yang dibutuhkan di GUI
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    private boolean rejected = false;

    public boolean isRejected() {
        return rejected;
    }

    public void setRejected(boolean rejected) {
        this.rejected = rejected;
    }

}
