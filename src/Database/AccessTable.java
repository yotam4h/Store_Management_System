package Database;

import java.sql.*;
import io.github.cdimascio.dotenv.*;

public class AccessTable {
    public static void main(String[] args) {
        // Load the .env file
        Dotenv dotenv = Dotenv.configure().load();

        // Database connection details
        String url = dotenv.get("DB_URL");
        String user = dotenv.get("DB_USER");
        String password = dotenv.get("DB_PASSWORD");

        String selectSQL = "SELECT * FROM users";

        try {
            // Establish connection to the database
            try (Connection conn = DriverManager.getConnection(url, user, password);
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(selectSQL)) {

                // Process the result set
                while (rs.next()) {
                    // Retrieve data from the result set
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    Timestamp createdAt = rs.getTimestamp("created_at");

                    // Display the results
                    System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email + ", Created At: " + createdAt);
                }

            } // No need for another catch here, SQLException will be caught by the outer catch block
        } catch (SQLException e) {
            System.out.println("Error connecting to the database or executing query: " + e.getMessage());
        }
    }
}
