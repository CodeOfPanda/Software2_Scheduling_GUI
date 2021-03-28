package Models;

import DBAccess.DBCountries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/** This class creates Countries and is responsible for holding Country data for retrieval.*/
public class Countries {
    private static ObservableList<String> countryNames = FXCollections.observableArrayList();

    private int countryID;
    private String country;
    private LocalDateTime createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;

/** This is a constructor method that creates instances of the Countries model.
 *  This method creates instances of the Countries model.
 *  @param countryID The Country's ID
 *  @param country The Country
 *  @param createDate The date and time the country record was created
 *  @param createdBy The users who created the country record
 *  @param lastUpdate The date and time the country record was last updated
 *  @param lastUpdatedBy The user who last updated the country record*/
    public Countries(int countryID, String country, LocalDateTime createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy) {
        this.countryID = countryID;
        this.country = country;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /** This method holds the Country.
     *  This method is a getter method that holds the Country name value and can pass that value when called.
     *  @return Returns Country*/
    public String getCountryNs() {
        return country;
    }

    /** This is a method that gets the names of the Countries and holds them in an ObservableList of Strings.
     *  This method uses a Lambda expression to make adding the Countries name for every Country record onto the ObservableList more efficient and less prone to typed errors.
     *  @return Returns an ObservableList of Strings containing the names of each Country*/
    public static ObservableList<String> getCountryNames() {
        countryNames.clear();
        DBCountries.getAllCountries().forEach((country) -> {
            countryNames.add(country.getCountryNs());
        });
        return countryNames;
    }

}
