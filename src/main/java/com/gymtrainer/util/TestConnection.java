
package com.gymtrainer.util;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConnection {

    public static void main(String[] args) {
        try (Connection con = Database.getConnection()) {
            System.out.println("MySQL Connection Successful!");
            System.out.println("Database: " +
                    con.getCatalog());
        } catch (SQLException e) {
            System.out.println("MySQL Connection Failed!");
            System.out.println(e.getMessage());
        }
    }
}

