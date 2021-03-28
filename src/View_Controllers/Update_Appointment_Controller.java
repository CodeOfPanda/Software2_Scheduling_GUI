package View_Controllers;

import DBAccess.DBAppointments;
import DBAccess.DBContacts;
import DBAccess.DBCustomers;
import DBAccess.DBUsers;
import Models.Appointments;
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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

/** This class is the controller for the Update_Appointment_Scene.fxml.*/
public class Update_Appointment_Controller {

    @FXML private TextField modApptID;
    @FXML private TextField modApptTitle;
    @FXML private TextField modApptDescript;
    @FXML private TextField modApptLocale;
    @FXML private ComboBox<String> modApptContact;
    @FXML private ComboBox<String> modApptType;
    @FXML private DatePicker modApptDate;
    @FXML private ComboBox<LocalTime> modStartTime;
    @FXML private ComboBox<LocalTime> modApptEndTime;
    @FXML private ComboBox<String> modApptCustName;
    @FXML private ComboBox<Integer> modApptUserID;

    /** This method acts as my initialize method and pre-populates the text fields and como-boxes for the update scene.
     *  This method populates the text fields and combo-boxes with the selected appointment that is being passed in as a parameter.
     *  @param appt The appointment that was selected to be updated*/
    // my initialize method to pre-populate the update appointment page.
    @FXML
    public void startApptUpdate(Appointments appt) {
        String apptID = String.valueOf(appt.getApptID());
        modApptID.setText(apptID);
        modApptID.setEditable(false);
        modApptTitle.setText(appt.getApptTitle());
        modApptDescript.setText(appt.getApptDescript());
        modApptLocale.setText(appt.getApptLocation());
        modApptContact.setItems(Appointments.getContactNames());
        modApptContact.setValue(DBContacts.getContactName(appt.getContact()));
        modApptType.setItems(Appointments.getAllApptTypes());
        modApptType.setValue(appt.getApptType());
        modApptCustName.setItems(Appointments.getCustomerNames());
        modApptCustName.setValue(DBCustomers.getCustomerName(appt.getApptCustomerID()));
        modApptDate.setValue(appt.getApptStart().toLocalDate());
        modApptDate.setValue(appt.getApptEnd().toLocalDate());
        modApptUserID.setItems(Appointments.getUserIDs());
        modApptUserID.setValue(appt.getApptUserID());
        modStartTime.setItems(Appointments.getStartWorkHours());
        modStartTime.setValue(appt.getApptStart().toLocalTime());
        modApptEndTime.setItems(Appointments.getEndWorkHours());
        modApptEndTime.setValue(appt.getApptEnd().toLocalTime());

    }

    /** This method is the action event for when the submit button is clicked.
     *  This method updates a specific appointments with the user's inputs and then takes the user back to the All_Appointments_Scene.
     *  @param event When the button is clicked.*/
    @FXML
    public void modApptSubmitBtnClicked(ActionEvent event) throws IOException {
        // when triggered an information alert will inform the user that their changes have been saved and take them
        // back to All_Appointments_Scene.
        String appt_ID = String.valueOf(modApptID.getText());

        if(isValid()){
            if (apptTimeIsValid()) {
                DBAppointments.modifyAppt(Integer.parseInt(appt_ID)
                        , modApptTitle.getText()
                        , modApptDescript.getText()
                        , modApptLocale.getText()
                        , modApptType.getValue()
                        , LocalDateTime.of(modApptDate.getValue(), modStartTime.getValue())
                        , LocalDateTime.of(modApptDate.getValue(), modApptEndTime.getValue())
                        , Appointments.getCurrentDateTime()
                        , DBUsers.getUserName(modApptUserID.getValue())
                        , DBCustomers.getCustomerID(modApptCustName.getValue())
                        , modApptUserID.getValue()
                        , DBContacts.getContactID(modApptContact.getValue()));
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
                error.setTitle("Conflicting Appointment Times");
                error.setHeaderText("Please make sure your appointment times do not overlap with another existing appointment on the same day");
                Optional<ButtonType> results = error.showAndWait();
            }
        } else {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Missing Information.");
            error.setHeaderText("Please make sure you have filled out each text field.");
            Optional<ButtonType> results = error.showAndWait();
        }
    }

    /** This method for the action event for when the cancel button is clicked.
     *  This method asks the user for confirmation before taking them back to the All_Appointments_Scene.
     *  @param event When the button is clicked*/
    @FXML
    public void modApptCancelBtnClicked(ActionEvent event) throws IOException {
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
        if (modApptTitle.getText().isEmpty() || modApptTitle.getText() == null) {
            return false;
        } else if (modApptDescript.getText().isEmpty() || modApptDescript.getText() == null) {
            return false;
        } else if (modApptLocale.getText().isEmpty() || modApptLocale.getText() == null) {
            return false;
        }
        return true;
    }

    public Boolean apptTimeIsValid() {

        AtomicReference<Boolean> valid = new AtomicReference<>(true);
        DBAppointments.getSpecificCustomerAppts(modApptCustName.getValue()).forEach((appt) -> {
            LocalDateTime newStart = LocalDateTime.of(modApptDate.getValue(), modStartTime.getValue());
            LocalDateTime newEnd = LocalDateTime.of(modApptDate.getValue(), modApptEndTime.getValue());

            //For modify: don't check against itself
            if (Integer.parseInt(modApptID.getText()) != appt.getApptID()) {

                //if the updated appointment and the appt in this loop is on the same day:
                if (newStart.toLocalDate().isEqual(appt.getApptStart().toLocalDate())) {

                    //check if newStartTime is within the appt.getApptStart and appt.getApptEnd
                    if ((newStart.toLocalTime().equals(appt.getApptStart().toLocalTime()) ||
                            newStart.toLocalTime().isAfter(appt.getApptStart().toLocalTime()))
                            & newStart.toLocalTime().isBefore(appt.getApptEnd().toLocalTime())) {
                        valid.set(false);
                    }

                    //check if newEndTime is after appt.getApptStart and before appt.getApptEnd
                    else if (newEnd.toLocalTime().isAfter(appt.getApptStart().toLocalTime())
                            & (newEnd.toLocalTime().equals(appt.getApptEnd().toLocalTime())
                            || newEnd.toLocalTime().isBefore(appt.getApptEnd().toLocalTime()))) {
                        valid.set(false);
                    }

                    //check for encapsulated appt
                    else if ((newStart.toLocalTime().equals(appt.getApptStart().toLocalTime())
                            || newStart.toLocalTime().isBefore(appt.getApptStart().toLocalTime()))
                            & (newEnd.toLocalTime().equals(appt.getApptEnd().toLocalTime())
                            || newEnd.toLocalTime().isAfter(appt.getApptEnd().toLocalTime()))) {
                        valid.set(false);
                    }
                    else if (newStart.toLocalTime().isAfter(newEnd.toLocalTime()) || newStart.toLocalTime().equals(newEnd.toLocalTime())) {
                        valid.set(false);
                    }
                }
            } else {
                //checking the same appt
            }
        });

        if (valid.get()) {
            return true;
        } else {
            return false;
        }

    }
}

//check if newEndTime is within with appt.getApptStart and appt.getApptEnd
//check if newStartTime is < appt.getApptStart and newEndTime is > than appt.getApptEnd