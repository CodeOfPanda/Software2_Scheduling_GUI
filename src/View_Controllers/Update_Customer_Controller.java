package View_Controllers;

import DBAccess.DBCustomers;
import DBAccess.DBFirst_Level_Divisions;
import Models.Appointments;
import Models.Countries;
import Models.Customers;
import Models.Users;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.Optional;


public class Update_Customer_Controller{

    @FXML private TextField modCustID;
    @FXML private TextField modCustName;
    @FXML private TextField modCustPhone;
    @FXML private TextField modCustAddress;
    @FXML private ComboBox<String> modCustCountry;
    @FXML private ComboBox<String> modCustDivision;
    @FXML private TextField modCustPostal;

    // my initialize method to pre-populate the modify customer page.
    @FXML
    public void updateCustomer(Customers selectedCustomer) {
        String custID = String.valueOf(selectedCustomer.getCustID());
        modCustID.setText(custID);
        modCustID.setEditable(false);
        modCustName.setText(selectedCustomer.getName());
        modCustPhone.setText(selectedCustomer.getPhone());
        modCustAddress.setText(selectedCustomer.getAddress());
        modCustCountry.setItems(Countries.getCountryNames());
        modCustCountry.setValue(DBCustomers.getCountryName(selectedCustomer.getCustID()));
        modCustDivision.setItems(DBFirst_Level_Divisions.getSpecificDivisions(modCustCountry.getValue()));
        modCustDivision.setValue(selectedCustomer.getDivName());
        modCustPostal.setText(selectedCustomer.getPostal());
    }

    @FXML
    void changeCountry(ActionEvent event) {
        modCustDivision.setItems(DBFirst_Level_Divisions.getSpecificDivisions(modCustCountry.getValue()));
    }

    // Submit Button Action Event
    @FXML
    void modCustSubmitBtnClicked(ActionEvent event) throws IOException {
        // when triggered an informational window will pop up, indicating the information was saved,
        // then take the user to All_Customer_Records_Scene.
        String customerID = String.valueOf(modCustID.getText());
        if(isValid()) {
            DBCustomers.modifyCustomer(Integer.parseInt(customerID)
                    , modCustName.getText()
                    , modCustAddress.getText()
                    , modCustPostal.getText()
                    , modCustPhone.getText()
                    , Appointments.getCurrentDateTime()
                    , Users.getLoggedInUser()
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
        } else {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Missing Information.");
            error.setHeaderText("Please make sure you have filled out each text field.");
            Optional<ButtonType> results = error.showAndWait();
        }

    }

    /** This method checks the text fields and combo-boxes to ensure each contains the correct value and returns a boolean value.
     *  This method checks the text fields and combo-boxes to ensure they are not null or not equal to empty, then returns a boolean value.
     *  @return Returns a boolean value of true or false*/
    public Boolean isValid() {
        if(modCustName.getText().isEmpty() || modCustName.getText() == null) {
            return false;
        } else if (modCustPhone.getText().isEmpty() || modCustPhone.getText() == null) {
            return false;
        } else if (modCustAddress.getText().isEmpty() || modCustAddress.getText() == null){
            return false;
        } else if (modCustPostal.getText().isEmpty() || modCustPostal.getText() == null) {
            return false;
        } else if (modCustCountry.getValue() == null) {
            return false;
        } else if (modCustDivision.getValue() == null) {
            return false;
        }
        return true;
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
