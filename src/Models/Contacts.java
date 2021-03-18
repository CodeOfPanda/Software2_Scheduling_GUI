package Models;

import javafx.beans.property.*;

public class Contacts {
    private IntegerProperty contactID;
    private StringProperty contactName;
    private StringProperty contactEmail;
    private static int ctID;
    private static String ctName;

    public Contacts(int contactID, String name, String email) {
        this.contactID = new SimpleIntegerProperty(contactID);
        ctID = contactID;
        contactName = new SimpleStringProperty(name);
        contactEmail = new SimpleStringProperty(email);
    }

    public Contacts(int ID, String name) { // going to use this to match the name and id for populating combo boxes
        contactID = new SimpleIntegerProperty(ID);
        ctID = ID;
        contactName = new SimpleStringProperty(name);
        ctName = name;
    }

    // contactID
    public IntegerProperty getContactID() {
        return contactID;
    }
    public static int getCtID() {
        return ctID;
    }
    // contact name
    public StringProperty getContactName() {
        return contactName;
    }
    public String getName(){return contactName.get();}

    // contact email
    public StringProperty getContactEmail() {
        return contactEmail;
    }
}

//Contact_ID int(10) AI PK
//Contact_Name varchar(50)
//Email varchar
