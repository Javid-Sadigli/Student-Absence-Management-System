import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CheckPresence extends JPanel {
    private String panelTitle;

    public CheckPresence() {
        this.panelTitle = "Attendance";

        //define the column names and initial data
        String[] columnNames = {"Lesson Name", "Date", "Time", "Location"};
        Object[][] data = {
                {"Lesson 1", "2024-04-07", "09:00 AM", "Room 101"},
                {"Lesson 2", "2024-04-07", "11:00 AM", "Room 102"},
                {"Lesson 3", "2024-04-07", "01:00 PM", "Room 103"},
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        JTable table = new JTable(model);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        table.setRowHeight(30); // Adjust as needed
        table.getColumnModel().getColumn(0).setPreferredWidth(150); // Adjust as needed

        JScrollPane scrollPane = new JScrollPane(table);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }

    public String getPanelTitle() {
        return panelTitle;
    }
}
