package lk.ijse.Controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.regex.Pattern;

public class LoginController {


    @FXML
    private TextField username;

    public static String name;

    @FXML
    void logOnAction(ActionEvent event) throws IOException {

        boolean isValidated = validate();

        if (isValidated) {
            name = username.getText();
            username.clear();
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(LoginController.class.getResource("/view/client.fxml"))));
            stage.setTitle("chat");
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.show();

        }

    }

    private boolean validate() {
        String idText = username.getText();
        boolean isIDValidated = Pattern.matches("\\b[A-Za-z]{4,}\\b", idText);
        if (!isIDValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid Username").show();
            return false;
        }
        return true;
    }

}
