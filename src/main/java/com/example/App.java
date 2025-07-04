package com.example;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        // Load the initial FXML (login view)
        scene = new Scene(loadFXML("auth/login"));
        stage.setScene(scene);
        stage.setTitle("Ajyal School Management System");
        stage.show();
    }

    /**
     * Switch the root of the current scene to a different FXML.
     */
    public static void setRoot(String fxml) throws IOException {
        System.out.println("Switching root to: " + fxml); // Debug log
        Parent root = loadFXML(fxml);
        if (root != null) {
            scene.setRoot(root);
        } else {
            System.err.println("FXML file not found: " + fxml);
        }
    }

    /**
     * Load an FXML file from /com/example/{fxml}.fxml
     */
    private static Parent loadFXML(String fxml) throws IOException {
        String resourcePath = "/com/example/" + fxml + ".fxml";
        System.out.println("Loading FXML: " + resourcePath); // Debug log

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(resourcePath));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}
