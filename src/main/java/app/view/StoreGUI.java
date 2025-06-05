package app.view;

import java.net.URL;
import java.util.ArrayList;

import app.model.steam.Game;
import app.model.steam.Library;
import app.model.users.Customer;
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
    private static BorderPane root;
    private static Customer currentCustomer;

    public static Customer getCurrentCustomer() {
        return currentCustomer;
    }

    @Override
    public void start(Stage primaryStage) {
        root = new BorderPane();
        currentCustomer = Customer.getCustomerById("C001");

        if (currentCustomer == null) {
            currentCustomer = new Customer("C001", "orang hitam legam", "test@email.com", "1234", new Library(), new ArrayList<>());
        }

        buildNavbar(primaryStage);
        showStoreContent();

        Scene scene = new Scene(root, 1000, 700);
        primaryStage.setTitle("Steam Store");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void buildNavbar(Stage stage) {
        HBox navbar = new HBox(20);
        navbar.setPadding(new Insets(10));
        navbar.setStyle("-fx-background-color: #1b2838;");
        navbar.setAlignment(Pos.CENTER_LEFT);

        Label profileLabel = new Label(currentCustomer.getName());
        styleNavLabel(profileLabel);

        Button cartBtn = new Button("Cart");
        Button libBtn = new Button("Library");
        Button logoutBtn = new Button("Logout");

        styleNavButton(cartBtn);
        styleNavButton(libBtn);
        styleNavButton(logoutBtn);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        navbar.getChildren().addAll(profileLabel, spacer, cartBtn, libBtn, logoutBtn);
        root.setTop(navbar);

        cartBtn.setOnAction(e -> CartGUI.showCartPage(currentCustomer, stage));

        libBtn.setOnAction(e -> {
            try {
                LibraryGUI.showLibrary(currentCustomer, stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        logoutBtn.setOnAction(e -> {
            Scene loginScene = new Scene(LoginGUI.getRoot(stage), 400, 350);
            stage.setScene(loginScene);
        });
    }

    private void showStoreContent() {
        VBox scrollContent = new VBox(20);
        scrollContent.setStyle("-fx-background-color: #171a21;");

        URL bannerURL = getClass().getClassLoader().getResource("images/banner.png");
        if (bannerURL != null) {
            Image bannerImg = new Image(bannerURL.toExternalForm());
            ImageView banner = new ImageView(bannerImg);
            banner.setPreserveRatio(true);
            banner.setFitWidth(1000); // Ukuran tetap
            scrollContent.getChildren().add(banner);
        }

        VBox gameList = new VBox(15);
        gameList.setPadding(new Insets(20));
        gameList.setAlignment(Pos.TOP_CENTER);

        for (Game game : Game.getAvailableGames()) {
            gameList.getChildren().add(createGameItem(game));
        }

        scrollContent.getChildren().add(gameList);

        ScrollPane scrollPane = new ScrollPane(scrollContent);
        scrollPane.setFitToWidth(false); // Tidak responsif
        scrollPane.setStyle("-fx-background: #171a21;");
        root.setCenter(scrollPane);
    }

    private HBox createGameItem(Game game) {
        HBox box = new HBox(15);
        box.setAlignment(Pos.CENTER_LEFT);
        box.setPadding(new Insets(10));
        box.setStyle("-fx-background-color: #1b2838; -fx-background-radius: 8;");
        box.setPrefWidth(960); // Ukuran tetap

        Image image = new Image(getClass().getClassLoader().getResource(game.getImagePath()).toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(140);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);

        VBox info = new VBox(5);
        Label titleLabel = new Label(game.getTitle());
        titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
        Label priceLabel = new Label(String.format("Rp%,.0f", game.getPrice()));
        priceLabel.setStyle("-fx-text-fill: lightgray;");

        Button detailBtn = new Button("Detail Game");
        detailBtn.setStyle("-fx-background-color: #2a475e; -fx-text-fill: white;");
        detailBtn.setOnMouseEntered(e -> detailBtn.setStyle("-fx-background-color: #66c0f4; -fx-text-fill: black;"));
        detailBtn.setOnMouseExited(e -> detailBtn.setStyle("-fx-background-color: #2a475e; -fx-text-fill: white;"));

        detailBtn.setOnAction(e -> GameDetailGUI.showDetailWindow(game, currentCustomer, (Stage) root.getScene().getWindow()));

        info.getChildren().addAll(titleLabel, priceLabel, detailBtn);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        box.getChildren().addAll(imageView, info, spacer);
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

    public static BorderPane getRoot() {
        return root;
    }

    public static void showStore(Stage stage) {
        try {
            new StoreGUI().start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
