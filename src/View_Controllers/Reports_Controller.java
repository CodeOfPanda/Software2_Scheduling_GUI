package View_Controllers;

import DBAccess.DBAppointments;
import Models.Report_AppointmentsByCountry;
import Models.Report_CustomerAppointments;
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
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;

/** This class is the controller to the Reports_Dashboards_Scene.fxml.*/
public class Reports_Controller implements Initializable {
    // Customer Appointments
    @FXML private TableView<Report_CustomerAppointments> custApptsTable;
    @FXML private TableColumn<Report_CustomerAppointments, String> monthCol;
    @FXML private TableColumn<Report_CustomerAppointments, String> custTypeCol;
    @FXML private TableColumn<Report_CustomerAppointments, Integer> custCountCol;
    // Appointments by Country
    @FXML private TableView<Report_AppointmentsByCountry> apptsByCountryTable;
    @FXML private TableColumn<Report_AppointmentsByCountry, String> countryCol;
    @FXML private TableColumn<Report_AppointmentsByCountry, String> apptsTypeCol;
    @FXML private TableColumn<Report_AppointmentsByCountry, Integer> apptsCountCol;

    /** This is the initialize method that sets the cell values in both table views.
     *  This method sets the initial cell values for both the Report_AppointmentsByCountry table view and the Report_CustomerAppointments table view.
     *  @param url The URL
     *  @param resourceBundle The ResourceBundle*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Customer Appointments Table
        custApptsTable.setItems(DBAppointments.getApptsByMonthType());
        monthCol.setCellValueFactory(new PropertyValueFactory<>("ReportCustMonth"));
        custTypeCol.setCellValueFactory(new PropertyValueFactory<>("ReportType"));
        custCountCol.setCellValueFactory(new PropertyValueFactory<>("ReportCustCount"));

        // Appointments by Country Table
        apptsByCountryTable.setItems(DBAppointments.getApptsByCountry());
        countryCol.setCellValueFactory(new PropertyValueFactory<>("ReportApptCountry"));
        apptsTypeCol.setCellValueFactory(new PropertyValueFactory<>("ReportApptType"));
        apptsCountCol.setCellValueFactory(new PropertyValueFactory<>("ReportApptCount"));
    }

    /** This is the method is the action event for when the log out button is clicked.
     *  This method confirms that the user wants to log out and does if the user confirms.
     *  @param event When the button is clicked*/
    @FXML
    public void reportsLogOutBtnClicked(ActionEvent event) throws IOException {
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

    /** This is the method for the action event for when the back button is clicked.
     *  This method confirms that the user wants to go back and does if the user confirms.
     *  @param event When the button is clicked*/
    @FXML
    public void reportsBackBtnClicked(ActionEvent event) throws IOException {
        // when triggered this takes the user to All_Appointments_Scene.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../resources/All_Appointments_Scene.fxml"));
        Parent backRepoRoot = loader.load();
        Stage backRepoStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene backRepoScene = new Scene(backRepoRoot);
        backRepoStage.setScene(backRepoScene);
        backRepoStage.show();
    }
}
