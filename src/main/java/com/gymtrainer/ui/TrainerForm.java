
package com.gymtrainer.ui;

import com.gymtrainer.dao.TrainerDAO;
import com.gymtrainer.model.Trainer;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TrainerForm extends JFrame {

    private JTextField nameField;
    private JTextField specializationField;
    private JTextField phoneField;

    private final TrainerDAO trainerDAO = new TrainerDAO();

    public TrainerForm() {
        setTitle("Gym Trainer - Trainer Management");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20));

        nameField = new JTextField();
        specializationField = new JTextField();
        phoneField = new JTextField();

        panel.add(new JLabel("Trainer Name:"));
        panel.add(nameField);

        panel.add(new JLabel("Specialization:"));
        panel.add(specializationField);

        panel.add(new JLabel("Phone Number:"));
        panel.add(phoneField);

        JButton saveButton = new JButton("Save Trainer");
        JButton viewButton = new JButton("View Trainers");

        panel.add(saveButton);
        panel.add(viewButton);

        saveButton.addActionListener(e -> saveTrainer());
        viewButton.addActionListener(e -> viewTrainers());

        add(panel);
    }

    private void saveTrainer() {
        String name = nameField.getText().trim();
        String specialization =
                specializationField.getText().trim();
        String phone = phoneField.getText().trim();

        if (name.isEmpty() || specialization.isEmpty()
                || phone.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please fill in all fields.");
            return;
        }

        Trainer trainer = new Trainer(
                0, name, specialization, phone);

        if (trainerDAO.addTrainer(trainer)) {
            JOptionPane.showMessageDialog(this,
                    "Trainer saved successfully!");

            nameField.setText("");
            specializationField.setText("");
            phoneField.setText("");
        } else {
            JOptionPane.showMessageDialog(this,
                    "Could not save trainer. Check the database.");
        }
    }

    private void viewTrainers() {
        List<Trainer> trainers = trainerDAO.getAllTrainers();

        if (trainers.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "No trainers found.");
            return;
        }

        StringBuilder result = new StringBuilder();

        for (Trainer t : trainers) {
            result.append("Trainer ID: ")
                    .append(t.getTrainerId())
                    .append("\nName: ")
                    .append(t.getName())
                    .append("\nSpecialization: ")
                    .append(t.getSpecialization())
                    .append("\nPhone: ")
                    .append(t.getPhone())
                    .append("\n\n--------------------\n\n");
        }

        JTextArea textArea = new JTextArea(result.toString(), 15, 35);
        textArea.setEditable(false);

        JOptionPane.showMessageDialog(this,
                new JScrollPane(textArea),
                "Trainer List",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TrainerForm().setVisible(true);
        });
    }
}

