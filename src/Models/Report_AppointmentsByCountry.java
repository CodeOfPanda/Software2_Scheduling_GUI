package Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Report_AppointmentsByCountry {
    private String country;
    private String type;
    private int count;

    public Report_AppointmentsByCountry(String country, String type, int count) {
        this.country = country;
        this.type = type;
        this.count = count;
    }

    public String getReportApptCountry() {return country;}
    public String getReportApptType() {return type;}
    public int getReportApptCount() {return count;}
}
