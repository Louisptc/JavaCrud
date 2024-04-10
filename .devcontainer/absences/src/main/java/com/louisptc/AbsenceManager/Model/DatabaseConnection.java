package com.louisptc.AbsenceManager.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {
    private final String url = "jdbc:sqlite:" + System.getProperty("user.dir") + "/absences/absences.db";

    public Connection connect() {
        Connection conn = null;
        try {
            // Create a new directory to store the SQLite database
            java.io.File newDir = new java.io.File(System.getProperty("user.dir") + "/absences");
            newDir.mkdir();

            // SQLite doesn't need a username or password
            conn = DriverManager.getConnection(url);
            System.out.println("Connected to the SQLite database successfully.");
            initializeDatabase(conn);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    private void initializeDatabase(Connection conn) {
        try (java.sql.Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DROP TABLE IF EXISTS students;");
            stmt.executeUpdate("DROP TABLE IF EXISTS promotions;");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS promotions ("
                    + "promotion_id SERIAL PRIMARY KEY,"
                    + "promotion_name VARCHAR(255) NOT NULL,"
                    + "promotion_year INTEGER NOT NULL"
                    + ");");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS students ("
                    + "student_id SERIAL PRIMARY KEY,"
                    + "first_name VARCHAR(255) NOT NULL,"
                    + "last_name VARCHAR(255) NOT NULL,"
                    + "email VARCHAR(255) UNIQUE NOT NULL,"
                    + "phone_number VARCHAR(255),"
                    + "absence_count INTEGER DEFAULT 0,"
                    + "is_delegate BOOLEAN DEFAULT false,"
                    + "promotion_id INTEGER NOT NULL,"
                    + "UNIQUE(first_name, last_name),"
                    + "FOREIGN KEY (promotion_id) REFERENCES promotions(promotion_id)"
                    + ");");

            System.out.println("Database tables created successfully.");
        } catch (SQLException e) {
            System.out.println("Error creating database tables: " + e.getMessage());
        }
    }
}

