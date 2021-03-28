package View_Controllers;

import DBAccess.DBCustomers;
import Models.Appointments;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/** This class this the controller for the Delete_Customer_Scene.fxml.*/
public class Delete_Customer_Controller implements Initializable {
    // customer names combobox for Delete GUI
    @FXML
    private ComboBox<String> deleteCustCombo;

    /** This is the initialize method that sets the values in the combo-box.
     *  This method sets the values in the combo-box to be the Customers names.
     *  @param url The URL
     *  @param resourceBundle The ResourceBundle*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        deleteCustCombo.setItems(Appointments.getCustomerNames());
    }

    /** This method is an action event for when the submit button is clicked.
     *  This method deletes the appointments that are associated with a customer, then deletes the customer.
     *  @param event When the button is clicked*/
    @FXML
    public void deleteCustSubmit(ActionEvent event) throws IOException {
        // deletes appropriate customer and their appointments
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        if (deleteCustCombo.getValue() != null) {
            confirmation.setTitle("Please confirm your action.");
            confirmation.setHeaderText(deleteCustCombo.getValue() + "'s Appointments will also be deleted.");
            confirmation.setContentText("Select OK if you wish to delete.");
            Optional<ButtonType> result = confirmation.showAndWait();
            if(result.get() == ButtonType.OK) {
                // custom message for specific customer was successfully deleted
                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setHeaderText(deleteCustCombo.getValue() + " has been deleted.");
                info.setContentText("Select OK to continue.");
                info.showAndWait();
                // deleting customer appointments then deleting customer
                DBCustomers.deleteCustomerAppts(DBCustomers.getCustomerID(deleteCustCombo.getValue()));
                DBCustomers.deleteCustomer(DBCustomers.getCustomerID(deleteCustCombo.getValue()));
                // reloading customer records scene
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../resources/All_Customer_Records_Scene.fxml"));
                Parent allCustomersRoot = loader.load();
                Stage allCustStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene allCustScene = new Scene(allCustomersRoot);
                allCustStage.setScene(allCustScene);
                allCustStage.show();
            }
        } else {
            confirmation.setTitle("No Customer was selected");
            confirmation.setHeaderText("Please select a Customer from the drop down.");
            confirmation.showAndWait();
        }

    }

    /** This method is the action event for when the cancel button is clicked.
     *  This method asks the user for confirmation before taking them back to the All_Customer_Records_Scene.
     *  @param event When the button is clicked*/
    @FXML
    public void deleteCancelBtn(ActionEvent event) throws IOException {
        // takes user back to the customer dashboard.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../resources/All_Customer_Records_Scene.fxml"));
        Parent allCustomersRoot = loader.load();
        Stage allCustStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene allCustScene = new Scene(allCustomersRoot);
        allCustStage.setScene(allCustScene);
        allCustStage.show();

    }
}
