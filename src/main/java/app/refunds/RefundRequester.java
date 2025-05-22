package app.refunds;

import app.steam.Game;

public interface RefundRequester {
    RefundRequest requestRefund(Game game);
}