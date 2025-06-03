package app.resource;

import app.model.steam.Game;
import app.model.users.Customer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CheckoutGUI {
    public static void showCheckoutPage(Customer customer, Stage stage) {
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setStyle("-fx-background-color: #1b2838;");

        Label title = new Label("Checkout");
        title.setStyle("-fx-font-size: 28px; -fx-text-fill: white; -fx-font-weight: bold;");

        VBox gameList = new VBox(15);
        gameList.setPadding(new Insets(10));
        gameList.setAlignment(Pos.TOP_CENTER);

        for (Game game : customer.getCart()) {
            HBox gameItem = new HBox(15);
            gameItem.setPadding(new Insets(10));
            gameItem.setStyle("-fx-background-color: #171a21; -fx-background-radius: 8;");
            gameItem.setAlignment(Pos.CENTER_LEFT);

            ImageView imageView = new ImageView(new Image(CheckoutGUI.class.getClassLoader().getResource(game.getImagePath()).toExternalForm()));
            imageView.setFitHeight(100);
            imageView.setPreserveRatio(true);

            VBox infoBox = new VBox(5);
            Label titleLabel = new Label(game.getTitle());
            titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
            Label priceLabel = new Label(String.format("Rp%,.0f", game.getPrice()));
            priceLabel.setStyle("-fx-text-fill: #c7d5e0;");

            infoBox.getChildren().addAll(titleLabel, priceLabel);

            Region spacer = new Region();
            HBox.setHgrow(spacer, Priority.ALWAYS);

            gameItem.getChildren().addAll(imageView, infoBox, spacer);
            gameList.getChildren().add(gameItem);
        }

        Button confirmBtn = new Button("Confirm Purchase");
        confirmBtn.setStyle("-fx-background-color: #5c7e10; -fx-text-fill: white; -fx-font-size: 16px;");
        // buat tambah semua game
        confirmBtn.setOnAction(e -> {
            for (Game game : customer.getCart()) {
                if (!customer.getLibrary().contains(game)) {
                    customer.getLibrary().add(game);
                }
            }
            customer.getCart().clear();
            LibraryGUI.showLibrary(customer, stage);
        });

        Button backBtn = new Button("← Back to Cart");
        backBtn.setStyle("-fx-background-color: #2a475e; -fx-text-fill: white;");
        backBtn.setOnAction(e -> CartGUI.showCartPage(customer, stage));

        layout.getChildren().addAll(title, gameList, confirmBtn, backBtn);

        ScrollPane scrollPane = new ScrollPane(layout);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #1b2838;");
        stage.getScene().setRoot(scrollPane);
    }
}
