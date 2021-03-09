package Models;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;


public class Appointments {

//    private static ObservableList<Appointments> allAppointments = FXCollections.observableArrayList(); // i don't know if I actually need this
    private IntegerProperty appointmentID;
    private StringProperty title;
    private StringProperty description;
    private StringProperty location;
    private StringProperty type;
    private LocalDateTime start;
    private LocalDateTime end;
    private LocalDateTime createDate;
    private StringProperty createdBy;
    private Timestamp lastUpdate;
    private StringProperty lastUpdatedBy;
    private IntegerProperty customerID;
    private IntegerProperty userID;
    private IntegerProperty contactID;


    public Appointments(int appointmentID, String title, String description, String location, String type
                        ,LocalDateTime start, LocalDateTime end, LocalDateTime createDate, String createdBy
                        ,Timestamp lastUpdate, String lastUpdatedBy, int customerID, int userID, int contactID)
    {
        this.appointmentID = new SimpleIntegerProperty(appointmentID);
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(description);
        this.location = new SimpleStringProperty(location);
        this.type = new SimpleStringProperty(type);
        this.start = start;
        this.end = end;
        this.createDate = createDate;
        this.createdBy = new SimpleStringProperty(createdBy);
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = new SimpleStringProperty(lastUpdatedBy);
        this.customerID = new SimpleIntegerProperty(customerID);
        this.userID = new SimpleIntegerProperty(userID);
        this.contactID = new SimpleIntegerProperty(contactID);
    }

    // appointmentID
    public IntegerProperty getAppointmentID() {
        return appointmentID;
    }
    public void setAppointmentID(int appointmentID) {
        this.appointmentID = new SimpleIntegerProperty(appointmentID);

    }

    // title
    public StringProperty getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = new SimpleStringProperty(title);
    }

    // description
    public StringProperty getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = new SimpleStringProperty(description);
    }

    // location
    public StringProperty getLocation() {
        return this.location;
    }
    public void setLocation(String location) {
        this.location = new SimpleStringProperty(location);
    }

    // type
    public StringProperty getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = new SimpleStringProperty(type);
    }

    // start datetime
    public LocalDateTime getStart() {
        return this.start;
    }
    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    // end datetime
    public LocalDateTime getEnd() {
        return this.end;
    }
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    // create_date datetime
    public LocalDateTime getCreateDate() {
        return this.createDate;
    }
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    // created_by
    public StringProperty getCreatedBy() {
        return this.createdBy;
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy = new SimpleStringProperty(createdBy);
    }

    // last_update timestamp
    public Timestamp getLastUpdate() {
        return this.lastUpdate;
    }
    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    // last_updated_by
    public StringProperty getLastUpdatedBy() {
        return lastUpdatedBy;
    }
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = new SimpleStringProperty(lastUpdatedBy);
    }

    // customer id
    public IntegerProperty getCustomerID() {
        return this.customerID;
    }
    public void setCustomerID(int customerID) {
        this.customerID = new SimpleIntegerProperty(customerID);
    }

    // user id
    public IntegerProperty getUserID() {
        return this.userID;
    }
//    public void setUserID(int userID) {
//        this.userID = userID;
//    }

    // contact id
    public IntegerProperty getContactID() {
        return this.contactID;
    }
//    public void setContactID(int contactID) {
//        this.contactID = contactID;
//    }

    // get all appointments method
//    public static ObservableList<Appointments> getAllAppts() {return allAppointments;}


}


//Appointment_ID int(10) AI PK
//Title varchar(50)
//Description varchar(50)
//Location varchar(50)
//Type varchar(50)
//Start datetime
//End datetime
//Create_Date datetime
//Created_By varchar(50)
//Last_Update timestamp
//Last_Updated_By varchar(50)
//Customer_ID int(10)
//User_ID int(10)
//Contact_ID int(10)