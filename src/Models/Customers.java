package Models;

import DBAccess.DBFirst_Level_Divisions;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Customers {

    private static ObservableList<String> divisionNames = FXCollections.observableArrayList();

    private final IntegerProperty customerID;
    private final String custName;
    private final StringProperty custAddress;
    private final StringProperty custPostal;
    private final StringProperty custPhone;
    private final ObjectProperty<LocalDateTime> createDate;
    private final StringProperty createdBy;
    private final Timestamp lastUpdate;
    private final StringProperty lastUpdatedBy;
    private final IntegerProperty divisionID;

    public Customers(int customerID, String name, String address, String postalCode, String phone, LocalDateTime createDate
                    ,String createdBy, Timestamp lastUpdate, String lastUpdatedBy, int divisionID)
    {
        this.customerID = new SimpleIntegerProperty(customerID);
        this.custName = name;
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
    public int getCustID() {return customerID.get();}

    // customer name
    public String getName(){return custName;}

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