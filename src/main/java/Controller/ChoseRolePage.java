package Controller;

import Model.Role;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChoseRolePage implements Initializable {
    public ArrayList<Role> roles = new ArrayList<>();
    public ArrayList<Button> rolesCard = new ArrayList<>();

    @FXML
    private TilePane list;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Role.initRoles(roles);
        Role.randomShuffling(roles);
        roles.remove(0);
    }

    public void showRoleList() {
        list.getChildren().removeAll(rolesCard);
        for (Role role : roles) {
           Button button = new Button(role.name());
           button.setPrefSize(80, 130);
           rolesCard.add(button);
           list.getChildren().add(button);
        }
    }
}
