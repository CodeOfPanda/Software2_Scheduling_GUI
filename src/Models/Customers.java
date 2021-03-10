package Models;

import javafx.beans.property.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/*
* I need to figure out how I want the state/province information displayed
* Do I include the createDate and other information in the gui?
*
*/

public class Customers {

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

    public Customers(int customerID, String name, String address, String postalCode, String phone, LocalDateTime createDate
                    ,String createdBy, Timestamp lastUpdate, String lastUpdatedBy, int divisionID)
    {
        this.customerID = new SimpleIntegerProperty(customerID);
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
        return this.customerID;
    }

    // customer name
    public StringProperty getCustName() {
        return this.custName;
    }

    // customer address
    public StringProperty getCustAddress() {
        return this.custAddress;
    }

    // customer postal code
    public StringProperty getCustPostal() {
        return this.custPostal;
    }

    // customer phone
    public StringProperty getCustPhone() {
        return this.custPhone;
    }

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
    public IntegerProperty getDivisionID() {
        return this.divisionID;
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