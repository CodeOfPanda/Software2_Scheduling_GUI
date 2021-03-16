package Models;

import javafx.beans.property.*;

public class Contacts {
    private static IntegerProperty contactID;
    private static StringProperty contactName;
    private static StringProperty contactEmail;

    public Contacts(int contactID, String name, String email) {
        Contacts.contactID = new SimpleIntegerProperty(contactID);
        contactName = new SimpleStringProperty(name);
        contactEmail = new SimpleStringProperty(email);
    }

    // contactID
    public static IntegerProperty getContactID() {
        return contactID;
    }

    // contact name
    public static StringProperty getContactName() {
        return contactName;
    }

    // contact email
    public static StringProperty getContactEmail() {
        return contactEmail;
    }
}

//Contact_ID int(10) AI PK
//Contact_Name varchar(50)
//Email varchar
