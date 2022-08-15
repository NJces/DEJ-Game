package Controller;

import Model.Game;
import Model.Player;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GameInit implements Initializable {

    private int playersNum;
    private ArrayList<Player> players = new ArrayList<>();
    public static Game game;
    public static Stage formerStage;
    private boolean showPlayerList = false;

    @FXML
    private VBox pageVb;
    @FXML
    private VBox playerListVb;
    @FXML
    private Button startGameBtn;
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
        if(!showPlayerList) {
            showPlayerList = true;
            upFlash.setSource(new Image("/Image/RedUpFlash.png"));
            downFlash.setSource(new Image("/Image/RedDownFlash.png"));
            playersNum = player_NumTxt.getText().charAt(0) - '0';
            showList();

            for (int i = 0; i < playersNum; i++) {
                Player player = players.get(i);
                HBox hBox = (HBox) playerListVb.getChildren().get(i);
                TextField name = (TextField) hBox.getChildren().get(1);
                TextField age = (TextField) hBox.getChildren().get(2);
                hBox.getChildren().add(new Label());
                ((Button) hBox.getChildren().get(3)).addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (name.getText().equals("")) {
                            name.setStyle("-fx-background-color: CHOCOLATE; -fx-background-insets: 10; -fx-background-radius: 20; -fx-border-color: red; -fx-border-width: 5; -fx-border-insets: 0; -fx-border-radius: 20;");
                            Label error = new Label();
                            error.setText("لطفا نام بازیکن را انتخاب کنید!");
                            error.setTextFill(Color.RED);
                            hBox.getChildren().set(4, error);
                        } else if (age.getText().equals("")) {
                            age.setStyle("-fx-background-color: CHOCOLATE; -fx-background-insets: 10; -fx-background-radius: 20; -fx-border-color: red; -fx-border-width: 5; -fx-border-insets: 0; -fx-border-radius: 20;");
                            Label error = new Label();
                            error.setText("لطفا سن بازیکن را انتخاب کنید!");
                            error.setTextFill(Color.RED);
                            hBox.getChildren().set(4, error);
                        } else {
                            age.setStyle("-fx-background-color: CHOCOLATE; -fx-background-insets: 10; -fx-background-radius: 20; -fx-border-color: red; -fx-border-width: 5; -fx-border-insets: 0; -fx-border-radius: 20;");
                            Label error = new Label();
                            error.setText("فرمت نامعتبر است!!");
                            error.setTextFill(Color.RED);
                            hBox.getChildren().set(4, error);
                        }
                        if (!name.getText().equals("")) {
                            name.setStyle("-fx-background-color: CHOCOLATE; -fx-background-insets: 10; -fx-background-radius: 20; -fx-border-color: brown; -fx-border-insets: 5; -fx-border-radius: 20;");
                            if (((Label) hBox.getChildren().get(4)).getText().equals("لطفا نام بازیکن را انتخاب کنید!")) {
                                ((Label) hBox.getChildren().get(4)).setText("");
                            }
                        }

                        if (!age.getText().equals("")) {
                            age.setStyle("-fx-background-color: CHOCOLATE; -fx-background-insets: 10; -fx-background-radius: 20; -fx-border-color: brown; -fx-border-insets: 5; -fx-border-radius: 20;");
                            if (((Label) hBox.getChildren().get(4)).getText().equals("لطفا سن بازیکن را انتخاب کنید!")) {
                                ((Label) hBox.getChildren().get(4)).setText("");
                            }
                        }

                        if (checkNumberFormat(age.getText())) {
                            age.setStyle("-fx-background-color: CHOCOLATE; -fx-background-insets: 10; -fx-background-radius: 20; -fx-border-color: brown; -fx-border-insets: 5; -fx-border-radius: 20;");
                            if (((Label) hBox.getChildren().get(4)).getText().equals("فرمت نامعتبر است!!")) {
                                ((Label) hBox.getChildren().get(4)).setText("");
                            }
                        }

                        if (!name.getText().equals("") && !age.getText().equals("") && checkNumberFormat(age.getText())) {
                            ((Button) hBox.getChildren().get(3)).setVisible(false);
                            hBox.getChildren().remove(4);
                            player.init(name.getText(), Integer.parseInt(age.getText()));
                        }

                    }
                });
            }
        }
    }

    private HBox setPlayerInfo(String codeTxt) {
        HBox info = new HBox();
        info.setSpacing(10);
        info.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        info.setAlignment(Pos.CENTER);
        //player number
        Label code = new Label();
        code.setText(codeTxt);
        code.setTextFill(Color.WHITE);
        code.setFont(new Font(18));
        code.setStyle("-fx-background-color: brown; -fx-background-insets: -2; -fx-background-radius: 10; -fx-border-color: red; -fx-border-insets: 2; -fx-border-radius: 10;");

        //name and age of player
        TextField name = new TextField();
        name.setPromptText("نام");
        name.setPrefWidth(90);
        name.setStyle("-fx-background-color: CHOCOLATE; -fx-background-insets: 10; -fx-background-radius: 20; -fx-border-color: brown; -fx-border-insets: 5; -fx-border-radius: 20;");
        TextField age = new TextField();
        age.setPromptText("سن");
        age.setPrefWidth(50);
        age.setStyle("-fx-background-color: CHOCOLATE; -fx-background-insets: 10; -fx-background-radius: 20; -fx-border-color: brown; -fx-border-insets: 5; -fx-border-radius: 20;");

        //submit player info
        Button submitPlayerInfoBtn = new Button("تایید");
        ImageInput tick = new ImageInput();
        tick.setSource(new Image("/Image/submit02.png"));
        tick.setX(10);
        tick.setY(0);
        submitPlayerInfoBtn.setEffect(tick);
        submitPlayerInfoBtn.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);

        info.getChildren().addAll(code, name, age, submitPlayerInfoBtn);
        return info;
    }

    private ArrayList<HBox> createPlayerList() {
        ArrayList<HBox> list = new ArrayList<>();
        for (int i = 0; i < playersNum; i ++) {
            players.add(new Player());
            list.add(setPlayerInfo(players.get(i).getCode()));
        }

        return list;
    }

    private void showList() {
        ArrayList<HBox> list = createPlayerList();
        playerListVb.getChildren().addAll(list);
    }

    private boolean checkNumberFormat(String text) {
        for (int i = 0; i < text.length(); i ++) {
            if (text.charAt(i) > '9' || text.charAt(i) < '0') {
                return false;
            }
        }
        return (text.equals("")) ? false : true;
    }

    public void startGame(ActionEvent actionEvent) throws IOException {

        if (playersNum == 0) {
            actionEvent.consume();
            startGameBtn.setStyle("-fx-background-color:  MAGENTA; -fx-background-radius: 10");
        }
        else if (!checkEnteredPlayersInfo()) {
            actionEvent.consume();
            startGameBtn.setStyle("-fx-background-color:  MAGENTA; -fx-background-radius: 10");
        }
        else  {
            startGameBtn.setStyle("-fx-background-color:  LAWNGREEN; -fx-background-radius: 10");
            game = new Game(players);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/GamePage.fxml"));
            Pane gamePage = loader.load();
            Scene scene = new Scene(gamePage);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("بازی دژ");
            stage.show();
            formerStage.close();
        }

    }

    private boolean checkEnteredPlayersInfo() {
        for (int i = 0; i < playersNum; i ++) {
            HBox info = (HBox) playerListVb.getChildren().get(i);
            if (info.getChildren().get(3).isVisible()) {
                return false;
            }
        }
        return true;
    }
}
