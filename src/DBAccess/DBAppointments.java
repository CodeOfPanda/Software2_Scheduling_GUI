package DBAccess;

import Models.Appointments;
import Models.Report_AppointmentsByCountry;
import Models.Report_CustomerAppointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DBConnection;
import java.sql.*;
import java.time.LocalDateTime;

// dbName: WJ07K54

/** This class queries a database in MySQL Workbench to access the data in the WJ07K54.appointments table.*/
public class DBAppointments {
    /** This is an ObservableList method that returns the data for each field in the WJ07K54.appointments table.
     *  This method queries the database to access the data for each field and with it creates an instance of type Appointments from the Appointments model.
     *  @return Returns an ObservableList of appointment information.*/
    public static ObservableList<Appointments> getAllAppts() {
        ObservableList<Appointments> apptList = FXCollections.observableArrayList();

        try {

            // My SQL statement
            String sql = "select * from WJ07K54.appointments;";
            PreparedStatement pStmt = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {
                // getting the data from mySQL
                int appointmentID = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                LocalDateTime createDate = rs.getTimestamp("Create_Date").toLocalDateTime();
                String createdBy = rs.getString("Created_By");
                Timestamp lastUpdate = rs.getTimestamp("Last_Update");
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int customerID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");
                Appointments list = new Appointments(appointmentID, title, description, location, type, start, end
                        ,createDate, createdBy,lastUpdate, lastUpdatedBy, customerID, userID, contactID);
                apptList.add(list);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return apptList;
    }

    /** This is an ObservableList method that returns a specific appointment that is associate with a specific Customer.
     *  This method queries the database to access the data of a specific appointment or appointments that are associated with a specific contact.
     *  @param customerName The Customer's Name
     *  @return Returns an ObservableList of type Appointments from the Appointments model that contains the Customer's appointment information.*/
    public static ObservableList<Appointments> getSpecificCustomerAppts(String customerName) {
        ObservableList<Appointments> apptList = FXCollections.observableArrayList();

        try {
            // My SQL statement
            String sql = "select a.*\n" +
                    "    from WJ07K54.appointments a\n" +
                    "    join WJ07K54.customers b on a.Customer_ID=b.Customer_ID\n" +
                    "    where Customer_Name=?;";
            PreparedStatement pStmt = DBConnection.getConnection().prepareStatement(sql);
            pStmt.setString(1, customerName);
            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {
                // getting the data from mySQL
                int appointmentID = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                LocalDateTime createDate = rs.getTimestamp("Create_Date").toLocalDateTime();
                String createdBy = rs.getString("Created_By");
                Timestamp lastUpdate = rs.getTimestamp("Last_Update");
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int customerID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");
                Appointments list = new Appointments(appointmentID, title, description, location, type, start, end
                        ,createDate, createdBy,lastUpdate, lastUpdatedBy, customerID, userID, contactID);
                apptList.add(list);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return apptList;
    }

    /** This is an ObservableList method that returns a specific appointment that is associate with a specific contact.
     *  This method queries the database to access the data of a specific appointment or appointments that are associated with a specific contact.
     *  @param contactName The Contact's Name
     *  @return Returns an ObservableList of type Appointments from the Appointments model that contains the appointment information.*/
    public static ObservableList<Appointments> getSpecificAppt(String contactName) {
        ObservableList<Appointments> apptList = FXCollections.observableArrayList();

        try {

            // My SQL statement
            String sql = "select a.*\n" +
                    "    from WJ07K54.appointments a\n" +
                    "    join WJ07K54.contacts b on a.Contact_ID=b.Contact_ID\n" +
                    "    where Contact_Name=?;";
            PreparedStatement pStmt = DBConnection.getConnection().prepareStatement(sql);
            pStmt.setString(1, contactName);
            ResultSet rs = pStmt.executeQuery();


            while (rs.next()) {
                // getting the data from mySQL
                int appointmentID = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                LocalDateTime createDate = rs.getTimestamp("Create_Date").toLocalDateTime();
                String createdBy = rs.getString("Created_By");
                Timestamp lastUpdate = rs.getTimestamp("Last_Update");
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int customerID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");
                Appointments list = new Appointments(appointmentID, title, description, location, type, start, end
                        ,createDate, createdBy,lastUpdate, lastUpdatedBy, customerID, userID, contactID);
                apptList.add(list);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return apptList;
    }

    /** This is an ObservableList method that returns any appointment that is in the current month and current year.
     *  This method queries the database to access the appointment data that is scheduled in the current month and year.
     *  @return Returns an ObservableList of appointment information that is associated with the current month and year.*/
    public static ObservableList<Appointments> getApptsThisMonth() {
        ObservableList<Appointments> apptsThisMonth = FXCollections.observableArrayList();

        try {
            // mySql statement
            String sql = "select *\n" +
                    "from WJ07K54.appointments\n" +
                    "where MONTH(Start) = MONTH(CURRENT_DATE())\n" +
                    "and YEAR(Start) = YEAR(CURRENT_DATE());";
            PreparedStatement pStmt = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {
                int appointmentID = rs.getInt("Appointment_ID");  // should be autogenerated
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime(); // unsure on if this will work found on Stack
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                LocalDateTime createDate = rs.getTimestamp("Create_Date").toLocalDateTime();
                String createdBy = rs.getString("Created_By");
                Timestamp lastUpdate = rs.getTimestamp("Last_Update");
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int customerID = rs.getInt("Customer_ID");  // should be autogenerated
                int userID = rs.getInt("User_ID");  // should be autogenerated
                int contactID = rs.getInt("Contact_ID"); // should be autogenerated
                Appointments list = new Appointments(appointmentID, title, description, location, type, start, end
                        ,createDate, createdBy,lastUpdate, lastUpdatedBy, customerID, userID, contactID);
                apptsThisMonth.add(list);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return apptsThisMonth;
    }

    /** This is an ObservableList method that returns any appointment that is in the current week and current year.
     *  This method queries the database to access the appointment data that is scheduled in the current week and year.
     *  @return Returns an ObservableList of appointment information that is associated with the current week and year.*/
    public static ObservableList<Appointments> getApptsThisWeek() {
        ObservableList<Appointments> apptsThisWeek = FXCollections.observableArrayList();

        try {
            // mySql statement
            String sql = "select *\n" +
                    "from WJ07K54.appointments\n" +
                    "where YEARWEEK(Start, 1) = YEARWEEK(CURRENT_DATE(), 1);";
            PreparedStatement pStmt = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {
                int appointmentID = rs.getInt("Appointment_ID");  // should be autogenerated
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime(); // unsure on if this will work found on Stack
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                LocalDateTime createDate = rs.getTimestamp("Create_Date").toLocalDateTime();
                String createdBy = rs.getString("Created_By");
                Timestamp lastUpdate = rs.getTimestamp("Last_Update");
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int customerID = rs.getInt("Customer_ID");  // should be autogenerated
                int userID = rs.getInt("User_ID");  // should be autogenerated
                int contactID = rs.getInt("Contact_ID"); // should be autogenerated
                Appointments list = new Appointments(appointmentID, title, description, location, type, start, end
                        ,createDate, createdBy,lastUpdate, lastUpdatedBy, customerID, userID, contactID);
                apptsThisWeek.add(list);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return apptsThisWeek;
    }

    /** This is a method that creates a new appointments in the database.
     *  This method inserts a new appointment into the WJ07K54.appointments table.
     *  @param title The appointment's title
     *  @param description The appointment's description
     *  @param location The appointment's location
     *  @param type The appointment's type
     *  @param start The date and time the appointment starts
     *  @param end The date and time the appointment ends
     *  @param createDate The date and time the appointment was created
     *  @param createdBy The user who created the appointment
     *  @param lastUpdate The date and time the appointment was last updated
     *  @param lastUpdatedBy The users who last updated the appointment
     *  @param customerID The ID of the customer associated with the appointment
     *  @param userID The ID of the user who is associated with the appointment
     *  @param contactID The ID of the contact who is associated with the appointment*/
    public static void createAppt(String title
            , String description
            , String location
            , String type
            , LocalDateTime start
            , LocalDateTime end
            , LocalDateTime createDate
            , String createdBy
            , LocalDateTime lastUpdate
            , String lastUpdatedBy
            , int customerID
            , int userID
            , int contactID
    )
    {
        try {
            // mySQL statement
            String createAppt = "INSERT INTO WJ07K54.appointments VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement psCA = DBConnection.getConnection().prepareStatement(createAppt);
            // appointment_id is null here
            psCA.setString(1, title);
            psCA.setString(2, description);
            psCA.setString(3, location);
            psCA.setString(4, type);
            psCA.setTimestamp(5, Timestamp.valueOf(start));
            psCA.setTimestamp(6, Timestamp.valueOf(end));
            psCA.setTimestamp(7, Timestamp.valueOf(createDate));
            psCA.setString(8, createdBy);
            psCA.setTimestamp(9, Timestamp.valueOf(lastUpdate));
            psCA.setString(10,lastUpdatedBy);
            psCA.setInt(11, customerID);
            psCA.setInt(12, userID);
            psCA.setInt(13, contactID);

            psCA.execute();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /** This is a method that updates an existing appointment in the database.
     *  This method updates an existing appointment into the WJ07K54.appointments table.
     * @param appt_ID The ID of the appointment that is to be updated
     *  @param title The appointment's title
     *  @param description The appointment's description
     *  @param location The appointment's location
     *  @param type The appointment's type
     *  @param start The date and time the appointment starts
     *  @param end The date and time the appointment ends
     *  @param lastUpdate The date and time the appointment was last updated
     *  @param lastUpdatedBy The users who last updated the appointment
     *  @param customerID The ID of the customer associated with the appointment
     *  @param userID The ID of the user who is associated with the appointment
     *  @param contactID The ID of the contact who is associated with the appointment*/
    // method to modify an appointment
    public static void modifyAppt(int appt_ID
            , String title
            , String description
            , String location
            , String type
            , LocalDateTime start
            , LocalDateTime end
            , LocalDateTime lastUpdate
            , String lastUpdatedBy
            , int customerID
            , int userID
            , int contactID)
    {
        try {
            // mySQL statement
            String sqlUpdate = "update WJ07K54.appointments " +
                    "set Title=?, Description=? ,Location=?, Type=?, Start=?, End=?, Last_Update=?" +
                    ", Last_Updated_By=?, Customer_ID=?, User_ID=?, Contact_ID=? " +
                    "where Appointment_ID=?;";
            PreparedStatement psUpdate = DBConnection.getConnection().prepareStatement(sqlUpdate);
            psUpdate.setString(1, title);
            psUpdate.setString(2, description);
            psUpdate.setString(3, location);
            psUpdate.setString(4, type);
            psUpdate.setTimestamp(5, Timestamp.valueOf(start));
            psUpdate.setTimestamp(6, Timestamp.valueOf(end));
            psUpdate.setTimestamp(7, Timestamp.valueOf(lastUpdate));
            psUpdate.setString(8, lastUpdatedBy);
            psUpdate.setInt(9, customerID);
            psUpdate.setInt(10, userID);
            psUpdate.setInt(11, contactID);
            psUpdate.setInt(12, appt_ID);

            psUpdate.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** This is a method that deletes an appointment from the database.
     *  This method deletes an appointment from the WJ07K54.appointments table.
     *  @param ID The ID of the appointment*/
    public static void deleteAppt(int ID) {
        try {
            // mySQL statement
            String sql = "delete from WJ07K54.appointments where Appointment_ID=?;";
            PreparedStatement pStmt = DBConnection.getConnection().prepareStatement(sql);
            pStmt.setInt(1, ID);
            pStmt.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /** This is an ObservableList method that returns the appointment's Type, the amount of each type, and the month that appointment is scheduled in.
     *  This method queries the database to access the data for appointment type and returns the different types by count and the month it is scheduled in.
     *  @return Returns an ObservableList of type Report_CustomerAppointments from the Report_CustomerAppointments model containing appointment information*/
    public static ObservableList<Report_CustomerAppointments> getApptsByMonthType() {
        ObservableList<Report_CustomerAppointments> apptsByMonthType = FXCollections.observableArrayList();

        try {
            // mySQL statement
            String sql = "select Monthname(Start) as Month, count(Type) as Count, Type\n" +
                    "from WJ07K54.appointments\n" +
                    "where YEAR(Start) = YEAR(CURRENT_DATE())\n" +
                    "group by 1,3;";
            PreparedStatement pStmt = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = pStmt.executeQuery();
            while (rs.next()) {
                String month = rs.getString("Month");
                int count = rs.getInt("Count");
                String type = rs.getString("Type");
                Report_CustomerAppointments list = new Report_CustomerAppointments(month, count, type);
                apptsByMonthType.add(list);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return apptsByMonthType;
    }
    /** This is an ObservableList method that returns the appointment's Type, the amount of each type, and the country where that appointment takes place.
     *  This method queries the database to access the data for appointment type and returns the different types by count and the country where that appointment takes place.
     *  @return Returns an ObservableList of type Report_AppointmentsByCountry from the Report_AppointmentsByCountry model containing appointment and country information*/
    public static ObservableList<Report_AppointmentsByCountry> getApptsByCountry() {
        ObservableList<Report_AppointmentsByCountry> apptsByCountry = FXCollections.observableArrayList();

        try {
            // mySQL statement
            String sql = "select d.Country, count(a.Type) as Count, a.Type\n" +
                    "from WJ07K54.appointments a\n" +
                    "\tjoin WJ07K54.customers b on a.Customer_ID=b.Customer_ID\n" +
                    "    join WJ07K54.first_level_divisions c on b.Division_ID=c.Division_ID\n" +
                    "    join WJ07K54.countries d on c.Country_ID=d.Country_ID\n" +
                    "group by 1, 3;";
            PreparedStatement pStmt = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = pStmt.executeQuery();
            while (rs.next()) {
                String country = rs.getString("Country");
                int count = rs.getInt("Count");
                String type = rs.getString("Type");
                Report_AppointmentsByCountry list = new Report_AppointmentsByCountry(country, type, count);
                apptsByCountry.add(list);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return apptsByCountry;
    }
    /** This method returns the number of appointments that are within 15 minutes of the user logging in to the application.
     *  This method queries the database to return the number of appointments that are within 15 minutes of the user logging into the application.
     *  @param time The current time in UTC plus 15 minutes
     *  @param currentTime The current time in UTC
     *  @return Returns the number of appointments that are within 15 minutes of the user logging into the application*/
    public static int getApptsIn15Mins(LocalDateTime time, LocalDateTime currentTime) {
        int count = 0;
        try {
            //mySQL statement
            String sql = "SELECT count(*) as Appointment_Count FROM WJ07K54.appointments\n" +
                    "where Start <=? and Start >= ?";
            PreparedStatement pStmt = DBConnection.getConnection().prepareStatement(sql);
            pStmt.setObject(1, time);
            pStmt.setObject(2, currentTime);
            ResultSet rs = pStmt.executeQuery();
            while (rs.next()) {
                count = rs.getInt("Appointment_Count");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    /** This is an ObservableList method that returns the appointments that are within 15 minutes of the user logging in to the application.
     *  This method queries the database to return the appointment or appointments that are within 15 minutes of the user logging into the application.
     *  @param time The current time in UTC plus 15 minutes
     *  @param currentTime The current time in UTC
     *  @return Returns an ObservableList of type Appointments from the Appointments model that contains the appointment(s) that are within 15 minutes of the user logging into the application*/
    public static ObservableList<Appointments> getApptDataWithIn15Min(LocalDateTime time, LocalDateTime currentTime) {
        ObservableList<Appointments> apptData = FXCollections.observableArrayList();
        try {
            //mySQL statement
            String sql = "select * from WJ07K54.appointments\n" +
                    "where Start <=? and Start >= ?";
            PreparedStatement pStmt = DBConnection.getConnection().prepareStatement(sql);
            pStmt.setObject(1, time);
            pStmt.setObject(2, currentTime);
            ResultSet rs = pStmt.executeQuery();
            while (rs.next()) {
                int appointmentID = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                LocalDateTime createDate = rs.getTimestamp("Create_Date").toLocalDateTime();
                String createdBy = rs.getString("Created_By");
                Timestamp lastUpdate = rs.getTimestamp("Last_Update");
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int customerID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");
                Appointments list = new Appointments(appointmentID, title, description, location, type, start, end
                        ,createDate, createdBy,lastUpdate, lastUpdatedBy, customerID, userID, contactID);
                apptData.add(list);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return apptData;
    }

}
