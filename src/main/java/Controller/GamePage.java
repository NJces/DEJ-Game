package Controller;

import Model.Game;
import Model.Player;
import Model.Role;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GamePage implements Initializable {

    private Game game;

    private Stage choseRoleStage;

    private ChoseRolePage choseRolePage;

    @FXML
    private VBox playersList;
    @FXML
    private Label turnTxt;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        game = GameInit.game;
        showList();
        turnTxt.setText(game.getCurentPlayer().getName());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ChoseRolePage.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle("Chose Role");
        stage.setScene(new Scene(pane));
        choseRoleStage = stage;
        choseRolePage = loader.getController();
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

    public void choseRole(ActionEvent actionEvent) throws IOException {
        if (!choseRoleIsFinished()) {
            choseRolePage.showRoleList();
            choseRoleStage.show();
            ArrayList<Button> rolesCard = choseRolePage.rolesCard;
            for (int i = 0; i < rolesCard.size(); i++) {
                Button button = rolesCard.get(i);
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        game.getCurentPlayer().setRoles(button.getText());
                        int roleCardIndex = choseRolePage.rolesCard.indexOf(button);
                        Role role = Role.findByName(button.getText());
                        choseRolePage.roles.remove(role);
                        choseRoleStage.close();
                        game.nextPlayer();
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                turnTxt.setText(game.getCurentPlayer().getName());
                            }
                        });
                    }
                });
            }
        }
        else {
            printRoles();
        }
    }

    private boolean choseRoleIsFinished() {
        if ((!game.getCurentPlayer().getCrown()) ||(game.getCurentPlayer().getCrown() && choseRolePage.roles.size()/game.getPlayers().size() >= 1)) {
            return false;
        }
        return true;
    }

    private void printRoles() {
        for (Player player : game.getPlayers()) {
            System.out.println("--------------------");
            System.out.println(player.getName() + ": ");
            player.printRole();
            System.out.println("--------------------");

        }
    }
}
