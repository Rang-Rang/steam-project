package app.model.refunds;
import java.util.List;

import app.model.steam.Game;

public interface RefundRequester {
public void requestRefund(Game game, String reason);
public void addRefundRequest(Game game, String reason);
public void removeRefundRequest(Refund refund);
public List<Refund> getRefundRequests();
}
