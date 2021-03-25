package Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Report_AppointmentsByCountry {
    private StringProperty country;
    private StringProperty type;
    private IntegerProperty count;

    public Report_AppointmentsByCountry(String country, String type, int count) {
        this.country = new SimpleStringProperty(country);
        this.type = new SimpleStringProperty(type);
        this.count = new SimpleIntegerProperty(count);
    }

    public StringProperty getReportApptCountry() {return country;}
    public StringProperty getReportApptType() {return type;}
    public IntegerProperty getReportApptCount() {return count;}
}
