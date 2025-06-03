package app.model.refunds;

import app.model.steam.Game;

public interface RefundRequester {
void requestRefund(Game game, String reason);
}
