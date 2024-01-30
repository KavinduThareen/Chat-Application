package lk.ijse.Controller;

import com.jfoenix.controls.JFXTextArea;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientController extends Thread {


    //  public VBox msgVbox;
    @FXML
    private VBox msgVbox;


    @FXML
    private AnchorPane EmojiPane;

    @FXML
    private Label lable;

    @FXML
    private JFXTextArea scrollPane;

    @FXML
    private TextField textField;




    BufferedReader reader;
    PrintWriter writer;
    Socket socket;
    private FileChooser fileChooser;
    private File filePath;




    public void initialize(){
        String username = LoginController.name;
        lable.setText(username);


        try{
            socket= new Socket("localhost",3002);
            System.out.println("Socket connected to server");
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);

            this.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
        EmojiPane.setVisible(false);
    }

    @Override
    public void run(){
        try{

            while (true){

                String msg = reader.readLine();
                String[] tokens = msg.split(" ");
                String cmd = tokens[0];

                StringBuilder fullMsg = new StringBuilder();
                for (int i = 1; i < tokens.length; i++) {
                    fullMsg.append(tokens[i]+" ");
                }

                String[] msgToAr = msg.split(" ");
                String st = "";
                for (int i = 0; i < msgToAr.length - 1; i++) {
                    st += msgToAr[i + 1] + " ";
                }

                Text text = new Text(st);
                String firstChars = "";
                if (st.length() > 3) {
                    firstChars = st.substring(0, 3);

                }

                if (firstChars.equalsIgnoreCase("img")) {
                    //for the Images

                    st = st.substring(3, st.length() - 1);


                    File file = new File(st);
                    Image image = new Image(file.toURI().toString());

                    ImageView imageView = new ImageView(image);

                    imageView.setFitHeight(150);
                    imageView.setFitWidth(200);


                    HBox hBox = new HBox(10);
                    hBox.setAlignment(Pos.BOTTOM_RIGHT);


                    if (!cmd.equalsIgnoreCase(lable.getText())) {

                        msgVbox.setAlignment(Pos.TOP_LEFT);
                        hBox.setAlignment(Pos.CENTER_LEFT);


                        Text text1 = new Text("  " + cmd + " :");
                        hBox.getChildren().add(text1);
                        hBox.getChildren().add(imageView);

                    } else {
                        hBox.setAlignment(Pos.BOTTOM_RIGHT);
                        hBox.getChildren().add(imageView);
                        Text text1 = new Text("");
                        hBox.getChildren().add(text1);

                    }

                    Platform.runLater(() -> msgVbox.getChildren().addAll(hBox));


                } else {

                    TextFlow tempFlow = new TextFlow();

                    if (!cmd.equalsIgnoreCase(lable.getText() + ":")) {
                        Text txtName = new Text(cmd + " ");
                        txtName.getStyleClass().add("txtName");
                        tempFlow.getChildren().add(txtName);

                        tempFlow.setStyle("-fx-color: rgb(239,242,255);" +
                                "-fx-background-color: rgb(191,152,232);" +
                                " -fx-background-radius: 20px");
                        tempFlow.setPadding(new Insets(9,20,7,20));
                    }

                    tempFlow.getChildren().add(text);
                    tempFlow.setMaxWidth(200); //200

                    TextFlow flow = new TextFlow(tempFlow);

                    HBox hBox = new HBox(15); //12




                    if (!cmd.equalsIgnoreCase(lable.getText() + ":")) {


                        msgVbox.setAlignment(Pos.TOP_LEFT);
                        hBox.setAlignment(Pos.CENTER_LEFT);
                        hBox.getChildren().add(flow);

                    } else {

                        Text text2 = new Text(fullMsg + " ");
                        TextFlow flow2 = new TextFlow(text2);
                        hBox.setAlignment(Pos.BOTTOM_RIGHT);
                        hBox.getChildren().add(flow2);
                        hBox.setPadding(new Insets(8,18,7,35));

                        flow2.setStyle("-fx-color: rgb(239,242,255);" +
                                "-fx-background-color: rgb(232,24,87);" +
                                "-fx-background-radius: 20px");
                        flow2.setPadding(new Insets(9,20,7,20));
                    }

                    Platform.runLater(() -> msgVbox.getChildren().addAll(hBox));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void  hideImoji(MouseEvent event) { EmojiPane.setVisible(false);}

    @FXML
    void imgSent(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");
        this.filePath = fileChooser.showOpenDialog(stage);
        writer.println(lable.getText() + " " + "img" + filePath.getPath());
    }

    @FXML
    void imojiSent(MouseEvent event) {
        EmojiPane.setVisible(true);
    }



    @FXML
    void sent(MouseEvent event) {
        String msg = textField.getText();
        writer.println(lable.getText() + ": " + msg);

        textField.clear();

        if(msg.equalsIgnoreCase("BYE") || (msg.equalsIgnoreCase("logout"))) {
            System.exit(0);

        }
    }

    @FXML
    void imoji1(MouseEvent event) {
        String emoji = new String(Character.toChars(128517));
        textField.setText(emoji);
        EmojiPane.setVisible(false);
    }

    @FXML
    void imoji2(MouseEvent event) {
        String emoji = new String(Character.toChars(128567));
        textField.setText(emoji);
        EmojiPane.setVisible(false);
    }

    @FXML
    void imoji3(MouseEvent event) {
        String emoji = new String(Character.toChars(128525));
        textField.setText(emoji);
        EmojiPane.setVisible(false);
    }

    @FXML
    void imoji4(MouseEvent event) {
        String emoji = new String(Character.toChars(128523));
        textField.setText(emoji);
        EmojiPane.setVisible(false);
    }

    @FXML
    void imoji5(MouseEvent event) {
        String emoji = new String(Character.toChars(129325));
        textField.setText(emoji);
        EmojiPane.setVisible(false);
    }

    @FXML
    void imoji6(MouseEvent event) {
        String emoji = new String(Character.toChars(128548));
        textField.setText(emoji);
        EmojiPane.setVisible(false);
    }




    @FXML
    void textOnAction(ActionEvent mouseEvent) {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");
        this.filePath = fileChooser.showOpenDialog(stage);
        writer.println(lable.getText() + " " + "img" + filePath.getPath());
    }





}
