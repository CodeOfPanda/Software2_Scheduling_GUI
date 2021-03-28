package Models;

/** This class creates Report_AppointmentsByCountry and is responsible for holding Report_AppointmentsByCountry data for retrieval.*/
public class Report_AppointmentsByCountry {
    private String country;
    private String type;
    private int count;

/** This is a constructor method that creates instances of the Report_AppointmentsByCountry model.
 *  This method creates instances of the Report_AppointmentsByCountry model.
 *  @param country Country Name
 *  @param type Appointment Type
 *  @param count number of appointments with specific type*/
    public Report_AppointmentsByCountry(String country, String type, int count) {
        this.country = country;
        this.type = type;
        this.count = count;
    }
    /** This method holds the Country.
     *  This method is a getter method that holds the Country's name value and can pass that value when called.
     *  @return Returns the Country*/
    public String getReportApptCountry() {return country;}
    /** This method holds the appointment Type.
     *  This method is a getter method that holds the appointment Type value and can pass that value when called.
     *  @return Returns the appointment Type*/
    public String getReportApptType() {return type;}
    /** This method holds the number of appointments with specific type.
     *  This method is a getter method that holds the number of appointments with a specific types value and can pass that value when called.
     *  @return Returns the number of appointments with specific type*/
    public int getReportApptCount() {return count;}
}
