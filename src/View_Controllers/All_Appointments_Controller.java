package View_Controllers;

import DBAccess.DBAppointments;
import Models.Appointments;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

/*
* a contact name is assigned to an appointment using a drop-down menu or combo box. --> Contact Table
*           query the DB to fill the drop-down. (done)
* a custom message is displayed in the user interface with the Appointment_ID and type of appointment cancelled. --> on delete.
* the Appointment_ID is auto-generated and disabled throughout the application --> get from the DB
*           the database has an auto-gen section in the DB already. (done)
*
* write code that enables the user to adjust appt times.
*       appt times should be stored in Coordinated Universal Time (UTC) (done)
*       they should be automatically and consistently updated according to the local time zone set on the user's computer.
*
* Application must allow the date/times to be changed (done)
*
* NOTE: there are up to three time zones in effect. UTC, EST, and system default. (local time will be checked against
* EST business hrs, (8 - 10) including weekends, before they are stored in the DB as UTC).
*/

public class All_Appointments_Controller implements Initializable {

    @FXML private TextField allApptSearch;
    @FXML private ToggleGroup apptToggleGroup;
    @FXML private TableView<Appointments> allApptTable;
    @FXML private TableColumn<Appointments, Integer> allApptID;
    @FXML private TableColumn<Appointments, String> allApptTitle;
    @FXML private TableColumn<Appointments, String> allApptDescript;
    @FXML private TableColumn<Appointments, String> allApptLocale;
    @FXML private TableColumn<Appointments, Integer> allApptCont;
    @FXML private TableColumn<Appointments, String> allApptType;
    @FXML private TableColumn<Appointments, LocalDateTime> allApptStrtTime;
    @FXML private TableColumn<Appointments, LocalDateTime> allApptEndTime;
    @FXML private TableColumn<Appointments, Integer> allApptCustID;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allApptTable.setItems(DBAppointments.getAllAppts());
        allApptID.setCellValueFactory(new PropertyValueFactory<>("ApptID"));
        allApptTitle.setCellValueFactory(new PropertyValueFactory<>("ApptTitle"));
        allApptDescript.setCellValueFactory(new PropertyValueFactory<>("ApptDescript"));
        allApptLocale.setCellValueFactory(new PropertyValueFactory<>("ApptLocation"));
        allApptCont.setCellValueFactory(new PropertyValueFactory<>("Contact"));
        allApptType.setCellValueFactory(new PropertyValueFactory<>("ApptType"));
        allApptStrtTime.setCellValueFactory(cellData -> cellData.getValue().getStart());
        allApptEndTime.setCellValueFactory(cellData -> cellData.getValue().getEnd());
        allApptCustID.setCellValueFactory(new PropertyValueFactory<>("ApptCustomerID"));
    }

    // all radio button action event
    @FXML
    void toggleAppts(ActionEvent event) {
        RadioButton selectedRadioButton = (RadioButton) apptToggleGroup.getSelectedToggle();
        String radioButtonText = selectedRadioButton.getText();
        // sets table to appropriate information based off of which radio button is selected
        if (radioButtonText.equals("All")) {
            allApptTable.setItems(DBAppointments.getAllAppts());
            allApptID.setCellValueFactory(new PropertyValueFactory<>("ApptID"));
            allApptTitle.setCellValueFactory(new PropertyValueFactory<>("ApptTitle"));
            allApptDescript.setCellValueFactory(new PropertyValueFactory<>("ApptDescript"));
            allApptLocale.setCellValueFactory(new PropertyValueFactory<>("ApptLocation"));
            allApptCont.setCellValueFactory(new PropertyValueFactory<>("Contact"));
            allApptType.setCellValueFactory(new PropertyValueFactory<>("ApptType"));
            allApptStrtTime.setCellValueFactory(cellData -> cellData.getValue().getStart());
            allApptEndTime.setCellValueFactory(cellData -> cellData.getValue().getEnd());
            allApptCustID.setCellValueFactory(new PropertyValueFactory<>("ApptCustomerID"));
        } else if (radioButtonText.equals("Monthly")) {
            allApptTable.setItems(DBAppointments.getApptsThisMonth());
            allApptID.setCellValueFactory(new PropertyValueFactory<>("ApptID"));
            allApptTitle.setCellValueFactory(new PropertyValueFactory<>("ApptTitle"));
            allApptDescript.setCellValueFactory(new PropertyValueFactory<>("ApptDescript"));
            allApptLocale.setCellValueFactory(new PropertyValueFactory<>("ApptLocation"));
            allApptCont.setCellValueFactory(new PropertyValueFactory<>("Contact"));
            allApptType.setCellValueFactory(new PropertyValueFactory<>("ApptType"));
            allApptStrtTime.setCellValueFactory(cellData -> cellData.getValue().getStart());
            allApptEndTime.setCellValueFactory(cellData -> cellData.getValue().getEnd());
            allApptCustID.setCellValueFactory(new PropertyValueFactory<>("ApptCustomerID"));
        } else if (radioButtonText.equals("Weekly")) {
            allApptTable.setItems(DBAppointments.getApptsThisWeek());
            allApptID.setCellValueFactory(new PropertyValueFactory<>("ApptID"));
            allApptTitle.setCellValueFactory(new PropertyValueFactory<>("ApptTitle"));
            allApptDescript.setCellValueFactory(new PropertyValueFactory<>("ApptDescript"));
            allApptLocale.setCellValueFactory(new PropertyValueFactory<>("ApptLocation"));
            allApptCont.setCellValueFactory(new PropertyValueFactory<>("Contact"));
            allApptType.setCellValueFactory(new PropertyValueFactory<>("ApptType"));
            allApptStrtTime.setCellValueFactory(cellData -> cellData.getValue().getStart());
            allApptEndTime.setCellValueFactory(cellData -> cellData.getValue().getEnd());
            allApptCustID.setCellValueFactory(new PropertyValueFactory<>("ApptCustomerID"));
        }

    }

    // Search Button Action Event
    @FXML
    public void allApptSearchBtnClicked(ActionEvent event) {

    }

    // add appointment button action event
    @FXML
    public void addApptBtn(ActionEvent event) throws IOException {
        // when triggered this takes the user to the Add_Appointment_Scene.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../resources/Add_Appointment_Scene.fxml"));
        Parent addApptRoot = loader.load();
        Stage addApptStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene addApptScene = new Scene(addApptRoot);
        addApptStage.setScene(addApptScene);
        addApptStage.show();
    }

    // update appointment button action event
    @FXML
    public void updateApptBtn(ActionEvent event) throws IOException {
        // when triggered this method checks to make sure an appointment was selected
        Appointments selectedAppt = allApptTable.getSelectionModel().getSelectedItem();
        if(selectedAppt != null) {
            updateAppt(event, selectedAppt);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("No appointment selected");
            alert.setHeaderText("Please select an appointment from the table to update.");
            alert.showAndWait();
        }
    }

    // this method makes it possible for me to pass in the selected appointment and use that data in my update appt controller.
    public void updateAppt(ActionEvent event, Appointments selectedAppt) throws IOException{
        // takes user to update appt scene
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../resources/Update_Appointment_Scene.fxml"));
        Parent modApptRoot = loader.load();
        Stage modApptStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene modApptScene = new Scene(modApptRoot);
        Update_Appointment_Scene controller = loader.getController();
        controller.startApptUpdate(selectedAppt);
        modApptStage.setScene(modApptScene);
        modApptStage.show();
    }

    // delete appointment action event
    @FXML
    public void deleteApptBtn(ActionEvent event) throws IOException {
        // when triggered this asks user for confirmation before deleting an appointment.
        Appointments selectedAppt = allApptTable.getSelectionModel().getSelectedItem();
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        if (selectedAppt != null) {
            confirmation.setHeaderText("Please confirm your action.");
            confirmation.setContentText("Select OK if you wish to delete.");
            Optional<ButtonType> result = confirmation.showAndWait();
            if(result.get() == ButtonType.OK) {
                DBAppointments.deleteAppt(selectedAppt.getApptID());
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../resources/All_Appointments_Scene.fxml"));
                Parent allApptRoot = loader.load();
                Stage allApptStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene allApptScene = new Scene(allApptRoot);
                allApptStage.setScene(allApptScene);
                allApptStage.show();
            }
        } else {
            confirmation.setTitle("No Appointment selected");
            confirmation.setHeaderText("Please select an Appointment from the list to delete");
            confirmation.showAndWait();
        }
    }

    // customer dashboard button action event
    @FXML
    public void customerDashBtn(ActionEvent event) throws IOException {
        //when triggered this takes the user to All_Customer_Records_Scene.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../resources/All_Customer_Records_Scene.fxml"));
        Parent allCustomersRoot = loader.load();
        Stage allCustStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene allCustScene = new Scene(allCustomersRoot);
        allCustStage.setScene(allCustScene);
        allCustStage.show();
    }

    // contact dashboard button action event
    @FXML
    public void contactDashBtnClicked(ActionEvent event) throws IOException {
        //when triggered this takes the user to Contact_Dashboard_Scene.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../resources/Contact_Dashboard_Scene.fxml"));
        Parent contDashRoot = loader.load();
        Stage contDashStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene contDashScene = new Scene(contDashRoot);
        contDashStage.setScene(contDashScene);
        contDashStage.show();
    }

    // reports dashboard button action event
    @FXML
    public void reportsDashBtnClicked(ActionEvent event) throws IOException {
        // when triggered this takes the user to the Reports_Dashboard_Scene.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../resources/Reports_Dashboard_Scene.fxml"));
        Parent repoDashRoot = loader.load();
        Stage repoDashStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene repoDashScene = new Scene(repoDashRoot);
        repoDashStage.setScene(repoDashScene);
        repoDashStage.show();
    }

    // log out button action event
    @FXML
    public void apptLogOutBtnClicked(ActionEvent event) throws IOException {
        //when triggered this takes the user back to the Login_Scene.
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Confirm Log out");
        alert.setHeaderText("Are you sure you want to Log out?");
        Optional<ButtonType> result = alert.showAndWait();
        // confirms log out request
        if (result.get() == ButtonType.OK) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../resources/Login_Scene.fxml"));
            Parent loginRoot = loader.load();

            Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene loginScene = new Scene(loginRoot);
            loginStage.setScene(loginScene);
            loginStage.show();
        }
    }
}
