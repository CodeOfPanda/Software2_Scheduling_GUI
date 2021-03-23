package DBAccess;

import Models.First_Level_Divisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class DBFirst_Level_Divisions {

    public static ObservableList<First_Level_Divisions> getAllDivisions() {
        ObservableList<First_Level_Divisions> divisionsList = FXCollections.observableArrayList();

        try{
            //mySQL statement
            String sql = "select * from WJ07K54.first_level_divisions;";
            PreparedStatement pStmt = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {
                int divisionID = rs.getInt("Division_ID");
                String division = rs.getString("Division");
                LocalDateTime createDate = rs.getObject("Create_Date", LocalDateTime.class);
                String createdBy = rs.getString("Created_By");
                Timestamp lastUpdate = rs.getTimestamp("Last_Update");
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int countryID = rs.getInt("COUNTRY_ID");
                First_Level_Divisions list = new First_Level_Divisions(divisionID, division, createDate, createdBy
                        , lastUpdate, lastUpdatedBy, countryID);
                divisionsList.add(list);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return divisionsList;
    }

    public static int getDivisionID(String name) {
        int divisionID = 0;
        try{
            //mySQL statement
            String idSql = "select Division_ID from WJ07K54.first_level_divisions where Division=?;";
            PreparedStatement pStmt = DBConnection.getConnection().prepareStatement(idSql);
            pStmt.setString(1, name);

            ResultSet rs = pStmt.executeQuery();
            while(rs.next()) {
                divisionID = rs.getInt("Division_ID");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return divisionID;
    }

    public static String getDivisionName(int ID) {
        String divisionName = null;
        try{
            //mySQL statement
            String dnSql = "select Division from WJ07K54.first_level_divisions where Division_ID=?;";
            PreparedStatement pStmt = DBConnection.getConnection().prepareStatement(dnSql);
            pStmt.setInt(1, ID);
            ResultSet rs = pStmt.executeQuery();
            while (rs.next()) {
                divisionName = rs.getString("Division");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return divisionName;
    }

}
