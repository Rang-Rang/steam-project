package app.steam;

import java.util.ArrayList;

import app.payments.Payment;
import app.payments.PaymentMethod;

public class Cart {
    ArrayList<Game> cartList = new ArrayList<Game>();

    public Cart(ArrayList<Game> cartList) {
        this.cartList = cartList;
    }
    
    public void addGame(Game game){
        for (Game listGame : cartList) {
            if(listGame.equals(game)){
                System.out.println("Game Sudah Ada Dalam Cart.");
            }else{
                cartList.add(game);
            }
        }
    }

    public void removeGame(Game game){
        for (Game listGame : cartList) {
            if (listGame.equals(game)) {
                cartList.remove(game);
            }else{
                System.out.println("Game Tidak Ditemukan.");
            }
        }
    }

    public Payment checkout(PaymentMethod paymentMethod){
        int total = 0;
        for (int i = 0; i < cartList.size(); i++) {
            total += cartList.get(i).getPrice();
        }

        return new Payment(total, paymentMethod, cartList);
    }
}
