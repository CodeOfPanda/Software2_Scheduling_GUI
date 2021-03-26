package Models;

import DBAccess.DBContacts;
import DBAccess.DBCustomers;
import DBAccess.DBUsers;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Appointments {
    // for populating combo-boxes.
    private static ObservableList<String> contactNames = FXCollections.observableArrayList();
    private static ObservableList<String> customerNames = FXCollections.observableArrayList();
    private static ObservableList<String> allApptTypes = FXCollections.observableArrayList();
    private static ObservableList<Integer> customerIDs = FXCollections.observableArrayList();
    private static ObservableList<Integer> userIDs = FXCollections.observableArrayList();
    private static ObservableList<LocalTime> startWorkHours = FXCollections.observableArrayList();
    private static ObservableList<LocalTime> endWorkHours = FXCollections.observableArrayList();

    private final IntegerProperty appointmentID;
    private final StringProperty title;
    private final StringProperty location;
    private final StringProperty description;
    private final StringProperty type;
    private final ObjectProperty<LocalDateTime> start;
    private final ObjectProperty<LocalDateTime> end;
    private final StringProperty lastUpdatedBy;
    private final IntegerProperty customerID;
    private final IntegerProperty userID;
    private final IntegerProperty contactID;

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
        this.lastUpdatedBy = new SimpleStringProperty(lastUpdatedBy);
        this.customerID = new SimpleIntegerProperty(customerID);
        this.userID = new SimpleIntegerProperty(userID);
        this.contactID = new SimpleIntegerProperty(contactID);
    }

    // appointmentID
    public IntegerProperty getAppointmentID() {
        return appointmentID;
    }
    public int getApptID() {
        return appointmentID.get();
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

    // current date and time
    public static LocalDateTime getCurrentDateTime() {
        LocalDateTime dateTime = LocalDateTime.now();
        return dateTime;
    }

    // last_updated_by
    public StringProperty getLastUpdatedBy() {
        return lastUpdatedBy;
    }

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
        allApptTypes.addAll("Planning Session", "De-Briefing", "Timeline Check");
        return allApptTypes;
    }

    public static ObservableList<Integer> getCustomerIDs() {
        customerIDs.clear();
        DBCustomers.getAllCust().forEach((customer) -> {
            customerIDs.add(customer.getCustID());
        });
        return customerIDs;
    }

    public static ObservableList<Integer> getUserIDs() {
        userIDs.clear();
        DBUsers.getAllUsers().forEach((user) -> {
            userIDs.add(user.getUserID());
        });
        return userIDs;
    }

    public static ObservableList<LocalTime> getStartWorkHours() {
//        startWorkHours.clear();
//        startWorkHours.addAll("08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00"
//                , "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00");


        LocalTime start = LocalTime.of(8, 00);
        LocalTime end = LocalTime.of(22, 00);

        while (start.isBefore(end.minusMinutes(30))) {
            startWorkHours.add(start);
            start = start.plusMinutes(30);
        }
        return startWorkHours;
    }

    public static ObservableList<LocalTime> getEndWorkHours() {
//        endWorkHours.clear();
//        endWorkHours.addAll("08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00"
//                , "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00");

        LocalTime start = LocalTime.of(8, 30);
        LocalTime end = LocalTime.of(22, 00);

        while (start.isBefore(end.plusSeconds(1))) {
            endWorkHours.add(start);
            start = start.plusMinutes(30);
        }
        return endWorkHours;
    }
}
