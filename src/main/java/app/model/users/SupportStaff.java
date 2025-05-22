package app.model.users;

import app.model.refunds.RefundProcessor;
import app.model.refunds.RefundRequester;

public class SupportStaff extends User implements RefundProcessor {
	

	public SupportStaff(String userId, String name, String email, String password) {
		super(userId, name, email, password);
	}

	@Override
	public boolean evaluateRefund(RefundRequester request) {
		return false;
	}

}
