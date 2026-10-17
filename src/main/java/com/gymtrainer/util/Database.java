package com.gymtrainer.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static final String URL =
            "jdbc:mysql://127.0.0.1:3306/gym_trainer_db";

    private static final String USER = "gym_app";

    private static final String PASSWORD = "GymApp@2026!";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}