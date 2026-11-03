# Clockr

A desktop time-clock app built with Java Swing. Employees clock in by name, and Clockr records timestamped clock-in and clock-out entries in a live table where they can be edited or removed.

<img width="835" height="370" alt="Clockr screenshot" src="https://github.com/user-attachments/assets/0c2995e9-7208-49f6-8bbf-c7edb5c87d9e" />

## Features

- **Clock in:** type an employee name and click **Clock In** to create a record stamped with the current date and time
- **Clock out:** select a record and click **Clock Out** to stamp the time it ended
- **Update:** select a record, type a new name, and click **Update** to fix a misspelled or wrong name
- **Delete / Clear:** delete one record, or clear the whole table
- **Input checks:** blank names are ignored, and actions that need a selected row do nothing until a row is selected
- **Dark-themed UI:** custom-styled `JTable`, color-coded buttons, and centered cells

## Tech Stack

- Java 17+ (uses switch expressions)
- Java Swing (`JFrame`, `JTable`, `AbstractTableModel`)
- Java Time API (`LocalDateTime`, `DateTimeFormatter`)
- Java Collections (`ArrayList`)

## Project Structure

| File | Responsibility |
|---|---|
| `Main.java` | Builds the window, styles the components, and wires each button to its action |
| `ClockRecordModel.java` | Data model for one record: id, employee, clock-in, clock-out |
| `ClockRecordManager.java` | Business logic: creates, reads, updates, and deletes records, and assigns ids |
| `ClockRecordJTable.java` | `AbstractTableModel` adapter that shows the manager's records in the `JTable` |

The UI never changes the data directly. Every action goes through `ClockRecordManager`, and the table model refreshes the view with `fireTableDataChanged()`.

## Getting Started

**Requirements:** JDK 17 or newer

```bash
git clone https://github.com/Jsn-Le/Clockr.git
cd Clockr
javac *.java
java Main
```

## How to Use

1. Type an employee's name in the text field and click **Clock In**.
2. When they're done, select their row and click **Clock Out**.
3. To fix a name, select the row, type the correct name, and click **Update**.
4. Use **Delete** to remove the selected row, or **Clear** to remove every record.

## Limitations & Next Steps

- Records live in memory and are lost when the app closes. **Next:** save them to a file or a database (SQLite or PostgreSQL).
- Clicking **Clock Out** again on a record overwrites its earlier clock-out time. **Next:** block clocking out twice, and stop an employee from clocking in twice.
- **Next:** show each shift's total hours and each employee's weekly total.
- **Next:** add unit tests for `ClockRecordManager` with JUnit.
