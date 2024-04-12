package GUI;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {
    private JPanel currentPanel;

    public MainFrame() {
        this.setTitle("Attendance Management System"); //default title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null); //center the frame on the screen
        this.setResizable(false);


        this.showDefaultPanel(); //show default panel
    }

    private void showDefaultPanel() {
        MenuPanel menuPanel = new MenuPanel();
//        AddStudentPanel addStudentPanel = new AddStudentPanel();
//        AddLessonPanel addLessonPanel = new AddLessonPanel();
//        List<String> lessons = readLessonNamesFromFile("src/Lessons");
//        CheckPresence checkPresence = new CheckPresence(lessons);

        this.setCurrentPanel(menuPanel);
        this.setTitle(menuPanel.getPanelTitle());
    }

    private List<String> readLessonNamesFromFile(String filePath) {
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

    public void setCurrentPanel(JPanel panel) {
        //remove current panel and add the new panel
        if (currentPanel != null) {
            this.getContentPane().remove(currentPanel);
        }
        currentPanel = panel;
        this.getContentPane().add(currentPanel);
        this.revalidate(); //refresh the frame
        this.repaint();
    }

    //main function
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);

        });
    }
}
