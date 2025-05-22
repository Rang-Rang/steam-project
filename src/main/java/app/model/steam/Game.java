package app.model.steam;

public class Game implements Playable{
    private String gameId, title;
    private double price;
    
    public Game(String gameId, String title, double price) {
        this.gameId = gameId;
        this.title = title;
        this.price = price;
    }

    public String getGameId() {
        return gameId;
    }



    public double getPrice() {
        return price;
    }

    public void download(){

    }

    public void play(){

    }

    public void exit(){

    }

    public String getDetail(){
        return "";
    }
}
