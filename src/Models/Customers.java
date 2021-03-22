package Models;

import DBAccess.DBCountries;
import DBAccess.DBFirst_Level_Divisions;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/*
* I need to figure out how I want the state/province information displayed
* Do I include the createDate and other information in the gui?
*
*/

public class Customers {

    private static ObservableList<String> divisionNames = FXCollections.observableArrayList();

    private IntegerProperty customerID;
    private StringProperty custName;
    private StringProperty custAddress;
    private StringProperty custPostal;
    private StringProperty custPhone;
    private ObjectProperty<LocalDateTime> createDate;
    private StringProperty createdBy;
    private Timestamp lastUpdate;
    private StringProperty lastUpdatedBy;
    private IntegerProperty divisionID;
    private static int custID;

    public Customers(int customerID, String name, String address, String postalCode, String phone, LocalDateTime createDate
                    ,String createdBy, Timestamp lastUpdate, String lastUpdatedBy, int divisionID)
    {
        this.customerID = new SimpleIntegerProperty(customerID);
        custID = customerID;
        this.custName = new SimpleStringProperty(name);
        this.custAddress = new SimpleStringProperty(address);
        this.custPostal = new SimpleStringProperty(postalCode);
        this.custPhone = new SimpleStringProperty(phone);
        this.createDate = new SimpleObjectProperty<>(createDate);
        this.createdBy = new SimpleStringProperty(createdBy);
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = new SimpleStringProperty(lastUpdatedBy);
        this.divisionID = new SimpleIntegerProperty(divisionID);
    }

    // customerID
    public IntegerProperty getCustomerID() {
        return customerID;
    }
    public int getCustID() {return custID;}
    public String getCstmrID() {return String.valueOf(custID);}

    // customer name
    public StringProperty getCustName() {
        return this.custName;
    }
    public String getName(){return custName.get();}

    // customer address
    public StringProperty getCustAddress() {
        return this.custAddress;
    }
    public String getAddress() {return custAddress.get();}

    // customer postal code
    public StringProperty getCustPostal() {
        return this.custPostal;
    }
    public String getPostal() {return custPostal.get();}

    // customer phone
    public StringProperty getCustPhone() {
        return this.custPhone;
    }
    public String getPhone(){return custPhone.get();}

    // create_date datetime
    public ObjectProperty<LocalDateTime> getCreateDate() {
        return this.createDate;
    }

    // created by
    public StringProperty getCreatedBy() {
        return this.createdBy;
    }

    // last update timestamp
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    // last updated by
    public StringProperty getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    // divisionID
    public int getDivisionID() {
        return divisionID.get();
    }

    public StringProperty getDivNameProperty() {
        String name = DBFirst_Level_Divisions.getDivisionName(getDivisionID());
        StringProperty divName = new SimpleStringProperty(name);
        return divName;
    }
    public String getDivName() {
        return DBFirst_Level_Divisions.getDivisionName(getDivisionID());
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