package Models;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Users {

    private int userID;
    private String userName;
    private String password;
    private LocalDateTime createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;
    private static String loggedInUser = null;

    public Users(int userID, String name, String password, LocalDateTime createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy) {
        this.userID = userID;
        userName = name;
        this.password = password;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }


    public int getUserID() {
        return userID;
    }

    public static void setLoggedInUser(String name) {
        loggedInUser = name;
    }
    
    public static String getLoggedInUser() {return loggedInUser;}
}
