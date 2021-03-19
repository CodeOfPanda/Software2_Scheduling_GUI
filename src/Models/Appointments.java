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
    private static LocalDateTime apptStart;
    private static LocalDateTime apptEnd;
    private static LocalDateTime apptCreateDate;
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
        apptID = appointmentID;
        this.title = new SimpleStringProperty(title);
        apptTitle = title;
        this.description = new SimpleStringProperty(description);
        apptDescription = description;
        this.location = new SimpleStringProperty(location);
        apptLocation = location;
        this.type = new SimpleStringProperty(type);
        apptType = type;
        this.start = new SimpleObjectProperty<>(start);
        apptStart = start;
        this.end = new SimpleObjectProperty<>(end);
        apptEnd = end;
        this.createDate = new SimpleObjectProperty<>(createDate);
        apptCreateDate = createDate;
        this.createdBy = new SimpleStringProperty(createdBy);
        apptCreatedBy = createdBy;
        this.lastUpdate = new SimpleObjectProperty<>(lastUpdate);
        this.lastUpdatedBy = new SimpleStringProperty(lastUpdatedBy);
        apptLastUpdatedBy = lastUpdatedBy;
        this.customerID = new SimpleIntegerProperty(customerID);
        apptCustomerID = customerID;
        this.userID = new SimpleIntegerProperty(userID);
        apptUserID = userID;
        this.contactID = new SimpleIntegerProperty(contactID);
        apptContactID = contactID;
    }

    // appointmentID
    public IntegerProperty getAppointmentID() {
        return appointmentID;
    }
    public static String getApptID() {
        return String.valueOf(apptID);
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
    public static String getApptTitle() {return apptTitle;}

    // description
    public StringProperty getDescription() {
        return description;
    }
    public static String getApptDescript() {return apptDescription;}

    // location
    public StringProperty getLocation() {
        return location;
    }
    public static String getApptLocation() {return apptLocation;}

    // type
    public StringProperty getType() {
        return type;
    }
    public static String getApptType() {return apptType;}

    // start datetime
    public ObjectProperty<LocalDateTime> getStart() {
        return start;
    }
    public static LocalDateTime getApptStart() {return apptStart;}

    // end datetime
    public ObjectProperty<LocalDateTime> getEnd() {
        return end;
    }
    public static LocalDateTime getApptEnd() {return apptEnd;}

    // create_date datetime
    public ObjectProperty<LocalDateTime> getCreateDate() {
        return createDate;
    }
    public static LocalDateTime getApptCreateDate() {
        return apptCreateDate;
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
    public static ObjectProperty<Timestamp> getApptLastUpdate() {return apptLastUpdate;}

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
