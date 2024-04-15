package GUI;

import models.Lesson;
import models.Student;
import models.Absent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Panel for managing the list of students and their absence status for a specific lesson.
 */
public class StudentListPanel extends JPanel {
    private JList<String> allStudentsList;
    private JList<String> absentStudentsList;
    private Student[] allStudentsArray;
    private Lesson currentLesson;

    /**
     * Constructs a new StudentListPanel for the specified lesson.
     *
     * @param lesson The lesson for which the student list is managed.
     */
    public StudentListPanel(Lesson lesson) {
        this.currentLesson = lesson;

        // Get group ID
        int groupId = lesson.getSubject().getGroup().getId();

        // Get students of selected group by ID
        allStudentsArray = Student.filterByGroup(groupId);

        // All students get to array
        List<Student> allStudents = new ArrayList<>();
        for (Student student : allStudentsArray) {
            allStudents.add(student);
        }

        // Set layout to GridBagLayout for the main panel
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Create list models for all students and absent students
        DefaultListModel<String> allStudentsModel = new DefaultListModel<>();
        DefaultListModel<String> absentStudentsModel = new DefaultListModel<>();


        // Create JLists
        allStudentsList = new JList<>(allStudentsModel);
        absentStudentsList = new JList<>(absentStudentsModel);

        // Create scroll panes for the lists
        JScrollPane allStudentsScrollPane = new JScrollPane(allStudentsList);
        JScrollPane absentStudentsScrollPane = new JScrollPane(absentStudentsList);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(allStudentsScrollPane, gbc);

        gbc.gridx = 1;
        this.add(absentStudentsScrollPane, gbc);

        // Add buttons to move students between lists
        JButton addToAbsentButton = new JButton("Add to Absent");
        JButton removeFromAbsentButton = new JButton("Remove from Absent");
        JButton saveButton = new JButton("Save");
        JButton returnButton = new JButton("Return to Menu"); // Add the return button

        // Add buttons to a separate panel to ensure they don't take up unnecessary space
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints buttonGBC = new GridBagConstraints();
        buttonGBC.insets = new Insets(5, 5, 5, 5);

        buttonGBC.gridx = 0;
        buttonGBC.gridy = 0;
        buttonPanel.add(addToAbsentButton, buttonGBC);

        buttonGBC.gridx = 1;
        buttonPanel.add(removeFromAbsentButton, buttonGBC);

        // Reset buttonGBC for submit button
        buttonGBC.gridx = 0;
        buttonGBC.gridy = 1;
        buttonGBC.gridwidth = 2;
        buttonPanel.add(saveButton, buttonGBC);

        // Add the return button
        buttonGBC.gridy = 2;
        buttonPanel.add(returnButton, buttonGBC);

        // Add buttonPanel to the main panel
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        this.add(buttonPanel, gbc);

        // Load absences for the current lesson
        Absent[] absences = Absent.filterByLesson(lesson.getId());
        for (Absent absence : absences) {
            absentStudentsModel.addElement(absence.getStudent().getFullName());
        }

        // All students add to model
        for (Student student : allStudents) {
            boolean isAbsent = false;
            for (Absent absence : absences) {
                if (student.getFullName().equals(absence.getStudent().getFullName())) {
                    isAbsent = true;
                    break;
                }
            }
            if (!isAbsent) {
                allStudentsModel.addElement(student.getFullName());
            }
        }

        // CONTROLLERS
        addToAbsentButton.addActionListener(e -> {
            String selectedStudent = allStudentsList.getSelectedValue();
            if (selectedStudent != null) {
                allStudentsModel.removeElement(selectedStudent);
                absentStudentsModel.addElement(selectedStudent);
            }
        });

        removeFromAbsentButton.addActionListener(e -> {
            String selectedStudentName = absentStudentsList.getSelectedValue();
            if (selectedStudentName != null) {
                absentStudentsModel.removeElement(selectedStudentName);
                allStudentsModel.addElement(selectedStudentName);
                Student selectedStudent = Student.findByName(selectedStudentName);
                Absent absent = Absent.findByLessonAndStudent(lesson.getId(), selectedStudent.getId());
                absent.destroy();
            }
        });

        saveButton.addActionListener(e -> {
            for (int i = 0; i < absentStudentsModel.getSize(); i++) {
                String studentName = absentStudentsModel.getElementAt(i);
                Student student = Student.findByName(studentName);

                if (student != null) {
                    boolean isAbsent = Arrays.stream(absences).anyMatch(absent -> absent.getStudent().equals(student));
                    if (!isAbsent) {
                        // If the student is not already marked as absent, create a new absence record
                        Absent absent = new Absent(student.getId(), lesson.getId());
                        absent.save();
                        JOptionPane.showMessageDialog(this, "Absent students saved successfully!");
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Absent students saved successfully! No changes made.");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(this, "Absent students saved successfully! No absent students.");
                }
            }
        });

        // Add action listener to return button
        returnButton.addActionListener(e -> {
            // Return to the main menu
            MenuPanel menuPanel = new MenuPanel();
            MainFrame mainFrame = (MainFrame) SwingUtilities.getWindowAncestor(StudentListPanel.this);
            mainFrame.setCurrentPanel(menuPanel);
        });
    }
}
