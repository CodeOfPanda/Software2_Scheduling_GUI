package DBAccess;

import Models.Contacts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// dbName: WJ07K54

public class DBContacts {

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

}

//Contact_ID int(10) AI PK
//Contact_Name varchar(50)
//Email varchar


