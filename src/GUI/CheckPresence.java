package GUI;

import models.Absent;
import models.Lesson;
import models.Subject;

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

        // Create a JList to display the lesson names
        DefaultListModel<String> lessonListModel = new DefaultListModel<>();
        if (lessons != null && !lessons.isEmpty()) {
            for (Lesson lesson : lessons) {
                lessonListModel.addElement(lesson.toString());
            }
        } else {
            // Handle case when no lessons are available
            lessonListModel.addElement("No lessons available");
        }

        // Create the JList
        lessonList = new JList<>(lessonListModel);
        lessonList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lessonList.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16)); // Set font size
        lessonList.setFixedCellHeight(50); // Set cell height
        lessonList.setBorder(new EmptyBorder(10, 10, 10, 10)); // Add padding

        // Set the model
        lessonList.setModel(lessonListModel);

        // Create buttons for adding and deleting lessons
        JButton addButton = new JButton("Add Lesson");
        JButton deleteButton = new JButton("Delete Lesson");
        JButton returnButton = new JButton("Return");

        // Set layout to BorderLayout
        setLayout(new BorderLayout());

        // Create a JPanel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(returnButton);

        // Add button panel to the top of the panel
        add(buttonPanel, BorderLayout.NORTH);

        // Wrap the list in a JScrollPane
        JScrollPane scrollPane = new JScrollPane(lessonList);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Add the scroll pane to the center of the panel
        add(scrollPane, BorderLayout.CENTER);

        //CONTROLLERS
        lessonList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    // Get the selected lesson
                    int selectedIndex = lessonList.getSelectedIndex();
                    if (selectedIndex >= 0 && selectedIndex < lessons.size()) {
                        Lesson selectedLesson = lessons.get(selectedIndex);

                        // Pass the selected lesson to the StudentListPanel
                        StudentListPanel studentListPanel = new StudentListPanel(selectedLesson);
                        MainFrame mainFrame = (MainFrame) SwingUtilities.getWindowAncestor(CheckPresence.this);
                        mainFrame.setCurrentPanel(studentListPanel);
                        // Now you can use studentListPanel as needed
                    } else {
                        // Handle invalid index
                        System.out.println("Invalid lesson index selected.");
                    }
                }
            }
        });

        // Add action listeners for the buttons
        addButton.addActionListener(e -> {

            AddLessonPanel addLessonPanel = new AddLessonPanel();
            MainFrame mainFrame = (MainFrame) SwingUtilities.getWindowAncestor(CheckPresence.this);
            mainFrame.setCurrentPanel(addLessonPanel);
        });

        deleteButton.addActionListener(e -> {

            int selectedIndex = lessonList.getSelectedIndex();
            if (selectedIndex >= 0 && selectedIndex < lessons.size()) {
                Lesson selectedLesson = lessons.get(selectedIndex);
                selectedLesson.destroy();
                lessonListModel.remove(selectedIndex);
                Absent[] selectedAbsents = Absent.filterByLesson(selectedLesson.getId());
                for (Absent absent : selectedAbsents){
                    absent.destroy();
                }
                selectedLesson.destroy();
                JOptionPane.showMessageDialog(CheckPresence.this, "Lesson deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(CheckPresence.this, "Please select a lesson to delete.");
            }
        });

        returnButton.addActionListener(e -> {
            MenuPanel menuPanel = new MenuPanel();
            MainFrame mainFrame = (MainFrame) SwingUtilities.getWindowAncestor(CheckPresence.this);
            mainFrame.setCurrentPanel(menuPanel);
        });
    }

    public String getPanelTitle() {
        return panelTitle;
    }
}
