package app.model.refunds;

public interface RefundProcessor {
    public boolean evaluateRefund(RefundRequester request);
}
