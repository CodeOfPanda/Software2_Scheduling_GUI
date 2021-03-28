package DBAccess;

import Models.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;


// dbName: WJ07K54
/** This class queries a database in MySQL Workbench to access the data in the WJ07K54.users table.*/
public class DBUsers {
    /** This is an ObservableList method that accesses and returns the data for each field in the WJ07K54.users table.
     *  This method accesses and returns the data in each field of the WJ07K54.users table.
     *  @return Returns an ObservableList of type Users from the Users model that contains User information*/
    public static ObservableList<Users> getAllUsers() {
        ObservableList<Users> userList = FXCollections.observableArrayList();

        try{
            // mySQL statement
            String sql = "select * from WJ07K54.users;";
            PreparedStatement psUsers = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = psUsers.executeQuery();

            while (rs.next()) {
                int userID = rs.getInt("User_ID");
                String name = rs.getString("User_Name");
                String password = rs.getString("Password");
                LocalDateTime createDate = rs.getTimestamp("Create_Date").toLocalDateTime();
                String createdBy = rs.getString("Created_By");
                Timestamp lastUpdate = rs.getTimestamp("Last_Update");
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                Users list = new Users(userID, name, password, createDate, createdBy, lastUpdate, lastUpdatedBy);
                userList.add(list);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
    /** This is a method that returns a specific User's Name that is associated with a specific User ID.
     *  This method queries the database and returns a specific User's name that is associated with a specific User ID.
     *  @param ID The Customer's ID
     *  @return Returns the Customer's name that is associated with the Customer ID.*/
    public static String getUserName(int ID) {
        String userNames = null;
        try {
            // mySQL statement
            String userNameSql = "select User_Name from WJ07K54.users where User_ID=?;";
            PreparedStatement psUserName = DBConnection.getConnection().prepareStatement(userNameSql);
            psUserName.setInt(1, ID);
            ResultSet nameResults = psUserName.executeQuery();

            while (nameResults.next()) {
                userNames = nameResults.getString("User_Name");
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return userNames;
    }
    /** This is an ObservableList method that returns the User Names.
     *  This method queries the database and returns an ObservableList of User Names.
     *  @return Returns an ObservableList of Strings containing User Names*/
    public static ObservableList<String> getUserNames() {
        ObservableList<String> names = FXCollections.observableArrayList();
        try {
            // mySQL statement
            String userNameSql = "select User_Name from WJ07K54.users;";
            PreparedStatement psUserName = DBConnection.getConnection().prepareStatement(userNameSql);

            ResultSet nameResults = psUserName.executeQuery();

            while (nameResults.next()) {
                String name = nameResults.getString("User_Name");
                names.add(name);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return names;
    }
    /** This is a method that returns the password that is associated with a specific User Name.
     *  This method queries the database and returns the password that is associated with a specific User Name.
     * @param userName The User's Name
     *  @return Returns a password*/
    public static String getUserPassword(String userName) {
        String userPassword = null;
        try {
            // mySQL statement
            String userPasswordsSql = "select Password from WJ07K54.users where User_Name=?;";
            PreparedStatement psUserPasswords = DBConnection.getConnection().prepareStatement(userPasswordsSql);
            psUserPasswords.setString(1, userName);
            ResultSet passwordResults = psUserPasswords.executeQuery();

            while (passwordResults.next()) {
                userPassword = passwordResults.getString("Password");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return userPassword;
    }

}
