package View_Controllers;

import DBAccess.DBAppointments;
import DBAccess.DBContacts;
import DBAccess.DBCustomers;
import DBAccess.DBUsers;
import Models.Appointments;
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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;

/*
* when adding/updating text fields are used to record:
*       apptID, title, description, location, contact, type, start date & time, end date & time, custID, user.
*       but you can use a combo box or date picker as well for the contact, type, sd&t, ed&t, custID (may want to display name), and user.
*/

// modified scene builder to fit new requirements

public class Add_Appointment_Controller implements Initializable {

    @FXML private TextField addApptID;
    @FXML private TextField addApptTitle;
    @FXML private TextField addApptDescript;
    @FXML private TextField addApptLocale;
    @FXML private ComboBox<String> addApptContact;
    @FXML private DatePicker addApptStartDate;
    @FXML private DatePicker addApptEndDate;
    @FXML private ComboBox<String> addApptType;
    @FXML private ComboBox<String> addApptStartTime;
    @FXML private ComboBox<String> addApptEndTime;
    @FXML private ComboBox<Integer> addApptCustID;
    @FXML private ComboBox<String> addApptCustName;
    @FXML private ComboBox<Integer> addApptUserID; // make the init data auto-generated based off of the log-in
    @FXML private Button addApptSubmitBtn;
    @FXML private Button addApptCancelBtn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addApptContact.setItems(Appointments.getContactNames());
        addApptCustName.setItems(Appointments.getCustomerNames());
        addApptType.setItems(Appointments.getAllApptTypes());
        addApptUserID.setItems(Appointments.getUserIDs());
        addApptStartTime.setItems(Appointments.getWorkHours());
        addApptEndTime.setItems(Appointments.getWorkHours());
    }

    // Submit Button Action Event
    @FXML
    void addApptSubmitBtnClicked(ActionEvent event) throws IOException {
        // when triggered an information alert will inform the user that their changes have been saved and take them
        // back to All_Appointments_Scene.

//        if(isValid()) {

//        addApptContact.getEditor().getText()
            // inserting into database
            DBAppointments.createAppt(addApptTitle.getText()
                    , addApptDescript.getText()
                    , addApptLocale.getText()
                    , addApptType.getValue()
                    , LocalDateTime.of(addApptStartDate.getValue(),
                            LocalTime.parse(addApptStartTime.getValue()))
                    , LocalDateTime.of(addApptEndDate.getValue(),
                            LocalTime.parse(addApptEndTime.getValue()))
                    , Appointments.getCurrentDateTime()
                    , DBUsers.getUserName(addApptUserID.getValue())
                    , Appointments.getCurrentDateTime()
                    , DBUsers.getUserName(addApptUserID.getValue())
                    , DBCustomers.getCustomerID(addApptCustName.getValue())
                    , addApptUserID.getValue()
                    , DBContacts.getContactID(addApptContact.getValue())
                    );

            Alert submit = new Alert(Alert.AlertType.INFORMATION);
            submit.initModality(Modality.NONE);
            submit.setTitle("Thank You!");
            submit.setHeaderText("Your appointment has been saved.");
            Optional<ButtonType> results = submit.showAndWait();
            if(results.get() == ButtonType.OK) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../resources/All_Appointments_Scene.fxml"));
                Parent submitApptRoot = loader.load();
                Stage submitApptStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene submitApptScene = new Scene(submitApptRoot);
                submitApptStage.setScene(submitApptScene);
                submitApptStage.show();
            }
//        } else {
//            Alert error = new Alert(Alert.AlertType.ERROR);
//            error.setTitle("Missing Information.");
//            error.setHeaderText("Please make sure you have filled out each text field.");
//            Optional<ButtonType> results = error.showAndWait();
//            }


    }

    // Cancel Button Action Event
    @FXML
    void addApptCancelBtnClicked(ActionEvent event) throws IOException {
        // when triggered an alert will ask for conformation before taking them back to All_Appointments_Scene.
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Confirm Cancel");
        alert.setHeaderText("Do you wish to Cancel?");
        alert.setContentText("Appointment information will not be saved.");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../resources/All_Appointments_Scene.fxml"));
            Parent cancelApptRoot = loader.load();

            Stage cancelApptStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene cancelApptScene = new Scene(cancelApptRoot);
            cancelApptStage.setScene(cancelApptScene);
            cancelApptStage.show();
        }
    }

    // checks to make sure each variable contains a value.
    public Boolean isValid() {
        // checks text fields/combo-boxes to ensure each contains a value.
        if (addApptTitle.getText().isEmpty() || addApptTitle.getText() == null) {
            return false;
        } else if (addApptDescript.getText().isEmpty() || addApptDescript.getText() == null) {
           return false;
        } else if (addApptLocale.getText().isEmpty() || addApptLocale.getText() == null) {
            return false;
        }
//        else if (addApptContact.getEditor().getText().isEmpty() || addApptContact.getEditor().getText() == null) {
//            return false;
//        }
//
//        Fix: combo-boxes are not validating..
//        else if (addApptType.getItems().isEmpty() || addApptType.getItems() == null) {
//            System.out.println(addApptType.getItems());
//            return false;
//        } // was trying out different conditions in addApptType
//        else if (addApptStrtTime.getEditor().getText().isEmpty() || addApptStrtTime.getEditor().getText() == null) {
//            return false;
//        } else if (addApptEndTime.getEditor().getText().isEmpty() || addApptEndTime.getEditor().getText() == null) {
//            return false;
//        } else if (addApptCustID.getEditor().getText().isEmpty() || addApptCustID.getEditor().getText() == null) {
//            return false;
//        } else if (addApptCustName.getEditor().getText().isEmpty() || addApptCustName.getEditor().getText() == null) {
//            return false;
//        }
//        else if (addApptUserID.getEditor().getText().isEmpty() || addApptUserID.getEditor().getText() == null) {
//            return false;
//        }
            return true;
    }




}

