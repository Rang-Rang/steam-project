package app.resource;

import app.model.steam.Game;
import app.model.steam.Library;
import app.model.steam.TrialGame;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LibraryGUI {
    private Library gameLib;
    private Game game;

    public void start(Stage stage) {
        gameLib = new Library();
        gameLib.addGame(new Game("gm01", "God Of War", 659000));
        gameLib.addGame(new Game("gm02", "Ghost Of Tsushima", 869000));
        gameLib.addGame(new Game("gm03", "Spider-man", 859000));
        gameLib.addGame(new Game("gm04", "Clair Obscur: Expedition 33", 499000));
        
        VBox root = new VBox(10);
        root.setPadding(new Insets(15));

        Label title = new Label("Game Library");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        root.getChildren().add(title);

        for (Game game : gameLib.displayLibrary()) {
            HBox gameBox = new HBox(10);
            gameBox.setPadding(new Insets(5));
            gameBox.setStyle("-fx-border-color: lightgray; -fx-border-radius: 5; -fx-padding: 10px;");

            String gameType = (game instanceof TrialGame) ? " (Trial)" : "";
            Label nameLabel = new Label(game.getTitle() + gameType);
            nameLabel.setPrefWidth(250);

            Button actionBtn = new Button("Download");

            gameBox.getChildren().addAll(nameLabel, actionBtn);
            root.getChildren().add(gameBox);

            actionBtn.setOnAction(e -> {
                switch (actionBtn.getText()) {
                    case "Download":
                        Stage downloadStage = new Stage();
                        VBox downloadRoot = new VBox(15);
                        downloadRoot.setPadding(new Insets(20));
                        
                        Label downloadingLabel = new Label(game.download());
                        ProgressBar progressBar = new ProgressBar(0);
                        
                        javafx.animation.Timeline timeline = new javafx.animation.Timeline(
                            new javafx.animation.KeyFrame(javafx.util.Duration.seconds(0), evt -> progressBar.setProgress(0)),
                            new javafx.animation.KeyFrame(javafx.util.Duration.seconds(3), evt -> progressBar.setProgress(1))
                        );
                        
                        timeline.setOnFinished(evt -> {
                            actionBtn.setText("Play");
                            downloadStage.close();
                        });
                        
                        timeline.play();

                        Button cancelDownloadBtn = new Button("Cancel");
                        cancelDownloadBtn.setOnAction(ev -> {
                            timeline.stop();
                            downloadStage.close();
                        });
                        
                        downloadRoot.getChildren().addAll(downloadingLabel, progressBar, cancelDownloadBtn);

                        Scene downloadScene = new Scene(downloadRoot, 300, 120);
                        downloadStage.setTitle("Downloading...");
                        downloadStage.setScene(downloadScene);
                        downloadStage.show();
                        break;

                    case "Play":
                        Stage gameStage = new Stage();
                        VBox gameRoot = new VBox(15);
                        gameRoot.setPadding(new Insets(20));

                        Label playingLabel = new Label(game.play());
                        Button exitGameBtn = new Button("Exit");

                        exitGameBtn.setOnAction(ev -> gameStage.close());

                        gameRoot.getChildren().addAll(playingLabel, exitGameBtn);

                        Scene gameScene = new Scene(gameRoot, 300, 150);
                        gameStage.setTitle("Game: " + game.getTitle());
                        gameStage.setScene(gameScene);
                        gameStage.show();

                        break;
                }
            });
        }

        Scene scene = new Scene(root, 500, 400);
        stage.setTitle("Game Library GUI");
        stage.setScene(scene);
        stage.show();
    }
}
