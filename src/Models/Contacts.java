package Models;

import javafx.beans.property.*;

/** This class creates Contacts and is responsible for holding Contact data for retrieval.*/
public class Contacts {
    private int contactID;
    private StringProperty contactName;
    private String contactEmail;

    /** This is a constructor method that creates instances of the Contacts Model.
     *  This method creates instances of the Contacts model.
     *  @param contactID The Contact's ID
     *  @param name The Contact's Name
     *  @param email The Contact's Email*/
    public Contacts(int contactID, String name, String email) {
        this.contactID = contactID;
        contactName = new SimpleStringProperty(name);
        contactEmail = email;
    }

    /** This method holds the Contact Name.
     *  This method is a getter method that holds the Contact Name value and can pass that value when called.
     *  @return Returns Contact Name*/
    public String getName(){return contactName.get();}

}

