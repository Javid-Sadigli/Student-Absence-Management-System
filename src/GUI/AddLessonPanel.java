package GUI;

import GUI.MenuPanel;
import models.Group;
import models.Lesson;
import models.Subject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A panel for adding new lessons.
 */
public class AddLessonPanel extends JPanel {

    private String panelTitle;
    private String lessonName;
    private String lessonTopic;
    private int dateMonth;
    private int dateDay;
    private String subjectName;

    /**
     * Constructs the AddLessonPanel.
     */
    public AddLessonPanel() {
        this.setLayout(new GridBagLayout());
        this.panelTitle = "New Lesson";

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Subject selection list
        JLabel subjectLabel = new JLabel("Select Subject:");
        this.add(subjectLabel, gbc);

        gbc.gridx++;
        gbc.anchor = GridBagConstraints.EAST;
        JComboBox<String> subjectComboBox = new JComboBox<>();
        List<String> subjectNames = getSubjectNames(); // Get list of subject names
        for (String name : subjectNames) {
            subjectComboBox.addItem(name); // Populate combo box with subject names
        }
        this.add(subjectComboBox, gbc);


        // Date
        gbc.gridx--;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel dateLabel = new JLabel("Choose the date from list");
        this.add(dateLabel, gbc);

        gbc.gridx++;
        gbc.anchor = GridBagConstraints.CENTER;
        String[] months = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        JComboBox<String> monthComboBox = new JComboBox<>(months);
        this.add(monthComboBox, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        Integer[] days = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
                17, 18, 19, 20, 21, 23, 23, 24, 25, 26, 27, 28, 29, 30, 31};
        JComboBox<Integer> daysComboBox = new JComboBox<>(days);
        this.add(daysComboBox, gbc);

        // Room options
        gbc.gridx--;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel roomLabel = new JLabel("Select Room: ");
        this.add(roomLabel, gbc);

        gbc.gridx++;
        gbc.anchor = GridBagConstraints.EAST;
        Integer[] roomOptions = {101, 102, 201, 202, 203, 204, 205, 206, 207, 208, 209,
                301, 302, 303, 304, 305, 306, 307, 308, 309,
                402, 403, 404, 405, 406, 407};
        JComboBox<Integer> roomComboBox = new JComboBox<>(roomOptions);
        this.add(roomComboBox, gbc);


        // Return button
        gbc.gridy++;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.WEST;
        JButton returnButton = new JButton("Return to Menu");
        this.add(returnButton, gbc);


        // Submit button
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        JButton submitButton = new JButton("Submit");
        this.add(submitButton, gbc);

        // CONTROLLERS
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuPanel menuPanel = new MenuPanel();
                MainFrame mainFrame = (MainFrame) SwingUtilities.getWindowAncestor(AddLessonPanel.this);
                mainFrame.setCurrentPanel(menuPanel);
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected month and day
                int monthIndex = monthComboBox.getSelectedIndex();
                int day = (Integer) daysComboBox.getSelectedItem();

                // Create a Calendar instance and set the month and day
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.MONTH, monthIndex);
                calendar.set(Calendar.DAY_OF_MONTH, day);

                // Create a Date object from the Calendar
                Date date = calendar.getTime();

                // Get the selected room and subject
                int room = (Integer) roomComboBox.getSelectedItem();
                String selectedSubject = (String) subjectComboBox.getSelectedItem();

                // Get the subject ID for the selected subject name
                int subjectId = getSubjectIdByName(selectedSubject);

                // Create a new Lesson object with the input values
                Lesson lesson = new Lesson(date, subjectId, room);

                // Save the Lesson object
                lesson.save();
                JOptionPane.showMessageDialog(AddLessonPanel.this, "Lesson saved successfully!");
            }
        });
    }

    private java.util.List<String> getSubjectNames() {
        List<String> subjectNames = new ArrayList<>();
        Subject[] subjects = Subject.getAll();
        for (Subject subject : subjects) {
            subjectNames.add(subject.getName());
        }
        return subjectNames;
    }

    /**
     * Retrieves the ID of a subject by its name.
     * 
     * @param subjectName The name of the subject.
     * @return The ID of the subject.
     */
    private int getSubjectIdByName(String subjectName) {
        Subject[] subjects = Subject.getAll();
        for (Subject subject : subjects) {
            if (subject.getName().equals(subjectName)) {
                return subject.getId();
            }
        }
        return -1;
    }

    /**
     * Sets the day of the date.
     * 
     * @param dateDay The day of the date.
     */
    public void setDateDay(int dateDay) {
        this.dateDay = dateDay;
    }

    /**
     * Sets the month of the date.
     * 
     * @param dateMonth The month of the date.
     */
    public void setDateMonth(int dateMonth) {
        this.dateMonth = dateMonth;
    }

    /**
     * Sets the name of the lesson.
     * 
     * @param lessonName The name of the lesson.
     */
    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    /**
     * Sets the topic of the lesson.
     * 
     * @param lessonTopic The topic of the lesson.
     */
    public void setLessonTopic(String lessonTopic) {
        this.lessonTopic = lessonTopic;
    }

    /**
     * Gets the panel title.
     * 
     * @return The panel title.
     */
    public String getPanelTitle() {
        return panelTitle;
    }

    /**
     * Gets the day of the date.
     * 
     * @return The day of the date.
     */
    public int getDateDay() {
        return dateDay;
    }

    /**
     * Gets the month of the date.
     * 
     * @return The month of the date.
     */
    public int getDateMonth() {
        return dateMonth;
    }

    /**
     * Gets the name of the lesson.
     * 
     * @return The name of the lesson.
     */
    public String getLessonName() {
        return lessonName;
    }

    /**
     * Gets the topic of the lesson.
     * 
     * @return The topic of the lesson.
     */
    public String getLessonTopic() {
        return lessonTopic;
    }
}
