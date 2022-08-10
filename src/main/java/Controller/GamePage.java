package Controller;

import Model.Game;
import Model.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GamePage implements Initializable {

    private Game game;

    @FXML
    private VBox playersList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        game = GameInit.game;
        showList();
    }

    private ArrayList<HBox> createPlayerList() {
        ArrayList<HBox> list = new ArrayList<>();
        for (Player player : game.getPlayers()) {
            HBox hBox = new HBox();
            hBox.setSpacing(5);


            Button info = new Button();

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
}
