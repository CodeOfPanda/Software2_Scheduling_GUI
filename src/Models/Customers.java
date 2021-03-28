package Models;

import DBAccess.DBFirst_Level_Divisions;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/** This class creates a Customer and is responsible for holding customer data for retrieval.*/
public class Customers {

    private static ObservableList<String> divisionNames = FXCollections.observableArrayList();

    private final int customerID;
    private final String custName;
    private final String custAddress;
    private final String custPostal;
    private final String custPhone;
    private final int divisionID;

/** This is a constructor method that creates instances of the Customers model.
 *  This method creates instances of the Customers model.
 *  @param customerID The Customer ID
 *  @param name The Customer's Name
 *  @param address The Customer's address
 *  @param postalCode The Customer's postal code
 *  @param phone The Customer's phone number
 *  @param createDate The date and time that the customer record was created
 *  @param createdBy The user who created the new customer record
 *  @param lastUpdate The date and time that the customer record was last updated
 *  @param lastUpdatedBy The user who updated the customer record last
 *  @param divisionID The Division_ID for the Customer's address*/
    public Customers(int customerID, String name, String address, String postalCode, String phone, LocalDateTime createDate
                    ,String createdBy, Timestamp lastUpdate, String lastUpdatedBy, int divisionID)
    {
        this.customerID = customerID;
        this.custName = name;
        this.custAddress = address;
        this.custPostal = postalCode;
        this.custPhone = phone;
        this.divisionID = divisionID;
    }

    /** This method holds the Customer_ID.
     *  This method is a getter method that holds the Customer_ID value and can pass that value when called.
     *  @return Returns Customer_ID*/
    public int getCustID() {return customerID;}

    /** This method holds the Customer's Name.
     *  This method is a getter method that holds the Customer's Name value and can pass that value when called.
     *  @return Returns Customer's Name*/
    public String getName(){return custName;}

    /** This method holds the Customer's address.
     *  This method is a getter method that holds the Customer's address value and can pass that value when called.
     *  @return Returns Customer's address*/
    public String getAddress() {return custAddress;}

    /** This method holds the Customer's postal code.
     *  This method is a getter method that holds the Customer's postal code value and can pass that value when called.
     *  @return Returns Customer's postal code*/
    public String getPostal() {return custPostal;}

    /** This method holds the Customer's phone number.
     *  This method is a getter method that holds the Customer's phone number value and can pass that value when called.
     *  @return Returns Customer's phone number*/
    public String getPhone(){return custPhone;}

    /** This method holds the Division_ID for the Customer's address.
     *  This method is a getter method that holds the Division_ID for the Customer's address value and can pass that value when called.
     *  @return Returns Division_ID for the Customer's address*/
    public int getDivisionID() {
        return divisionID;
    }

    /** This method holds the Division for the Customer's address.
     *  This method is a getter method that holds the Division for the Customer's address value and can pass that value when called.
     *  @return Returns Division for the Customer's address*/
    public String getDivName() {
        return DBFirst_Level_Divisions.getDivisionName(getDivisionID());
    }

}
