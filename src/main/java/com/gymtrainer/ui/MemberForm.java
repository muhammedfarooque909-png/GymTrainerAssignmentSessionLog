
package com.gymtrainer.ui;

import com.gymtrainer.dao.MemberDAO;
import com.gymtrainer.model.Member;

import javax.swing.*;
import java.awt.*;

public class MemberForm extends JFrame {

    private JTextField nameField;
    private JTextField ageField;
    private JTextField genderField;
    private JTextField phoneField;
    private JTextField dateField;

    private final MemberDAO memberDAO = new MemberDAO();

    public MemberForm() {
        setTitle("Gym Trainer - Member Management");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        nameField = new JTextField();
        ageField = new JTextField();
        genderField = new JTextField();
        phoneField = new JTextField();
        dateField = new JTextField("2026-10-17");

        panel.add(new JLabel("Member Name:"));
        panel.add(nameField);

        panel.add(new JLabel("Age:"));
        panel.add(ageField);

        panel.add(new JLabel("Gender:"));
        panel.add(genderField);

        panel.add(new JLabel("Phone:"));
        panel.add(phoneField);

        panel.add(new JLabel("Join Date (YYYY-MM-DD):"));
        panel.add(dateField);

        JButton saveButton = new JButton("Save Member");
        JButton viewButton = new JButton("View Members");

        panel.add(saveButton);
        panel.add(viewButton);

        saveButton.addActionListener(e -> saveMember());
        viewButton.addActionListener(e -> viewMembers());

        add(panel);
    }

    private void saveMember() {
        try {
            String name = nameField.getText().trim();
            int age = Integer.parseInt(ageField.getText().trim());
            String gender = genderField.getText().trim();
            String phone = phoneField.getText().trim();
            String joinDate = dateField.getText().trim();

            if (name.isEmpty() || gender.isEmpty()
                    || phone.isEmpty() || joinDate.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please fill in all fields.");
                return;
            }

            Member member = new Member(
                    0, name, age, gender, phone, joinDate);

            if (memberDAO.addMember(member)) {
                JOptionPane.showMessageDialog(this,
                        "Member saved successfully!");
                nameField.setText("");
                ageField.setText("");
                genderField.setText("");
                phoneField.setText("");
            } else {
                JOptionPane.showMessageDialog(this,
                        "Could not save member. Check the database.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Age must be a number.");
        }
    }

    private void viewMembers() {
        StringBuilder result = new StringBuilder();

        for (Member m : memberDAO.getAllMembers()) {
            result.append("ID: ").append(m.getMemberId())
                    .append(" | Name: ").append(m.getName())
                    .append(" | Age: ").append(m.getAge())
                    .append(" | Phone: ").append(m.getPhone())
                    .append("\n");
        }

        if (result.isEmpty()) {
            result.append("No members found.");
        }

        JTextArea textArea = new JTextArea(result.toString(), 15, 35);
        textArea.setEditable(false);

        JOptionPane.showMessageDialog(this,
                new JScrollPane(textArea),
                "Gym Members",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MemberForm().setVisible(true);
        });
    }
}
