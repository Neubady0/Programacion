package org.example.demojdbc.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    public static Connection getConnection() throws SQLException {
        String url      = "jdbc:mysql://localhost:3306/miBase";
        String user     = "root";
        String password = "root";
        return DriverManager.getConnection(url, user, password);
    }
}

