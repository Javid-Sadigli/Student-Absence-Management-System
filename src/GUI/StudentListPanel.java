package GUI;

import models.Lesson;
import models.Student;
import models.Absent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StudentListPanel extends JPanel {
    private JList<String> allStudentsList;
    private JList<String> absentStudentsList;

    public StudentListPanel(Lesson lesson) {
        //get group ID
        int groupId = lesson.getSubject().getGroup().getId();

        //get students of selected group by ID
        Student[] allStudentsArray = Student.filterByGroup(groupId);

        //all students
        List<Student> allStudents = new ArrayList<>();
        for (Student student : allStudentsArray) {
            allStudents.add(student);
        }

        // Set layout to GridBagLayout for the main panel
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //create list models for all students and absent students
        DefaultListModel<String> allStudentsModel = new DefaultListModel<>();
        DefaultListModel<String> absentStudentsModel = new DefaultListModel<>();

        //all students
        for (Student student : allStudents) {
            allStudentsModel.addElement(student.getFullName());
        }

        //create JLists
        allStudentsList = new JList<>(allStudentsModel);
        absentStudentsList = new JList<>(absentStudentsModel);

        //create scroll panes for the lists
        JScrollPane allStudentsScrollPane = new JScrollPane(allStudentsList);
        JScrollPane absentStudentsScrollPane = new JScrollPane(absentStudentsList);

        // Add JLists to the panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(allStudentsScrollPane, gbc);

        gbc.gridx = 1;
        this.add(absentStudentsScrollPane, gbc);

        //add buttons to move students between lists
        JButton addToAbsentButton = new JButton("Add to Absent");
        JButton removeFromAbsentButton = new JButton("Remove from Absent");
        JButton saveButton = new JButton("Save"); // Added save button

        //add buttons to a separate panel to ensure they don't take up unnecessary space
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints buttonGBC = new GridBagConstraints();
        buttonGBC.insets = new Insets(5, 5, 5, 5);

        buttonGBC.gridx = 0;
        buttonGBC.gridy = 0;
        buttonPanel.add(addToAbsentButton, buttonGBC);

        buttonGBC.gridx = 1;
        buttonPanel.add(removeFromAbsentButton, buttonGBC);

        //reset buttonGBC for submit button
        buttonGBC.gridx = 0;
        buttonGBC.gridy = 1;
        buttonGBC.gridwidth = 2;
        buttonPanel.add(saveButton, buttonGBC); // Add save button

        // Add buttonPanel to the main panel
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        this.add(buttonPanel, gbc);

        // CONTROLLERS
        addToAbsentButton.addActionListener(e -> {
            String selectedStudent = allStudentsList.getSelectedValue();
            if (selectedStudent != null) {
                allStudentsModel.removeElement(selectedStudent);
                absentStudentsModel.addElement(selectedStudent);
            }
        });

        removeFromAbsentButton.addActionListener(e -> {
            String selectedStudent = absentStudentsList.getSelectedValue();
            if (selectedStudent != null) {
                absentStudentsModel.removeElement(selectedStudent);
                allStudentsModel.addElement(selectedStudent);
            }
        });

        saveButton.addActionListener(e -> {
            for (int i = 0; i < absentStudentsModel.getSize(); i++) {
                String studentName = absentStudentsModel.getElementAt(i);
                Student student = findByName(studentName);
                if (student != null) {
                    Absent absent = new Absent(student.getId(), lesson.getId());
                    absent.save();
                }
            }
            JOptionPane.showMessageDialog(this, "Absent students saved successfully!");
        });
    }

    // Method to find a student by name
    private Student findByName(String name) {
        for (int i = 0; i < allStudentsList.getModel().getSize(); i++) {
            if (allStudentsList.getModel().getElementAt(i).equals(name)) {
                return Student.getAll()[i];
            }
        }
        return null;
    }
}
