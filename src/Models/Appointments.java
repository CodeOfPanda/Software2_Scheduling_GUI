package Models;

import DBAccess.DBContacts;
import DBAccess.DBCustomers;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Timestamp;
import java.time.LocalDateTime;


public class Appointments {
    // properties to populate the table data.
    // for some reason these property variables can't be static and load the data correctly.
    private static ObservableList<Appointments> allAppts = FXCollections.observableArrayList();

    // for populating combo-boxes.
    private static ObservableList<String> contactNames = FXCollections.observableArrayList();
    private static ObservableList<String> customerNames = FXCollections.observableArrayList();
    private static ObservableList<String> allApptTypes = FXCollections.observableArrayList();
    private static ObservableList<Integer> customerIDs = FXCollections.observableArrayList();

    private final IntegerProperty appointmentID;
    private final StringProperty title;
    private final StringProperty location;
    private final StringProperty description;
    private final StringProperty type;
    private final ObjectProperty<LocalDateTime> start;
    private final ObjectProperty<LocalDateTime> end;
    private final ObjectProperty<LocalDateTime> createDate;
    private final StringProperty createdBy;
    private final ObjectProperty<Timestamp> lastUpdate;
    private final StringProperty lastUpdatedBy;
    private final IntegerProperty customerID;
    private final IntegerProperty userID;
    private final IntegerProperty contactID;
    // static variables
//    private static int apptIDCount = 0;
//    private static int apptID;
//    private static String apptTitle;
//    private static String apptLocation;
//    private static String apptDescription;
//    private static String apptType;
//    private static LocalDateTime apptStart;
//    private static LocalDateTime apptEnd;
    private static LocalDateTime apptCreateDate;
    private static String apptCreatedBy;
    private static String apptLastUpdatedBy;
//    private static int apptCustomerID;
//    private static int apptUserID;
//    private static int apptContactID;


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
        // static variables
//        apptContactID = contactID;
//        apptType = type;
//        apptDescription = description;
//        apptLocation = location;
//        apptTitle = title;
//        apptStart = start;
//        apptEnd = end;
        apptCreateDate = createDate;
        apptCreatedBy = createdBy;
        apptLastUpdatedBy = lastUpdatedBy;
//        apptCustomerID = customerID;
//        apptUserID = userID;
//        apptID = appointmentID;
    }

    // appointmentID
    public IntegerProperty getAppointmentID() {
        return appointmentID;
    }
    public String getApptID() {
        return String.valueOf(appointmentID.get());
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
    public LocalDateTime getApptStart() {return start.get();}

    // end datetime
    public ObjectProperty<LocalDateTime> getEnd() {
        return end;
    }
    public LocalDateTime getApptEnd() {return end.get();}

    // create_date datetime
    public ObjectProperty<LocalDateTime> getCreateDate() {
        return createDate;
    }
    public static LocalDateTime getApptCreateDate() {
        return apptCreateDate;
    }
    public static LocalDateTime getCurrentDateTime() {
        LocalDateTime dateTime = LocalDateTime.now();
        return dateTime;
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
    public IntegerProperty getApptContactID() { return contactID;}
    public int getContact() {
        return contactID.get();
    }

    // methods to populate combo-boxes
    public static ObservableList<String> getContactNames() {
        contactNames.clear();
        DBContacts.getAllContacts().forEach((contact) -> {
            contactNames.add(contact.getName());
        });
        return contactNames;
    }

    public static ObservableList<String> getCustomerNames() {
        customerNames.clear();
        DBCustomers.getAllCust().forEach((customer) -> {
            customerNames.add(customer.getName());
        });
        return customerNames;
    }

    public static ObservableList<String> getAllApptTypes() {
        allApptTypes.clear();
        allApptTypes.addAll("Planning", "De-Briefing", "Timeline Check");
        return allApptTypes;
    }

    public static ObservableList<Integer> getCustomerIDs() {
        customerIDs.clear();
        DBCustomers.getAllCust().forEach((customer) -> {
            customerIDs.add(customer.getCustID());
        });
        return customerIDs;
    }
}
