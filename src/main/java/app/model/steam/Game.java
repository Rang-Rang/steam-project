    package app.model.steam;

    import java.util.ArrayList;
    import java.util.Arrays;
    import java.util.Objects;

    public class Game implements Playable{
        private String gameId, title;
        private double price;
        
    private String imagePath;
        // Static list of all available games
    // Static list of all available games
private static final ArrayList<Game> availableGames = new ArrayList<>(Arrays.asList(
    new Game("1", "God of Cry", 777_000, "images/godofwar.png"),
    new Game("2", "Ghost of Tsushima", 888_000, "images/tsushima.png"),
    new Game("3", "Spider-Man Remastered", 899_000, "images/spiderman.png"),
    new Game("4", "Clair Obscur: Expedition 33", 499_000, "images/expedition33.png")
));

public Game(String gameId, String title, double price, String imagePath) {
    this.gameId = gameId;
    this.title = title;
    this.price = price;
    this.imagePath = imagePath;
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
    return "exisiting game";
}

// Tambahan penting untuk membandingkan Game secara isi, bukan referensi
@Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof Game)) return false;
    Game other = (Game) obj;
    return Objects.equals(gameId, other.gameId);
}

@Override
public int hashCode() {
    return Objects.hash(gameId);
}


    }
