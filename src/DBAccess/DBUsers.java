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
public class DBUsers {

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
                LocalDateTime createDate = rs.getObject("Create_Date", LocalDateTime.class);
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

}


/*User_ID int(10) AI PK
User_Name varchar(50)
Password text
Create_Date datetime
Created_By varchar(50)
Last_Update timestamp
Last_Updated_By*/