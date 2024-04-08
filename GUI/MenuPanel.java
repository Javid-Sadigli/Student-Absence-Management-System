import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {
    public MenuPanel() {
        JButton addStudentButton = new JButton("Add Student");
        JButton addLessonButton = new JButton("Add Lesson");
        JButton checkPresenceButton = new JButton("Check Presence");

        add(addStudentButton);
        add(addLessonButton);
        add(checkPresenceButton);

        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame mainFrame = (MainFrame) SwingUtilities.getWindowAncestor(MenuPanel.this);
                mainFrame.setCurrentPanel(new AddStudentPanel());
            }
        });

    }
}
