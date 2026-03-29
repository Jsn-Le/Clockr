import java.time.*;
import java.util.*;

public class ClockRecordManager {

    private final List<ClockRecordModel> records = new ArrayList<>();
    private int nextId = 1;

    public ClockRecordModel addRecord (String employee) {
        LocalDateTime currentTime = LocalDateTime.now();
        ClockRecordModel record = new ClockRecordModel(nextId, employee, currentTime, null);
        records.add(record);
        nextId++;
        return record;
    }

    public List<ClockRecordModel> getAllRecords() {
        return new ArrayList<>(records);
    }

    public ClockRecordModel getRecordById (int id) {
        for (ClockRecordModel record : records) {
            if (record.getId() == id) {
                return record;
            }
        }
        return null;
    }

    public boolean deleteRecordById (int id) {
        ClockRecordModel record = getRecordById(id);
        if (record == null) {
            return false;
        }
        records.remove(record);
        return true;
    }

    public ClockRecordModel setClockOutById (int id, LocalDateTime clockOut) {
        ClockRecordModel record = getRecordById(id);
        if (record == null) {
            return null;
        }
        record.setClockOut(clockOut);
        return record;
    }

    public ClockRecordModel updateEmployeeNameById (int id, String employee) {
        ClockRecordModel record = getRecordById(id);
        if (record == null) {
            return null;
        }
        record.setEmployee(employee);
        return record;
    }

}
