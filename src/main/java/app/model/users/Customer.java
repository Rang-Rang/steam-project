package app.model.users;

import java.util.ArrayList;

import app.model.refunds.RefundRequester;
import app.model.steam.Game;

public class Customer extends User implements RefundRequester{
    ArrayList<Game> library;
	ArrayList<Game> cart;
	
	public Customer(String userId, String name, String email, String password, ArrayList<Game> library,
			ArrayList<Game> cart) {
		super(userId, name, email, password);
		this.library = library;
		this.cart = cart;
	}

	public Customer(String userId, String name, String email, String password){
		super(userId, name, email, password);
	}
	
	public void buyGame(Game game) {
		
	}
	
	public void requestRefund(Game game) {
		
	}

	public ArrayList<Game> getLibrary() {
		return library;
	}

	public ArrayList<Game> getCart() {
		return cart;
	}
}
