package app.resource;


import app.model.users.Customer;
import app.model.users.SupportStaff;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
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
    Scene scene = new Scene(getRoot(primaryStage), 400, 350);
    primaryStage.setTitle("Steam Login");
    primaryStage.setScene(scene);
    primaryStage.setMinWidth(300);
    primaryStage.setMinHeight(300);
    primaryStage.show();
}

public static Parent getRoot(Stage stage) {
    VBox root = new VBox();
    root.setPadding(new Insets(30));
    root.setAlignment(Pos.CENTER);
    root.setSpacing(20);
    root.setStyle("-fx-background-color: #1b2838;");

    // Title
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

    // Form input
    TextField usernameField = new TextField();
    usernameField.setPromptText("Steam username or email");
    styleInput(usernameField);

    PasswordField passwordField = new PasswordField();
    passwordField.setPromptText("Password");
    styleInput(passwordField);

    Button loginBtn = new Button("SIGN IN");
    loginBtn.setStyle("-fx-background-color: #5c7e10; -fx-text-fill: white; -fx-font-size: 14px;");
    loginBtn.setMaxWidth(Double.MAX_VALUE);

    Label msgLabel = new Label();
    msgLabel.setTextFill(Color.web("#e74c3c"));

    VBox form = new VBox(usernameField, passwordField, loginBtn, msgLabel);
    form.setAlignment(Pos.CENTER);
    form.setSpacing(12);
    form.setMaxWidth(300);

    // Responsif
    usernameField.prefWidthProperty().bind(Bindings.min(form.widthProperty(), root.widthProperty().multiply(0.7)));
    passwordField.prefWidthProperty().bind(usernameField.prefWidthProperty());
    loginBtn.prefWidthProperty().bind(usernameField.prefWidthProperty());

    // Login logic
    loginBtn.setOnAction(e -> handleLogin(stage, usernameField.getText(), passwordField.getText(), msgLabel));

    root.getChildren().addAll(headerBox, form);
    return root;
}

private static void styleInput(TextField field) {
    field.setStyle("-fx-background-color: #2a475e; -fx-text-fill: white; -fx-prompt-text-fill: #c7d5e0;");
}

private static void handleLogin(Stage stage, String username, String password, Label msgLabel) {
    if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
        msgLabel.setText("Please enter credentials.");
        return;
    }

    String user = username.trim();
    String pass = password.trim();

    try {
        SupportStaff admin = SupportStaff.findByCredential(user, pass);
        if (admin != null) {
            msgLabel.setText("");
            SupportGUI.showSupportDashboard(stage);
            return;
        }

        Customer customer = Customer.findByCredential(user, pass);
        if (customer != null) {
            msgLabel.setText("");
            StoreGUI.showStore(stage);
            return;
        }

        msgLabel.setText("Invalid credentials. Try again.");

    } catch (Exception ex) {
        msgLabel.setText("An error occurred while logging in.");
        ex.printStackTrace();
    }
}

public static void main(String[] args) {
    launch(args);
}

}
