package View_Controllers;

import DBAccess.DBAppointments;
import Models.Appointments;
import Models.Report_CustomerAppointments;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Reports_Controller implements Initializable {
    // Customer Appointments
    @FXML private TableView<Report_CustomerAppointments> custApptsTable;
    @FXML private TableColumn<Report_CustomerAppointments, String> monthCol;
    @FXML private TableColumn<Report_CustomerAppointments, String> custTypeCol;
    @FXML private TableColumn<Report_CustomerAppointments, Integer> custCountCol;
    // Appointments by Country
    @FXML private TableView<?> apptsByCountryTable;
    @FXML private TableColumn<?, ?> countryCol;
    @FXML private TableColumn<?, ?> apptsTypeCol;
    @FXML private TableColumn<?, ?> apptsCountCol;
    @FXML private Button reportsBackBtn;
    @FXML private Button reportsLogOutBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        custApptsTable.setItems(DBAppointments.getApptsByMonthType());
        monthCol.setCellValueFactory(cellData -> cellData.getValue().getReportCustMonth());
        custTypeCol.setCellValueFactory(cellData -> cellData.getValue().getReportType());
        custCountCol.setCellValueFactory(cellData -> cellData.getValue().getReportCustCount().asObject());
    }

    @FXML
    public void reportsBackBtnClicked(ActionEvent event) {

    }

    @FXML
    public void reportsLogOutBtnClicked(ActionEvent event) {

    }


    public void reportsBackBtnClicked(javafx.event.ActionEvent event) throws IOException {
        // when triggered this takes the user to All_Appointments_Scene.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../resources/All_Appointments_Scene.fxml"));
        Parent backRepoRoot = loader.load();
        Stage backRepoStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene backRepoScene = new Scene(backRepoRoot);
        backRepoStage.setScene(backRepoScene);
        backRepoStage.show();

    }

    public void reportsLogOutBtnClicked(javafx.event.ActionEvent event) throws IOException {
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
