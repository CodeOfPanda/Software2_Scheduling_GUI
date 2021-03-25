package View_Controllers;

import DBAccess.DBUsers;
import Models.Appointments;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

/*
* accepts user name and password (done)
*
* displays the string version of the ZoneID in loginLocation Label (done)
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
    @FXML private TextField userName;
    @FXML private PasswordField password;
    @FXML private Button signInButton;
    @FXML private Button exitButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ZoneId zone = ZoneId.systemDefault();
        String zoneID = String.valueOf(zone);
        loginLocale.setText(zoneID);
    }

    // sign in button action
    public void signInBtnClicked(ActionEvent actionEvent) throws IOException {
        // when triggered this validates the user, writes the activity to the reports file, then takes the user to All_Appointments_Scene.

        // creating my login_activity file
        try {
            File activity = new File("login_activity.txt");
            if (activity.createNewFile()) {
                System.out.println("File created: " + activity.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // creating my file writer
        FileWriter fileWriter = new FileWriter("login_activity.txt", true);

        // userName and password validation check
        AtomicBoolean userValid = new AtomicBoolean(false);
        AtomicBoolean passwordValid = new AtomicBoolean(false);
        DBUsers.getUserNames().forEach((name) -> {
            if(userName.getText().equals(name)) {
                userValid.set(true);
                if (password.getText().equals(DBUsers.getUserPassword(name))) {
                    passwordValid.set(true);
                }
            }
        });

        // writing to file, error handling, and changing scenes
        if (userValid.get() && passwordValid.get()) {
            // if bool is true : log-in attempts, dates, and time stamps and attempt was successful in a file named login_activity.txt.
            fileWriter.write(userName.getText() + " attempted log-in at " + LocalDateTime.now() + " and was admitted successfully.\n");
            fileWriter.close();
            System.out.println(fileWriter);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../resources/All_Appointments_Scene.fxml"));
            Parent allAppointmentsRoot = loader.load();
            Stage allAppointmentsStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene allAppointmentsScene = new Scene(allAppointmentsRoot);
            allAppointmentsStage.setScene(allAppointmentsScene);
            allAppointmentsStage.show();
        } else {
            // else: log-in attempts, dates, and time stamps and attempt was unsuccessful in a file named login_activity.txt.
            fileWriter.write(userName.getText() + " attempted log-in at " + LocalDateTime.now() + " and was not admitted.\n");
            fileWriter.close();
            System.out.println(fileWriter);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.NONE);
            alert.setTitle("Incorrect UserName or Password");
            alert.setHeaderText("Please try again.");
            alert.showAndWait();
        }
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
