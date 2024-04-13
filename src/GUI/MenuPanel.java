package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import models.Lesson;

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
        JButton addSubjectButton = new JButton("Add Subject"); // New button for Add Subject
        JButton addGroupButton = new JButton("Add Group"); // New button for Add Group

        // make buttons larger
        Dimension buttonSize = new Dimension(300, 50);
        addStudentButton.setPreferredSize(buttonSize);
        addLessonButton.setPreferredSize(buttonSize);
        checkPresenceButton.setPreferredSize(buttonSize);
        addSubjectButton.setPreferredSize(buttonSize); // Set size for new buttons
        addGroupButton.setPreferredSize(buttonSize);

        // GridBagConstraints for button positioning
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // add some padding

        // adding buttons to the panel with GridBagConstraints
        this.add(addStudentButton, gbc);

        gbc.gridy++;
        this.add(addLessonButton, gbc);

        gbc.gridy++;
        this.add(checkPresenceButton, gbc);

        gbc.gridy++;
        this.add(addSubjectButton, gbc); // Add the new button to the panel

        gbc.gridy++;
        this.add(addGroupButton, gbc); // Add the new button to the panel


        //CONTROLLERS
        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddStudentPanel addStudentPanel = new AddStudentPanel();
                MainFrame mainFrame = (MainFrame) SwingUtilities.getWindowAncestor(MenuPanel.this);
                mainFrame.setCurrentPanel(addStudentPanel);
            }
        });

        addLessonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddLessonPanel addLessonPanel = new AddLessonPanel();
                MainFrame mainFrame = (MainFrame) SwingUtilities.getWindowAncestor(MenuPanel.this);
                mainFrame.setCurrentPanel(addLessonPanel);
            }
        });

        checkPresenceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Lesson[] lessons = Lesson.getAll();
                List<Lesson> lessonList = Arrays.asList(lessons); // Convert array to list
                CheckPresence checkPresence = new CheckPresence(lessonList);
                MainFrame mainFrame = (MainFrame) SwingUtilities.getWindowAncestor(MenuPanel.this);
                mainFrame.setCurrentPanel(checkPresence);
            }
        });

        addSubjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddSubjectPanel addSubjectPanel = new AddSubjectPanel(); // Create an instance of AddSubjectPanel
                MainFrame mainFrame = (MainFrame) SwingUtilities.getWindowAncestor(MenuPanel.this);
                mainFrame.setCurrentPanel(addSubjectPanel); // Set the current panel to AddSubjectPanel
            }
        });

        addGroupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddGroupPanel addGroupPanel = new AddGroupPanel(); // Create an instance of AddGroupPanel
                MainFrame mainFrame = (MainFrame) SwingUtilities.getWindowAncestor(MenuPanel.this);
                mainFrame.setCurrentPanel(addGroupPanel); // Set the current panel to AddGroupPanel
            }
        });

    }

    public String getPanelTitle() {
        return panelTitle;
    }
}
