package View_Controllers;

import DBAccess.DBCustomers;
import Models.Appointments;
import Models.Customers;
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
import java.util.Optional;
import java.util.ResourceBundle;


public class All_Customers_Controller implements Initializable {

    @FXML private ComboBox<String> customerNameCombo;
    @FXML private Button allCustSearchBtn;
    @FXML private TextField allCustSearchTextField;
    @FXML private TableView<Customers> allCustTable;
    @FXML private TableColumn<Customers, Integer> allCustID;
    @FXML private TableColumn<Customers, String> allCustName;
    @FXML private TableColumn<Customers, String> allCustAddress;
    @FXML private TableColumn<Customers, String> allCustDivision;
    @FXML private TableColumn<Customers, String> allCustPostal;
    @FXML private TableColumn<Customers, String> allCustPhone;

    /** This is the initialize method that sets the cell values in the table view.
     *  This method sets the initial cell values for the Customers table view.
     *  @param url The URL
     *  @param resourceBundle The ResourceBundle*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> newCustNames = Appointments.getCustomerNames();
        newCustNames.add("All Customers");
        customerNameCombo.setItems(newCustNames.sorted());
        customerNameCombo.setValue("All Customers");
        allCustTable.setItems(DBCustomers.getAllCust());
        allCustID.setCellValueFactory(new PropertyValueFactory<>("CustID"));
        allCustName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        allCustAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        allCustPostal.setCellValueFactory(new PropertyValueFactory<>("Postal"));
        allCustPhone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        allCustDivision.setCellValueFactory(new PropertyValueFactory<>("DivisionID"));
    }

    /** This method is the action event for when a different customer name is selected out of the customer name combo-box.
     *  This method filters the Customer information based off of the customer name that is selected in the customer name combo-box.
     *  @param event When a customer name is selected.*/
    @FXML
    void changeCustName(ActionEvent event) {
        if(customerNameCombo.getValue() == "All Customers") {
            allCustTable.setItems(DBCustomers.getAllCust());
            allCustID.setCellValueFactory(new PropertyValueFactory<>("CustID"));
            allCustName.setCellValueFactory(new PropertyValueFactory<>("Name"));
            allCustAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
            allCustPostal.setCellValueFactory(new PropertyValueFactory<>("Postal"));
            allCustPhone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
            allCustDivision.setCellValueFactory(new PropertyValueFactory<>("DivisionID"));
        } else {
            allCustTable.setItems(DBCustomers.getSpecificCustomer(customerNameCombo.getValue()));
            allCustID.setCellValueFactory(new PropertyValueFactory<>("CustID"));
            allCustName.setCellValueFactory(new PropertyValueFactory<>("Name"));
            allCustAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
            allCustPostal.setCellValueFactory(new PropertyValueFactory<>("Postal"));
            allCustPhone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
            allCustDivision.setCellValueFactory(new PropertyValueFactory<>("DivisionID"));
        }
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
        // when triggered this checks to make sure there was a customer selected
        Customers selectedCustomer = allCustTable.getSelectionModel().getSelectedItem();

        if (selectedCustomer != null) {
            modifyCustomer(event, selectedCustomer);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("No record selected");
            alert.setHeaderText("Please select a customer from the table to update.");
            alert.showAndWait();
        }
    }

    public void modifyCustomer(ActionEvent event, Customers selectedCustomer) throws IOException {
        // when triggered this passes the selected customer to the method in the Update_Customer_Controller.
        // and takes the user to that scene.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../resources/Update_Customer_Scene.fxml"));
        Parent modCustRoot = loader.load();
        Stage modCustStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene modCustScene = new Scene(modCustRoot);
        Update_Customer_Controller controller = loader.getController();
        controller.updateCustomer(selectedCustomer);
        modCustStage.setScene(modCustScene);
        modCustStage.show();
    }

    // Delete Button Action Event
    @FXML
    public void allCustDelBtnClicked(ActionEvent event) throws IOException {
        //when triggered this takes them to the delete a customer GUI
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../resources/Delete_Customer_Scene.fxml"));
        Parent deleteRoot = loader.load();
        Stage deleteStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene deleteScene = new Scene(deleteRoot);
        deleteStage.setScene(deleteScene);
        deleteStage.show();
    }

    /** This is the method for the action event for when the back button is clicked.
     *  This method confirms that the user wants to go back and does if the user confirms.
     *  @param event When the button is clicked*/    @FXML
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

    /** This is the method for the action event for when the log out button is clicked.
     *  This method confirms that the user wants to log out and does if the user confirms.
     *  @param event When the button is clicked*/
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
