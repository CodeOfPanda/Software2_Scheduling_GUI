package Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Report_CustomerAppointments {
    private String month;
    private int count;
    private String type;

    public Report_CustomerAppointments (String month, int count, String type) {
        this.month = month;
        this.count = count;
        this.type = type;
    }

    public String getReportCustMonth() {return month;}
    public int getReportCustCount() {return count;}
    public String getReportType() {return type;}

}
