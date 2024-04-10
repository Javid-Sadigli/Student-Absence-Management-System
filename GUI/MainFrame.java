
import javax.swing.*;

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
//        MenuPanel menuPanel = new MenuPanel();
//        AddStudentPanel addStudentPanel = new AddStudentPanel();
//        AddLessonPanel addLessonPanel = new AddLessonPanel();
        CheckPresence checkPresence = new CheckPresence();

        this.setCurrentPanel(checkPresence);
        this.setTitle(checkPresence.getPanelTitle());
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
