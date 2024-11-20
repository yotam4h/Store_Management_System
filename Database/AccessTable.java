import java.sql.*;

public class AccessTableExample {
    public static void main(String[] args) {
        // Database connection details
        String url = "jdbc:mysql://ifa3z.h.filess.io:3307/StoreManagementSystem_statement";
        String user = "StoreManagementSystem_statement";
        String password = "9d05a4dffc9ff707e2c31864cb098c6eb23782a0";

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
