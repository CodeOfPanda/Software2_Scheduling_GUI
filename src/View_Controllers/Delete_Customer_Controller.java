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

public class Delete_Customer_Controller implements Initializable {
    // customer names combobox for Delete GUI
    @FXML
    private ComboBox<String> deleteCustCombo;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        deleteCustCombo.setItems(Appointments.getCustomerNames());
    }

    // Submit Button for Delete GUI
    @FXML
    void deleteCustSubmit(ActionEvent event) throws IOException {

        // want to delete the customer on this action event
        // then reload the all customer records scene
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        if (deleteCustCombo.getValue() != null) {
            confirmation.setTitle("Please confirm your action.");
            confirmation.setHeaderText(deleteCustCombo.getValue() + "'s Appointments will also be deleted.");
            confirmation.setContentText("Select OK if you wish to delete.");
            Optional<ButtonType> result = confirmation.showAndWait();
            if(result.get() == ButtonType.OK) {
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

    // Cancel Button for Delete Customer GUI
    @FXML
    public void deleteCancelBtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../resources/All_Customer_Records_Scene.fxml"));
        Parent allCustomersRoot = loader.load();
        Stage allCustStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene allCustScene = new Scene(allCustomersRoot);
        allCustStage.setScene(allCustScene);
        allCustStage.show();
    }
}
