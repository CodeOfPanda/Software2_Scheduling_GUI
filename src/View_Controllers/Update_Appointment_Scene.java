package View_Controllers;

import DBAccess.DBAppointments;
import DBAccess.DBContacts;
import DBAccess.DBCustomers;
import Models.Appointments;
import Models.Contacts;
import Models.Customers;
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
    @FXML private ComboBox<?> modApptStartTime;
    @FXML private DatePicker modApptEndDate;
    @FXML private ComboBox<?> modApptEndTime;
    // ask if i need to keep customer id in the GUI or if it can be auto - generated off of the name?
//    @FXML private ComboBox<Integer> modApptCustID;
    @FXML private ComboBox<String> modApptCustName;
    @FXML private ComboBox<Integer> modApptUserID;
    @FXML private Button modApptSubmitBtn;
    @FXML private Button modApptCancelBtn;

    // my initialize method to pre-populate the update appointment page.
    @FXML
    public void startApptUpdate(Appointments appt) {
        modApptID.setText(appt.getApptID());
        modApptID.setEditable(false);
        modApptTitle.setText(appt.getApptTitle());
        modApptDescript.setText(appt.getApptDescript());
        modApptLocale.setText(appt.getApptLocation());
//        modApptContact.getItems().clear();
        modApptContact.setItems(Appointments.getContactNames());
        modApptContact.setValue(DBContacts.getContactName(appt.getContact()));
        modApptType.setItems(Appointments.getAllApptTypes());
        modApptType.setValue(appt.getApptType());
        modApptCustName.setItems(Appointments.getCustomerNames());
        modApptCustName.setValue(DBCustomers.getCustomerName(appt.getApptCustomerID()));
        modApptStartDate.setValue(appt.getApptStart().toLocalDate());
        modApptEndDate.setValue(appt.getApptEnd().toLocalDate());
    }

    // Submit Button Action Event
    @FXML
    public void modApptSubmitBtnClicked(ActionEvent event) throws IOException {
        // when triggered an information alert will inform the user that their changes have been saved and take them
        // back to All_Appointments_Scene.
        String appt_ID = String.valueOf(modApptID.getText());

        if(isValid()){
            DBAppointments.modifyAppt(Integer.parseInt(appt_ID), modApptTitle.getText(), modApptDescript.getText()
                    ,modApptLocale.getText(), modApptType.getValue(),modApptStartDate.getValue().atStartOfDay()
                    ,modApptEndDate.getValue().atStartOfDay(), Appointments.getApptCreatedBy(), Appointments.getCurrentDateTime()
                    ,Appointments.getApptLastUpdatedBy(),Customers.getCustID(), /*UserID, */ Contacts.getCtID());
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
