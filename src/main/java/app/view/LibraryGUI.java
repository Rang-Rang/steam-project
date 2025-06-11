package app.view;

import java.util.Optional;

import app.model.steam.Game;
import app.model.steam.TrialGame;
import app.model.users.Customer;
import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
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
import javafx.stage.Modality;
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

        Button strBtn = new Button("Store");
        Button cartBtn = new Button("Cart");
        Button libBtn = new Button("Library");
        Button logoutBtn = new Button("Logout");

        styleNavButton(strBtn);
        styleNavButton(cartBtn);
        styleNavButton(libBtn);
        styleNavButton(logoutBtn);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        navbar.getChildren().addAll(profileLabel, spacer, strBtn, cartBtn, libBtn, logoutBtn);
        root.setTop(navbar);

        strBtn.setOnAction(e -> StoreGUI.showStore(stage));
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

        for (Game game : customer.getLibrary().displayLibrary()) {
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

            String titleText = game.getTitle();
            if (game instanceof TrialGame) {
                titleText += " (Trial)";
            }

            Label title = new Label(titleText);
            title.setStyle("-fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");
            title.setWrapText(true);
            title.setTextAlignment(TextAlignment.CENTER);

            Button playableBtn = new Button("Download");
            playableBtn.setStyle("-fx-background-color: #66c0f4; -fx-text-fill: black;");

            playableBtn.setOnAction(ev -> {
                if (playableBtn.getText().equals("Download")) {
                    Stage popupStage = new Stage();
                    popupStage.initModality(Modality.APPLICATION_MODAL);
                    popupStage.setTitle("Download");

                    ProgressBar progressBar = new ProgressBar();
                    progressBar.setPrefWidth(250);

                    String downloadText = (game instanceof TrialGame)
                            ? ((TrialGame) game).download()
                            : game.download();

                    Label downloadLabel = new Label(downloadText);
                    downloadLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: white;");

                    VBox vbox = new VBox(10, downloadLabel, progressBar);
                    vbox.setAlignment(Pos.CENTER);
                    vbox.setPadding(new Insets(20));
                    vbox.setStyle("-fx-background-color: #1b2838;");

                    Scene scene = new Scene(vbox);
                    popupStage.setScene(scene);
                    popupStage.show();

                    playableBtn.setDisable(true);

                    PauseTransition pause = new PauseTransition(Duration.seconds(2));
                    pause.setOnFinished(f -> {
                        popupStage.close();
                        playableBtn.setText("Play");
                        playableBtn.setDisable(false);
                    });
                    pause.play();

                } else if (playableBtn.getText().equals("Play")) {
                    String playText;
                    String timeLeftText = "";

                    if (game instanceof TrialGame trialGame) {
                        playText = trialGame.play();
                        timeLeftText = "Time left: " + trialGame.getTrialDuration() + " mins";
                    } else {
                        playText = game.play();
                    }

                    Stage popupStage = new Stage();
                    popupStage.initModality(Modality.APPLICATION_MODAL);
                    popupStage.setTitle("Playing");

                    Label labelPlay = new Label(playText);
                    labelPlay.setStyle("-fx-font-size: 14px;");

                    VBox vbox;
                    if (!timeLeftText.isEmpty()) {
                        Label labelTimeLeft = new Label(timeLeftText);
                        labelTimeLeft.setStyle("-fx-font-size: 12px; -fx-text-fill: orange;");
                        vbox = new VBox(10, labelPlay, labelTimeLeft);
                    } else {
                        vbox = new VBox(10, labelPlay);
                    }
                    vbox.setAlignment(Pos.CENTER);
                    vbox.setPadding(new Insets(20));

                    Scene scene = new Scene(vbox, 250, 120);
                    popupStage.setScene(scene);
                    popupStage.show();

                    playableBtn.setText("Exit");

                    popupStage.setOnCloseRequest(e -> {
                        playableBtn.setText("Play");
                    });
                } else if (playableBtn.getText().equals("Exit")) {
                    String exitText = (game instanceof TrialGame)
                            ? ((TrialGame) game).exit()
                            : game.exit();

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Exit Game");
                    alert.setHeaderText(null);
                    alert.setContentText(exitText);
                    alert.showAndWait();

                    playableBtn.setText("Play");
                }
            });

            Button refundBtn = new Button();
            if (game instanceof TrialGame) {
                refundBtn.setText("Remove");
                refundBtn.setStyle("-fx-background-color: #e67e22; -fx-text-fill: white;");
                refundBtn.setOnAction(ev -> {
                    Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
                    confirm.setTitle("Remove Trial");
                    confirm.setHeaderText(null);
                    confirm.setContentText("Are you sure you want to remove this trial game from your library?");
                    Optional<javafx.scene.control.ButtonType> result = confirm.showAndWait();
                    if (result.isPresent() && result.get() == javafx.scene.control.ButtonType.OK) {
                        customer.getLibrary().removeGame(game);
                        tilePane.getChildren().remove(card);
                    }
                });
            } else {
                refundBtn.setText("Refund");
                refundBtn.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");
                refundBtn.setOnAction(ev -> {
                    if (customer.getRefundRequests().stream()
                            .anyMatch(r -> r.getGame().equals(game) && !r.isApproved() && !r.isRejected())) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Refund Already Requested");
                        alert.setHeaderText(null);
                        alert.setContentText("You already requested a refund for this game.");
                        alert.showAndWait();
                        return;
                    }

                    TextInputDialog dialog = new TextInputDialog();
                    dialog.setTitle("Refund Request");
                    dialog.setHeaderText("Enter the reason for refund:");
                    dialog.setContentText("Reason:");

                    Optional<String> result = dialog.showAndWait();
                    result.ifPresent(reason -> {
                        customer.addRefundRequest(game, reason);
                        refundBtn.setDisable(true);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Refund Request");
                        alert.setHeaderText(null);
                        alert.setContentText("Refund request submitted and awaiting support approval.");
                        alert.showAndWait();
                    });
                });
            }

            card.getChildren().addAll(imageView, title, playableBtn, refundBtn);
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
