package app.model.steam;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application
{
    public static void main( String[] args ){
        System.out.println( "Hello World!" );

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Komponen UI
        Label label = new Label("Hello, JavaFX!");
        Button button = new Button("Klik saya");

        // Aksi saat tombol diklik
        button.setOnAction(e -> label.setText("Tombol diklik!"));

        // Layout
        VBox root = new VBox(10); // 10px spacing
        root.setStyle("-fx-padding: 20; -fx-alignment: center;");
        root.getChildren().addAll(label, button);

        // Scene dan Stage
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("Aplikasi JavaFX Sederhana");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
