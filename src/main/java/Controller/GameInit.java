package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.input.MouseDragEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class GameInit implements Initializable {
    @FXML
    private ImageInput upFlash;
    @FXML
    private ImageInput downFlash;
    @FXML
    private Button boostBtn;
    @FXML
    private Button reduceBtn;
    @FXML
    private Label player_NumTxt;
    @FXML
    private Button submitBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void boost(ActionEvent actionEvent) {
        if (player_NumTxt.getText().charAt(0) < '7') {
            downFlash.setSource(new Image("/Image/RedDownFlash.png"));
            upFlash.setSource(new Image("/Image/BlueUpFlash.png"));
            int number = player_NumTxt.getText().charAt(0) - '0';
            player_NumTxt.setText(number + 1 + " ");
        }
    }

    public void radius(ActionEvent actionEvent) {
        if (player_NumTxt.getText().charAt(0) > '2') {
            upFlash.setSource(new Image("/Image/RedUpFlash.png"));
            downFlash.setSource(new Image("/Image/BlueDownFlash.png"));
            int number = player_NumTxt.getText().charAt(0) - '0';
            player_NumTxt.setText(number - 1 + " ");
        }
    }

    public void showPlayerList(ActionEvent actionEvent) {
        upFlash.setSource(new Image("/Image/RedUpFlash.png"));
        downFlash.setSource(new Image("/Image/RedDownFlash.png"));
    }
}
