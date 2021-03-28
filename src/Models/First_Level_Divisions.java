package Models;

import DBAccess.DBFirst_Level_Divisions;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/** This class creates First_Level_Divisions and is responsible for holding First_Level_Divisions data for retrieval.*/
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


/** This is a constructor method that creates instances of the First_Level_Divisions model.
 *  This method creates instances of the First_Level_Divisions model.
 *  @param divisionID The Division_ID
 *  @param division The Division name
 *  @param createDate The date and time the first_level_divisions record was created
 *  @param createdBy The user who created the first_level_divisions record
 *  @param lastUpdate The date and time the first_level_divisions record was last updated
 *  @param lastUpdatedBy The user who last updated the first_level_divisions record
 *  @param countryID The Country_ID*/
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

    /** This method holds the Division.
     *  This method is a getter method that holds the Division's name value and can pass that value when called.
     *  @return Returns the Division*/
    public String getDivisions() {
        return division;
    }


    /** This is a method that gets the names of the Divisions names and holds them in an ObservableList of Strings.
     *  This method uses a Lambda expression to make adding the Division for every first_level_divisions record onto the ObservableList more efficient and less prone to typed errors.
     *  @return Returns an ObservableList of Strings containing the names of each Division*/
    public static ObservableList<String> getDivisionNames() {
        divisionNames.clear();
        DBFirst_Level_Divisions.getAllDivisions().forEach((d) -> {
            divisionNames.add(d.getDivisions());
        });
        return divisionNames;
    }

}
