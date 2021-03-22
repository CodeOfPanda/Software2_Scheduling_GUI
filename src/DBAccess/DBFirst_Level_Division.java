package DBAccess;

import Models.Countries;
import Models.First_Level_Division;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class DBFirst_Level_Division {

    public static ObservableList<First_Level_Division> getAllCountries() {
        ObservableList<First_Level_Division> divisionsList = FXCollections.observableArrayList();

        try{
            //mySQL statement
            String sql = "select * from WJ07K54.countries;";
            PreparedStatement pStmt = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {
                int divisionID = rs.getInt("Division_ID");
                String division = rs.getString("Division");
                LocalDateTime createDate = rs.getObject("Create_Date", LocalDateTime.class);
                String createdBy = rs.getString("Created_By");
                Timestamp lastUpdate = rs.getTimestamp("Last_Update");
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int countryID = rs.getInt("Country_ID");
                First_Level_Division list = new First_Level_Division(divisionID, division, createDate, createdBy
                        , lastUpdate, lastUpdatedBy, countryID);
                divisionsList.add(list);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return divisionsList;
    }

}
