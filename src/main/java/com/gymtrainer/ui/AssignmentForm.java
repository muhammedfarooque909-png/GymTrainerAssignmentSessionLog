
package com.gymtrainer.ui;

import com.gymtrainer.dao.AssignmentDAO;
import com.gymtrainer.model.Assignment;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AssignmentForm extends JFrame {

    private JTextField memberIdField;
    private JTextField trainerIdField;
    private JTextField startDateField;
    private JTextField endDateField;

    private final AssignmentDAO assignmentDAO = new AssignmentDAO();

    public AssignmentForm() {
        setTitle("Gym Trainer - Trainer Assignment");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20));

        memberIdField = new JTextField();
        trainerIdField = new JTextField();
        startDateField = new JTextField("2026-10-17");
        endDateField = new JTextField();

        panel.add(new JLabel("Member ID:"));
        panel.add(memberIdField);

        panel.add(new JLabel("Trainer ID:"));
        panel.add(trainerIdField);

        panel.add(new JLabel("Start Date (YYYY-MM-DD):"));
        panel.add(startDateField);

        panel.add(new JLabel("End Date (optional):"));
        panel.add(endDateField);

        JButton assignButton = new JButton("Assign Trainer");
        JButton viewButton = new JButton("View Assignments");

        panel.add(assignButton);
        panel.add(viewButton);

        assignButton.addActionListener(e -> saveAssignment());
        viewButton.addActionListener(e -> viewAssignments());

        add(panel);
    }

    private void saveAssignment() {
        try {
            int memberId = Integer.parseInt(
                    memberIdField.getText().trim());

            int trainerId = Integer.parseInt(
                    trainerIdField.getText().trim());

            String startDate = startDateField.getText().trim();
            String endDate = endDateField.getText().trim();

            if (startDate.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please enter the start date.");
                return;
            }

            Assignment assignment = new Assignment(
                    0, memberId, trainerId, startDate, endDate);

            if (assignmentDAO.addAssignment(assignment)) {
                JOptionPane.showMessageDialog(this,
                        "Trainer assigned successfully!");

                memberIdField.setText("");
                trainerIdField.setText("");
                endDateField.setText("");
            } else {
                JOptionPane.showMessageDialog(this,
                        "Assignment failed. Check member/trainer IDs "
                                + "and dates.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Member ID and Trainer ID must be numbers.");
        }
    }

    private void viewAssignments() {
        List<String> assignments =
                assignmentDAO.getAllAssignments();

        if (assignments.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "No assignments found.");
            return;
        }

        JTextArea area = new JTextArea(15, 45);
        for (String assignment : assignments) {
            area.append(assignment + "\n\n");
        }
        area.setEditable(false);

        JOptionPane.showMessageDialog(this,
                new JScrollPane(area),
                "Trainer Assignments",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new AssignmentForm().setVisible(true));
    }
}
