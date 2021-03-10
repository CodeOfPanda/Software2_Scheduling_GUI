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