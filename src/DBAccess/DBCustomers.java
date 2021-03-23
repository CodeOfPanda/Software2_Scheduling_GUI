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

public class DBCustomers {

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
                LocalDateTime createDate = rs.getObject("Create_Date", LocalDateTime.class);
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
                LocalDateTime createDate = rs.getObject("Create_Date", LocalDateTime.class);
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

    // adding a customer
    public static void createCustomer(String customerName
            , String address
            , String postalCode
            , String phone
            , LocalDateTime createDate
//            , String createdBy
            , LocalDateTime lastUpdate
//            , String lastUpdatedBy
            , int divisionID
            )
    {
        try {
            // mySQL statement
            String sql = "insert into WJ07K54.customers values(null, ?, ?, ?, ?, ?, null, ?, null, ?);";
            PreparedStatement pStmt = DBConnection.getConnection().prepareStatement(sql);

            pStmt.setString(1, customerName);
            pStmt.setString(2, address);
            pStmt.setString(3, postalCode);
            pStmt.setString(4, phone);
            pStmt.setObject(5, createDate);
//            pStmt.setString(6, createdBy);
            pStmt.setObject(6, lastUpdate);
//            pStmt.setString(8, lastUpdatedBy);
            pStmt.setInt(7, divisionID); // can't be null

            pStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // modifying a customer
    public static void modifyCustomer(int customerID
            , String customerName
            , String address
            , String postalCode
            , String phone
//            , String createdBy
            , LocalDateTime lastUpdate
//            , String lastUpdatedBy
            , int divisionID)
    {
        try{
            //mySql statement
            String sql = "update WJ07K54.customers " +
                    "set Customer_Name=?, Address=?, Postal_Code=?, Phone=?, Last_Update=?, Division_ID=? " +
                    "where Customer_ID=?;";
            PreparedStatement pStmt = DBConnection.getConnection().prepareStatement(sql);
            pStmt.setString(1, customerName);
            pStmt.setString(2, address);
            pStmt.setString(3, postalCode);
            pStmt.setString(4, phone);
            //pStmt.setString(, createdBy);
            pStmt.setObject(5, lastUpdate);
            //pStmt.setString(, lastUpdatedBy);
            pStmt.setInt(6, divisionID); // can't be null
            pStmt.setInt(7, customerID);
            pStmt.execute();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

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



//Customer_ID int(10) AI PK
//Customer_Name varchar(50)
//Address varchar(100)
//Postal_Code varchar(50)
//Phone varchar(50)
//Create_Date datetime
//Created_By varchar(50)
//Last_Update timestamp
//Last_Updated_By varchar(50)
//Division_ID int(10)