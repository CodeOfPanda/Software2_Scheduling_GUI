package View_Controllers;

import DBAccess.DBAppointments;
import Models.Appointments;
import javafx.collections.ObservableList;
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

public class Contact_Controller implements Initializable {

    @FXML private ComboBox<String> contactNamesCombo;
    @FXML private TableView<Appointments> contactTable;
    @FXML private TableColumn<Appointments, Integer> contactApptID;
    @FXML private TableColumn<Appointments, String> contactApptTitle;
    @FXML private TableColumn<Appointments, String> contactApptType;
    @FXML private TableColumn<Appointments, String> contactApptDescript;
    @FXML private TableColumn<Appointments, LocalDateTime> contactApptStrtTime;
    @FXML private TableColumn<Appointments, LocalDateTime> contactApptEndTime;
    @FXML private TableColumn<Appointments, Integer> contactApptCustID;

    /** This is the initialize method that sets the cell values in the table view.
     *  This method sets the initial cell values for the Contacts appointment table view.
     *  In this method I use a Lambda expression to populate the cell values for the start and end time columns, this lambda expression makes it easy to populate LocalDateTime ObjectProperties.
     *  @param url The URL
     *  @param resourceBundle The ResourceBundle*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> newContactNames = Appointments.getContactNames();
        newContactNames.add("All Contacts");
        contactNamesCombo.setItems(newContactNames.sorted());
        contactNamesCombo.setValue("All Contacts");
        contactTable.setItems(DBAppointments.getAllAppts());
        contactApptID.setCellValueFactory(new PropertyValueFactory<>("ApptID"));
        contactApptTitle.setCellValueFactory(new PropertyValueFactory<>("ApptTitle"));
        contactApptDescript.setCellValueFactory(new PropertyValueFactory<>("ApptDescript"));
        contactApptType.setCellValueFactory(new PropertyValueFactory<>("ApptType"));
        //lambda expression
        contactApptStrtTime.setCellValueFactory(cellData -> cellData.getValue().getStart());
        //lambda expression
        contactApptEndTime.setCellValueFactory(cellData -> cellData.getValue().getEnd());
        contactApptCustID.setCellValueFactory(new PropertyValueFactory<>("ApptCustomerID"));
    }

    /** This method is the action event for when a contact name is selected from the Contact name combo-box.
     *  This method filters the Contact information based off of the Contact that has been selected in the combo-box.
     *  @param event When a Contact name is selected from the combo-box.*/
    @FXML
    void changeContactName(ActionEvent event) {
        if(contactNamesCombo.getValue() == "All Contacts") {
            contactTable.setItems(DBAppointments.getAllAppts());
            contactApptID.setCellValueFactory(new PropertyValueFactory<>("ApptID"));
            contactApptTitle.setCellValueFactory(new PropertyValueFactory<>("ApptTitle"));
            contactApptDescript.setCellValueFactory(new PropertyValueFactory<>("ApptDescript"));
            contactApptType.setCellValueFactory(new PropertyValueFactory<>("ApptType"));
            //lambda expression
            contactApptStrtTime.setCellValueFactory(cellData -> cellData.getValue().getStart());
            //lambda expression
            contactApptEndTime.setCellValueFactory(cellData -> cellData.getValue().getEnd());
            contactApptCustID.setCellValueFactory(new PropertyValueFactory<>("ApptCustomerID"));
        } else {
            contactTable.setItems(DBAppointments.getSpecificAppt(contactNamesCombo.getValue()));
            contactApptID.setCellValueFactory(new PropertyValueFactory<>("ApptID"));
            contactApptTitle.setCellValueFactory(new PropertyValueFactory<>("ApptTitle"));
            contactApptDescript.setCellValueFactory(new PropertyValueFactory<>("ApptDescript"));
            contactApptType.setCellValueFactory(new PropertyValueFactory<>("ApptType"));
            //lambda expression
            contactApptStrtTime.setCellValueFactory(cellData -> cellData.getValue().getStart());
            //lambda expression
            contactApptEndTime.setCellValueFactory(cellData -> cellData.getValue().getEnd());
            contactApptCustID.setCellValueFactory(new PropertyValueFactory<>("ApptCustomerID"));
        }
    }

    /** This method is the action event for when the View All Customers Button is clicked.
     *  This method takes the user to the All_Customer_Records_Scene.
     *  @param event When the button is clicked.*/
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

    /** This method is the action event for when the View All Appointments Button is clicked.
     *  This method takes the user to the All_Appointments_Scene.
     *  @param event When the button is clicked.*/
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

    /** This is the method for the action event for when the log out button is clicked.
     *  This method confirms that the user wants to log out and does if the user confirms.
     *  @param event When the button is clicked*/
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
