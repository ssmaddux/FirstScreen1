package maddux.firstscreen;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * the main method
 * loads main form
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 450);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }




    public static void main(String[] args) {
        launch();
    }
}