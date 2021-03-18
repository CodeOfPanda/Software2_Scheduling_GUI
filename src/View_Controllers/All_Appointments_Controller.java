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
*           query the DB to fill the drop-down. X
* a custom message is displayed in the user interface with the Appointment_ID and type of appointment cancelled. --> on delete.
* the Appointment_ID is auto-generated and disabled throughout the application --> get from the DB
*           the database has an auto-gen section in the DB already.
*
* write code that enables the user to adjust appt times.
*       appt times should be stored in Coordinated Universal Time (UTC)
*       they should be automatically and consistently updated according to the local time zone set on the user's computer.
*
* Application must allow the date/times to be changed
*
* NOTE: there are up to three time zones in effect. UTC, EST, and system default. (local time will be checked against
* EST business hrs, (8 - 10) including weekends, before they are stored in the DB as UTC).
*/

public class All_Appointments_Controller implements Initializable {

    @FXML private TextField allApptSearch;
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
        allApptID.setCellValueFactory(cellData -> cellData.getValue().getAppointmentID().asObject());
        allApptTitle.setCellValueFactory(cellData -> cellData.getValue().getTitle());
        allApptDescript.setCellValueFactory(cellData -> cellData.getValue().getDescription());
        allApptLocale.setCellValueFactory(cellData -> cellData.getValue().getLocation());
        allApptCont.setCellValueFactory(cellData -> cellData.getValue().getApptContactID().asObject());
        allApptType.setCellValueFactory(cellData -> cellData.getValue().getType());
        allApptStrtTime.setCellValueFactory(cellData -> cellData.getValue().getStart());
        allApptEndTime.setCellValueFactory(cellData -> cellData.getValue().getEnd());
        allApptCustID.setCellValueFactory(cellData -> cellData.getValue().getCustomerID().asObject());
    }



    /* ******************** new scene-builder methods ************************/
    // all radio button action event
    @FXML
    public void allRadioClicked(ActionEvent actionEvent) {
    }

    // monthly radio button action event
    @FXML
    public void monthlyRadioClicked(ActionEvent actionEvent) {
    }

    // weekly radio button action event
    @FXML
    public void weeklyRadioClicked(ActionEvent actionEvent) {
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
        // when triggered this takes the user to the Update_Appointment_Scene.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../resources/Update_Appointment_Scene.fxml"));
        Parent modApptRoot = loader.load();
        Stage modApptStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene modApptScene = new Scene(modApptRoot);
        modApptStage.setScene(modApptScene);
        modApptStage.show();
    }

    // delete appointment action event
    @FXML
    public void deleteApptBtn(ActionEvent actionEvent) {
        // when triggered this asks user for confirmation before deleting an appointment.
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

    // records dashboard button action event
    @FXML
    public void recordsDashBtnClicked(ActionEvent actionEvent) {
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
