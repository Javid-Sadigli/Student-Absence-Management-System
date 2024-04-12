package GUI;

import models.Lesson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.MonthDay;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class AddLessonPanel extends JPanel{

    private String panelTitle;
    private String lessonName;
    private String lessonTopic;
    private int dateMonth;
    private int dateDay;

    public AddLessonPanel(){

        this.setLayout(new GridBagLayout());

        this.panelTitle = "New Lesson";

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;


        JLabel nameLabel = new JLabel("Course Name: ");
        this.add(nameLabel, gbc);

        gbc.gridy++;
        JLabel topicLabel = new JLabel("Topic of the Lesson: ");
        this.add(topicLabel, gbc);

        gbc.gridy++;
        JLabel dateLabel = new JLabel("Choose the date from list");
        this.add(dateLabel, gbc);

        gbc.anchor = GridBagConstraints.EAST;

        gbc.gridx++;
        gbc.gridy = 0;
        JTextField lessonName = new JTextField(20);
        this.add(lessonName, gbc);

        gbc.gridy++;
        JTextField lessonTopic = new JTextField(20);
        this.add(lessonTopic, gbc);


        // Months

        gbc.gridy++;
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


        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        JButton submitButton = new JButton("Submit");
        this.add(submitButton, gbc);

        //CONTROLLERS
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the values from the text fields and combo boxes
                String name = lessonName.getText();
                String topic = lessonTopic.getText();
                int monthIndex = monthComboBox.getSelectedIndex(); // 0-based index of the selected month
                int day = (Integer) daysComboBox.getSelectedItem(); // Selected day

                // Set the values to corresponding variables
                setLessonName(name);
                setLessonTopic(topic);
                setDateMonth(monthIndex + 1); // Convert 0-based index to 1-based month
                setDateDay(day);

                // Debugging: Print the values to console
                System.out.println("Lesson Name: " + getLessonName());
                System.out.println("Lesson Topic: " + getLessonTopic());
                System.out.println("Date Month: " + getDateMonth());
                System.out.println("Date Day: " + getDateDay());
            }
        });



    }

    public void setDateDay(int dateDay) {
        this.dateDay = dateDay;
    }

    public void setDateMonth(int dateMonth) {
        this.dateMonth = dateMonth;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public void setLessonTopic(String lessonTopic) {
        this.lessonTopic = lessonTopic;
    }

    public String getPanelTitle() {
        return panelTitle;
    }

    public int getDateDay() {
        return dateDay;
    }

    public int getDateMonth() {
        return dateMonth;
    }

    public String getLessonName() {
        return lessonName;
    }

    public String getLessonTopic() {
        return lessonTopic;
    }

}
