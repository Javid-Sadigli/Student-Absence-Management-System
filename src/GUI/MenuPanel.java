package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        JButton studentsButton = new JButton("Students");
        JButton groupsButton = new JButton("Groups");
        JButton checkPresenceButton = new JButton("Check Presence");
        JButton subjectsButton = new JButton("Subjects"); // Renamed button for Subjects

        // make buttons larger
        Dimension buttonSize = new Dimension(300, 50);
        studentsButton.setPreferredSize(buttonSize);
        groupsButton.setPreferredSize(buttonSize);
        checkPresenceButton.setPreferredSize(buttonSize);
        subjectsButton.setPreferredSize(buttonSize); // Set size for new button

        // GridBagConstraints for button positioning
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // add some padding

        // adding buttons to the panel with GridBagConstraints
        this.add(studentsButton, gbc);

        gbc.gridy++;
        this.add(groupsButton, gbc);

        gbc.gridy++;
        gbc.gridy++; // Skip the row for the removed button

        gbc.gridy++;
        this.add(checkPresenceButton, gbc);

        gbc.gridy++;
        this.add(subjectsButton, gbc); // Add the new button to the panel

        //CONTROLLERS
        studentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StudentsPanel studentsPanel = new StudentsPanel();
                MainFrame mainFrame = (MainFrame) SwingUtilities.getWindowAncestor(MenuPanel.this);
                mainFrame.setCurrentPanel(studentsPanel);
            }
        });

        groupsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GroupsPanel groupsPanel = new GroupsPanel();
                MainFrame mainFrame = (MainFrame) SwingUtilities.getWindowAncestor(MenuPanel.this);
                mainFrame.setCurrentPanel(groupsPanel);
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

        subjectsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SubjectsPanel subjectsPanel = new SubjectsPanel(); // Create an instance of SubjectsPanel
                MainFrame mainFrame = (MainFrame) SwingUtilities.getWindowAncestor(MenuPanel.this);
                mainFrame.setCurrentPanel(subjectsPanel); // Set the current panel to SubjectsPanel
            }
        });
    }

    public String getPanelTitle() {
        return panelTitle;
    }
}
