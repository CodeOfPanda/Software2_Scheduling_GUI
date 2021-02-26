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

public class Add_Appointment_Controller implements Initializable {

    @FXML private TextField addApptID;
    @FXML private TextField addApptTitle;
    @FXML private TextField addApptDescript;
    @FXML private TextField addApptLocale;
    @FXML private ChoiceBox<?> addApptContact;
    @FXML private TextField addApptType;
    @FXML private TextField addApptStrtTime;
    @FXML private TextField addApptEndTime;
    @FXML private TextField addApptCustID;
    @FXML private TextField addApptUserID;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    // Submit Button Action Event
    @FXML
    void addApptSubmitBtnClicked(ActionEvent event) throws IOException {
        // when triggered an information alert will inform the user that their changes have been saved and take them
        // back to Customer_Appointments_Scene.
        Alert submit = new Alert(Alert.AlertType.INFORMATION);
        submit.initModality(Modality.NONE);
        submit.setTitle("Thank You!");
        submit.setHeaderText("Your appointment has been saved.");
        Optional<ButtonType> results = submit.showAndWait();
        if(results.get() == ButtonType.OK) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../resources/Customer_Appointments_Scene.fxml"));
            Parent submitApptRoot = loader.load();

            Stage submitApptStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene submitApptScene = new Scene(submitApptRoot);
            submitApptStage.setScene(submitApptScene);
            submitApptStage.show();
        }

    }

    // Cancel Button Action Event
    @FXML
    void addApptCancelBtnClicked(ActionEvent event) throws IOException {
        // when triggered an alert will ask for conformation before taking them back to Customer_Appointments_Scene.
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Confirm Cancel");
        alert.setHeaderText("Do you wish to Cancel?");
        alert.setContentText("Appointment information will not be saved.");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../resources/Customer_Appointments_Scene.fxml"));
            Parent cancelApptRoot = loader.load();

            Stage cancelApptStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene cancelApptScene = new Scene(cancelApptRoot);
            cancelApptStage.setScene(cancelApptScene);
            cancelApptStage.show();
        }
    }


}
