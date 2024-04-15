package GUI;

import models.Group;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Panel for adding a new group.
 */
public class AddGroupPanel extends JPanel {

    private String groupName;
    private String groupLeader;

    /**
     * Constructs the AddGroupPanel.
     */
    public AddGroupPanel() {

        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel nameLabel = new JLabel("Group Name: ");
        this.add(nameLabel, gbc);

        gbc.gridx++;
        JTextField groupNameField = new JTextField(20);
        this.add(groupNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        JButton submitButton = new JButton("Submit");
        this.add(submitButton, gbc);

        JButton returnButton = new JButton("Return"); // Create return button
        gbc.gridy++;
        this.add(returnButton, gbc); // Add return button to the panel

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                groupName = groupNameField.getText();
                Group newGroup = new Group(groupName);
                newGroup.save();
                JOptionPane.showMessageDialog(AddGroupPanel.this, "Group saved successfully!");
            }
        });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuPanel menuPanel = new MenuPanel();
                MainFrame mainFrame = (MainFrame) SwingUtilities.getWindowAncestor(AddGroupPanel.this);
                mainFrame.setCurrentPanel(menuPanel);
            }
        });
    }

    /**
     * Gets the name of the group.
     *
     * @return The name of the group.
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * Retrieves the leader of the group.
     *
     * @return The leader of the group.
     */   
    public String getGroupLeader() {
        return groupLeader;
    }
}
