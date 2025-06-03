package app.resource;

import app.model.steam.Game;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TrialGameGUI {
    public static void showTrialPage(Game game, Stage primaryStage) {
        Stage trialStage = new Stage(); 
        trialStage.setTitle("Trial - " + game.getTitle());

        VBox layout = new VBox(20);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Trial Game");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Label gameInfo = new Label("You're trying: " + game.getTitle());
        gameInfo.setStyle("-fx-font-size: 18px;");

        layout.getChildren().addAll(titleLabel, gameInfo);
        Scene scene = new Scene(layout, 400, 300);
        trialStage.setScene(scene);
        trialStage.show();
    }
}
