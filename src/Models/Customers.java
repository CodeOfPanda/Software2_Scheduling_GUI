package Models;

import DBAccess.DBFirst_Level_Divisions;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Customers {

    private static ObservableList<String> divisionNames = FXCollections.observableArrayList();

    private final int customerID;
    private final String custName;
    private final String custAddress;
    private final String custPostal;
    private final String custPhone;
    private final int divisionID;

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

    // customerID
    public int getCustID() {return customerID;}

    // customer name
    public String getName(){return custName;}

    // customer address
    public String getAddress() {return custAddress;}

    // customer postal code
    public String getPostal() {return custPostal;}

    // customer phone
    public String getPhone(){return custPhone;}

    // divisionID
    public int getDivisionID() {
        return divisionID;
    }

    // getting divisions names
    public String getDivName() {
        return DBFirst_Level_Divisions.getDivisionName(getDivisionID());
    }

}
