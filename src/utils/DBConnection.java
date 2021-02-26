package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // JDBC URL parts
    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String ipAddress = "//wgudb.ucertify.com/";
    private static final String dbName = "WJ07K54";

    //JDBC URL
    private static final String jdbcURL = protocol + vendorName + ipAddress + dbName;

    //Driver interface reference
    private static final String MYSQLJDBCDRIVER = "com.mysql.cj.jdbc.Driver";
    private static Connection conn = null;

    //Username and Password
    private static final String username = "U07K54";
    private static final String password = "53689049412";

    // method to connect database with custom message
    public static Connection startConnection() {
        try {
            Class.forName(MYSQLJDBCDRIVER);
            conn = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Connection successful");
        }
        catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error: not connecting!" + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }

    //keeps the connection to the Db open throughout the entire runtime.
    //allows faster and more efficient querying on a single user application.
    public static Connection getConnection() {
        return conn;
    }

    // method to close database connection with custom error message
    public static void closeConnection() {
        try {
            conn.close();
            System.out.println("Connection closed");
        }
        catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            //do nothing, because it could have already been closed out prior to the end of the program.
            //we don't need to worry about it throwing an exception after the end of the program.
        }
    }
}
