
package com.gymtrainer.ui;

import com.gymtrainer.dao.WorkoutSessionDAO;
import com.gymtrainer.model.WorkoutSession;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class WorkoutSessionForm extends JFrame {

    private JTextField memberIdField;
    private JTextField trainerIdField;
    private JTextField dateField;
    private JTextField durationField;
    private JTextArea workoutArea;

    private final WorkoutSessionDAO sessionDAO =
            new WorkoutSessionDAO();

    public WorkoutSessionForm() {
        setTitle("Gym Trainer - Workout Session Log");
        setSize(500, 430);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20));

        memberIdField = new JTextField();
        trainerIdField = new JTextField();
        dateField = new JTextField("2026-10-17");
        durationField = new JTextField();

        workoutArea = new JTextArea(3, 20);
        workoutArea.setLineWrap(true);
        workoutArea.setWrapStyleWord(true);

        panel.add(new JLabel("Member ID:"));
        panel.add(memberIdField);

        panel.add(new JLabel("Trainer ID:"));
        panel.add(trainerIdField);

        panel.add(new JLabel("Session Date (YYYY-MM-DD):"));
        panel.add(dateField);

        panel.add(new JLabel("Duration (minutes):"));
        panel.add(durationField);

        panel.add(new JLabel("Workout Details:"));
        panel.add(new JScrollPane(workoutArea));

        JButton saveButton = new JButton("Save Session");
        JButton viewButton = new JButton("View Sessions");

        panel.add(saveButton);
        panel.add(viewButton);

        saveButton.addActionListener(e -> saveSession());
        viewButton.addActionListener(e -> viewSessions());

        add(panel);
    }

    private void saveSession() {
        try {
            String memberText = memberIdField.getText().trim();
            String trainerText = trainerIdField.getText().trim();
            String date = dateField.getText().trim();
            String durationText = durationField.getText().trim();
            String details = workoutArea.getText().trim();

            if (memberText.isEmpty() || trainerText.isEmpty()
                    || date.isEmpty() || durationText.isEmpty()
                    || details.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please fill in all fields.");
                return;
            }

            int memberId = Integer.parseInt(memberText);
            int trainerId = Integer.parseInt(trainerText);
            int duration = Integer.parseInt(durationText);

            if (duration <= 0) {
                JOptionPane.showMessageDialog(this,
                        "Duration must be greater than zero.");
                return;
            }

            WorkoutSession session = new WorkoutSession(
                    0, memberId, trainerId, date,
                    duration, details);

            if (sessionDAO.addSession(session)) {
                JOptionPane.showMessageDialog(this,
                        "Workout session saved successfully!");

                memberIdField.setText("");
                trainerIdField.setText("");
                durationField.setText("");
                workoutArea.setText("");
            } else {
                JOptionPane.showMessageDialog(this,
                        "Could not save session. Check the IDs and date.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Member ID, Trainer ID and duration must be numbers.");
        }
    }

    private void viewSessions() {
        List<String> sessions = sessionDAO.getAllSessions();

        if (sessions.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "No workout sessions found.");
            return;
        }

        JTextArea area = new JTextArea(18, 40);
        for (String session : sessions) {
            area.append(session + "\n\n--------------------\n\n");
        }
        area.setEditable(false);

        JOptionPane.showMessageDialog(this,
                new JScrollPane(area),
                "Workout Session History",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new WorkoutSessionForm().setVisible(true));
    }
}
