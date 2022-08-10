package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.omg.CORBA.MARSHAL;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Menu implements Initializable {
    @FXML
    private BorderPane mainPage;

    public static Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void gotoGameInitPage(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/GameInit.fxml"));
        Pane pane = loader.load();
        mainPage.setCenter(pane);
        GameInit.formerStage = stage;
        System.out.println("....");

    }
}
