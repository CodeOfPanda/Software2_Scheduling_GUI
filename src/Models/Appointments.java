package Models;

import DBAccess.DBContacts;
import DBAccess.DBCustomers;
import DBAccess.DBUsers;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Timestamp;
import java.time.*;

/** This class creates Appointments and is responsible for holding appointment data for retrieval.*/
public class Appointments {
    // for populating combo-boxes.
    private static ObservableList<String> contactNames = FXCollections.observableArrayList();
    private static ObservableList<String> customerNames = FXCollections.observableArrayList();
    private static ObservableList<String> allApptTypes = FXCollections.observableArrayList();
    private static ObservableList<Integer> customerIDs = FXCollections.observableArrayList();
    private static ObservableList<Integer> userIDs = FXCollections.observableArrayList();
    private static ObservableList<LocalTime> startWorkHours = FXCollections.observableArrayList();
    private static ObservableList<LocalTime> endWorkHours = FXCollections.observableArrayList();

    private final int appointmentID;
    private final String title;
    private final String location;
    private final String description;
    private final String type;
    private final ObjectProperty<LocalDateTime> start;
    private final ObjectProperty<LocalDateTime> end;
    private final String lastUpdatedBy;
    private final int customerID;
    private final int userID;
    private final int contactID;

    /** This is a constructor method that creates instances of the Appointment model.
     *  This method creates instances of the Appointment model.
     *  @param appointmentID The Appointment_ID
     *  @param title The appointment's Title
     *  @param description The appointment's Description
     *  @param location The appointment's Location
     *  @param type The appointment's Type
     *  @param start The date and time the appointment starts
     *  @param end The date and time the appointment ends
     *  @param createDate The date and time the appointment was created
     *  @param createdBy The user who created the appointment
     *  @param lastUpdate The date and time the appointment was last updated
     *  @param lastUpdatedBy The users who last updated the appointment
     *  @param customerID The ID of the customer associated with the appointment
     *  @param userID The ID of the user who is associated with the appointment
     *  @param contactID The ID of the contact who is associated with the appointment*/
    public Appointments(int appointmentID, String title, String description, String location, String type
                        ,LocalDateTime start, LocalDateTime end, LocalDateTime createDate, String createdBy
                        ,Timestamp lastUpdate, String lastUpdatedBy, int customerID, int userID, int contactID)
    {
        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = new SimpleObjectProperty<>(start);
        this.end = new SimpleObjectProperty<>(end);
        this.lastUpdatedBy = lastUpdatedBy;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
    }

    /** This method holds the Appointment_ID.
     *  This method is a getter method that holds the Appointment_ID value and can pass that value when called.
     *  @return Returns Appointment_ID*/
    public int getApptID() {
        return appointmentID;
    }

    /** This method holds the appointment's Title.
     *  This method is a getter method that holds the appointment's Title value and can pass that value when called.
     *  @return Returns the appointment's Title*/
    public String getApptTitle() {return title;}

    /** This method holds the appointment's Description.
     *  This method is a getter method that holds the appointment's Description value and can pass that value when called.
     *  @return Returns the appointment's Description*/
    public String getApptDescript() {return description;}

    /** This method holds the appointment's Location.
     *  This method is a getter method that holds the appointment's Location value and can pass that value when called.
     *  @return Returns the appointment's Location*/
    public String getApptLocation() {return location;}

    /** This method holds the appointment's Type.
     *  This method is a getter method that holds the appointment's Type value and can pass that value when called.
     *  @return Returns the appointment's Type*/
    public String getApptType() {return type;}

    /** This method holds the appointment's start date and time.
     *  This method is a getter method that holds the appointment's start date and time values and can pass those values when called.
     *  @return Returns the appointment's start date and time*/
    public ObjectProperty<LocalDateTime> getStart() {
        return start;
    }
    public LocalDateTime getApptStart() {
        return start.get();
    }

    /** This method holds the appointment's end date and time.
     *  This method is a getter method that holds the appointment's end date and time values and can pass those values when called.
     *  @return Returns the appointment's end date and time*/
    public ObjectProperty<LocalDateTime> getEnd() {
        return end;
    }
    public LocalDateTime getApptEnd() {return end.get();}

    /** This method holds the current date and time.
     *  This method is a getter method that holds the current date and time values and can pass those values when called.
     *  @return Returns the current date and time*/
    public static LocalDateTime getCurrentDateTime() {
        LocalDateTime dateTime = LocalDateTime.now();
        return dateTime;
    }

    /** This method holds the Customer_ID.
     *  This method is a getter method that holds the Customer_ID value and can pass that value when called.
     *  @return Returns the Customer_ID*/
    public int getApptCustomerID() {return customerID;}

    /** This method holds the User_ID.
     *  This method is a getter method that holds the User_ID value and can pass that value when called.
     *  @return Returns the User_ID*/
    public int getApptUserID() {return userID;}

