import java.sql.*;


public class InsertTable {

    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to the database!");

            // Use PreparedStatement for the insert operation
            String insertSQL = "INSERT INTO users (name, email) VALUES (?, ?)";
            String selectSQL = "SELECT * FROM users";
            try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                pstmt.setString(1, "John Doe");
                pstmt.setString(2, "john.doe@example.com");
                pstmt.executeUpdate();
                System.out.println("Data inserted successfully!");
            } catch (SQLException e) {
                System.out.println("Error during insert operation: " + e.getMessage());
            }

        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }

    }

}