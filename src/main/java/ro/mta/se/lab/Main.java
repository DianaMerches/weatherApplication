package ro.mta.se.lab;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ro.mta.se.lab.controller.WeatherController;
import java.io.IOException;
import java.net.URL;

public class Main extends Application {

    public void start(Stage primaryStage) {
        FXMLLoader loader = new FXMLLoader();

        try {
            loader.setLocation(this.getClass().getResource("/view/WeatherInterface.fxml"));
            loader.setController(new WeatherController());
            primaryStage.setScene(new Scene(loader.load()));
            primaryStage.setTitle("Weather App");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args)
    {
        launch(args);
    }
}