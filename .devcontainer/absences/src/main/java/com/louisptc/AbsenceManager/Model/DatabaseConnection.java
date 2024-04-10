package com.louisptc.AbsenceManager.Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {
    private final String url = "jdbc:sqlite" + System.getProperty("user.dir") + "/absences/absences.db";

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
        String sqlFilePath = "src/Resources/db/init.sql";
        try (BufferedReader reader = new BufferedReader(new FileReader(sqlFilePath))) {
            String line;
            StringBuilder sqlStatement = new StringBuilder();
            
            // SQLite connection object created earlier
            try (java.sql.Statement stmt = conn.createStatement()) {
                while ((line = reader.readLine()) != null) {
                    // Ignore comments and empty lines
                    if(line.trim().isEmpty() || line.trim().startsWith("--")) {
                        continue;
                    }
                    // Add the line to the current statement
                    sqlStatement.append(line);
                    // If the line ends with a semicolon, it's the end of the statement
                    if (line.endsWith(";")) {
                        stmt.execute(sqlStatement.toString());
                        sqlStatement.setLength(0); // Clear the StringBuilder for the next statement
                    }
                }
            }
            System.out.println("Database initialization completed successfully.");
        } catch (SQLException e) {
            System.out.println("Error executing database initialization script: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading SQL file: " + e.getMessage());
        }
    }
}

