import java.time.*;

public class ClockRecordModel {

    private final int id;
    private String employee;
    private final LocalDateTime clockIn;
    private LocalDateTime clockOut;
    
    public ClockRecordModel (int id, String employee, LocalDateTime clockIn, LocalDateTime clockOut) {
        this.id = id;
        this.employee = employee;
        this.clockIn = clockIn;
        this.clockOut = clockOut;
    }

    public int getId() {
        return id;
    }

    public String getEmployee() {
        return employee;
    }

    public LocalDateTime getClockIn() {
        return clockIn;
    }

    public LocalDateTime getClockOut() {
        return clockOut;
    }

    public void setEmployee (String employee) {
        this.employee = employee;
    }

    public void setClockOut (LocalDateTime clockOut) {
        this.clockOut = clockOut;
    }

}
