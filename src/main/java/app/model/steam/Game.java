package app.model.steam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Game implements Playable {
    private String gameId, title, imagePath, description;
    private double price;

    private static final ArrayList<Game> availableGames = new ArrayList<>(Arrays.asList(
        new Game("1", "God of Cry", 777_000, "images/godofwar.png", 
            "His vengeance against the Gods of Olympus years behind him," + 
            " Kratos now lives as a man in the realm of Norse Gods and monsters." + 
            " It is in this harsh, unforgiving world that he must fight to survive…" + 
            " and teach his son to do the same."
        ),
        
        new Game("2", "Ghost of Tsushima", 888_000, "images/tsushima.png",
            "A storm is coming. Forge your own path through this open-world action" + 
            " adventure and uncover its hidden wonders."
        ),

        new Game("3", "Spider-Man Remastered", 899_000, "images/spiderman.png",
            "In Marvel’s Spider-Man Remastered, the worlds of Peter Parker and " +
            "Spider-Man collide in an original action-packed story." +
            " Play as an experienced Peter Parker, " +
            "fighting big crime and iconic villains in Marvel’s New York. " +
            "Web-swing through vibrant neighborhoods and defeat villains with epic"
        ),
        
        new Game("4", "Clair Obscur: Expedition 33", 499_000, "images/expedition33.png", 
            "Lead the members of Expedition 33 on their quest to destroy the Paintress " +
            "so that she can never paint death again. Explore a world of wonders inspired " +
            "by Belle Époque France and battle unique enemies in this turn-based RPG with real-time mechanics."
        )
    ));

    public Game(String gameId, String title, double price, String imagePath, String desc) {
        this.gameId = gameId;
        this.title = title;
        this.price = price;
        this.imagePath = imagePath;
        this.description = desc;
    }

    public String getDescription() {
        return description;
    }

    public String getGameId() {
        return gameId;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getDetail() {
        return String.format("ID: %s\nTitle: %s\nPrice: Rp%,.0f", gameId, title, price);
    }

    public static ArrayList<Game> getAvailableGames() {
        return availableGames;
    }

    @Override
    public String download() {
        return "Downloading " + getTitle() + "...";
    }

    @Override
    public String play() {
        return "Playing " + getTitle();
    }

    @Override
    public String exit() {
        return "Exiting Game";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Game)) return false;
        Game other = (Game) obj;
        return Objects.equals(gameId, other.gameId);
    }
}
