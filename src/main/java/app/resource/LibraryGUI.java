package app.resource;

import java.util.Optional;

import app.model.steam.Game;
import app.model.users.Customer;
import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LibraryGUI {

    public static void showLibrary(Customer customer, Stage stage) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #1b2838;");

        HBox navbar = new HBox(20);
        navbar.setPadding(new Insets(10));
        navbar.setStyle("-fx-background-color: #1b2838;");
        navbar.setAlignment(Pos.CENTER_LEFT);

        Label profileLabel = new Label(customer.getName());
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

        cartBtn.setOnAction(e -> CartGUI.showCartPage(customer, stage));
        libBtn.setOnAction(e -> {});
        logoutBtn.setOnAction(e -> {
            Scene loginScene = new Scene(LoginGUI.getRoot(stage), 400, 350);
            stage.setScene(loginScene);
        });

        TilePane tilePane = new TilePane();
        tilePane.setPadding(new Insets(20));
        tilePane.setHgap(20);
        tilePane.setVgap(20);
        tilePane.setPrefColumns(3);
        tilePane.setAlignment(Pos.TOP_CENTER);

        for (Game game : customer.getLibrary()) {
            VBox card = new VBox(10);
            card.setAlignment(Pos.CENTER);
            card.setPadding(new Insets(10));
            card.setStyle("-fx-background-color: #2a475e; -fx-background-radius: 10;");
            card.setPrefWidth(200);

            ImageView imageView = new ImageView();
            try {
                imageView.setImage(new Image(LibraryGUI.class.getResourceAsStream("/" + game.getImagePath())));
            } catch (Exception e) {
                imageView.setImage(new Image(LibraryGUI.class.getResourceAsStream("/images/placeholder.png")));
            }
            imageView.setFitWidth(160);
            imageView.setFitHeight(90);
            imageView.setPreserveRatio(true);

            Label title = new Label(game.getTitle());
            title.setStyle("-fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");
            title.setWrapText(true);
            title.setTextAlignment(TextAlignment.CENTER);

            Button downloadBtn = new Button("Download");
            downloadBtn.setStyle("-fx-background-color: #66c0f4; -fx-text-fill: black;");
            downloadBtn.setOnAction(ev -> {
                downloadBtn.setText("Downloading...");
                downloadBtn.setDisable(true);
                PauseTransition pause = new PauseTransition(Duration.seconds(2));
                pause.setOnFinished(f -> {
                    downloadBtn.setText("Download");
                    downloadBtn.setDisable(false);
                });
                pause.play();
            });

            Button refundBtn = new Button("Refund");
            refundBtn.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");
            refundBtn.setOnAction(ev -> {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Refund Request");
                dialog.setHeaderText("Enter the reason for refund:");
                dialog.setContentText("Reason:");

                Optional<String> result = dialog.showAndWait();
                result.ifPresent(reason -> {
                    customer.addRefundRequest(game, reason);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Refund Request");
                    alert.setHeaderText(null);
                    alert.setContentText("Refund request submitted and awaiting support approval.");
                    alert.showAndWait();
                });
            });

            card.getChildren().addAll(imageView, title, downloadBtn, refundBtn);
            tilePane.getChildren().add(card);
        }
        ScrollPane scrollPane = new ScrollPane(tilePane);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #1b2838;");
        root.setCenter(scrollPane);

        Scene scene = new Scene(root, 1000, 700);
        stage.setScene(scene);
        stage.setTitle("Steam Library");
    }

    private static void styleNavButton(Button button) {
        button.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 14px;");
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #2a475e; -fx-text-fill: white; -fx-font-size: 14px;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 14px;"));
    }

    private static void styleNavLabel(Label label) {
        label.setStyle("-fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");
    }
}
