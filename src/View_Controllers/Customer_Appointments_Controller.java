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


/*
* write code that enables the user to view appointment schedules by current month/current week/ & all, using a TableView & allows
*       the user to choose between these two options using tabs or radio buttons for filtering appointments.
*       include the follwing: Appointment_ID, Title, Description, Location, Contact --> Contact Name not ID, Type,
*       Start Date & Time, End Date & Time, Customer_ID --> recommends including custName as well.
*/

public class Customer_Appointments_Controller implements Initializable {

    @FXML private TabPane custApptTablePane;
    // Weekly Appointments tab
    @FXML private Tab weeklyTab;
    @FXML private TableView<?> weeklyTableView;  //weekly tableview and columns
    @FXML private TableColumn<?, ?> sunColm;
    @FXML private TableColumn<?, ?> monColm;
    @FXML private TableColumn<?, ?> tuesColm;
    @FXML private TableColumn<?, ?> wedColm;
    @FXML private TableColumn<?, ?> thursColm;
    @FXML private TableColumn<?, ?> FriColm;
    @FXML private TableColumn<?, ?> satColm;
    // Monthly Appointments tab
    @FXML private Tab monthlyTab;  //monthly tableview and columns
    @FXML private TableView<?> monthlyTableView;
    @FXML private TableColumn<?, ?> weeklyDateColm;
    @FXML private TableColumn<?, ?> monTimeColm;
    // All Appointments tab
    @FXML private Tab allTab;  //all tableview and columns
    @FXML private TableView<?> allTableView;
    @FXML private TableColumn<?, ?> allDateColm;
    @FXML private TableColumn<?, ?> allTimeColm;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    // Add Button Action Event
    @FXML
    public void custApptAddBtnClicked(ActionEvent event) throws IOException {
        // when triggered this takes the user to the Add_Appointment_Scene.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../resources/Add_Appointment_Scene.fxml"));
        Parent addApptRoot = loader.load();

        Stage addApptStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene addApptScene = new Scene(addApptRoot);

        addApptStage.setScene(addApptScene);
        addApptStage.show();
    }

    // Update Button Action Event
    @FXML
    public void custApptModBtnClicked(ActionEvent event) throws IOException {
        // when triggered this takes the user to the Update_Appointment_Scene.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../resources/Update_Appointment_Scene.fxml"));
        Parent modApptRoot = loader.load();

        Stage modApptStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene modApptScene = new Scene(modApptRoot);

        modApptStage.setScene(modApptScene);
        modApptStage.show();

    }

    // Delete Button Action Event
    @FXML
    public void custApptDelBtnClicked(ActionEvent event) {
        // when triggered this asks user for confirmation before deleting an appointment.

    }

    // Back Button Action Event
    @FXML
    public void custApptBackBtnClicked(ActionEvent event) throws IOException {
        // when triggered this takes the user to the All_Appointment_Records_Scene.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../resources/All_Appointment_Records_Scene.fxml"));
        Parent backApptsRoot = loader.load();

        Stage backApptsStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene backApptsScene = new Scene(backApptsRoot);

        backApptsStage.setScene(backApptsScene);
        backApptsStage.show();

    }

    // Save Button Action Event
    @FXML
    public void custApptSaveBtnClicked(ActionEvent event) throws IOException {
        // when triggered this saves and a pop up message indicates to the user that the changes have been saved.
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Thank you!");
        alert.setHeaderText("Your changes have been saved.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../resources/Customer_Appointments_Scene.fxml"));
            Parent custApptsRoot = loader.load();

            Stage custApptsStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene custApptsScene = new Scene(custApptsRoot);
            custApptsStage.setScene(custApptsScene);
            custApptsStage.show();
        }
    }

}
