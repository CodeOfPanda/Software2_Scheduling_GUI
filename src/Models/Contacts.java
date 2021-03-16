package Models;

import javafx.beans.property.*;

public class Contacts {
    private IntegerProperty contactID;
    private StringProperty contactName;
    private StringProperty contactEmail;

    public Contacts(int contactID, String name, String email) {
        this.contactID = new SimpleIntegerProperty(contactID);
        contactName = new SimpleStringProperty(name);
        contactEmail = new SimpleStringProperty(email);
    }

    // contactID
    public IntegerProperty getContactID() {
        return contactID;
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
