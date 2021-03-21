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

    // contactID
//    public IntegerProperty getContactID() {
//        return contactID;
//    }
    public static int getContactID() {
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
