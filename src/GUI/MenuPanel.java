package GUI;

import javax.swing.*;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        this.add(addStudentButton, gbc);

        gbc.gridy++;
        this.add(addLessonButton, gbc);

        gbc.gridy++;
        this.add(checkPresenceButton, gbc);


        // Create a JLabel with custom styling to resemble an underlined reference
        JLabel referenceLabel = new JLabel("<html><u>I am a Student</u></html>");
        referenceLabel.setForeground(Color.BLUE);
        referenceLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


        gbc.gridy++;
        add(referenceLabel, gbc);

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
                CheckPresence checkPresence= new CheckPresence(readLessonNamesFromFile("src/Lessons"));
                MainFrame mainFrame = (MainFrame) SwingUtilities.getWindowAncestor(MenuPanel.this);
                mainFrame.setCurrentPanel(checkPresence);
            }
        });

    }

    //data retrieving for Check Presence Button
    private java.util.List<String> readLessonNamesFromFile(String filePath) {
        List<String> lessons = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lessons.add(line.trim()); // Add lesson name to the list
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lessons;
    }



    public String getPanelTitle() {
        return panelTitle;
    }
}
