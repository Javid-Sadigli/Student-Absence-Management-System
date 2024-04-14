package GUI;

import models.Absent;
import models.Lesson;
import models.Subject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubjectsPanel extends JPanel {
    private JList<String> subjectsList;

    public SubjectsPanel() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        DefaultListModel<String> subjectsModel = new DefaultListModel<>();
        Subject[] allSubjects = Subject.getAll();
        for (Subject subject : allSubjects) {
            subjectsModel.addElement(subject.getName());
        }
        subjectsList = new JList<>(subjectsModel);
        JScrollPane subjectsScrollPane = new JScrollPane(subjectsList);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(subjectsScrollPane, gbc);

        JButton deleteButton = new JButton("Delete");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 0.0;
        this.add(deleteButton, gbc);

        JButton addSubjectButton = new JButton("Add Subject");
        gbc.gridy = 2;
        this.add(addSubjectButton, gbc);

        JButton returnButton = new JButton("Return to Menu");
        gbc.gridy = 3;
        this.add(returnButton, gbc);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = subjectsList.getSelectedIndex();
                if (selectedIndex != -1) {
                    Subject selectedSubject = allSubjects[selectedIndex];
                    selectedSubject.destroy();
                    subjectsModel.remove(selectedIndex);
                    Lesson[] selectedLessons = Lesson.filterBySubject(selectedSubject.getId());
                    for (Lesson lesson : selectedLessons){
                        Absent[] selectedAbsents = Absent.filterByLesson(lesson.getId());
                        for (Absent absent : selectedAbsents){
                            absent.destroy();
                        }
                    }
                    for (Lesson lesson : selectedLessons){
                        lesson.destroy();
                    }
                    selectedSubject.destroy();
                    JOptionPane.showMessageDialog(SubjectsPanel.this, "Subject deleted successfully.");
                } else {
                    JOptionPane.showMessageDialog(SubjectsPanel.this, "Please select a subject to delete.");
                }
            }
        });

        addSubjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show the add subject panel
                AddSubjectPanel addSubjectPanel = new AddSubjectPanel();
                MainFrame mainFrame = (MainFrame) SwingUtilities.getWindowAncestor(SubjectsPanel.this);
                mainFrame.setCurrentPanel(addSubjectPanel);
            }
        });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Return to the main menu
                MenuPanel menuPanel = new MenuPanel();
                MainFrame mainFrame = (MainFrame) SwingUtilities.getWindowAncestor(SubjectsPanel.this);
                mainFrame.setCurrentPanel(menuPanel);
            }
        });
    }
}
