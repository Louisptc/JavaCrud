package AbsenceManager.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import AbsenceManager.Utils.DbUtil;

public class DatabaseConnection {
    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String user = "postgres";
    private final String password = "root";

    public Connection connect() {
        Connection conn = null; 
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");

            //test connection

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT version()");
            if (rs.next()) {
                System.out.println("PostgreSQL version: " + rs.getString(1));
            }

            // Execute SQL file for database initialization
            String sqlFilePath = "src/Resources/db/init.sql";
            DbUtil.executeSqlFile(conn, sqlFilePath);
            System.out.println("Database initialization completed successfully.");
            

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
