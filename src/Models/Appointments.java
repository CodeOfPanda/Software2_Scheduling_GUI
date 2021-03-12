package Models;

import javafx.beans.property.*;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.sql.Timestamp;
import java.time.LocalDateTime;


public class Appointments {

    private static int apptIDCount = 0;
    private static IntegerProperty appointmentID;
    private static StringProperty title;
    private static StringProperty description;
    private static StringProperty location;
    private static StringProperty type;
    private static ObjectProperty<LocalDateTime> start;
    private static ObjectProperty<LocalDateTime> end;
    private static LocalDateTime createDate;
    private static StringProperty createdBy;
    private static Timestamp lastUpdate;
    private static StringProperty lastUpdatedBy;
    private static IntegerProperty customerID;
    private static IntegerProperty userID;
    private static IntegerProperty contactID;


    // constructor method
    public Appointments(int appointmentID, String title, String description, String location, String type
                        ,LocalDateTime start, LocalDateTime end, LocalDateTime createDate, String createdBy
                        ,Timestamp lastUpdate, String lastUpdatedBy, int customerID, int userID, int contactID)
    {
        this.appointmentID = new SimpleIntegerProperty(appointmentID);
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(description);
        this.location = new SimpleStringProperty(location);
        this.type = new SimpleStringProperty(type);
        this.start = new SimpleObjectProperty<>(start);
        this.end = new SimpleObjectProperty<>(end);
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
    public static int getApptID() {
        return appointmentID.get();
    }
    // for the auto gen ID
    public static int getApptIDCount() {
        return apptIDCount;
    }
    public static void setApptIDCount() {
        apptIDCount += 1;
    }

    // title
    public StringProperty getTitle() {
        return title;
    }
    public static String getApptTitle() {return title.get();}

    // description
    public StringProperty getDescription() {
        return description;
    }
    public static String getApptDescript() {return description.get();}

    // location
    public StringProperty getLocation() {
        return location;
    }
    public static String getApptLocation() {return location.get();}

    // type
    public StringProperty getType() {
        return type;
    }
    public static String getApptType() {return type.get();}

    // start datetime
    public ObjectProperty<LocalDateTime> getStart() {
        return start;
    }

    // end datetime
    public ObjectProperty<LocalDateTime> getEnd() {
        return end;
    }

    // create_date datetime
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    // created_by
    public StringProperty getCreatedBy() {
        return createdBy;
    }

    // last_update timestamp
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    // last_updated_by
    public StringProperty getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    // customer id
    public IntegerProperty getCustomerID() {
        return customerID;
    }
    public static int getApptCustomerID() {return customerID.get();}

    // user id
    public IntegerProperty getUserID() {
        return userID;
    }
    public static int getApptUserID() {return userID.get();}

    // contact id
    public int getContact() {
        return contactID.get();
    }
    public IntegerProperty getApptContactID() { return contactID;}

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