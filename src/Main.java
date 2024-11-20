import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Main
{
    public static void main(String[] args)
    {
        // Database connection details
        String url = "jdbc:mysql://ifa3z.h.filess.io:3307/StoreManagementSystem_statement";
        String user = "StoreManagementSystem_statement";
        String password = "9d05a4dffc9ff707e2c31864cb098c6eb23782a0";

        // SQL query to create the table
        String createTableSQL = "CREATE TABLE users ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "name VARCHAR(50), "
                + "email VARCHAR(100) UNIQUE, "
                + "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";

        // Try-with-resources to auto-close resources
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {

            // Execute the query
            stmt.executeUpdate(createTableSQL);
            System.out.println("Table 'users' created successfully!");

        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }
}