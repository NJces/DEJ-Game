package Controller;

import Model.Game;
import Model.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GamePage implements Initializable {

    private Game game;

    private int playerIndex = 1;

    @FXML
    private VBox playersList;
    @FXML
    private Label turnTxt;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        game = GameInit.game;
        showList();
        turnTxt.setText(game.getPlayers().get(0).getName());
    }

    private ArrayList<HBox> createPlayerList() {
        ArrayList<HBox> list = new ArrayList<>();
        for (Player player : game.getPlayers()) {
            HBox hBox = new HBox();
            hBox.setSpacing(5);


            Button info = new Button();
            Image crownPng = new Image("/Image/Crown.png");
            Image infoPng = new Image("/Image/Info.png");
            ImageInput imageInput = new ImageInput();
            if (player.getCrown()) {
                imageInput.setSource(crownPng);
                imageInput.setY(-10);
                imageInput.setX(-15);
            }
            else {
                imageInput.setSource(infoPng);
                imageInput.setY(-3);
                imageInput.setX(-5);
            }
            info.setEffect(imageInput);

            Label name = new Label(player.getName());

            hBox.getChildren().addAll(info, name);

            list.add(hBox);
        }
        return list;
    }

    private void showList() {
        ArrayList<HBox> list = createPlayerList();
        playersList.getChildren().addAll(list);
    }

    public void start() {
        while (!isFinished()) {
            //play game
        }
    }

    private boolean isFinished() {
        int playersNum = game.getPlayers().size();
        for (Player player : game.getPlayers()) {
            if (playersNum <= 3 && player.getCityStructuresSize() >= 8) {
                return true;
            }
            else if(playersNum <= 7 && player.getCityStructuresSize() >= 7) {
                return true;
            }
        }
        return false;
    }

    public void choseRole(ActionEvent actionEvent) {
        if (playerIndex < game.getPlayers().size()) {
            turnTxt.setText(game.getPlayers().get(playerIndex ++).getName());
        }
    }
}
