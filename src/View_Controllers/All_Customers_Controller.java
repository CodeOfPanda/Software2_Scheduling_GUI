package View_Controllers;

import DBAccess.DBCustomers;
import Models.Appointments;
import Models.Customers;
import Models.First_Level_Divisions;
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
import java.util.Optional;
import java.util.ResourceBundle;

/*
 * all of the customer's appointments must be deleted prior to deleting a customer.
 *          use an error message indicating this need.
 *
 * when deleting a customer record, a custom message should display in the user interface ("are you sure?" --> "Customer Removed")
 */

public class All_Customers_Controller implements Initializable {
    ObservableList<String> customerNames = FXCollections.observableArrayList();
    @FXML private ComboBox<String> customerNameCombo;
    @FXML private Button allCustSearchBtn;
    @FXML private TextField allCustSearchTextField;
    @FXML private TableView<Customers> allCustTable;
    @FXML private TableColumn<Customers, Integer> allCustID;
    @FXML private TableColumn<Customers, String> allCustName;
    @FXML private TableColumn<Customers, String> allCustAddress;
    @FXML private TableColumn<First_Level_Divisions, String> allCustDivision;
    @FXML private TableColumn<Customers, String> allCustPostal;
    @FXML private TableColumn<Customers, String> allCustPhone;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        customerNameCombo.setItems(Appointments.getCustomerNames());// dont think i need this
        allCustTable.setItems(DBCustomers.getAllCust());
        allCustID.setCellValueFactory(cellData -> cellData.getValue().getCustomerID().asObject());
//        allCustName.setCellValueFactory(cellData -> cellData.getValue().getCustName());
        allCustAddress.setCellValueFactory(cellData -> cellData.getValue().getCustAddress());
        allCustPostal.setCellValueFactory(cellData -> cellData.getValue().getCustPostal());
        allCustPhone.setCellValueFactory(cellData -> cellData.getValue().getCustPhone());

    }

    // Search Button Action Event
    @FXML
    public void allCustSearchBtnClicked(ActionEvent event) {

    }

    // Add Button Action Event
    @FXML
    public void allCustAddBtnClicked(ActionEvent event) throws IOException {
        // when triggered this takes the user to Add_Customer_Scene.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../resources/Add_Customer_Scene.fxml"));
        Parent addCustRoot = loader.load();

        Stage addCustStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene addCustScene = new Scene(addCustRoot);
        addCustStage.setScene(addCustScene);
        addCustStage.show();

    }

    // Modify Button Action Event
    @FXML
    public void allCustModBtnClicked(ActionEvent event) throws IOException {
        // when triggered this takes the user to Update_Customer_Scene.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../resources/Update_Customer_Scene.fxml"));
        Parent modCustRoot = loader.load();
        Stage modCustStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene modCustScene = new Scene(modCustRoot);
        modCustStage.setScene(modCustScene);
        modCustStage.show();
    }

    // Delete Button Action Event
    @FXML
    public void allCustDelBtnClicked(ActionEvent event) {
        // when triggered this gives user a confirmation alert before deleting a customer.
        // it needs to ask user if they would like to also delete any appointments this customer had.

    }

    // Back Button Action Event
    @FXML
    public void allCustBackBtnClicked(ActionEvent event) throws IOException {
        // when triggered this takes the user to All_Appointments_Scene.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../resources/All_Appointments_Scene.fxml"));
        Parent backCustRoot = loader.load();
        Stage backCustStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene backCustScene = new Scene(backCustRoot);
        backCustStage.setScene(backCustScene);
        backCustStage.show();

    }

    // Log out Action Event
    @FXML
    public void allCustLogOutBtnClicked(ActionEvent event) throws IOException {
        // when triggered this takes the user to Login_Scene.
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
