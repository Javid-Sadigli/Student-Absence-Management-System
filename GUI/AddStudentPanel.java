import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AddStudentPanel extends JPanel {
    private String panelTitle;
    public AddStudentPanel() {
        //grid bag layout for panel
        this.setLayout(new GridBagLayout());

        this.panelTitle = "New Student";

        //constraint to manage layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5); //add padding


        JLabel titleLabel = new JLabel("Add Student");


        //description for fields
        JLabel nameLabel = new JLabel("Name: ");
        this.add(nameLabel, gbc);

        gbc.gridy++;
        JLabel surnameLabel = new JLabel("Surname: ");
        this.add(surnameLabel, gbc);

        gbc.gridy++;
        JLabel idLabel = new JLabel("ID: ");
        this.add(idLabel, gbc);



        //input fields
        gbc.gridx = 1;
        gbc.gridy = 0;
        JTextField studentName = new JTextField(20);
        this.add(studentName, gbc);

        gbc.gridy++;
        JTextField studentSurname = new JTextField(20);
        this.add(studentSurname, gbc);

        gbc.gridy++;
        JTextField studentID = new JTextField(20);
        this.add(studentID, gbc);


        //submit button
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        JButton submitButton = new JButton("Submit");
        this.add(submitButton, gbc);

    }

    public String getPanelTitle() {
        return panelTitle;
    }
}

