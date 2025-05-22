package app.model.steam;

import java.util.*;

public class Library {
    ArrayList<Game> ownedGames = new ArrayList<Game>();

    public void addGame(Game game){
        if(isOwned(game)){
            System.out.println("Game Sudah Dimiliki.");
        }else{
            ownedGames.add(game);
        }
    }

    public void removeGame(Game game){
        for (Game listGame : ownedGames) {
            if (listGame.equals(game)) {
                ownedGames.remove(game);
            }else{
                System.out.println("Game Tidak Ditemukan.");
            }
        }
    }

    public boolean isOwned(Game game){
        for (Game listGame : ownedGames) {
            if(listGame.equals(game)){
                return true;
            }
        }
        return false;
    }

    public void displayLibrary(){
        
    }
}
