package View_Controllers;

import Models.Appointments;
import Models.Contacts;
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
import java.util.Optional;
import java.util.ResourceBundle;

public class Contact_Controller implements Initializable {

    @FXML private Button contactSearchBtn;
    @FXML private TextField contactSearchText;
    @FXML private TableView<Contacts> contactTable;
    @FXML private TableColumn<Contacts, Integer> contactApptID;
    @FXML private TableColumn<Contacts, String> contactApptTitle;
    @FXML private TableColumn<Contacts, String> contactApptType;
    @FXML private TableColumn<Contacts, String> contactApptDescript;
    @FXML private TableColumn<Contacts, String> contactApptStrtTime;
    @FXML private TableColumn<Contacts, String> contactApptEndTime;
    @FXML private TableColumn<Contacts, Integer> contactApptCustID;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


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
        loader.setLocation(getClass().getResource("../resources/All_Appointment_Records_Scene.fxml"));
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
