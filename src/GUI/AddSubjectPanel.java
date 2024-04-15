package GUI;

import models.Subject;
import models.Group;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Panel for adding a new subject with specified details.
 */
public class AddSubjectPanel extends JPanel {

    private String subjectName;
    private JComboBox<String> groupComboBox;

    /**
     * Constructs a new AddSubjectPanel with default settings.
     */
    public AddSubjectPanel() {

        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel nameLabel = new JLabel("Subject Name: ");
        this.add(nameLabel, gbc);

        gbc.gridy++;
        JTextField subjectNameField = new JTextField(20);
        this.add(subjectNameField, gbc);

        // Get a list of available group names
        List<String> groupNames = getGroupNames();

        gbc.gridy++;
        JLabel groupLabel = new JLabel("Select Group: ");
        this.add(groupLabel, gbc);

        gbc.gridx++;
        groupComboBox = new JComboBox<>(groupNames.toArray(new String[0]));
        this.add(groupComboBox, gbc);

        // Add a return button
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 1;
        gbc.gridy++;
        gbc.gridwidth = 1;
        JButton returnButton = new JButton("Return to Menu");
        this.add(returnButton, gbc);

        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 2;
        JButton submitButton = new JButton("Submit");
        this.add(submitButton, gbc);

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuPanel menuPanel = new MenuPanel();
                MainFrame mainFrame = (MainFrame) SwingUtilities.getWindowAncestor(AddSubjectPanel.this);
                mainFrame.setCurrentPanel(menuPanel);
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                subjectName = subjectNameField.getText();
                String selectedGroupName = (String) groupComboBox.getSelectedItem();

                int groupId = getGroupIdByName(selectedGroupName);
                if (groupId != -1) {
                    Subject newSubject = new Subject(subjectName, groupId);
                    newSubject.save();
                    // Optionally, you can display a message confirming the subject has been saved
                    JOptionPane.showMessageDialog(AddSubjectPanel.this, "Subject saved successfully!");
                } else {
                    JOptionPane.showMessageDialog(AddSubjectPanel.this, "Error: Invalid group selection!");
                }
            }
        });

    }

    /**
     * Retrieves a list of group names.
     *
     * @return List of group names.
     */
    private List<String> getGroupNames() {
        // Retrieve and return a list of group names from the database
        List<String> groupNames = new ArrayList<>();
        Group[] groups = Group.getAll();
        for (Group group : groups) {
            groupNames.add(group.getName());
        }
        return groupNames;
    }

    /**
     * Retrieves the ID of a group by its name.
     *
     * @param groupName Name of the group.
     * @return ID of the group.
     */
    private int getGroupIdByName(String groupName) {
        // Retrieve and return the group ID based on the group name
        Group[] groups = Group.getAll();
        for (Group group : groups) {
            if (group.getName().equals(groupName)) {
                return group.getId();
            }
        }
        return -1; // Return -1 if group name is not found
    }

    /**
     * Gets the name of the subject.
     *
     * @return The name of the subject.
     */
    public String getSubjectName() {
        return subjectName;
    }
}
