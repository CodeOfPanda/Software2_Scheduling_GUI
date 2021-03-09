package View_Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML private TextField loginLocation;
    @FXML private TextField loginUserID;
    @FXML private PasswordField loginPassword;
    @FXML private Button signInButton;
    @FXML private Button exitButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void signInBtnClicked(ActionEvent actionEvent) throws IOException {
        //when triggered this takes the user to All_Appointment_Records_Scene.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../resources/All_Appointment_Records_Scene.fxml"));
        Parent allAppointmentsRoot = loader.load();

        All_Appointments_Controller controller = loader.getController();
        controller.sampleData();

        Stage allAppointmentsStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene allAppointmentsScene = new Scene(allAppointmentsRoot);
        allAppointmentsStage.setScene(allAppointmentsScene);
        allAppointmentsStage.show();
    }

    public void exitBtnClicked(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Confirm Exit");
        alert.setHeaderText("Are you sure you want to Exit?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.exit(0);
        }
    }
}
