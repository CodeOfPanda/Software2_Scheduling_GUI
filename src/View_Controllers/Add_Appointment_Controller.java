package View_Controllers;

import Models.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

/*
* when adding/updating text fields are used to record:
*       apptID, title, description, location, contact, type, start date & time, end date & time, custID, user.
*       but you can use a combo box or date picker as well for the contact, type, sd&t, ed&t, custID (may want to display name), and user.
*/


public class Add_Appointment_Controller {

    ObservableList<Appointments> addingAppts = FXCollections.observableArrayList();
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

    // for the auto gen ID.
    // i need to start the count after the last appointment from the database.
    public void initApptID() {
        addApptID.setText(String.valueOf(Appointments.getApptIDCount() + 1));
        addApptID.setEditable(false);
    }

    // Submit Button Action Event
    @FXML
    void addApptSubmitBtnClicked(ActionEvent event) throws IOException {
        // when triggered an information alert will inform the user that their changes have been saved and take them
        // back to Customer_Appointments_Scene.

        if(isValid()) {

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
        } else {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Missing Information.");
            error.setHeaderText("Please make sure you have filled out each text field.");
            Optional<ButtonType> results = error.showAndWait();
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



    public Boolean isValid() {
        boolean isTrue = false;
        // checks text fields/checkboxes to ensure each contains a value.
        if (addApptTitle.getText().isEmpty() || addApptTitle.getText() == null) {
            return isTrue;
        } else if (addApptDescript.getText().isEmpty() || addApptDescript.getText() == null) {
            return isTrue;
        } else if (addApptLocale.getText().isEmpty() || addApptLocale.getText() == null) {
            return isTrue;
//        } else if (addApptContact.getItems().isEmpty() || addApptContact.getItems() == null) {  // I need to populate the checkbox with contactIDs
//            return false;
//        }
        } else if (addApptType.getText().isEmpty() || addApptType.getText() == null) {
            return isTrue;
        } else if (addApptStrtTime.getText().isEmpty() || addApptStrtTime.getText() == null) {
            return isTrue;
        } else if (addApptEndTime.getText().isEmpty() || addApptEndTime.getText() == null) {
            return isTrue;
        } else if (addApptCustID.getText().isEmpty() || addApptCustID.getText() == null) {
            return isTrue;
        } else if (addApptUserID.getText().isEmpty() || addApptUserID.getText() == null) {
            return isTrue;
        }
        return true;

//        if(!isTrue) {
//            int apptID = Appointments.getApptID();
//            String title = Appointments.getApptTitle();
//            String description = Appointments.getApptDescript();
//            String location = Appointments.getApptLocation();
//            String type = Appointments.getApptType();
//            int custID = Appointments.getApptContactID();
//            int userID = Appointments.getApptUserID();
//            int contactID = Appointments.getApptContactID();
//
//
//        }
    }

}

/*
*   @FXML private TextField addApptID;
    @FXML private TextField addApptTitle;
*   @FXML private TextField addApptDescript;
    @FXML private TextField addApptLocale;
    @FXML private ChoiceBox<?> addApptContact;
    @FXML private TextField addApptType;
    @FXML private TextField addApptStrtTime;
    @FXML private TextField addApptEndTime;
    @FXML private TextField addApptCustID;
    @FXML private TextField addApptUserID;
*/
