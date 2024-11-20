import java.sql.*;


public class CreateTable {

    public static void main(String[] args) {
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