package app.resource;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StoreGUI extends Application {
@Override
public void start(Stage primaryStage) {
BorderPane root = new BorderPane();

            // NAVBAR
    HBox navbar = new HBox(20);
    navbar.setPadding(new Insets(10));
    navbar.setStyle("-fx-background-color: #1b2838;");
    navbar.setAlignment(Pos.CENTER_LEFT);

    Button cartBtn = new Button("Cart");
    Button libraryBtn = new Button("Library");
    Label profileLabel = new Label("orang hitam legam");
    Button logoutBtn = new Button("Logout");

    styleNavButton(cartBtn);
    styleNavButton(libraryBtn);
    styleNavLabel(profileLabel);
    styleNavButton(logoutBtn);

    Region spacer = new Region();
    HBox.setHgrow(spacer, Priority.ALWAYS);
    navbar.getChildren().addAll(cartBtn, libraryBtn, spacer, profileLabel, logoutBtn);
    root.setTop(navbar);

    // SCROLL CONTENT
    VBox scrollContent = new VBox(20);
    scrollContent.setPadding(new Insets(0));
    scrollContent.setStyle("-fx-background-color: #171a21;");

    // BANNER
    ImageView banner = new ImageView(new Image(getClass().getResource("/images/banner.png").toExternalForm()));
    banner.setPreserveRatio(true);
    banner.setFitWidth(1000); // default
    // Responsif: Bind lebar banner ke lebar scrollContent dikurangi padding
    banner.fitWidthProperty().bind(scrollContent.widthProperty().subtract(40));

    scrollContent.getChildren().add(banner);

    // GAME LIST
    VBox gameList = new VBox(15);
    gameList.setPadding(new Insets(20));
    gameList.setAlignment(Pos.TOP_LEFT);
    gameList.setMaxWidth(1000);

    gameList.getChildren().addAll(
        createGameItem("God of War", "/images/godofwar.png", "Rp659.000"),
        createGameItem("Ghost of Tsushima", "/images/tsushima.png", "Rp869.000"),
        createGameItem("Spider-Man Remastered", "/images/spiderman.png", "Rp859.000"),
        createGameItem("Clair Obscur: Expedition 33", "/images/expedition33.png", "Rp499.000")
    );

    scrollContent.getChildren().add(gameList);

    ScrollPane scrollPane = new ScrollPane(scrollContent);
    scrollPane.setFitToWidth(true);
    scrollPane.setStyle("-fx-background: #171a21;");

    root.setCenter(scrollPane);

    Scene scene = new Scene(root, 1000, 700);
    primaryStage.setTitle("Steam Store");
    primaryStage.setScene(scene);
    primaryStage.show();
}

private HBox createGameItem(String title, String imagePath, String price) {
    HBox box = new HBox(15);
    box.setAlignment(Pos.CENTER_LEFT);
    box.setPadding(new Insets(10));
    box.setStyle("-fx-background-color: #1b2838; -fx-background-radius: 8;");
    box.setMaxWidth(Double.MAX_VALUE);

    ImageView image = new ImageView(new Image(getClass().getResource(imagePath).toExternalForm()));
    image.setFitHeight(140);
    image.setPreserveRatio(true);
    image.setSmooth(true);

    VBox info = new VBox(5);
    Label titleLabel = new Label(title);
    titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
    Label priceLabel = new Label(price);
    priceLabel.setStyle("-fx-text-fill: lightgray;");

    Button addToCartBtn = new Button("Add to Cart");
    addToCartBtn.setStyle("-fx-background-color: #2a475e; -fx-text-fill: white;");
    addToCartBtn.setOnMouseEntered(e -> addToCartBtn.setStyle("-fx-background-color: #66c0f4; -fx-text-fill: black;"));
    addToCartBtn.setOnMouseExited(e -> addToCartBtn.setStyle("-fx-background-color: #2a475e; -fx-text-fill: white;"));

    info.getChildren().addAll(titleLabel, priceLabel, addToCartBtn);

    Region spacer = new Region();
    HBox.setHgrow(spacer, Priority.ALWAYS);

    box.getChildren().addAll(image, info, spacer);
    return box;
}

private void styleNavButton(Button button) {
    button.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 14px;");
    button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #2a475e; -fx-text-fill: white; -fx-font-size: 14px;"));
    button.setOnMouseExited(e -> button.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 14px;"));
}

private void styleNavLabel(Label label) {
    label.setStyle("-fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");
}

public static void main(String[] args) {
    launch(args);
}

}
