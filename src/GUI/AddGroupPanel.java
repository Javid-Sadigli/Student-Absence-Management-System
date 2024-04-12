package GUI;

import models.Group;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddGroupPanel extends JPanel {

    private String groupName;
    private String groupLeader;

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

//        gbc.gridx = 0;
//        gbc.gridy++;
//        JLabel leaderLabel = new JLabel("Group Leader: ");
//        this.add(leaderLabel, gbc);
//
//        gbc.gridx++;
//        JTextField groupLeaderField = new JTextField(20);
//        this.add(groupLeaderField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        JButton submitButton = new JButton("Submit");
        this.add(submitButton, gbc);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                groupName = groupNameField.getText();
                Group newGroup = new Group(groupName);
                newGroup.save();
                JOptionPane.showMessageDialog(AddGroupPanel.this, "Group saved successfully!");
            }
        });
    }

    public String getGroupName() {
        return groupName;
    }

    public String getGroupLeader() {
        return groupLeader;
    }
}
