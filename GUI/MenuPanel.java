import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    private String panelTitle;
    public MenuPanel() {
        // GridBagLayout for MenuPanel
        this.setLayout(new GridBagLayout());

        panelTitle = "Attendance Management System";

        // creating buttons
        JButton addStudentButton = new JButton("Add Student");
        JButton addLessonButton = new JButton("Add Lesson");
        JButton checkPresenceButton = new JButton("Check Presence");

        // make buttons larger
        Dimension buttonSize = new Dimension(300, 50);
        addStudentButton.setPreferredSize(buttonSize);
        addLessonButton.setPreferredSize(buttonSize);
        checkPresenceButton.setPreferredSize(buttonSize);

        // GridBagConstraints for button positioning
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // add some padding

        // adding buttons to the panel with GridBagConstraints
        add(addStudentButton, gbc);

        gbc.gridy++;
        add(addLessonButton, gbc);

        gbc.gridy++;
        add(checkPresenceButton, gbc);


        // Create a JLabel with custom styling to resemble an underlined reference
        JLabel referenceLabel = new JLabel("<html><u>Leave a Note</u></html>");
        referenceLabel.setForeground(Color.BLUE);
        referenceLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        gbc.gridy++;
        add(referenceLabel, gbc);

    }

//
//    public void setPanelTitle(String panelTitle) {
//        this.panelTitle = panelTitle;
//    }

    public String getPanelTitle() {
        return panelTitle;
    }
}
