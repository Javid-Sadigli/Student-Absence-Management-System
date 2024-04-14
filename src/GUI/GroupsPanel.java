package GUI;

import models.Group;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GroupsPanel extends JPanel {
    private JList<String> groupsList;

    public GroupsPanel() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        DefaultListModel<String> groupsModel = new DefaultListModel<>();
        Group[] allGroups = Group.getAll();
        for (Group group : allGroups) {
            groupsModel.addElement(group.getName());
        }
        groupsList = new JList<>(groupsModel);
        JScrollPane groupsScrollPane = new JScrollPane(groupsList);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(groupsScrollPane, gbc);

        JButton deleteButton = new JButton("Delete");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 0.0;
        this.add(deleteButton, gbc);

        JButton addGroupButton = new JButton("Add Group");
        gbc.gridy = 2;
        this.add(addGroupButton, gbc);

        JButton returnButton = new JButton("Return");
        gbc.gridy = 3;
        this.add(returnButton, gbc);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = groupsList.getSelectedIndex();
                if (selectedIndex != -1) {
                    Group selectedGroup = allGroups[selectedIndex];
                    selectedGroup.destroy();
                    groupsModel.remove(selectedIndex);
                    JOptionPane.showMessageDialog(GroupsPanel.this, "Group deleted successfully.");
                } else {
                    JOptionPane.showMessageDialog(GroupsPanel.this, "Please select a group to delete.");
                }
            }
        });

        addGroupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show the add group panel
                AddGroupPanel addGroupPanel = new AddGroupPanel();
                MainFrame mainFrame = (MainFrame) SwingUtilities.getWindowAncestor(GroupsPanel.this);
                mainFrame.setCurrentPanel(addGroupPanel);
            }
        });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Return to the main menu
                MenuPanel menuPanel = new MenuPanel();
                MainFrame mainFrame = (MainFrame) SwingUtilities.getWindowAncestor(GroupsPanel.this);
                mainFrame.setCurrentPanel(menuPanel);
            }
        });
    }
}
