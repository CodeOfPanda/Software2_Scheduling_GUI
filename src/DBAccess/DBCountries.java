package DBAccess;

import Models.Countries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

// dbName: WJ07K54

public class DBCountries {

    public static ObservableList<Countries> getAllCountries() {
        ObservableList<Countries> countriesList = FXCollections.observableArrayList();

        try{
            //mySQL statement
            String sql = "select * from WJ07K54.countries;";
            PreparedStatement pStmt = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {
                int countryID = rs.getInt("Country_ID");
                String country = rs.getString("Country");
                LocalDateTime createDate = rs.getObject("Create_Date", LocalDateTime.class);
                String createdBy = rs.getString("Created_By");
                Timestamp lastUpdate = rs.getTimestamp("Last_Update");
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                Countries list = new Countries(countryID, country, createDate, createdBy, lastUpdate, lastUpdatedBy);
                countriesList.add(list);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return countriesList;
    }

    public static String getCountryNames(int ID) {
        String countryNames = null;
        try{
            //mySql statement
            String sql = "select Country from WJ07K54.countries where Country_ID=?;";
            PreparedStatement pStmt = DBConnection.getConnection().prepareStatement(sql);
            pStmt.setInt(1, ID);
            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {
                countryNames = rs.getString("Country");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return countryNames;
    }

}
