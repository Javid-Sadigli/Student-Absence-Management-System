package GUI;

import models.Group;
import models.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

        // Add double-click listener to the groupsList
        groupsList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedIndex = groupsList.getSelectedIndex();
                    if (selectedIndex != -1) {
                        Group selectedGroup = allGroups[selectedIndex];
                        // Get students of the selected group
                        Student[] students = selectedGroup.getStudents();
                        // Create a StringBuilder to store student names
                        StringBuilder studentNames = new StringBuilder();
                        for (Student student : students) {
                            studentNames.append(student.getFullName()).append("\n");
                        }
                        // Show student names in a dialog
                        JOptionPane.showMessageDialog(GroupsPanel.this, studentNames.toString(), "Students in " + selectedGroup.getName(), JOptionPane.PLAIN_MESSAGE);
                    }
                }
            }
        });

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
