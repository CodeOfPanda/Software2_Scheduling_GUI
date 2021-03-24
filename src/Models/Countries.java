package Models;

import DBAccess.DBCountries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Countries {
    private static ObservableList<String> countryNames = FXCollections.observableArrayList();

    private int countryID;
    private String country;
    private LocalDateTime createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;

    public Countries(int countryID, String country, LocalDateTime createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy) {
        this.countryID = countryID;
        this.country = country;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public int getCountryID() {
        return countryID;
    }

    public String getCountryNs() {
        return country;
    }

    public static ObservableList<String> getCountryNames() {
        countryNames.clear();
        DBCountries.getAllCountries().forEach((country) -> {
            countryNames.add(country.getCountryNs());
        });
        return countryNames;
    }

}
