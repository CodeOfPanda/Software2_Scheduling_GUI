package Models;

import DBAccess.DBFirst_Level_Divisions;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class First_Level_Divisions {
    private static ObservableList<String> divisionNames = FXCollections.observableArrayList();

    private int divisionID;
    private String division;
    private StringProperty divisionNs;
    private LocalDateTime createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;
    private int countryID;

    public First_Level_Divisions (int divisionID, String division, LocalDateTime createDate, String createdBy
                                , Timestamp lastUpdate, String lastUpdatedBy, int countryID)
    {
        divisionNs = new SimpleStringProperty(division);
        this.divisionID = divisionID;
        this.division = division;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.countryID = countryID;
    }

    // division id
    public int getDivisionID() {
        return divisionID;
    }

    // division name
    public StringProperty getDivisionNs() {
        return divisionNs;
    }
    public String getDivisions() {
        return division;
    }


    // observable list of the division names, used to populate data in combo-boxes
    public static ObservableList<String> getDivisionNames() {
        divisionNames.clear();
        DBFirst_Level_Divisions.getAllDivisions().forEach((d) -> {
            divisionNames.add(d.getDivisions());
        });
        return divisionNames;
    }

}
