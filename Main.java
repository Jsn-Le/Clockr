import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.time.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class Main {

    private static void styleButton(JButton button, Color color) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 14, 8, 14));
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("Clockr");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000,1000);

            Color bgColor = new Color(24, 26, 32);
            Color panelColor = new Color(36, 39, 48);
            Color accentColor = new Color(76, 175, 255);
            Color textColor = Color.WHITE;

            frame.getContentPane().setBackground(bgColor);

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
            recordsPanel.setBackground(bgColor);
            controlPanel.setBackground(panelColor);
            controlPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

            controlPanel.add(clearButton);
            controlPanel.add(updateButton);

            controlPanel.add(textfield);
            textfield.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            textfield.setBackground(new Color(50, 54, 65));
            textfield.setForeground(textColor);
            textfield.setCaretColor(textColor);
            textfield.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));

            controlPanel.add(clockInButton);
            controlPanel.add(clockOutButton);
            controlPanel.add(deleteButton);

            styleButton(clockInButton, new Color(46, 204, 113));
            styleButton(clockOutButton, new Color(76, 175, 255));
            styleButton(updateButton, new Color(155, 89, 182));
            styleButton(deleteButton, new Color(255, 92, 92));
            styleButton(clearButton, new Color(120, 120, 120));

            ClockRecordManager clockRecordManager = new ClockRecordManager();
            ClockRecordJTable clockRecordJTable = new ClockRecordJTable(clockRecordManager);

            JTable jTable = new JTable(clockRecordJTable);
            jTable.setFont(new Font("Segoe UI", Font.PLAIN, 15));
            jTable.setRowHeight(32);
            jTable.setBackground(new Color(30, 33, 40));
            jTable.setForeground(textColor);
            jTable.setGridColor(new Color(60, 64, 75));
            jTable.setSelectionBackground(accentColor);
            jTable.setSelectionForeground(Color.BLACK);

            jTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
            jTable.getTableHeader().setBackground(new Color(45, 49, 60));
            jTable.getTableHeader().setForeground(textColor);

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

            for (int i = 0; i < jTable.getColumnCount(); i++) {
                jTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

            JScrollPane scrollPane = new JScrollPane(jTable);
            scrollPane.getViewport().setBackground(bgColor);
            scrollPane.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            
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
