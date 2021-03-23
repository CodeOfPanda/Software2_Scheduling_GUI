package View_Controllers;

import DBAccess.DBCountries;
import DBAccess.DBCustomers;
import DBAccess.DBFirst_Level_Divisions;
import Models.Appointments;
import Models.Countries;
import Models.Customers;
import Models.First_Level_Divisions;
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
* the customer data auto-populates in the form
* country and f-l division data is also auto-populated in their combo boxes.
* the f-l division data should be filtered by the user's selection of a country (when choosing u.s., filter so it only shows states)
* customerID must be disabled
*/


// modified scene builder to the new requirements.


public class Update_Customer_Controller{

    @FXML private TextField modCustID;
    @FXML private TextField modCustName;
    @FXML private TextField modCustPhone;
    @FXML private TextField modCustAddress;
    @FXML private ComboBox<String> modCustCountry;
    @FXML private ComboBox<String> modCustDivision;
    @FXML private TextField modCustPostal;
    @FXML private Button modCustSubmitBtn;
    @FXML private Button modCustCancelBtn;

    // my initialize method to pre-populate the modify customer page.
    @FXML
    public void updateCustomer(Customers selectedCustomer) {

        modCustID.setText(selectedCustomer.getCstmrID());
        modCustName.setText(selectedCustomer.getName());
        modCustPhone.setText(selectedCustomer.getPhone());
        modCustAddress.setText(selectedCustomer.getAddress());
        modCustCountry.setItems(Countries.getCountryNames());
        modCustCountry.setValue(DBCustomers.getCountryName(selectedCustomer.getCustID()));
        modCustDivision.setItems(First_Level_Divisions.getDivisionNames());
        modCustDivision.setValue(selectedCustomer.getDivName());
        modCustPostal.setText(selectedCustomer.getPostal());
    }

    // Submit Button Action Event
    @FXML
    void modCustSubmitBtnClicked(ActionEvent event) throws IOException {
        // when triggered an informational window will pop up, indicating the information was saved,
        // then take the user to All_Customer_Records_Scene.
        String customerID = String.valueOf(modCustID.getText());
        DBCustomers.modifyCustomer(Integer.parseInt(customerID)
                , modCustName.getText()
                , modCustAddress.getText()
                , modCustPostal.getText()
                , modCustPhone.getText()
                , Appointments.getCurrentDateTime()
                , DBFirst_Level_Divisions.getDivisionID(modCustDivision.getValue()));

        Alert submit = new Alert(Alert.AlertType.INFORMATION);
        submit.initModality(Modality.NONE);
        submit.setTitle("Thank You!");
        submit.setHeaderText("Your appointment has been saved.");
        Optional<ButtonType> results = submit.showAndWait();
        if(results.get() == ButtonType.OK) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../resources/All_Customer_Records_Scene.fxml"));
            Parent submitCustRoot = loader.load();

            Stage submitCustStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene submitCustScene = new Scene(submitCustRoot);
            submitCustStage.setScene(submitCustScene);
            submitCustStage.show();
        }
    }

    // Cancel Button Action Event
    @FXML
    void modCustCancelBtnClicked(ActionEvent event) throws IOException {
        // when triggered an alert will ask for conformation before taking them back to All_Customer_Records_Scene.
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Confirm Cancel");
        alert.setHeaderText("Do you wish to Cancel?");
        alert.setContentText("Appointment information will not be saved.");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../resources/All_Customer_Records_Scene.fxml"));
            Parent cancelCustRoot = loader.load();

            Stage cancelCustStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene cancelCustScene = new Scene(cancelCustRoot);
            cancelCustStage.setScene(cancelCustScene);
            cancelCustStage.show();
        }
    }
}
