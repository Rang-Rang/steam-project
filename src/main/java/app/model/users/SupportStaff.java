package app.model.users;

import java.util.ArrayList;
import java.util.List;

public class SupportStaff extends User {
    private List<Customer> customers;
    // private static final List<SupportStaff> supportStaffs = new ArrayList<>();
    private static List<SupportStaff> allStaff = new ArrayList<>();

    public SupportStaff(String userId, String name, String email, String password, List<Customer> customers) {
        super(userId, name, email, password);
        this.customers = customers;
        // supportStaffs.add(this);
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public static SupportStaff findByCredential(String nameOrEmail, String pass) {
        for (SupportStaff s : allStaff) {
            if (s.isLoginMatch(nameOrEmail, pass)) {
                return s;
            }
        }
        return null;
    }
    
    static {
        allStaff.add(new SupportStaff("S001", "admin", "admin@email.com", "123", new ArrayList<>()));
    }


}
