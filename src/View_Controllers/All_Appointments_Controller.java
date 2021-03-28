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

/** This class is the controller class for the All_Appointments_Scene.fxml.*/
public class All_Appointments_Controller implements Initializable {

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

    /** This is the initialize method that sets the cell values in the table view.
     *  This method sets the initial cell values for Appointments table view.
     *  In this method I use a Lambda expression to populate the cell values for the start and end time columns, this lambda expression makes it easy to populate LocalDateTime ObjectProperties.
     *  @param url The URL
     *  @param resourceBundle The ResourceBundle*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allApptTable.setItems(DBAppointments.getAllAppts());
        allApptID.setCellValueFactory(new PropertyValueFactory<>("ApptID"));
        allApptTitle.setCellValueFactory(new PropertyValueFactory<>("ApptTitle"));
        allApptDescript.setCellValueFactory(new PropertyValueFactory<>("ApptDescript"));
        allApptLocale.setCellValueFactory(new PropertyValueFactory<>("ApptLocation"));
        allApptCont.setCellValueFactory(new PropertyValueFactory<>("Contact"));
        allApptType.setCellValueFactory(new PropertyValueFactory<>("ApptType"));
        //lambda expression
        allApptStrtTime.setCellValueFactory(cellData -> cellData.getValue().getStart());
        //lambda expression
        allApptEndTime.setCellValueFactory(cellData -> cellData.getValue().getEnd());
        allApptCustID.setCellValueFactory(new PropertyValueFactory<>("ApptCustomerID"));
    }

    /** This method is the action event for when the radio buttons are toggled between.
     *  This method filters the appointments by the current week, current month, or all appointments and changes the records accordingly.
     *  <p>In this method I use a Lambda expression to populate the cell values for the start and end time columns, this lambda expression makes it easy to populate LocalDateTime ObjectProperties.</p>
     *  @param event When the buttons are toggles between.*/
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
            //lambda expression
            allApptStrtTime.setCellValueFactory(cellData -> cellData.getValue().getStart());
            //lambda expression
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
            //lambda expression
            allApptStrtTime.setCellValueFactory(cellData -> cellData.getValue().getStart());
            //lambda expression
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
            //lambda expression
            allApptStrtTime.setCellValueFactory(cellData -> cellData.getValue().getStart());
            //lambda expression
            allApptEndTime.setCellValueFactory(cellData -> cellData.getValue().getEnd());
            allApptCustID.setCellValueFactory(new PropertyValueFactory<>("ApptCustomerID"));
        }

    }


    /** This method is the action event for when the add appointment button is clicked.
     *  This method takes the user to the Add_Appointment_Scene.
     *  @param event When the button is clicked.*/
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

    /** This method is the action event for when the update appointment button is clicked.
     *  This method checks to make sure an appointment record has been selected and then passes that selected record to another method.
     *  @param event When the button is clicked.*/
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

    /** This method takes in the selected appointment and then takes the user to the Update_Customer_Scene.
     *  This method passes the selected appointment to the Update_Appointment_Controller to set the initial values in the text fields and combo-boxes.
     *  @param event When the update button is clicked
     *  @param selectedAppt The selected customer's record that needs to be updated.*/
    public void updateAppt(ActionEvent event, Appointments selectedAppt) throws IOException{
        // takes user to update appt scene
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../resources/Update_Appointment_Scene.fxml"));
        Parent modApptRoot = loader.load();
        Stage modApptStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene modApptScene = new Scene(modApptRoot);
        Update_Appointment_Controller controller = loader.getController();
        controller.startApptUpdate(selectedAppt);
        modApptStage.setScene(modApptScene);
        modApptStage.show();
    }

    /** This method is the action event for when the delete button is clicked.
     *  This method checks to make sure an appointment is selected, then asks the user for a confirmation before deleting the appointment.
     *  @param event When the button is clicked.*/
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

                // custom message:
                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setHeaderText("Appointment_ID: " + selectedAppt.getApptID() + " of Type: " + selectedAppt.getApptType() + " was deleted.");
                info.setContentText("Select OK to continue.");
                info.showAndWait();
            }
        } else {
            confirmation.setTitle("No Appointment selected");
            confirmation.setHeaderText("Please select an Appointment from the list to delete");
            confirmation.showAndWait();
        }
    }

    /** This method is the action event for when the Customer Dashboard Button is clicked.
     *  This method takes the user to the All_Customer_Records_Scene.
     *  @param event When the button is clicked.*/
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

    /** This method is the action event for when the Contact Dashboard Button is clicked.
     *  This method takes the user to the Contact_Dashboard_Scene.
     *  @param event When the button is clicked.*/
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

    /** This method is the action event for when the Reports Dashboard Button is clicked.
     *  This method takes the user to the Reports_Dashboard_Scene.
     *  @param event When the button is clicked.*/
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

    /** This is the method for the action event for when the log out button is clicked.
     *  This method confirms that the user wants to log out and does if the user confirms.
     *  @param event When the button is clicked*/
    @FXML
    public void apptLogOutBtnClicked(ActionEvent event) throws IOException {
        //when triggered this takes the user back to the Login_Scene.
        Alert exit = new Alert(Alert.AlertType.CONFIRMATION);
        exit.initModality(Modality.NONE);
        exit.setTitle("Confirm Log out");
        exit.setHeaderText("Are you sure you want to Log out?");
        Optional<ButtonType> result = exit.showAndWait();
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
