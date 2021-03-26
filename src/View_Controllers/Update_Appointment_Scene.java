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
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/*
* when adding/updating text fields are used to record:
*       apptID, title, description, location, contact, type, start date & time, end date & time, custID, user.
*       but you can use a combo box or date picker as well for the contact, type, sd&t, ed&t, custID (may want to display the name), and user.
* all of the original appt info is displayed on the update form in the local time zone --> system default time.
* all of the appt fields can be updated except Appointment_ID, which must be disabled.
*/

// modified scene builder to fit new requirements


public class Update_Appointment_Scene {

    @FXML private TextField modApptID;
    @FXML private TextField modApptTitle;
    @FXML private TextField modApptDescript;
    @FXML private TextField modApptLocale;
    @FXML private ComboBox<String> modApptContact;
    @FXML private ComboBox<String> modApptType;
    @FXML private DatePicker modApptStartDate;
    @FXML private ComboBox<LocalTime> modStartTime;
    @FXML private DatePicker modApptEndDate;
    @FXML private ComboBox<LocalTime> modApptEndTime;
//    @FXML private ComboBox<Integer> modApptCustID;
    @FXML private ComboBox<String> modApptCustName;
    @FXML private ComboBox<Integer> modApptUserID;
    @FXML private Button modApptSubmitBtn;
    @FXML private Button modApptCancelBtn;

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
        modApptStartDate.setValue(appt.getApptStart().toLocalDate());
        modApptEndDate.setValue(appt.getApptEnd().toLocalDate());
        modApptUserID.setItems(Appointments.getUserIDs());
        modApptUserID.setValue(appt.getApptUserID());
        modStartTime.setItems(Appointments.getStartWorkHours());
        modStartTime.setValue(appt.getApptStart().toLocalTime());
        //.format(DateTimeFormatter.ofPattern("HH:mm")
        modApptEndTime.setItems(Appointments.getEndWorkHours());
        modApptEndTime.setValue(appt.getApptEnd().toLocalTime());

    }

    // Submit Button Action Event
    @FXML
    public void modApptSubmitBtnClicked(ActionEvent event) throws IOException {
        // when triggered an information alert will inform the user that their changes have been saved and take them
        // back to All_Appointments_Scene.
        String appt_ID = String.valueOf(modApptID.getText());

        if(isValid()){
            DBAppointments.modifyAppt(Integer.parseInt(appt_ID)
                    , modApptTitle.getText()
                    , modApptDescript.getText()
                    , modApptLocale.getText()
                    , modApptType.getValue()
                    , LocalDateTime.of(modApptStartDate.getValue(), modStartTime.getValue())
                    , LocalDateTime.of(modApptEndDate.getValue(), modApptEndTime.getValue())
                    , Appointments.getCurrentDateTime()
                    , DBUsers.getUserName(modApptUserID.getValue())
                    , DBCustomers.getCustomerID(modApptCustName.getValue())
                    , modApptUserID.getValue()
                    , DBContacts.getContactID(modApptContact.getValue()));
        }

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
    }

    // Cancel Button Action Event
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

    public Boolean isValid() {

        return true;
    }
}
