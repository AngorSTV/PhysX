package FX;/**
 * Created by Андрей on 11.02.2016.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Start extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Start.class.getClassLoader().getResource("./FX/main.fxml"));
            BorderPane rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.setTitle("Galaxy simulation");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
