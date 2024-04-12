package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CheckPresence extends JPanel {
    private String panelTitle;
    private JList<String> lessonList;

    public CheckPresence(List<String> lessons) {
        this.panelTitle = "Attendance";

        // Create a JList to display the lesson names
        DefaultListModel<String> listModel = new DefaultListModel<>();

        if (lessons != null && !lessons.isEmpty()) {
            for (String lesson : lessons) {
                listModel.addElement(lesson);
            }
        } else {
            // Read lesson names from file if no lessons provided
            lessons = readLessonNamesFromFile("lessons.txt");
            for (String lesson : lessons) {
                listModel.addElement(lesson);
            }
        }

        // Create the JList
        lessonList = new JList<>(listModel);
        lessonList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lessonList.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24)); // Set font size
        lessonList.setFixedCellHeight(50); // Set cell height
        lessonList.setBorder(new EmptyBorder(10, 10, 10, 10)); // Add padding

        // Add a mouse listener to handle click events
        lessonList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    // Get the selected lesson
                    String selectedLesson = lessonList.getSelectedValue();
                    // You can navigate to the next panel with the selected lesson here
                    System.out.println("Selected Lesson: " + selectedLesson);
                }
            }
        });

        // Wrap the list in a JScrollPane
        JScrollPane scrollPane = new JScrollPane(lessonList);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Set layout to BorderLayout
        setLayout(new BorderLayout());

        // Add the scroll pane to the center of the panel
        add(scrollPane, BorderLayout.CENTER);
    }

    private List<String> readLessonNamesFromFile(String filePath) {
        List<String> lessons = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lessons.add(line.trim());
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
