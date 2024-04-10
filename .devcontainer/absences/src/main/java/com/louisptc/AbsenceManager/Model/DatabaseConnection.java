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
    
            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS promotions (" +
                "promotion_id INTEGER PRIMARY KEY," +
                "promotion_name TEXT NOT NULL," +
                "promotion_year INTEGER NOT NULL);"
            );
    
            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS students (" +
                "student_id INTEGER PRIMARY KEY," +
                "first_name TEXT NOT NULL," +
                "last_name TEXT NOT NULL," +
                "email TEXT UNIQUE NOT NULL," +
                "phone_number TEXT," +
                "absence_count INTEGER DEFAULT 0," +
                "is_delegate INTEGER NOT NULL DEFAULT 0," +
                "promotion_id INTEGER NOT NULL," +
                "UNIQUE(first_name, last_name)," +
                "FOREIGN KEY (promotion_id) REFERENCES promotions(promotion_id));"
            );
    
            // Insert example promotions
            stmt.executeUpdate(
                "INSERT INTO promotions (promotion_name, promotion_year) VALUES " +
                "('Computer Science', 2021)," +
                "('Mathematics', 2022);"
            );
    
            // Insert example students
            stmt.executeUpdate(
                "INSERT INTO students (first_name, last_name, email, phone_number, absence_count, is_delegate, promotion_id) VALUES " +
                "('John', 'Doe', 'john.doe@example.com', '555-1234', 0, 0, (SELECT promotion_id FROM promotions WHERE promotion_name = 'Computer Science'))," +
                "('Jane', 'Smith', 'jane.smith@example.com', '555-5678', 0, 0, (SELECT promotion_id FROM promotions WHERE promotion_name = 'Mathematics'));"
            );
    
            System.out.println("Database tables and example data created successfully.");
        } catch (SQLException e) {
            System.out.println("Error creating database tables or inserting example data: " + e.getMessage());
        }
    }
    
}

