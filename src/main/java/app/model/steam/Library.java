package app.model.steam;

import java.util.*;

public class Library {
    ArrayList<Game> ownedGames;

    public Library() {
        ownedGames = new ArrayList<Game>();
    }

    public void addGame(Game game){
        ownedGames.add(game);
    }

    public void removeGame(Game game){
        ownedGames.remove(game);
    }

    public boolean isOwned(Game game){
        for (Game listGame : ownedGames) {
            if(listGame.equals(game)){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Game> displayLibrary(){
        return ownedGames;
    }
}
