import GUI.MainFrame;
import javax.swing.*;

/**
 * The main class to start the application.
 */
public class App 
{
    /**
     * Main method to start the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) 
    {
        // Launch the main frame on the event dispatch thread
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);

        });
    }
}