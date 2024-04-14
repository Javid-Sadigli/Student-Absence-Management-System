package GUI;

import models.Student;
import models.Absent;
import models.Lesson;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class StudentAbsencePanel extends JPanel {
    private Student student;
    Absent[] allAbsences;
    Absent[] subjectAbsences;

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

        //
        for (Absent absence : allAbsences) {
            int subjectId = absence.getLesson().getSubject().getId();
            subjectAbsences = student.getAbsentsForSubject(subjectId);

        }

        // Create a map to store the count of absences per subject
        HashMap<String, Integer> subjectCounts = new HashMap<>();

        // Count the absences for each subject
        for (Absent absence : subjectAbsences) {
            String subjectName = absence.getLesson().getSubject().getName();
            subjectCounts.put(subjectName, subjectCounts.getOrDefault(subjectName, 0) + 1);
        }

        JPanel studentAbsencePanel = new JPanel();
        studentAbsencePanel.setLayout(new GridLayout(2, 1));

        // Create a panel for subjects
        JPanel subjectPanel = new JPanel(new GridBagLayout());
        subjectPanel.setBorder(BorderFactory.createTitledBorder("Subjects"));
        GridBagConstraints subjectGbc = new GridBagConstraints();
        subjectGbc.gridx = 0;
        subjectGbc.gridy = 0;
        subjectGbc.anchor = GridBagConstraints.WEST;
        for (String subjectName : subjectCounts.keySet()) {
            JLabel subjectLabel = new JLabel(subjectName);
            JLabel countLabel = new JLabel(subjectCounts.get(subjectName).toString());
            subjectPanel.add(subjectLabel, subjectGbc);
            subjectGbc.gridx++;
            JLabel space = new JLabel(".......................");
            subjectPanel.add(space);
            subjectGbc.gridx++;
            subjectGbc.anchor = GridBagConstraints.EAST;
            JLabel countTextLabel = new JLabel("Count: ");
            subjectPanel.add(countTextLabel, subjectGbc);
            subjectGbc.gridx++;
            subjectPanel.add(countLabel, subjectGbc);

            subjectGbc.gridx = 0;
            subjectGbc.gridy++;
        }

        // Add subjects panel to a scroll pane
        JScrollPane subjectScrollPane = new JScrollPane(subjectPanel);
        studentAbsencePanel.add(subjectScrollPane);

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
        studentAbsencePanel.add(lessonScrollPane);

        // Add the parent panel to the main panel
        add(studentAbsencePanel, BorderLayout.CENTER);
    }
}
