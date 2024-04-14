package GUI;

import models.Group;
import models.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AddStudentPanel extends JPanel {
    private String panelTitle;
    private JComboBox<String> groupComboBox;

    public AddStudentPanel() {
        // Grid bag layout for panel
        this.setLayout(new GridBagLayout());

        this.panelTitle = "New Student";

        // Constraint to manage layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5); // Add padding

        // Title label
        JLabel titleLabel = new JLabel("Add Student");
        gbc.gridwidth = 2;
        this.add(titleLabel, gbc);

        // Name label and field
        gbc.gridy++;
        gbc.gridwidth = 1;
        JLabel nameLabel = new JLabel("Name: ");
        this.add(nameLabel, gbc);

        gbc.gridx++;
        JTextField studentName = new JTextField(20);
        this.add(studentName, gbc);

        // Surname label and field
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel surnameLabel = new JLabel("Surname: ");
        this.add(surnameLabel, gbc);

        gbc.gridx++;
        JTextField studentSurname = new JTextField(20);
        this.add(studentSurname, gbc);


        // Group selection label and combo box
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel groupLabel = new JLabel("Select Group: ");
        this.add(groupLabel, gbc);

        gbc.gridx++;
        List<String> groupNames = getGroupNames();
        groupComboBox = new JComboBox<>(groupNames.toArray(new String[0]));
        this.add(groupComboBox, gbc);

        // Return button
        gbc.gridy++;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.EAST;
        JButton returnButton = new JButton("Return to Menu");
        this.add(returnButton, gbc);

        // Submit button
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        JButton submitButton = new JButton("Submit");
        this.add(submitButton, gbc);

        // Controllers

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuPanel menuPanel = new MenuPanel();
                MainFrame mainFrame = (MainFrame) SwingUtilities.getWindowAncestor(AddStudentPanel.this);
                mainFrame.setCurrentPanel(menuPanel);
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = studentName.getText();
                String surname = studentSurname.getText();
                String selectedGroupName = (String) groupComboBox.getSelectedItem();
                int groupId = getGroupIdByName(selectedGroupName);

                if (groupId != -1) {
                    Student newStudent = new Student(name + " " + surname, groupId);
                    newStudent.save();
                    // Optionally, you can display a message confirming the student has been saved
                    JOptionPane.showMessageDialog(AddStudentPanel.this, "Student saved successfully!");
                } else {
                    JOptionPane.showMessageDialog(AddStudentPanel.this, "Error: Invalid group selection!");
                }
            }
        });
    }

    private List<String> getGroupNames() {
        // Retrieve and return a list of group names from the database
        List<String> groupNames = new ArrayList<>();
        Group[] groups = Group.getAll();
        for (Group group : groups) {
            groupNames.add(group.getName());
        }
        return groupNames;
    }

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

    public String getPanelTitle() {
        return panelTitle;
    }
}
