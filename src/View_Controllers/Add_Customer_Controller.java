package View_Controllers;

import DBAccess.DBCustomers;
import DBAccess.DBFirst_Level_Divisions;
import Models.Appointments;
import Models.Countries;

import Models.First_Level_Divisions;
import Models.Users;
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

public class Add_Customer_Controller implements Initializable {

    @FXML private TextField addCustID;
    @FXML private TextField addCustName;
    @FXML private TextField addCustPhone;
    @FXML private TextField addCustAddress;
    @FXML private ComboBox<String> addCustCountry;
    @FXML private ComboBox<String> addCustDivision;
    @FXML private TextField addCustPostal;

    /*
    * when adding and updating a customer, text fields are used to collect: custName, address, postalCode, phone
    *       customerID is auto-generated (done)
    *       first-level division & country data is collected using separate combo boxes.
    * ex of address: 123 ABC Street, White Plains
    *                123 ABC Street, Newmarket  (done)
    */


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addCustID.setEditable(false);
        addCustCountry.setItems(Countries.getCountryNames());
        addCustDivision.setItems(First_Level_Divisions.getDivisionNames());
    }

    @FXML
    void changeCountry(ActionEvent event) {
        addCustDivision.setItems(DBFirst_Level_Divisions.getSpecificDivisions(addCustCountry.getValue()));
    }

    // Submit Button Action Event
    @FXML
    void addCustSubmitBtnClicked(ActionEvent event) throws IOException {
        // when triggered an informational window will pop up, indicating the information was saved,
        // then take the user to All_Customer_Records_Scene.
        if(isValid()) {
            DBCustomers.createCustomer(addCustName.getText()
                    , addCustAddress.getText()
                    , addCustPostal.getText()
                    , addCustPhone.getText()
                    , Appointments.getCurrentDateTime()
                    , Users.getLoggedInUser()
                    , Appointments.getCurrentDateTime()
                    , Users.getLoggedInUser()
                    , DBFirst_Level_Divisions.getDivisionID(addCustDivision.getValue())
            );

            Alert submit = new Alert(Alert.AlertType.INFORMATION);
            submit.initModality(Modality.NONE);
            submit.setTitle("Thank You!");
            submit.setHeaderText("Your customer has been saved.");
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

    // Cancel Button Action Event
    @FXML
    void addCustCancelBtnClicked(ActionEvent event) throws IOException {
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

    /** This method checks the text fields and combo-boxes to ensure each contains the correct value and returns a boolean value.
     *  This method checks the text fields and combo-boxes to ensure they are not null or not equal to empty, then returns a boolean value.
     *  @return Returns a boolean value of true or false*/
    public Boolean isValid() {
        if(addCustName.getText().isEmpty() || addCustName.getText() == null) {
            return false;
        } else if (addCustPhone.getText().isEmpty() || addCustPhone.getText() == null) {
            return false;
        } else if (addCustAddress.getText().isEmpty() || addCustAddress.getText() == null){
            return false;
        } else if (addCustPostal.getText().isEmpty() || addCustPostal.getText() == null) {
            return false;
        } else if (addCustCountry.getValue() == null) {
            return false;
        } else if (addCustDivision.getValue() == null) {
            return false;
        }
        return true;
    }
}


//@FXML private TextField addCustID;
//    @FXML private TextField addCustName;
//    @FXML private TextField addCustPhone;
//    @FXML private TextField addCustAddress;
//    @FXML private ComboBox<String> addCustCountry;
//    @FXML private ComboBox<String> addCustDivision;
//    @FXML private TextField addCustPostal;