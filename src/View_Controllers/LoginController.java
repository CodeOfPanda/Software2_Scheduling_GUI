package View_Controllers;

import DBAccess.DBAppointments;
import DBAccess.DBUsers;
import Models.Appointments;
import Models.Users;
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
import java.time.*;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

/*
* displays the login form in English or French based on the user's computer language setting to translate all the text,
*     labels, buttons, and errors on the form
* Locale.setDefault(new Locale("fr")); for translating language
*     put on the first line of program in main.
**/

public class LoginController implements Initializable {
    @FXML private Label loginLocale;
    @FXML private TextField userName;
    @FXML private PasswordField password;

    ZoneId zone = ZoneId.systemDefault();
    ZonedDateTime zdt = ZonedDateTime.now(zone);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
            //set logged in user
            Users.setLoggedInUser(userName.getText());

            // if bool is true : log-in attempts, dates, and time stamps and attempt was successful in a file named login_activity.txt.
            fileWriter.write("User: " + userName.getText() + " attempted log-in at " + LocalDateTime.now() + " and was admitted successfully.\n");
            fileWriter.close();

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

        // checking to see if there is an appt with in 15 min.
        LocalTime local = LocalTime.now();
        LocalDateTime ldt = LocalDateTime.of(LocalDate.now(), local);
        ZonedDateTime localZDT = ldt.atZone(ZoneId.systemDefault());
        ZonedDateTime utcZDT = localZDT.withZoneSameInstant(ZoneId.of("UTC"));
        LocalDateTime currentTimeUTCPlus15Min = utcZDT.plusMinutes(15).toLocalDateTime();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(DBAppointments.getApptsIn15Mins(currentTimeUTCPlus15Min, utcZDT.toLocalDateTime()) > 0) {
            DBAppointments.getApptDataWithIn15Min(currentTimeUTCPlus15Min).forEach((appt) -> {
                alert.setTitle("Upcoming Appointment");
                alert.setHeaderText("Appointment_ID: " + appt.getApptID() + "\nDate: " + appt.getApptStart().toLocalDate() + "\nTime: "
                        + appt.getApptStart().toLocalTime() + "\n\nStarts within 15 minutes.");
                alert.setContentText("Select OK to continue.");
                alert.showAndWait();
            });
        } else {
            alert.setTitle("Upcoming Appointments");
            alert.setHeaderText("There are no upcoming appointments within 15 minutes.");
            alert.setContentText("Select OK to continue");
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
