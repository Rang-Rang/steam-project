package app.view;

import app.model.steam.Game;
import app.model.users.Customer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class InvoiceGUI {

    public static void showInvoice(Customer customer, List<Game> purchasedGames, double totalAmount, Stage stage) {
        VBox root = new VBox(20);
        root.setStyle("-fx-background-color: #1b2838; -fx-padding: 30;");
        root.setAlignment(Pos.TOP_LEFT);

        Label greeting = new Label("Thank you for your purchase!");
        greeting.setStyle("-fx-text-fill: #00bfff; -fx-font-size: 16px; -fx-font-weight: bold;");

        Label thankYou = new Label("Thank you for your recent transaction.");
        thankYou.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");

        Label info = new Label("The items below have been added to your library.");
        info.setStyle("-fx-text-fill: #aaaaaa; -fx-font-size: 12px;");

        VBox invoiceBox = new VBox(10);
        invoiceBox.setStyle("-fx-background-color: #2a2a2a; -fx-padding: 20;");
        invoiceBox.setAlignment(Pos.TOP_LEFT);

        for (Game game : purchasedGames) {
            HBox gameRow = new HBox(15);
            gameRow.setAlignment(Pos.CENTER_LEFT);

            ImageView gameImage = new ImageView(new Image(
                InvoiceGUI.class.getClassLoader().getResource(game.getImagePath()).toExternalForm()
            ));
            gameImage.setFitHeight(60);
            gameImage.setPreserveRatio(true);

            VBox gameInfo = new VBox(2);
            Label title = new Label(game.getTitle());
            title.setStyle("-fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");

            Label price = new Label(String.format("Rp%,.0f", game.getPrice()));
            price.setStyle("-fx-text-fill: #cccccc; -fx-font-size: 12px;");

            gameInfo.getChildren().addAll(title, price);
            gameRow.getChildren().addAll(gameImage, gameInfo);

            invoiceBox.getChildren().add(gameRow);
        }

        invoiceBox.getChildren().addAll(
            makeInfoRow("Invoice", "INV" + System.currentTimeMillis()),
            makeInfoRow("Date Issued", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy @ HH:mm"))),
            makeInfoRow("Payment Method", "Credit Card")
        );

        // Total
        HBox totalRow = new HBox();
        totalRow.setAlignment(Pos.CENTER_RIGHT);
        totalRow.setPadding(new Insets(10, 0, 0, 0));
        Label total = new Label("TOTAL  Rp" + String.format("%,.0f", totalAmount));
        total.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
        totalRow.getChildren().add(total);

        // Countdown Label
        Label countdownLabel = new Label();
        countdownLabel.setStyle("-fx-text-fill: #aaaaaa; -fx-font-size: 12px;");
        countdownLabel.setAlignment(Pos.CENTER);
        countdownLabel.setPadding(new Insets(20, 0, 0, 0));

        VBox content = new VBox(10, greeting, thankYou, info, invoiceBox, totalRow, countdownLabel);
        content.setPadding(new Insets(10));

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #1b2838;");

        stage.setScene(new Scene(scrollPane, 600, 500));

        // Start countdown
        startCountdown(stage, countdownLabel);
    }

    private static void startCountdown(Stage stage, Label label) {
        final int[] secondsLeft = {5};

        label.setText("Returning to home in " + secondsLeft[0] + " seconds...");

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            secondsLeft[0]--;
            if (secondsLeft[0] > 0) {
                label.setText("Returning to home in " + secondsLeft[0] + " seconds...");
            } else {
                StoreGUI.showStore(stage);
            }
        }));
        timeline.setCycleCount(5);
        timeline.play();
    }

    private static HBox makeInfoRow(String label, String value) {
        Label lbl = new Label(label + " :");
        lbl.setStyle("-fx-text-fill: #aaaaaa; -fx-font-size: 12px;");

        Label val = new Label(value);
        val.setStyle("-fx-text-fill: white; -fx-font-size: 12px;");

        HBox row = new HBox(10, lbl, val);
        row.setAlignment(Pos.CENTER_LEFT);
        return row;
    }
}
