import java.awt.BorderLayout;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("Clockr");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500,1000);

            JPanel recordsPanel = new JPanel(new BorderLayout());
            JPanel controlPanel = new JPanel();
            JTextField textfield = new JTextField(25);
            JButton clockInButton = new JButton("Clock In");
            JButton clockOutButton = new JButton("Clock Out");

            frame.add(recordsPanel, BorderLayout.CENTER);
            frame.add(controlPanel, BorderLayout.SOUTH);
            controlPanel.add(textfield);
            controlPanel.add(clockInButton);
            controlPanel.add(clockOutButton);

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

            frame.setVisible(true);

        });
    }

}
