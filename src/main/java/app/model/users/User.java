package app.model.users;

public abstract class User {
    private String userId;
    private String name;
    private String email;
    private String password;
	
	public User(String userId, String name, String email, String password) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public boolean isLoginMatch(String input, String inputPassword) {
	    return (this.name.equals(input) || this.email.equals(input)) && this.password.equals(inputPassword);
	}
    
    public void logout() {
    	
    }

	public String getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}
    
}
