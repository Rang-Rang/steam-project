package app.view;

import java.net.URL;

import app.model.steam.Game;
import app.model.steam.TrialGame;
import app.model.users.Customer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameDetailGUI {
    public static void showDetailWindow(Game game, Customer customer, Stage primaryStage) {
        VBox root = new VBox(25);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: #1b2838;");

        // Tombol Back to Store di kanan atas
        Button backBtn = new Button("< Back to Store");
        backBtn.setStyle("-fx-background-color: #34495e; -fx-text-fill: white;");
        backBtn.setOnAction(e -> {
            StoreGUI.showStore(primaryStage);
        });

        HBox topBar = new HBox();
        topBar.setAlignment(Pos.TOP_LEFT);
        topBar.getChildren().add(backBtn);

        // Konten utama
        VBox contentBox = new VBox(25);
        contentBox.setAlignment(Pos.TOP_CENTER);

        // Image
        ImageView imageView = new ImageView();
        URL imageUrl = GameDetailGUI.class.getClassLoader().getResource(game.getImagePath());
        if (imageUrl != null) {
            imageView.setImage(new Image(imageUrl.toExternalForm()));
            imageView.setFitWidth(520);
            imageView.setPreserveRatio(true);
        }

        // Title
        Label titleLabel = new Label(game.getTitle());
        titleLabel.setStyle("-fx-font-size: 32px; -fx-text-fill: white; -fx-font-weight: bold;");

        // Price
        Label priceLabel = new Label(String.format("Rp%,.0f", game.getPrice()));
        priceLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: #c7d5e0;");

        // Description
        Label descLabel = new Label("Description:\n" + game.getDescription());
        descLabel.setStyle("-fx-text-fill: #c7d5e0; -fx-font-size: 16px;");
        descLabel.setWrapText(true);
        descLabel.setMaxWidth(520);

        // Add to Cart Button
        Button addToCartBtn = new Button("Add to Cart");
        addToCartBtn.setPrefWidth(180);
        addToCartBtn.setStyle("-fx-background-color: #5c7e10; -fx-text-fill: white; -fx-font-size: 15px;");
        addToCartBtn.setOnAction(e -> {
            boolean alreadyOwnedFull = false;

            for (Game g : customer.getLibrary().displayLibrary()) {
                if (g.getTitle().equals(game.getTitle()) && !(g instanceof TrialGame)) {
                    alreadyOwnedFull = true;
                    break;
                }
            }

            if (alreadyOwnedFull) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Informasi");
                alert.setHeaderText(null);
                alert.setContentText("Game sudah ada di library Anda.");
                alert.showAndWait();
            } else {
                if (!customer.getCart().contains(game)) {
                    customer.getCart().add(game);
                }
                CartGUI.showCartPage(customer, primaryStage);
            }
        });

        // Play Trial Button
        Button trialBtn = new Button("Play Trial");
        trialBtn.setPrefWidth(180);
        trialBtn.setStyle("-fx-background-color: #2a475e; -fx-text-fill: white; -fx-font-size: 15px;");
        trialBtn.setOnAction(e -> {
            boolean alreadyOwned = false;

            for (Game g : customer.getLibrary().displayLibrary()) {
                if (g.getTitle().equals(game.getTitle())) {
                    alreadyOwned = true;
                    break;
                }
            }

            if (!alreadyOwned) {
                Game trialGame = new TrialGame(game.getGameId(), game.getTitle(), game.getPrice(), 30, game.getImagePath(), game.getDescription());
                customer.getLibrary().addGame(trialGame);
            }

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Trial Added");
            alert.setHeaderText(null);
            alert.setContentText("Trial version of \"" + game.getTitle() + "\" added to your library.");
            alert.showAndWait();

            LibraryGUI.showLibrary(customer, primaryStage);
        });

        HBox buttonBox = new HBox(20);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(addToCartBtn, trialBtn);

        contentBox.getChildren().addAll(imageView, titleLabel, priceLabel, descLabel, buttonBox);

        root.getChildren().addAll(topBar, contentBox);

        primaryStage.getScene().setRoot(root);
    }
}
