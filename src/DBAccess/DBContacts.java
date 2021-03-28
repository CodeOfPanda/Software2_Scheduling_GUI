package DBAccess;

import Models.Contacts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// dbName: WJ07K54

/** This class queries a database in MySQL Workbench to access the data in the WJ07K54.contacts table.*/
public class DBContacts {
    /** This is an ObservableList method that returns the data for each field in the WJ07K54.contacts table.
     *  This method queries the database to access the data for each field in the WJ07K54.contacts table.
     *  @return Returns an ObservableList of type Contacts from the Contacts model that contains contact information.*/
    public static ObservableList<Contacts> getAllContacts() {
        ObservableList<Contacts> contactAppts = FXCollections.observableArrayList();

        try{
            // mySQL statement
            String sql = "select * from WJ07K54.contacts;";
            PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int contactID = rs.getInt("Contact_ID");
                String name = rs.getString("Contact_Name");
                String email = rs.getString("Email");
                Contacts list = new Contacts(contactID, name, email);
                contactAppts.add(list);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return contactAppts;
    }
    /** This is a method that returns a specific Contact's name.
     *  This method queries the database to access the Contact's name that is associated with a specific Contact ID.
     *  @param ID The Contact's ID
     *  @return Returns the Contact's Name*/
    public static String getContactName(int ID) {
        String contactName = null;
        try{
            // mySQL statement
            String nameSql = "select Contact_Name from WJ07K54.contacts where Contact_ID=?;";
            PreparedStatement psName = DBConnection.getConnection().prepareStatement(nameSql);
            psName.setInt(1, ID);
            ResultSet nameResults = psName.executeQuery();

            while (nameResults.next()) {
                contactName = nameResults.getString("Contact_Name");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contactName;
    }
    /** This is a method that returns a specific Contact's ID.
     *  This method queries the database to access the Contact's ID that is associated with a specific Contact Name.
     *  @param name The Contact's Name
     *  @return Returns the Contact's ID*/
    public static int getContactID(String name) {
        int contactID = 0;
        try {
            // mySQL statement
            String idSql = "select Contact_ID from WJ07K54.contacts where Contact_Name=?;";
            PreparedStatement psID = DBConnection.getConnection().prepareStatement(idSql);
            psID.setString(1, name);
            ResultSet idResults = psID.executeQuery();

            while (idResults.next()) {
                contactID = idResults.getInt("Contact_ID");
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return contactID;
    }

}


