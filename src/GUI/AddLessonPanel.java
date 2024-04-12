package GUI;

import javax.swing.*;
import java.awt.*;
import java.time.MonthDay;
import java.util.Date;

public class AddLessonPanel extends JPanel{

    private String panelTitle;
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

    }

    public String getPanelTitle() {
        return panelTitle;
    }
}
