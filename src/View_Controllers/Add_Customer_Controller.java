package View_Controllers;

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
    @FXML private TextField addCustFName;
    @FXML private TextField addCustLName;
    @FXML private TextField addCustEmail;
    @FXML private TextField addCustPhone;
    @FXML private TextField addCustAddress;
    @FXML private TextField addCustAptNum;
    @FXML private ChoiceBox<?> addCustCountry;
    @FXML private TextField addCustCity;
    @FXML private ChoiceBox<?> addCustState;
    @FXML private TextField addCustPostal;
    @FXML private TextField addCustProvince;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    // Submit Button Action Event
    @FXML
    void addCustSubmitBtnClicked(ActionEvent event) throws IOException {
        // when triggered an informational window will pop up, indicating the information was saved,
        // then take the user to All_Customer_Records_Scene.
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
}
