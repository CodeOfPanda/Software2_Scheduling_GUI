package Models;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class First_Level_Division {
    private int divisionID;
    private String division;
    private LocalDateTime createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;
    private int countryID;

    public First_Level_Division(int divisionID, String division, LocalDateTime createDate, String createdBy
                                , Timestamp lastUpdate, String lastUpdatedBy, int countryID)
    {
        this.divisionID = divisionID;
        this.division = division;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.countryID = countryID;
    }

}
