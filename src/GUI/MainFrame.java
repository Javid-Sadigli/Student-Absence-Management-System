package GUI;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * MainFrame class that is inherited from JFrame 
 * Implements the main frame of the application
 */
public class MainFrame extends JFrame {
    private JPanel currentPanel;

    /**
     * Constructor function to initialize the main frame
     */
    public MainFrame() {
        this.setTitle("Attendance Management System"); //default title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null); //center the frame on the screen
        this.setResizable(false);


        this.showDefaultPanel(); //show default panel
    }

    /**
     * Private method that displays the default menu panel
     */
    private void showDefaultPanel() {
        MenuPanel menuPanel = new MenuPanel();
        this.setCurrentPanel(menuPanel);
        this.setTitle(menuPanel.getPanelTitle());
    }

    /**
     * Method for setting the current panel of the frame
     * @param panel New panel that that will be current panel
     */
    public void setCurrentPanel(JPanel panel) {
        //remove current panel and add the new panel
        if (currentPanel != null) {
            this.getContentPane().remove(currentPanel);
        }
        currentPanel = panel;
        this.getContentPane().add(currentPanel);
        this.revalidate();
        this.repaint();
    }

}
