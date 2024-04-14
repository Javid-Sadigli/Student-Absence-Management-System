package GUI;

import models.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentsPanel extends JPanel {
    private JList<String> studentsList;

    public StudentsPanel() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        DefaultListModel<String> studentsModel = new DefaultListModel<>();
        Student[] allStudents = Student.getAll();
        for (Student student : allStudents) {
            studentsModel.addElement(student.getFullName());
        }
        studentsList = new JList<>(studentsModel);
        JScrollPane studentsScrollPane = new JScrollPane(studentsList);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(studentsScrollPane, gbc);

        JButton deleteButton = new JButton("Delete");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 0.0;
        this.add(deleteButton, gbc);

        JButton addStudentButton = new JButton("Add Student");
        gbc.gridy = 2;
        this.add(addStudentButton, gbc);

        JButton returnButton = new JButton("Return");
        gbc.gridy = 3;
        this.add(returnButton, gbc);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = studentsList.getSelectedIndex();
                if (selectedIndex != -1) {
                    Student selectedStudent = allStudents[selectedIndex];
                    selectedStudent.destroy();
                    studentsModel.remove(selectedIndex);
                    JOptionPane.showMessageDialog(StudentsPanel.this, "Student deleted successfully.");
                } else {
                    JOptionPane.showMessageDialog(StudentsPanel.this, "Please select a student to delete.");
                }
            }
        });

        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show the add student panel
                AddStudentPanel addStudentPanel = new AddStudentPanel();
                MainFrame mainFrame = (MainFrame) SwingUtilities.getWindowAncestor(StudentsPanel.this);
                mainFrame.setCurrentPanel(addStudentPanel);
            }
        });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Return to the main menu
                MenuPanel menuPanel = new MenuPanel();
                MainFrame mainFrame = (MainFrame) SwingUtilities.getWindowAncestor(StudentsPanel.this);
                mainFrame.setCurrentPanel(menuPanel);
            }
        });
    }
}
