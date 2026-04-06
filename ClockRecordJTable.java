import java.time.format.DateTimeFormatter;
import javax.swing.table.AbstractTableModel;

public class ClockRecordJTable extends AbstractTableModel {

    private final ClockRecordManager clockRecordManager;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public ClockRecordJTable(ClockRecordManager clockRecordManager) {
        this.clockRecordManager = clockRecordManager;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public int getRowCount() {
        return clockRecordManager.getAllRecords().size();
    }

    @Override
    public String getColumnName(int column) {
        return switch(column) {
            case 0 -> "Employee";
            case 1 -> "Clocked In";
            case 2 -> "Clocked Out";
            default -> null;
        };
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ClockRecordModel record = clockRecordManager.getAllRecords().get(rowIndex);

        return switch (columnIndex) {
            case 0 -> record.getEmployee();
            case 1 -> record.getClockIn().format(formatter);
            case 2 -> record.getClockOut() != null
                        ? record.getClockOut().format(formatter)
                        : "";
            default -> null;
        };
    }
    
}
