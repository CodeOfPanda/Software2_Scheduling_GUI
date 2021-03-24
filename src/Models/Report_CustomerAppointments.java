package Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Report_CustomerAppointments {
    private StringProperty month;
    private IntegerProperty count;
    private StringProperty type;

    public Report_CustomerAppointments (String month, int count, String type) {
        this.month = new SimpleStringProperty(month);
        this.count = new SimpleIntegerProperty(count);
        this.type = new SimpleStringProperty(type);
    }

    public StringProperty getReportCustMonth() {return month;}
    public IntegerProperty getReportCustCount() {return count;}
    public StringProperty getReportType() {return type;}

}
