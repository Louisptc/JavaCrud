package com.louisptc.AbsenceManager.Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Statement;

public class DbUtil {

    public static void executeSqlFile(Connection conn, String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            StringBuilder sqlStatement = new StringBuilder();
            
            while ((line = reader.readLine()) != null) {
                // Ignore comments and empty lines
                if(line.trim().isEmpty() || line.trim().startsWith("--")) {
                    continue;
                }
                sqlStatement.append(line);
                // When a semicolon is found, execute the statement and reset the StringBuilder
                if (line.endsWith(";")) {
                    try (Statement stmt = conn.createStatement()) {
                        stmt.execute(sqlStatement.toString());
                        sqlStatement = new StringBuilder(); // Reset the SQL statement
                    } catch (Exception e) {
                        System.out.println("Failed to execute SQL statement: " + e.getMessage());
                    }
                }
            }
            reader.close();
            System.out.println("SQL script executed successfully.");
        } catch (Exception e) {
            System.out.println("Failed to execute SQL script: " + e.getMessage());
        }
    }
}
