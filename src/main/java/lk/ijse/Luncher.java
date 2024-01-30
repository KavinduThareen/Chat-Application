package lk.ijse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Luncher extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/Login.fxml"));
        Scene mainScene = new Scene(root);
        stage.setScene(mainScene);
        stage.setTitle("chat");
        stage.centerOnScreen();
        stage.show();
    }
}
