package app.model.refunds;

import app.steam.Game;

public class RefundRequest {
    private String requestId;
    private Game game;
    private String userId;
    private String status;
    private String reason;
    
    public RefundRequest(String requestId, Game game, String userId, String status, String reason) {
        this.requestId = requestId;
        this.game = game;
        this.userId = userId;
        this.status = status;
        this.reason = reason;
    }

        public void submit() {
            this.status = "Terkirim";
            System.out.println("Permintaan pengembalian dana sudah terkirim oleh user: " + userId);
        }

        public String getStatus() {
            return status;
        }
}