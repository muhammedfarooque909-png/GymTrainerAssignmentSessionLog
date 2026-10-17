
package com.gymtrainer.dao;

import com.gymtrainer.model.Member;
import com.gymtrainer.util.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {

    public boolean addMember(Member member) {
        String sql = "INSERT INTO members "
                + "(name, age, gender, phone, join_date) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, member.getName());
            ps.setInt(2, member.getAge());
            ps.setString(3, member.getGender());
            ps.setString(4, member.getPhone());
            ps.setString(5, member.getJoinDate());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error adding member: " + e.getMessage());
            return false;
        }
    }

    public List<Member> getAllMembers() {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT * FROM members";

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Member member = new Member(
                    rs.getInt("member_id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("gender"),
                    rs.getString("phone"),
                    rs.getString("join_date")
                );

                members.add(member);
            }

        } catch (SQLException e) {
            System.out.println("Error viewing members: " + e.getMessage());
        }

        return members;
    }
}
