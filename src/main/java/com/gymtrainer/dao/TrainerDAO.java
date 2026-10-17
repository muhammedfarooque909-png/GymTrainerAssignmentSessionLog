
package com.gymtrainer.dao;

import com.gymtrainer.model.Trainer;
import com.gymtrainer.util.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrainerDAO {

    public boolean addTrainer(Trainer trainer) {
        String sql = "INSERT INTO trainers "
                + "(name, specialization, phone) VALUES (?, ?, ?)";

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, trainer.getName());
            ps.setString(2, trainer.getSpecialization());
            ps.setString(3, trainer.getPhone());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error adding trainer: " + e.getMessage());
            return false;
        }
    }

    public List<Trainer> getAllTrainers() {
        List<Trainer> trainers = new ArrayList<>();
        String sql = "SELECT * FROM trainers";

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Trainer trainer = new Trainer(
                        rs.getInt("trainer_id"),
                        rs.getString("name"),
                        rs.getString("specialization"),
                        rs.getString("phone")
                );

                trainers.add(trainer);
            }

        } catch (SQLException e) {
            System.out.println("Error viewing trainers: " + e.getMessage());
        }

        return trainers;
    }
}