    /** This method holds the Contact_ID.
     *  This method is a getter method that holds the Contact_ID value and can pass that value when called.
     *  @return Returns the Contact_ID*/
    public int getContact() {
        return contactID;
    }

    /*  --------------  methods to populate combo-boxes  -------------------  */

    /** This is a method that gets the names of the Contacts and holds them in an ObservableList of Strings.
     *  This method uses a Lambda expression to make adding the contact name for every contact record onto the ObservableList more efficient and less prone to typed errors.
     *  @return Returns an ObservableList of Strings containing the names of each Contact*/
    public static ObservableList<String> getContactNames() {
        contactNames.clear();
        DBContacts.getAllContacts().forEach((contact) -> {
            contactNames.add(contact.getName());
        });
        return contactNames;
    }

    /** This is a method that gets the names of the Customers and holds them in an ObservableList of Strings.
     *  This method uses a Lambda expression to make adding the Customer name for every Customers record onto the ObservableList more efficient and less prone to typed errors.
     *  @return Returns an ObservableList of Strings containing the names of each Customer*/
    public static ObservableList<String> getCustomerNames() {
        customerNames.clear();
        DBCustomers.getAllCust().forEach((customer) -> {
            customerNames.add(customer.getName());
        });
        return customerNames;
    }

    /** This is a method that gets the appointment Types and holds them in an ObservableList of Strings.
     *  This method uses a Lambda expression to make adding the appointment Types for every Appointment record onto the ObservableList more efficient and less prone to typed errors.
     *  @return Returns an ObservableList of Strings containing the appointment Types*/
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

    /** This is a method that gets the User_IDs and holds them in an ObservableList of Strings.
     *  This method uses a Lambda expression to make adding User_IDs for every User record onto the ObservableList more efficient and less prone to typed errors.
     *  @return Returns an ObservableList of Strings containing User_IDs*/
    public static ObservableList<Integer> getUserIDs() {
        userIDs.clear();
        DBUsers.getAllUsers().forEach((user) -> {
            userIDs.add(user.getUserID());
        });
        return userIDs;
    }
    /** This is a method that populates the start time combobox in the systemDefault time zone.
     *  This method populates the start time combobox with the appropriate system default time based off of the EST business hours.
     *  @return Returns the appointment times for scheduling hours.*/
    public static ObservableList<LocalTime> getStartWorkHours() {

        LocalTime startTimeEST = LocalTime.of(8, 00);

        LocalDateTime startLDTEST = LocalDateTime.of(LocalDate.now(), startTimeEST);
        ZonedDateTime estZDT = startLDTEST.atZone(ZoneId.of("America/New_York"));
        ZonedDateTime localZDT = estZDT.withZoneSameInstant(ZoneId.systemDefault());

        LocalTime start = localZDT.toLocalTime();

        LocalTime endTimeEST = LocalTime.of(22, 00);
        LocalDateTime endLDTEST = LocalDateTime.of(LocalDate.now(), endTimeEST);
        ZonedDateTime endESTZDT = endLDTEST.atZone(ZoneId.of("America/New_York"));
        ZonedDateTime endLocalZDT = endESTZDT.withZoneSameInstant(ZoneId.systemDefault());

        LocalTime end = endLocalZDT.toLocalTime();

        while (start.isBefore(end)) {
            startWorkHours.add(start);
            start = start.plusMinutes(30);
        }
        return startWorkHours;
    }
    /** This is a method that populates the end time combobox in the systemDefault time zone.
     *  This method populates the end time combobox with the appropriate system default time based off of the EST business hours.
     *  @return Returns the appointment times for scheduling hours.*/
    public static ObservableList<LocalTime> getEndWorkHours() {

        LocalTime startTimeEST = LocalTime.of(8, 30);

        LocalDateTime startLDTEST = LocalDateTime.of(LocalDate.now(), startTimeEST);
        ZonedDateTime estZDT = startLDTEST.atZone(ZoneId.of("America/New_York"));
        ZonedDateTime localZDT = estZDT.withZoneSameInstant(ZoneId.systemDefault());

        LocalTime start = localZDT.toLocalTime();

        LocalTime endTimeEST = LocalTime.of(22, 00);
        LocalDateTime endLDTEST = LocalDateTime.of(LocalDate.now(), endTimeEST);
        ZonedDateTime endESTZDT = endLDTEST.atZone(ZoneId.of("America/New_York"));
        ZonedDateTime endLocalZDT = endESTZDT.withZoneSameInstant(ZoneId.systemDefault());

        LocalTime end = endLocalZDT.toLocalTime();

        while (start.isBefore(end.plusSeconds(1))) {
            endWorkHours.add(start);
            start = start.plusMinutes(30);
        }
        return endWorkHours;
    }

}
