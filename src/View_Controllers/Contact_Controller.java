package View_Controllers;

import DBAccess.DBAppointments;
import Models.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class Contact_Controller implements Initializable {

    @FXML private Button contactSearchBtn;
    @FXML private ComboBox<String> contactNamesCombo;
    @FXML private TextField contactSearchText;
    @FXML private TableView<Appointments> contactTable;
    @FXML private TableColumn<Appointments, Integer> contactApptID;
    @FXML private TableColumn<Appointments, String> contactApptTitle;
    @FXML private TableColumn<Appointments, String> contactApptType;
    @FXML private TableColumn<Appointments, String> contactApptDescript;
    @FXML private TableColumn<Appointments, LocalDateTime> contactApptStrtTime;
    @FXML private TableColumn<Appointments, LocalDateTime> contactApptEndTime;
    @FXML private TableColumn<Appointments, Integer> contactApptCustID;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> newContactNames = Appointments.getContactNames();
        newContactNames.add("All Contacts");
        contactNamesCombo.setItems(newContactNames.sorted());
        contactNamesCombo.setValue("All Contacts");
        contactTable.setItems(DBAppointments.getAllAppts());
        contactApptID.setCellValueFactory(cellData -> cellData.getValue().getAppointmentID().asObject());
        contactApptTitle.setCellValueFactory(cellData -> cellData.getValue().getTitle());
        contactApptDescript.setCellValueFactory(cellData -> cellData.getValue().getDescription());
        contactApptType.setCellValueFactory(cellData -> cellData.getValue().getType());
        contactApptStrtTime.setCellValueFactory(cellData -> cellData.getValue().getStart());
        contactApptEndTime.setCellValueFactory(cellData -> cellData.getValue().getEnd());
        contactApptCustID.setCellValueFactory(cellData -> cellData.getValue().getCustomerID().asObject());
    }

    @FXML
    void changeContactName(ActionEvent event) {
        if(contactNamesCombo.getValue() == "All Contacts") {
            contactTable.setItems(DBAppointments.getAllAppts());
            contactApptID.setCellValueFactory(cellData -> cellData.getValue().getAppointmentID().asObject());
            contactApptTitle.setCellValueFactory(cellData -> cellData.getValue().getTitle());
            contactApptDescript.setCellValueFactory(cellData -> cellData.getValue().getDescription());
            contactApptType.setCellValueFactory(cellData -> cellData.getValue().getType());
            contactApptStrtTime.setCellValueFactory(cellData -> cellData.getValue().getStart());
            contactApptEndTime.setCellValueFactory(cellData -> cellData.getValue().getEnd());
            contactApptCustID.setCellValueFactory(cellData -> cellData.getValue().getCustomerID().asObject());
        } else {
            contactTable.setItems(DBAppointments.getSpecificAppt(contactNamesCombo.getValue()));
            contactApptID.setCellValueFactory(cellData -> cellData.getValue().getAppointmentID().asObject());
            contactApptTitle.setCellValueFactory(cellData -> cellData.getValue().getTitle());
            contactApptDescript.setCellValueFactory(cellData -> cellData.getValue().getDescription());
            contactApptType.setCellValueFactory(cellData -> cellData.getValue().getType());
            contactApptStrtTime.setCellValueFactory(cellData -> cellData.getValue().getStart());
            contactApptEndTime.setCellValueFactory(cellData -> cellData.getValue().getEnd());
            contactApptCustID.setCellValueFactory(cellData -> cellData.getValue().getCustomerID().asObject());
        }
    }

    // Search Button Action Event
    @FXML
    void contactSearchBtnClicked(ActionEvent event) {

    }

    // View All Customers Button Action Event
    @FXML
    void viewAllCustBtnClicked(ActionEvent event) throws IOException {
        // when triggered this will take the user to All_Customer_Records_Scene.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../resources/All_Customer_Records_Scene.fxml"));
        Parent viewCustsRoot = loader.load();
        Stage viewCustsStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene viewCustsScene = new Scene(viewCustsRoot);
        viewCustsStage.setScene(viewCustsScene);
        viewCustsStage.show();
    }

    // View All Appointments Button Action Event
    @FXML
    void viewAllApptsBtnClicked(ActionEvent event) throws IOException {
        // when triggered this will take the user to All_Appointment_Records_Scene.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../resources/All_Appointments_Scene.fxml"));
        Parent viewApptsRoot = loader.load();
        Stage viewApptsStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene viewApptsScene = new Scene(viewApptsRoot);
        viewApptsStage.setScene(viewApptsScene);
        viewApptsStage.show();
    }

    // Log out Button Action Event
    @FXML
    void contactLogOutBtnClicked(ActionEvent event) throws IOException {
        // when triggered this will take the user to Login_Scene.
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
