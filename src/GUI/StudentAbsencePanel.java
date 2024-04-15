package GUI;

import GUI.MainFrame;
import GUI.MenuPanel;
import models.Absent;
import models.Lesson;
import models.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * Panel for displaying absences of a specific student.
 */
public class StudentAbsencePanel extends JPanel {
    private Student student;
    private Absent[] allAbsences;

    /**
     * Constructs a new StudentAbsencePanel for the specified student.
     *
     * @param student The student whose absences are to be displayed.
     */
    public StudentAbsencePanel(Student student) {
        this.student = student;

        // Set layout
        setLayout(new BorderLayout());

        // Create label to display student name
        JLabel nameLabel = new JLabel("Absences for: " + student.getFullName());
        nameLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(nameLabel, BorderLayout.NORTH);

        // Retrieve absences for the student
        allAbsences = student.getAbsents();

        // Create a panel for lessons
        JPanel lessonPanel = new JPanel(new GridBagLayout());
        lessonPanel.setBorder(BorderFactory.createTitledBorder("Lessons"));
        GridBagConstraints lessonGbc = new GridBagConstraints();
        lessonGbc.gridx = 0;
        lessonGbc.gridy = 0;
        lessonGbc.anchor = GridBagConstraints.WEST;
        for (Absent absence : allAbsences) {
            Lesson lesson = absence.getLesson();
            JLabel lessonLabel = new JLabel(lesson.toString());
            lessonPanel.add(lessonLabel, lessonGbc);
            lessonGbc.gridy++;
        }

        // Add lessons panel to a scroll pane
        JScrollPane lessonScrollPane = new JScrollPane(lessonPanel);
        add(lessonScrollPane, BorderLayout.CENTER);

        // Create a "Return to Menu" button
        JButton returnButton = new JButton("Return to Menu");

        // Add action listener to the return button
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Return to the menu panel
                MenuPanel menuPanel = new MenuPanel();
                MainFrame mainFrame = (MainFrame) SwingUtilities.getWindowAncestor(StudentAbsencePanel.this);
                mainFrame.setCurrentPanel(menuPanel);
            }
        });

        // Add the return button to the panel
        add(returnButton, BorderLayout.SOUTH);
    }
}
