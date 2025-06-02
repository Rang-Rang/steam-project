package app.resource;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LoginGUI extends Application {
@Override
public void start(Stage primaryStage) {
    VBox root = new VBox();
    root.setPadding(new Insets(30));
    root.setAlignment(Pos.CENTER);
    root.setSpacing(20);
    root.setStyle("-fx-background-color: #1b2838;");

    // Title Area
    Label title = new Label("SIGN IN");
    title.setTextFill(Color.web("#c7d5e0"));
    title.setStyle("-fx-font-weight: bold;");
    title.setFont(Font.font("Arial", 24));

    Label subtitle = new Label("to your Steam account");
    subtitle.setTextFill(Color.web("#c7d5e0"));
    subtitle.setFont(Font.font("Arial", 14));

    VBox headerBox = new VBox(title, subtitle);
    headerBox.setAlignment(Pos.CENTER);
    headerBox.setSpacing(5);

    // Input Area
    TextField usernameField = new TextField();
    usernameField.setPromptText("Steam username");
    styleInput(usernameField);

    PasswordField passwordField = new PasswordField();
    passwordField.setPromptText("Password");
    styleInput(passwordField);

    Button loginBtn = new Button("SIGN IN");
    loginBtn.setStyle("-fx-background-color: #5c7e10; -fx-text-fill: white; -fx-font-size: 14px;");
    loginBtn.setMaxWidth(Double.MAX_VALUE);

    Label msgLabel = new Label();
    msgLabel.setTextFill(Color.web("#e74c3c"));

    // Layout untuk form
    VBox form = new VBox(usernameField, passwordField, loginBtn, msgLabel);
    form.setAlignment(Pos.CENTER);
    form.setSpacing(12);
    form.setMaxWidth(300);
    form.setPrefWidth(0.7); // 70% width if container grows

    // Buat form responsif
    usernameField.prefWidthProperty().bind(Bindings.min(form.widthProperty(), root.widthProperty().multiply(0.7)));
    passwordField.prefWidthProperty().bind(usernameField.prefWidthProperty());
    loginBtn.prefWidthProperty().bind(usernameField.prefWidthProperty());

    loginBtn.setOnAction(e -> {
        String user = usernameField.getText();
        String pass = passwordField.getText();

        if (user.equals("admin") && pass.equals("123")) {
            msgLabel.setText("");
            primaryStage.close();
            try {
                new StoreGUI().start(new Stage()); // Ganti jika nama class berbeda
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            msgLabel.setText("Invalid credentials. Try again.");
        }
    });

    root.getChildren().addAll(headerBox, form);

    Scene scene = new Scene(root, 400, 350);
    primaryStage.setTitle("Steam Login");
    primaryStage.setScene(scene);
    primaryStage.setMinWidth(300);
    primaryStage.setMinHeight(300);
    primaryStage.show();
}

private void styleInput(TextField field) {
    field.setStyle("-fx-background-color: #2a475e; -fx-text-fill: white; -fx-prompt-text-fill: #c7d5e0;");
}

public static void main(String[] args) {
    launch(args);
}


}
