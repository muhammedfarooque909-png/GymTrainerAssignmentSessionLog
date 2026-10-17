
package com.gymtrainer.dao;

import com.gymtrainer.model.WorkoutSession;
import com.gymtrainer.util.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkoutSessionDAO {

    public boolean addSession(WorkoutSession session) {

        String sql = "INSERT INTO workout_sessions "
                + "(member_id, trainer_id, session_date, "
                + "duration_minutes, workout_details) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, session.getMemberId());
            ps.setInt(2, session.getTrainerId());
            ps.setString(3, session.getSessionDate());
            ps.setInt(4, session.getDurationMinutes());
            ps.setString(5, session.getWorkoutDetails());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(
                    "Error saving session: " + e.getMessage());
            return false;
        }
    }

    public List<String> getAllSessions() {

        List<String> sessions = new ArrayList<>();

        String sql = "SELECT s.session_id, m.name AS member_name, "
                + "t.name AS trainer_name, s.session_date, "
                + "s.duration_minutes, s.workout_details "
                + "FROM workout_sessions s "
                + "JOIN members m ON s.member_id = m.member_id "
                + "JOIN trainers t ON s.trainer_id = t.trainer_id "
                + "ORDER BY s.session_date DESC";

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String details =
                        "Session ID: " + rs.getInt("session_id")
                        + "\nMember: " + rs.getString("member_name")
                        + "\nTrainer: " + rs.getString("trainer_name")
                        + "\nDate: " + rs.getDate("session_date")
                        + "\nDuration: "
                        + rs.getInt("duration_minutes") + " minutes"
                        + "\nWorkout: "
                        + rs.getString("workout_details");

                sessions.add(details);
            }

        } catch (SQLException e) {
            System.out.println(
                    "Error viewing sessions: " + e.getMessage());
        }

        return sessions;
    }
}
