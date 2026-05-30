import java.awt.BorderLayout;
import java.time.*;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("Clockr");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(750,1000);

            JPanel recordsPanel = new JPanel(new BorderLayout());
            JPanel controlPanel = new JPanel();
            JButton clearButton = new JButton("Clear");
            JButton updateButton = new JButton("Update");
            JTextField textfield = new JTextField(25);
            JButton clockInButton = new JButton("Clock In");
            JButton clockOutButton = new JButton("Clock Out");
            JButton deleteButton = new JButton("Delete");

            frame.add(recordsPanel, BorderLayout.CENTER);
            frame.add(controlPanel, BorderLayout.SOUTH);
            controlPanel.add(clearButton);
            controlPanel.add(updateButton);
            controlPanel.add(textfield);
            controlPanel.add(clockInButton);
            controlPanel.add(clockOutButton);
            controlPanel.add(deleteButton);

            ClockRecordManager clockRecordManager = new ClockRecordManager();
            ClockRecordJTable clockRecordJTable = new ClockRecordJTable(clockRecordManager);
            JTable jTable = new JTable(clockRecordJTable);
            JScrollPane scrollPane = new JScrollPane(jTable);
            
            recordsPanel.add(scrollPane);

            clockInButton.addActionListener(e -> {
                String employee = textfield.getText();
                employee = employee.trim();

                if (employee.isEmpty()) {
                    return;
                }

                clockRecordManager.addRecord(employee);
                clockRecordJTable.fireTableDataChanged();

                textfield.setText("");
            });

            clockOutButton.addActionListener(e -> {
                int selectedRow = jTable.getSelectedRow();
                if (selectedRow == -1) {
                    return;
                }
                ClockRecordModel record = clockRecordJTable.getRecordAt(selectedRow);

                int id = record.getId();
                LocalDateTime clockOut = LocalDateTime.now();

                clockRecordManager.setClockOutById(id, clockOut);
                clockRecordJTable.fireTableDataChanged();
            });

            deleteButton.addActionListener(e -> {
                int selectedRow = jTable.getSelectedRow();
                if (selectedRow == -1) {
                    return;
                }
                ClockRecordModel record = clockRecordJTable.getRecordAt(selectedRow);

                int id = record.getId();

                clockRecordManager.deleteRecordById(id);
                clockRecordJTable.fireTableDataChanged();
            });

            clearButton.addActionListener(e -> {
                clockRecordManager.deleteAllRecords();
                clockRecordJTable.fireTableDataChanged();
            });

            updateButton.addActionListener(e -> {
                int selectedRow = jTable.getSelectedRow();
                if (selectedRow == -1) {
                    return;
                }
                ClockRecordModel record = clockRecordJTable.getRecordAt(selectedRow);

                int id = record.getId();
                String employee = textfield.getText();
                employee = employee.trim();
                if (employee.isEmpty()) {
                    return;
                }

                clockRecordManager.updateEmployeeNameById(id, employee);
                clockRecordJTable.fireTableDataChanged();

                textfield.setText("");
            });

            frame.setVisible(true);

        });
    }

}
