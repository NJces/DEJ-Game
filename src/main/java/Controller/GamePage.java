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
import javafx.scene.control.Alert;
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
    private int playersNum_IsPlayed = 1;

    private Stage choseRoleStage;

    private ChoseRolePage choseRolePage;

    private boolean choseRole_iClicked = false;

    @FXML
    private VBox playersList;
    @FXML
    private Label turnTxt;
    @FXML
    private Label gideTxt;
    @FXML
    private Label structCheck;
    @FXML
    private Label roleCheck;
    @FXML
    private Label sourceCheck;

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

    /**
     * create a list of HBoxes that show some information of players
     * @return
     */
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

    /**
     * show list of players at the start
     */
    private void showList() {
        ArrayList<HBox> list = createPlayerList();
        playersList.getChildren().addAll(list);
    }

    /**
     * show list of player that update in game
     */
    public void showListAgain() {
        ArrayList<HBox> list = createPlayerList();
        playersList.getChildren().remove(0, game.getPlayers().size());
        playersList.getChildren().addAll(list);
    }

    /**
     * check if game is finished (based on number of structure cards that struct)
     */
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
        if (!choseRole_iClicked) {
            if (!choseRoleIsFinished()) {
                choseRolePage.showRoleList();
                choseRoleStage.show();
                ArrayList<Button> rolesCard = choseRolePage.rolesCard;
                for (int i = 0; i < rolesCard.size(); i++) {
                    Button button = rolesCard.get(i);
                    button.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            game.getCurentPlayer().setRolesByFarsiName(button.getText());
                            int roleCardIndex = choseRolePage.rolesCard.indexOf(button);
                            Role role = Role.findByFarsiName(button.getText());
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
            } else {
                printRoles();
                gideTxt.setText("انتخاب نقش تمام شد، شروع بازی");
                Player player = game.nextTurn();
                String role = game.getCurentTurn().getFarsiName();
                turnTxt.setText(role + ": " + player.getName());
                choseRole_iClicked = true;
                sourceCheck.setVisible(true);
            }
        }
    }

    /**
     * check if all players chose role or not
     */
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

    public void submitTurn(ActionEvent actionEvent) {
        if (choseRole_iClicked) {
            if (checkGameLoopIsFinished()) {
                if (sourceCheck.isVisible()) {
                    playersNum_IsPlayed++;
                    Player player = game.nextTurn();
                    String role = game.getCurentTurn().getFarsiName();
                    turnTxt.setText(role + ": " + player.getName());
                    if (player.getCrown() && !player.hasRole(Role.King) && game.hasKingCardRole()) {
                        player.lossCrown();
                    }
                    if (role.equals(Role.King.getFarsiName())) {
                        player.setCrown();
                    }
                } else {
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setHeaderText("برداشت منابع الزامی است!");
                }
                System.out.println(playersNum_IsPlayed);
            }
            else {
                //preparing for next game loop
                prepareNextLoop();
            }
        }
    }

    /**
     * check if all role that chose played or not (based on number of players)
     */
    private boolean checkGameLoopIsFinished() {
        int playersNum = game.getPlayers().size();
        if ((playersNum == 2 && playersNum_IsPlayed < 3 * playersNum)
            || (playersNum == 3 && playersNum_IsPlayed < 2 * playersNum)
            || (playersNum >= 4 && playersNum_IsPlayed < playersNum)) {
            return true;
        }
        return false;
    }

    /**
     * prepare next loop for chose role again and continue the game
     */
    private void prepareNextLoop() {
        //finished last loop
        gideTxt.setText("اتمام دور بازی، انتخاب نقش");
        sourceCheck.setVisible(false);
        roleCheck.setVisible(false);
        structCheck.setVisible(false);
        game.finishedAGameLoop();
        choseRole_iClicked = false;

        //start new loop
        choseRolePage.roles = new ArrayList<>();
        Role.initRoles(choseRolePage.roles);
        Role.randomShuffling(choseRolePage.roles);
        choseRolePage.roles.remove(0);
        playersNum_IsPlayed = 1;
        game.setCrownInGame();
        turnTxt.setText(game.getCurentPlayer().getName());
        showListAgain();
    }
}
