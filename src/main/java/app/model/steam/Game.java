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

    public String getTitle(){
        return title;
    }

    public String download(){
        return "Downloading " + getTitle() + "...";
    }

    public String play(){
        return "Playing " + getTitle();
    }

    public String exit(){
        return "Exiting Game ";
    }

    public String getDetail(){
        return "";
    }
}
