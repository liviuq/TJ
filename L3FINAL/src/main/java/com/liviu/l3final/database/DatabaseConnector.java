package com.liviu.l3final.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    public static Connection connect() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection connection = null;
        try {
            String url = "jdbc:postgresql://localhost:5432/L3?";
            String user = "postgres";
            String password = "L3";
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}