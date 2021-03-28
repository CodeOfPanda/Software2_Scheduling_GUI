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

/** This class queries a database in MySQL Workbench to access the data in the WJ07K54.first_level_divisions table.*/
public class DBFirst_Level_Divisions {
    /** This is an ObservableList method that returns the data in each field in the WJ07K54.first_level_divisions table. S
     *  This method returns the first_level_divisions data and with it creates an instance of type First_Level_Divisions from the First_Level_Divisions Model.
     *  @return Returns an observableArrayList of Type First_Level_Divisions.*/
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
                LocalDateTime createDate = rs.getTimestamp("Create_Date").toLocalDateTime();
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
    /** This is an ObservableList method that returns the data for a specific Division in the WJ07K54.first_level_divisions table.
     *  This method queries the database to access the Division data that is associated with a specific Country.
     *  @param countryName The Country's Name
     *  @return Returns an ObservableList of Strings with each Division that is associated with the Country.*/
    public static ObservableList<String> getSpecificDivisions(String countryName) {
        ObservableList<String> divisionNames = FXCollections.observableArrayList();

        try {
            // mySQL statement
            String sql = "select a.Division\n" +
                    "from WJ07K54.first_level_divisions a \n" +
                    "join WJ07K54.countries b on a.Country_ID=b.COUNTRY_ID\n" +
                    "where b.Country=?;";
            PreparedStatement pStmt = DBConnection.getConnection().prepareStatement(sql);
            pStmt.setString(1, countryName);
            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {
                String divisions = rs.getString("Division");
                divisionNames.add(divisions);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return divisionNames;
    }
    /** This is a method that returns the Division_ID that is associated with a specific Division.
     *  This method queries the database to access the Division_ID that is associated with a specific Division.
     *  @param name The Division's Name
     *  @return Returns the Division_ID*/
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
    /** This is a method that returns a Division that is associated with a specific Division_ID.
     *  This method queries the database to access the Division that is associated with a specific Division_ID.
     *  @param ID - The Division_ID
     *  @return Returns the Division*/
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
