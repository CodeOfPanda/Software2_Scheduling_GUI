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
* accepts user name and password
*
* displays the string version of the ZoneID in loginLocation Label
*
* displays the login form in English or French based on the user's computer language setting to translate all the text,
*     labels, buttons, and errors on the form
* Locale.setDefault(new Locale("fr")); for translating language
*     put on the first line of program in main.
*
* provide an alert when there is an appt within 15 min of the user's login. a custom message should be displayed including
*     the apptID, date, & time.
*     if the user does not have an appt within 15 min, display a message indicating there are no upcoming appts.
*
* writes code that provides the ability to track user activity by recording all user login attempts, dates, and time stamps
*     and whether each attempt was successful in a file named login_activity.txt.
*     Append each new record to the existing file, and save to the root folder of the application.
*     ex: "User Davis successfully logged in at 2020-07-21 16:00:00" or "User test gave invalid log-in at 2020-07-23 14:00:00" (system default)
**/

public class LoginController implements Initializable {
    @FXML private Label loginLocale;
    @FXML private TextField loginUserID;
    @FXML private PasswordField loginPassword;
    @FXML private Button signInButton;
    @FXML private Button exitButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void signInBtnClicked(ActionEvent actionEvent) throws IOException {
        //when triggered this takes the user to All_Appointment_Records_Scene.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../resources/All_Appointment_Records_Scene.fxml"));
        Parent allAppointmentsRoot = loader.load();

        Stage allAppointmentsStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene allAppointmentsScene = new Scene(allAppointmentsRoot);
        allAppointmentsStage.setScene(allAppointmentsScene);
        allAppointmentsStage.show();
    }

    public void exitBtnClicked(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Confirm Exit");
        alert.setHeaderText("Are you sure you want to Exit?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.exit(0);
        }
    }
}
