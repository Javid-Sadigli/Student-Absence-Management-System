package GUI;

import models.Lesson;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class CheckPresence extends JPanel {
    private String panelTitle;
    private JList<String> lessonList;

    public CheckPresence(List<Lesson> lessons) {
        this.panelTitle = "Attendance";

        //create a JList to display the lesson names
        DefaultListModel<String> listModel = new DefaultListModel<>();

        if (lessons != null && !lessons.isEmpty()) {
            for (Lesson lesson : lessons) {
                //lesson details
                listModel.addElement("Subject: " + lesson.getSubject());
//                listModel.addElement("Date: " + lesson.getDate());
//                listModel.addElement("Room: " + lesson.getRoom());
//                listModel.addElement("ID: " + lesson.getId());
                //separator
                listModel.addElement("----------------------");
            }
        } else {
            //handle case when no lessons are available
            listModel.addElement("No lessons available");
        }

        //create the JList
        lessonList = new JList<>(listModel);
        lessonList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lessonList.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16)); // Set font size
        lessonList.setFixedCellHeight(50); // Set cell height
        lessonList.setBorder(new EmptyBorder(10, 10, 10, 10)); // Add padding

        //CONTROLLERS
        lessonList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String selectedLesson = lessonList.getSelectedValue(); //get selected lesson
                    System.out.println("Selected Lesson: " + selectedLesson);
                }
            }
        });

        //wrap the list in a JScrollPane
        JScrollPane scrollPane = new JScrollPane(lessonList);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        //set layout to BorderLayout
        setLayout(new BorderLayout());

        //add the scroll pane to the center of the panel
        add(scrollPane, BorderLayout.CENTER);
    }

    public String getPanelTitle() {
        return panelTitle;
    }
}
