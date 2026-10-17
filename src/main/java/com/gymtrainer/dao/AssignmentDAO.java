
package com.gymtrainer.dao;

import com.gymtrainer.model.Assignment;
import com.gymtrainer.util.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AssignmentDAO {

    public boolean addAssignment(Assignment assignment) {

        String sql = "INSERT INTO trainer_assignments "
                + "(member_id, trainer_id, start_date, end_date) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, assignment.getMemberId());
            ps.setInt(2, assignment.getTrainerId());
            ps.setString(3, assignment.getStartDate());

            if (assignment.getEndDate() == null
                    || assignment.getEndDate().isEmpty()) {
                ps.setNull(4, java.sql.Types.DATE);
            } else {
                ps.setString(4, assignment.getEndDate());
            }

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(
                    "Error adding assignment: " + e.getMessage());
            return false;
        }
    }

    public List<String> getAllAssignments() {

        List<String> assignments = new ArrayList<>();

        String sql = "SELECT a.assignment_id, m.name AS member_name, "
                + "t.name AS trainer_name, a.start_date, a.end_date "
                + "FROM trainer_assignments a "
                + "JOIN members m ON a.member_id = m.member_id "
                + "JOIN trainers t ON a.trainer_id = t.trainer_id";

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String details =
                        "Assignment ID: " + rs.getInt("assignment_id")
                        + " | Member: " + rs.getString("member_name")
                        + " | Trainer: " + rs.getString("trainer_name")
                        + " | Start: " + rs.getDate("start_date")
                        + " | End: " + rs.getDate("end_date");

                assignments.add(details);
            }

        } catch (SQLException e) {
            System.out.println(
                    "Error viewing assignments: " + e.getMessage());
        }

        return assignments;
    }
}
