package Models;
import DBAccess.DBAppointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Timestamp;
import java.time.LocalDateTime;


public class Appointments {

    private static ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();
//    ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();
    private int appointmentID;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private LocalDateTime createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;
    private int customerID;
    private int userID;
    private int contactID;


    public Appointments(int appointmentID, String title, String description, String location, String type
                        ,LocalDateTime start, LocalDateTime end, LocalDateTime createDate, String createdBy
                        ,Timestamp lastUpdate, String lastUpdatedBy, int customerID, int userID, int contactID)
    {
        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
    }

    // appointmentID
    public int getAppointmentID() {
        return this.appointmentID;
    }
    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;

    }

    // title
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    // description
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    // location
    public String getLocation() {
        return this.location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    // type
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
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
    public String getCreatedBy() {
        return this.createdBy;
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    // last_update timestamp
    public Timestamp getLastUpdate() {
        return this.lastUpdate;
    }
    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    // last_updated_by
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    // customer id
    public int getCustomerID() {
        return this.customerID;
    }
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    // user id
    public int getUserID() {
        return this.userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }

    // contact id
    public int getContactID() {
        return this.contactID;
    }
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    // get all appointments method
    public static ObservableList<Appointments> getAllAppts() {return allAppointments;}
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