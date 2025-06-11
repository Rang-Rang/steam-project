package app.view;
import java.util.ArrayList;
import java.util.List;
import app.model.steam.Game;
import app.model.steam.Transaction;
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

        Button payButton = new Button("Bayar Sekarang");
        payButton.setStyle("-fx-background-color: #5c7e10; -fx-text-fill: white; -fx-font-size: 14px;");
        payButton.setOnAction(e -> {
        double totalAmount = 0;
        for (Game game : customer.getCart()) {
            totalAmount += game.getPrice();
        }

        boolean success = PaymentPopup.showPaymentDialog(customer, totalAmount);
        if (success) {
            List<Game> purchasedGames = new ArrayList<>(customer.getCart());

            for (Game game : purchasedGames) {
                customer.getLibrary().addGame(game);
                Transaction trx = new Transaction("TX" + System.currentTimeMillis(), game, customer, null);
            }

            customer.getCart().clear();

            // Menampilkan halaman invoice
            InvoiceGUI.showInvoice(customer, purchasedGames, totalAmount, stage);
        } else {
            Alert fail = new Alert(Alert.AlertType.ERROR);
            fail.setTitle("Gagal");
            fail.setHeaderText(null);
            fail.setContentText("Pembayaran gagal. Periksa kembali data kartu.");
            fail.showAndWait();
        }
        });

        Button backBtn = new Button("← Back to Cart");
        backBtn.setStyle("-fx-background-color: #2a475e; -fx-text-fill: white;");
        backBtn.setOnAction(e -> CartGUI.showCartPage(customer, stage));

        layout.getChildren().addAll(title, gameList, payButton, backBtn);

        ScrollPane scrollPane = new ScrollPane(layout);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #1b2838;");
        stage.getScene().setRoot(scrollPane);
    }
}
