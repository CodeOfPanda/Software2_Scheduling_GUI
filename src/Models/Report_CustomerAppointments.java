package Models;

/** This class creates Report_CustomerAppointments and is responsible for holding Report_CustomerAppointments data for retrieval.*/
public class Report_CustomerAppointments {
    private String month;
    private int count;
    private String type;

    /** This is a constructor method that creates instances of the Report_CustomerAppointments model.
     *  This method creates instances of the Report_CustomerAppointments model.
     *  @param month The current month
     *  @param type Appointment Type
     *  @param count number of appointments with specific type*/
    public Report_CustomerAppointments (String month, int count, String type) {
        this.month = month;
        this.count = count;
        this.type = type;
    }

    /** This method holds the current month.
     *  This method is a getter method that holds the current month value and can pass that value when called.
     *  @return Returns the current month*/
    public String getReportCustMonth() {return month;}
    /** This method holds the number of appointments with specific type.
     *  This method is a getter method that holds the number of appointments with a specific types value and can pass that value when called.
     *  @return Returns the number of appointments with specific type*/
    public int getReportCustCount() {return count;}
    /** This method holds the appointment Type.
     *  This method is a getter method that holds the appointment Type value and can pass that value when called.
     *  @return Returns the appointment Type*/
    public String getReportType() {return type;}

}
