package app.view;

import java.net.URL;

import app.model.steam.Game;
import app.model.users.Customer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle("-fx-background-color: #1b2838;");

        ImageView imageView = new ImageView();
        URL imageUrl = GameDetailGUI.class.getClassLoader().getResource(game.getImagePath());
        if (imageUrl != null) {
            imageView.setImage(new Image(imageUrl.toExternalForm()));
            imageView.setFitWidth(520);
            imageView.setPreserveRatio(true);
        }

        Label titleLabel = new Label(game.getTitle());
        titleLabel.setStyle("-fx-font-size: 32px; -fx-text-fill: white; -fx-font-weight: bold;");

        Label priceLabel = new Label(String.format("Rp%,.0f", game.getPrice()));
        priceLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: #c7d5e0;");

        Button addToCartBtn = new Button("Add to Cart");
        addToCartBtn.setPrefWidth(180);
        addToCartBtn.setStyle("-fx-background-color: #5c7e10; -fx-text-fill: white; -fx-font-size: 15px;");
        addToCartBtn.setOnAction(e -> {
            if (!customer.getCart().contains(game)) {
                customer.getCart().add(game);
            }
            CartGUI.showCartPage(customer, primaryStage);
        });

        Button trialBtn = new Button("Play Trial");
        trialBtn.setPrefWidth(180);
        trialBtn.setStyle("-fx-background-color: #2a475e; -fx-text-fill: white; -fx-font-size: 15px;");
        trialBtn.setOnAction(e -> {
            TrialGameGUI.showTrialPage(game, primaryStage);
        });

        HBox buttonBox = new HBox(20);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(addToCartBtn, trialBtn);

        root.getChildren().addAll(imageView, titleLabel, priceLabel, buttonBox);

        primaryStage.getScene().setRoot(root);
    }
}
