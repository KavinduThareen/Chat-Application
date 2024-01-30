package lk.ijse.Controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class LoginController {


    @FXML
    private TextField username;

    public static String name;

    @FXML
    void logOnAction(ActionEvent event) throws IOException {
        name=username.getText();
        username.clear();
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(LoginController.class.getResource("/view/loading.fxml"))));
        stage.setTitle("chat");
        stage.centerOnScreen();
        stage.show();

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            try {
                // Load client.fxml scene
                Scene clientScene = new Scene(FXMLLoader.load(getClass().getResource("/view/client.fxml")));

                // Set client.fxml scene
                stage.setScene(clientScene);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }));

        timeline.play();

    }

}
