import java.time.*;

public class ClockRecordModel {

    private String employee;
    private final LocalTime clockIn;
    private final LocalTime clockOut;
    
    public ClockRecordModel (String employee, LocalTime clockIn, LocalTime clockOut) {
        this.employee = employee;
        this.clockIn = clockIn;
        this.clockOut = clockOut;
    }

    public String getEmployee() {
        return employee;
    }

    public LocalTime getClockIn() {
        return clockIn;
    }

    public LocalTime getClockOut() {
        return clockOut;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

}
