package app.model.refunds;

import app.model.steam.Game;

public interface RefundRequester {
    public void requestRefund(Game game);
}
