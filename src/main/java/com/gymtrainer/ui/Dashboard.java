
package com.gymtrainer.ui;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {

    public Dashboard() {
        setTitle("Gym Trainer Assignment and Session Log");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel title = new JLabel(
                "GYM TRAINER MANAGEMENT SYSTEM",
                SwingConstants.CENTER);

        title.setFont(new Font("Arial", Font.BOLD, 20));

        JButton memberButton = new JButton("Member Management");
        JButton trainerButton = new JButton("Trainer Management");
        JButton assignmentButton = new JButton("Trainer Assignment");
        JButton sessionButton = new JButton("Workout Session Log");
        JButton exitButton = new JButton("Exit");

        JPanel buttonPanel = new JPanel(
                new GridLayout(5, 1, 12, 12));

        buttonPanel.setBorder(
                BorderFactory.createEmptyBorder(20, 80, 20, 80));

        buttonPanel.add(memberButton);
        buttonPanel.add(trainerButton);
        buttonPanel.add(assignmentButton);
        buttonPanel.add(sessionButton);
        buttonPanel.add(exitButton);

        add(title, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        memberButton.addActionListener(e ->
                new MemberForm().setVisible(true));

        trainerButton.addActionListener(e ->
                new TrainerForm().setVisible(true));

        assignmentButton.addActionListener(e ->
                new AssignmentForm().setVisible(true));

        sessionButton.addActionListener(e ->
                new WorkoutSessionForm().setVisible(true));

        exitButton.addActionListener(e -> System.exit(0));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new Dashboard().setVisible(true));
    }
}
