
import javax.swing.table.AbstractTableModel;

public class ClockRecordJTable extends AbstractTableModel {

    private final ClockRecordManager clockRecordManager;

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
            case 0 -> record.getId();
            case 1 -> record.getEmployee();
            case 2 -> record.getClockIn();
            case 3 -> record.getClockOut();
            default -> null;
        };
    }
    
}
