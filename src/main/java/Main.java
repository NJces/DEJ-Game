import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Menu.fxml"));
        Pane pane = loader.load();
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("DEJ-Game");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
