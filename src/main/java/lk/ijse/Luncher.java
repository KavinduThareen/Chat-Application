package lk.ijse;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class Luncher extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // Load the loading scene
        Parent loadingRoot = FXMLLoader.load(getClass().getResource("/view/loading.fxml"));
        Scene loadingScene = new Scene(loadingRoot);

        // Set the loading scene initially
        stage.setScene(loadingScene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();

        // Create a timeline to switch to the login scene after 1 second
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            try {
                Parent loginRoot = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
                Scene loginScene = new Scene(loginRoot);
                stage.setScene(loginScene);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }));
        timeline.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
