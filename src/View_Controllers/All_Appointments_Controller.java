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
        allApptCont.setCellValueFactory(cellData -> cellData.getValue().getContactID().asObject());
        allApptType.setCellValueFactory(cellData -> cellData.getValue().getType());
        allApptStrtTime.setCellValueFactory(cellData -> cellData.getValue().getStart());
        allApptEndTime.setCellValueFactory(cellData -> cellData.getValue().getEnd());
        allApptCustID.setCellValueFactory(cellData -> cellData.getValue().getCustomerID().asObject());
    }

    // Search Button Action Event
    @FXML
    public void allApptSearchBtnClicked(ActionEvent event) {

    }

    // View All Customers Button Action Event
    @FXML
    public void viewCustBtnClicked(ActionEvent event) throws IOException {
        //when triggered this takes the user to All_Customer_Records_Scene.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../resources/All_Customer_Records_Scene.fxml"));
        Parent allCustomersRoot = loader.load();

        Stage allCustStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene allCustScene = new Scene(allCustomersRoot);
        allCustStage.setScene(allCustScene);
        allCustStage.show();
    }

    // Customer Profile Button Action Event
    @FXML
    public void custProfBtnClicked(ActionEvent event) throws IOException {
        //when triggered this takes the user to Customer_Appointments_Scene.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../resources/Customer_Appointments_Scene.fxml"));
        Parent custApptsRoot = loader.load();

        Stage custApptStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene custApptScene = new Scene(custApptsRoot);

        custApptStage.setScene(custApptScene);
        custApptStage.show();
    }

    // Contact Profile Button Action Event
    @FXML
    public void ContProfBtnClicked(ActionEvent event) throws IOException {
        //when triggered this takes the user to Contact_Dashboard_Scene.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../resources/Contact_Dashboard_Scene.fxml"));
        Parent contDashRoot = loader.load();

        Stage contDashStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene contDashScene = new Scene(contDashRoot);
        contDashStage.setScene(contDashScene);
        contDashStage.show();
    }

    // Log out Button Action Event
    @FXML
    void allApptLogOutBtnClicked(ActionEvent event) throws IOException {
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
