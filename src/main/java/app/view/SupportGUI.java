package app.view;

import java.util.ArrayList;
import java.util.List;

import app.model.refunds.Refund;
import app.model.steam.Game;
import app.model.users.Customer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SupportGUI {

    public static void showSupportDashboard(Stage stage) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #1b2838;");

        HBox navbar = new HBox(20);
        navbar.setPadding(new Insets(10));
        navbar.setStyle("-fx-background-color: #1b2838;");
        navbar.setAlignment(Pos.CENTER_LEFT);

        Label supportLabel = new Label("Support");
        styleNavLabel(supportLabel);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button logoutBtn = new Button("Logout");
        styleNavButton(logoutBtn);
        logoutBtn.setOnAction(e -> {
            Scene loginScene = new Scene(LoginGUI.getRoot(stage), 400, 350);
            stage.setScene(loginScene);
        });

        navbar.getChildren().addAll(supportLabel, spacer, logoutBtn);
        root.setTop(navbar);

        VBox contentBox = new VBox(20);
        contentBox.setPadding(new Insets(20));
        contentBox.setAlignment(Pos.TOP_CENTER);

        Label title = new Label("Support Dashboard - Refund Requests");
        title.setFont(new Font("Arial", 24));
        title.setTextFill(Color.WHITE);
        contentBox.getChildren().add(title);

        List<Customer> customers = Customer.getAllCustomers();

        for (Customer customer : customers) {
            for (Refund refund : new ArrayList<>(customer.getRefundRequests())) {
                if (refund.isApproved() || refund.isRejected()) continue;

                Game game = refund.getGame();

                HBox row = new HBox(15);
                row.setPadding(new Insets(15));
                row.setAlignment(Pos.CENTER_LEFT);
                row.setStyle("-fx-background-color: #2a475e; -fx-background-radius: 10;");
                row.setPrefWidth(700);

                VBox infoBox = new VBox(5);
                Label userLabel = new Label("User: " + refund.getUserName());
                Label gameLabel = new Label("Game: " + game.getTitle());
                Label reasonLabel = new Label("Reason: " + refund.getReason());

                for (Label lbl : List.of(userLabel, gameLabel, reasonLabel)) {
                    lbl.setTextFill(Color.WHITE);
                    lbl.setFont(new Font(14));
                }

                infoBox.getChildren().addAll(userLabel, gameLabel, reasonLabel);

                Button approveBtn = new Button("Approve");
                approveBtn.setStyle("-fx-background-color: #66c0f4; -fx-text-fill: black;");
                approveBtn.setOnAction(e -> {
                refund.setApproved(true);
                customer.removeFromLibrary(game); 
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Refund Approved");
                alert.setHeaderText(null);
                alert.setContentText("Refund approved and game removed from library.");
                alert.showAndWait();
                showSupportDashboard(stage); 
                });

                Button rejectBtn = new Button("Reject");
                rejectBtn.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");
                rejectBtn.setOnAction(e -> {
                    refund.setRejected(true);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Refund Rejected");
                    alert.setHeaderText(null);
                    alert.setContentText("Refund rejected. Game remains in the library.");
                    alert.showAndWait();
                    showSupportDashboard(stage);
                });

                Region rowSpacer = new Region();
                HBox.setHgrow(rowSpacer, Priority.ALWAYS);

                row.getChildren().addAll(infoBox, rowSpacer, approveBtn, rejectBtn);
                contentBox.getChildren().add(row);
            }
        }

        ScrollPane scrollPane = new ScrollPane(contentBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #1b2838;");
        root.setCenter(scrollPane);

        Scene scene = new Scene(root, 900, 600);
        stage.setScene(scene);
        stage.setTitle("Support - Refund Approval");
        stage.show();
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
