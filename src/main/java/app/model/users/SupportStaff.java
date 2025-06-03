package app.model.users;

import java.util.List;

import app.model.refunds.RefundProcessor;
import app.model.refunds.RefundRequester;

public class SupportStaff extends User implements RefundProcessor {

    private List<Customer> customers;

    public SupportStaff(String userId, String name, String email, String password, List<Customer> customers) {
        super(userId, name, email, password);
        this.customers = customers;
    }

    @Override
    public boolean evaluateRefund(RefundRequester request) {
        return false; // Logic evaluasi refund
    }

    public List<Customer> getCustomers() {
        return customers;
    }
}
