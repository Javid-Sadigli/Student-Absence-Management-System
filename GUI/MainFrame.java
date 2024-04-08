
import javax.swing.*;

public class MainFrame extends JFrame {
    private JPanel currentPanel;

    public MainFrame() {
        setTitle("Attendance Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); //center the frame on the screen
        setResizable(false);

        showDefaultPanel(); //show default panel
    }

    private void showDefaultPanel() {
        MenuPanel menuPanel = new MenuPanel();
        setCurrentPanel(menuPanel);
    }

    public void setCurrentPanel(JPanel panel) {
        //remove current panel and add the new panel
        if (currentPanel != null) {
            getContentPane().remove(currentPanel);
        }
        currentPanel = panel;
        getContentPane().add(currentPanel);
        revalidate(); //refresh the frame
        repaint();
    }

    //main function
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
