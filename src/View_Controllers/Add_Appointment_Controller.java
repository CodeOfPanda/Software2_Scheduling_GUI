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

/** This class is the Add_Appointment_Controller for the Add_Appointment_Scene.*/
public class Add_Appointment_Controller implements Initializable {

    @FXML private TextField addApptID;
    @FXML private TextField addApptTitle;
    @FXML private TextField addApptDescript;
    @FXML private TextField addApptLocale;
    @FXML private ComboBox<String> addApptContact;
    @FXML private DatePicker addApptDate;
    @FXML private ComboBox<String> addApptType;
    @FXML private ComboBox<LocalTime> addApptStartTime;
    @FXML private ComboBox<LocalTime> addApptEndTime;
    @FXML private ComboBox<String> addApptCustName;
    @FXML private ComboBox<Integer> addApptUserID;

    /** This is the initialize method that sets the initial values for the fxml text fields and combo-boxes.
     *  This method populates the combo-boxes with appointment and customer information.
     *  @param url The URL
     *  @param resourceBundle The ResourceBundle*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addApptID.setEditable(false);
        addApptContact.setItems(Appointments.getContactNames());
        addApptCustName.setItems(Appointments.getCustomerNames());
        addApptType.setItems(Appointments.getAllApptTypes());
        addApptUserID.setItems(Appointments.getUserIDs());
        addApptStartTime.setItems(Appointments.getStartWorkHours());
        addApptEndTime.setItems(Appointments.getEndWorkHours());
    }

    /** This method is the action event for when the submit button is clicked.
     *  This method calls the method to create a new Appointment and takes the user to the All_Appointment_Scene.
     *  @param event When the button is clicked*/
    @FXML
    void addApptSubmitBtnClicked(ActionEvent event) throws IOException {
        // when triggered an information alert will inform the user that their changes have been saved and take them
        // back to All_Appointments_Scene.

        if(isValid()) {
           // inserting into database
            DBAppointments.createAppt(addApptTitle.getText()
                    , addApptDescript.getText()
                    , addApptLocale.getText()
                    , addApptType.getValue()
                    , LocalDateTime.of(addApptDate.getValue(), addApptStartTime.getValue())
                    , LocalDateTime.of(addApptDate.getValue(), addApptEndTime.getValue())
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
        } else {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Missing Information.");
            error.setHeaderText("Please make sure you have filled out each text field.");
            Optional<ButtonType> results = error.showAndWait();
            }
    }

    /** This method is the action event for when the cancel button is clicked.
     *  This method asks the user for confirmation before taking them back to the All_Appointments_Scene.
     *  @param event When the button is clicked*/
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

    /** This method checks the text fields and combo-boxes to ensure each contains the correct value and returns a boolean value.
     *  This method checks the text fields and combo-boxes to ensure they are not null or not equal to empty, then returns a boolean value.
     *  @return Returns a boolean value of true or false*/
    public Boolean isValid() {
        // checks text fields/combo-boxes to ensure each contains a value.
        if (addApptTitle.getText().isEmpty() || addApptTitle.getText() == null) {
            return false;
        } else if (addApptDescript.getText().isEmpty() || addApptDescript.getText() == null) {
           return false;
        } else if (addApptLocale.getText().isEmpty() || addApptLocale.getText() == null) {
            return false;
        } else if (addApptContact.getValue() == null) {
            return false;
        }else if (addApptType.getValue() == null) {
            return false;
        } else if (addApptStartTime.getValue() == null) {
            return false;
        } else if (addApptEndTime.getValue() == null) {
            return false;
        } else if (addApptCustName.getValue() == null) {
            return false;
        }else if (addApptUserID.getValue() == null) {
            return false;
        }
            return true;
    }

}

