package app.view;

import app.model.steam.Game;
import app.model.users.Customer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
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

public class CartGUI {
    public static void showCartPage(Customer customer, Stage stage) {
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setStyle("-fx-background-color: #1b2838;");

        Label title = new Label("Your Cart");
        title.setStyle("-fx-font-size: 28px; -fx-text-fill: white; -fx-font-weight: bold;");

        VBox gameList = new VBox(15);
        gameList.setPadding(new Insets(10));
        gameList.setAlignment(Pos.TOP_CENTER);

        if (customer.getCart().isEmpty()) {
            Label emptyLabel = new Label("Your cart is empty.");
            emptyLabel.setStyle("-fx-text-fill: #c7d5e0; -fx-font-size: 16px;");
            gameList.getChildren().add(emptyLabel);
        } else {
            for (Game game : customer.getCart()) {
                HBox gameItem = new HBox(15);
                gameItem.setPadding(new Insets(10));
                gameItem.setStyle("-fx-background-color: #171a21; -fx-background-radius: 8;");
                gameItem.setAlignment(Pos.CENTER_LEFT);

                ImageView imageView = new ImageView(new Image(
                        CartGUI.class.getClassLoader().getResource(game.getImagePath()).toExternalForm()));
                imageView.setFitHeight(100);
                imageView.setPreserveRatio(true);

                VBox infoBox = new VBox(5);
                Label titleLabel = new Label(game.getTitle());
                titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
                Label priceLabel = new Label(String.format("Rp%,.0f", game.getPrice()));
                priceLabel.setStyle("-fx-text-fill: #c7d5e0;");

                Button removeBtn = new Button("Remove");
                removeBtn.setStyle("-fx-background-color: #c0392b; -fx-text-fill: white;");
                // buat hapus game dari cart
                removeBtn.setOnAction(e -> {
                    customer.getCart().remove(game);
                    showCartPage(customer, stage);
                });

                infoBox.getChildren().addAll(titleLabel, priceLabel, removeBtn);

                Region spacer = new Region();
                HBox.setHgrow(spacer, Priority.ALWAYS);

                gameItem.getChildren().addAll(imageView, infoBox, spacer);
                gameList.getChildren().add(gameItem);
            }
        }

        HBox buttonBox = new HBox(15);
        buttonBox.setAlignment(Pos.CENTER);

        Button checkoutBtn = new Button("Checkout");
        checkoutBtn.setStyle("-fx-background-color: #5c7e10; -fx-text-fill: white; -fx-font-size: 14px;");
        checkoutBtn.setOnAction(e -> {
            if (customer.getCart().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Peringatan");
                    alert.setHeaderText(null);
                    alert.setContentText("Keranjang kosong. Tambahkan game terlebih dahulu.");
                    alert.showAndWait();
            } else {
                CheckoutGUI.showCheckoutPage(customer, stage);
            }
        });

        Button backBtn = new Button("← Back to Store");
        backBtn.setStyle("-fx-background-color: #2a475e; -fx-text-fill: white;");
        backBtn.setOnAction(e -> {
            StoreGUI.showStore(stage); 
        });

        buttonBox.getChildren().addAll(checkoutBtn, backBtn);

        layout.getChildren().addAll(title, gameList, buttonBox);

        ScrollPane scrollPane = new ScrollPane(layout);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #1b2838;");
        scrollPane.setFitToHeight(true); 

        stage.getScene().setRoot(scrollPane);
    }

}
