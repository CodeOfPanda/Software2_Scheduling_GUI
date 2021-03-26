package View_Controllers;

import Models.Appointments;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
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
    void deleteCustSubmit(ActionEvent event) {

    }
}
