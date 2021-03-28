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
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;


/** This class is the controller for the Login_Scene.fxml*/
public class LoginController implements Initializable {

    @FXML private Label welcomeLabel;
    @FXML private Label loginLocale;
    @FXML private Label userNameLabel;
    @FXML private TextField userName;
    @FXML private Label passwordLabel;
    @FXML private PasswordField password;
    @FXML private Button signInBtnLabel;
    @FXML private Button exitBtnLabel;
    private String incorrectTitle;
    private String tryAgain;
    private String confirmExitTitle;
    private String confirmExit;

    ZoneId zone = ZoneId.systemDefault();
    ZonedDateTime zdt = ZonedDateTime.now(zone);

    /** This is the initialize method, it sets the language and zoneID to the system default values.
     *  This method sets the labels and error messages to the correct language based off of the system default location.
     *  @param url The URL
     *  @param resourceBundle The ResourceBundle*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Locale locale = Locale.getDefault();
        ResourceBundle rb = ResourceBundle.getBundle("Languages/login", locale);
        welcomeLabel.setText(rb.getString("welcome"));
        userNameLabel.setText(rb.getString("userName"));
        passwordLabel.setText(rb.getString("password"));
        signInBtnLabel.setText(rb.getString("signInBtn"));
        exitBtnLabel.setText(rb.getString("exitBtn"));
        incorrectTitle = rb.getString("incorrectUserOrPassTitle");
        tryAgain = rb.getString("tryAgain");
        confirmExitTitle = rb.getString("confirmExitTitle");
        confirmExit = rb.getString("confirmExit");
        String zoneID = String.valueOf(zone);
        loginLocale.setText(zoneID);
    }

    /** This method this the action event for when the sign in button is clicked.
     *  This method writes to the login_activity.txt file, validates the userName and password, and checks if there is upcoming appointment within 15 minutes.
     *  @param actionEvent When the button is clicked*/
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
            // checking to see if there is an appt with in 15 min.
            LocalTime local = LocalTime.now();
            LocalDateTime ldt = LocalDateTime.of(LocalDate.now(), local);
            ZonedDateTime localZDT = ldt.atZone(ZoneId.systemDefault());
            ZonedDateTime utcZDT = localZDT.withZoneSameInstant(ZoneId.of("UTC"));
            LocalDateTime currentTimeUTCPlus15Min = utcZDT.plusMinutes(15).toLocalDateTime();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            if(DBAppointments.getApptsIn15Mins(currentTimeUTCPlus15Min, utcZDT.toLocalDateTime()) > 0) {
                DBAppointments.getApptDataWithIn15Min(currentTimeUTCPlus15Min).forEach((appt) -> {
                    // these messages are not on the login page and do not need translating
                    alert.setTitle("Upcoming Appointment");
                    alert.setHeaderText("Appointment_ID: " + appt.getApptID() + "\nDate: " + appt.getApptStart().toLocalDate() + "\nTime: "
                            + appt.getApptStart().toLocalTime() + "\n\nStarts within 15 minutes.");
                    alert.setContentText("Select OK to continue.");
                    alert.showAndWait();
                });
            } else {
                // these messages are not on the login page and do not need translating
                alert.setTitle("Upcoming Appointments");
                alert.setHeaderText("There are no upcoming appointments within 15 minutes.");
                alert.setContentText("Select OK to continue");
                alert.showAndWait();
            }
        } else {
            // else: log-in attempts, dates, and time stamps and attempt was unsuccessful in a file named login_activity.txt.
            fileWriter.write(userName.getText() + " attempted log-in at " + LocalDateTime.now() + " and was not admitted.\n");
            fileWriter.close();
            System.out.println(fileWriter);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.NONE);
            alert.setTitle(incorrectTitle);
            alert.setHeaderText(tryAgain);
            alert.showAndWait();
        }

    }
    /** This is the method for the action event for when the exit button is clicked.
     *  This method confirms that the user wants to exit the application and exits if the user confirms.
     *  @param actionEvent When the button is clicked*/
    public void exitBtnClicked(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle(confirmExitTitle);
        alert.setHeaderText(confirmExit);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.exit(0);
        }
    }
}
