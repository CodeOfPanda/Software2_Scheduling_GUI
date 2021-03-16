package Models;

import javafx.beans.property.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;


public class Appointments {
    // properties to populate the table data.
    // for some reason these property variables can't be static and load the data correctly.
    private IntegerProperty appointmentID;
    private StringProperty title;
    private StringProperty location;
    private StringProperty description;
    private StringProperty type;
    private ObjectProperty<LocalDateTime> start;
    private ObjectProperty<LocalDateTime> end;
    private ObjectProperty<LocalDateTime> createDate;
    private StringProperty createdBy;
    private ObjectProperty<Timestamp> lastUpdate;
    private StringProperty lastUpdatedBy;
    private IntegerProperty customerID;
    private IntegerProperty userID;
    private IntegerProperty contactID;
    // static methods
    private static int apptIDCount = 0;
    private static int apptID;
    private static String apptTitle;
    private static String apptLocation;
    private static String apptDescription;
    private static String apptType;
    private static ObjectProperty<LocalDateTime> apptStart;
    private static ObjectProperty<LocalDateTime> apptEnd;
    private static ObjectProperty<LocalDateTime> apptCreateDate;
    private static String apptCreatedBy;
    private static ObjectProperty<Timestamp> apptLastUpdate;
    private static String apptLastUpdatedBy;
    private static int apptCustomerID;
    private static int apptUserID;
    private static int apptContactID;
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
        this.createDate = new SimpleObjectProperty<>(createDate);
        this.createdBy = new SimpleStringProperty(createdBy);
        this.lastUpdate = new SimpleObjectProperty<>(lastUpdate);
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
        return Appointments.apptID;
    }
    // for the auto gen ID
    public static int getApptIDCount() {
        return apptIDCount;
    }
    public void setApptIDCount() {
        apptIDCount += 1;
    }

    // title
    public StringProperty getTitle() {
        return title;
    }
    public String getApptTitle() {return title.get();}

    // description
    public StringProperty getDescription() {
        return description;
    }
    public String getApptDescript() {return description.get();}

    // location
    public StringProperty getLocation() {
        return location;
    }
    public String getApptLocation() {return location.get();}

    // type
    public StringProperty getType() {
        return type;
    }
    public String getApptType() {return type.get();}

    // start datetime
    public ObjectProperty<LocalDateTime> getStart() {
        return start;
    }

    // end datetime
    public ObjectProperty<LocalDateTime> getEnd() {
        return end;
    }

    // create_date datetime
    public ObjectProperty<LocalDateTime> getCreateDate() {
        return createDate;
    }

    // created_by
    public StringProperty getCreatedBy() {
        return createdBy;
    }
    public static String getApptCreatedBy() {return apptCreatedBy;}

    // last_update timestamp
    public ObjectProperty<Timestamp> getLastUpdate() {
        return lastUpdate;
    }

    // last_updated_by
    public StringProperty getLastUpdatedBy() {
        return lastUpdatedBy;
    }
    public static String getApptLastUpdatedBy() {return apptLastUpdatedBy;}

    // customer id
    public IntegerProperty getCustomerID() {
        return customerID;
    }
    public int getApptCustomerID() {return customerID.get();}

    // user id
    public IntegerProperty getUserID() {
        return userID;
    }
    public int getApptUserID() {return userID.get();}

    // contact id
    public int getContact() {
        return contactID.get();
    }
    public IntegerProperty getApptContactID() { return contactID;}

}
