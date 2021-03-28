package DBAccess;

// dbName: WJ07K54

import Models.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/** This class queries a database in MySQL Workbench to access the data in the WJ07K54.customers table.*/
public class DBCustomers {
    /** This is an ObservableList method that returns the data for each field in the WJ07K54.customers table.
     *  This method queries the database to access and return the data for each field in the WJ07K54.customers table.
     *  @return Returns an ObservableList of type Customers from the Customers model that contains Customer information.*/
    public static ObservableList<Customers> getAllCust() {
        ObservableList<Customers> custList = FXCollections.observableArrayList();

        try{
            // mySQL statement
            String sql = "select * from WJ07K54.customers;";
            PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                int customerID = rs.getInt("Customer_ID");
                String name = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postalCode = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                LocalDateTime createDate = rs.getTimestamp("Create_Date").toLocalDateTime();
                String createdBy = rs.getString("Created_By");
                Timestamp lastUpdate = rs.getTimestamp("Last_Update");
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int divisionID = rs.getInt("Division_ID");
                Customers list = new Customers(customerID, name, address, postalCode, phone, createDate, createdBy, lastUpdate
                                                ,lastUpdatedBy, divisionID);
                custList.add(list);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return custList;
    }
    /** This is an ObservableList method that returns the data for a specific Customer.
     * This method queries the database to access and return the data for each field for a specific customer that is associated with a customer's name.
     * @param custName The Customer's Name
     * @return Returns an ObservableList of type Customers from the Customers model that contains the specific Customer's information.*/
    public static ObservableList<Customers> getSpecificCustomer(String custName) {
        ObservableList<Customers> custList = FXCollections.observableArrayList();

        try{
            // mySQL statement
            String sql = "select * from WJ07K54.customers where Customer_Name=?;";
            PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(sql);
            pstmt.setString(1, custName);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                int customerID = rs.getInt("Customer_ID");
                String name = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postalCode = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                LocalDateTime createDate = rs.getTimestamp("Create_Date").toLocalDateTime();
                String createdBy = rs.getString("Created_By");
                Timestamp lastUpdate = rs.getTimestamp("Last_Update");
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int divisionID = rs.getInt("Division_ID");
                Customers list = new Customers(customerID, name, address, postalCode, phone, createDate, createdBy, lastUpdate
                        ,lastUpdatedBy, divisionID);
                custList.add(list);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return custList;
    }
    /** This is a method that returns a specific Customer's Name that is associated with a specific Customer ID.
     *  This method queries the database and returns a specific Customer's name that is associated with a specific Customer ID.
     *  @param ID The Customer's ID
     *  @return Returns the Customer's name that is associated with the Customer ID.*/
    public static String getCustomerName(int ID) {
        String customerName = null;
        try{
            // mySQL statement
            String nameSql = "select Customer_Name from WJ07K54.customers where Customer_ID=?;";
            PreparedStatement psName = DBConnection.getConnection().prepareStatement(nameSql);
            psName.setInt(1, ID);
            ResultSet nameResults = psName.executeQuery();

            while (nameResults.next()) {
                customerName = nameResults.getString("Customer_Name");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerName;
    }

    /** This is a method that returns a specific Customer ID that is associated with a specific Customer's Name.
     *  This method queries the database and returns a specific Customer ID that is associated with a specific Customer's Name.
     *  @param name The Customer's Name
     *  @return Returns the Customer ID that is associated with the Customer Name.*/
    public static int getCustomerID(String name) {
        int customerID = 0;
        try {
            // mySQL statement
            String idSql = "select Customer_ID from WJ07K54.customers where Customer_Name=?;";
            PreparedStatement psID = DBConnection.getConnection().prepareStatement(idSql);
            psID.setString(1, name);
            ResultSet idResults = psID.executeQuery();

            while (idResults.next()) {
                customerID = idResults.getInt("Customer_ID");
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return customerID;
    }

    /** This is a method that creates a new Customer in the WJ07K54.customers table.
     *  This method creates a new customer record in the WJ07K54.customers table.
     *  @param customerName The Customer's Name
     *  @param address The Customer's address
     *  @param postalCode The Customer's postal code
     *  @param phone The Customer's phone number
     *  @param createDate The date and time that the customer record was created
     *  @param createdBy The user who created the new customer record
     *  @param lastUpdate The date and time that the customer record was last updated
     *  @param lastUpdatedBy The user who updated the customer record last
     *  @param divisionID The Division_ID for the Customer's address*/
    public static void createCustomer(String customerName
            , String address
            , String postalCode
            , String phone
            , LocalDateTime createDate
            , String createdBy
            , LocalDateTime lastUpdate
            , String lastUpdatedBy
            , int divisionID
            )
    {
        try {
            // mySQL statement
            String sql = "insert into WJ07K54.customers values(null, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement pStmt = DBConnection.getConnection().prepareStatement(sql);

            pStmt.setString(1, customerName);
            pStmt.setString(2, address);
            pStmt.setString(3, postalCode);
            pStmt.setString(4, phone);
            pStmt.setTimestamp(5, Timestamp.valueOf(createDate));
            pStmt.setString(6, createdBy);
            pStmt.setTimestamp(7, Timestamp.valueOf(lastUpdate));
            pStmt.setString(8, lastUpdatedBy);
            pStmt.setInt(9, divisionID); // can't be null

            pStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** This is a method that updates an existing Customer in the WJ07K54.customers table.
     *  This method updates an existing customer record in the WJ07K54.customers table.
     * @param customerID the Customer ID
     *  @param customerName The Customer's Name
     *  @param address The Customer's address
     *  @param postalCode The Customer's postal code
     *  @param phone The Customer's phone number
     *  @param lastUpdate The date and time that the customer record was last updated
     *  @param lastUpdatedBy The user who updated the customer record last
     *  @param divisionID The Division_ID for the Customer's address*/
    public static void modifyCustomer(int customerID
            , String customerName
            , String address
            , String postalCode
            , String phone
            , LocalDateTime lastUpdate
            , String lastUpdatedBy
            , int divisionID)
    {
        try{
            //mySql statement
            String sql = "update WJ07K54.customers " +
                    "set Customer_Name=?, Address=?, Postal_Code=?, Phone=?, Last_Update=?, Last_Updated_By=?, Division_ID=? " +
                    "where Customer_ID=?;";
            PreparedStatement pStmt = DBConnection.getConnection().prepareStatement(sql);
            pStmt.setString(1, customerName);
            pStmt.setString(2, address);
            pStmt.setString(3, postalCode);
            pStmt.setString(4, phone);
            pStmt.setTimestamp(5, Timestamp.valueOf(lastUpdate));
            pStmt.setString(6, lastUpdatedBy);
            pStmt.setInt(7, divisionID); // can't be null
            pStmt.setInt(8, customerID);
            pStmt.execute();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** This is a method that deletes a specific Customer's appointments that is associated with a specific Customer ID.
     *  This method deletes all of the fields of a specific Customer's appointment(s) from the database.
     *  @param ID The Customer ID*/
    public static void deleteCustomerAppts(int ID) {
        try {
            // mySQL statement
            String sql = "delete a.*\n" + // change to delete
                    "from WJ07K54.appointments a\n" +
                    "where a.Customer_ID in\n" +
                    "\t(select b.Customer_ID \n" +
                    "\t from WJ07K54.customers b\n" +
                    "     where Customer_ID=?);";
            PreparedStatement pStmt = DBConnection.getConnection().prepareStatement(sql);
            pStmt.setInt(1, ID);
            pStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /** This is a method that deletes a specific Customer that is associated with a specific Customer ID.
     *  This method deletes all of the fields of a specific Customer from the database.
     *  @param ID The Customer ID*/
    public static void deleteCustomer(int ID) {
        try {
            // mySQL statement
            String sql = "delete \n" + // change to delete
                    "from WJ07K54.customers\n" +
                    "where Customer_ID=?;";
            PreparedStatement pStmt = DBConnection.getConnection().prepareStatement(sql);
            pStmt.setInt(1, ID);
            pStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** This is a method that returns a specific Country that is associated with a specific Customer ID.
     *  This method queries the database and returns a specific Country that is associated with a specific Customer ID.
     *  @param ID The Customer ID
     *  @return Returns the Country that is associated with the Customer ID.*/
    public static String getCountryName(int ID) {
        String countryName = null;
        try{
            // mySQL statement
            String nameSql = "select c.Country \n" +
                    "from WJ07K54.customers a\n" +
                    "join WJ07K54.first_level_divisions b on a.Division_ID=b.Division_ID\n" +
                    "join WJ07K54.countries c on b.COUNTRY_ID=c.Country_ID " +
                    "where a.Customer_ID=?;";
            PreparedStatement psName = DBConnection.getConnection().prepareStatement(nameSql);
            psName.setInt(1, ID);
            ResultSet nameResults = psName.executeQuery();

            while (nameResults.next()) {
                countryName = nameResults.getString("Country");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return countryName;
    }

}
