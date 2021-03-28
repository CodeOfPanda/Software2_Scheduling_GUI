package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.DBConnection;

import java.util.Locale;

public class Main extends Application {

    @Override
    public void start(Stage Stage) throws Exception{
//        Locale.setDefault(new Locale("fr"));
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../resources/Login_Scene.fxml"));
        Parent loginRoot = loader.load();

        Stage loginStage = new Stage();
        Scene loginScene = new Scene(loginRoot);

        loginStage.setScene(loginScene);
        loginStage.show();

    }

    public static void main(String[] args) {
        DBConnection.startConnection();
        launch(args);
        DBConnection.closeConnection();
    }
}