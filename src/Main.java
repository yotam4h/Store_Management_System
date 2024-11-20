import java.sql.*;


public class Main
{
    public static void main(String[] args)
    {
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


//        try (Connection conn = DriverManager.getConnection(url, user, password)) {
//            System.out.println("Connected to the database!");
//
//            // Use PreparedStatement for the insert operation
//            String insertSQL = "INSERT INTO users (name, email) VALUES (?, ?)";
//            String selectSQL = "SELECT * FROM users";
//            try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
//                pstmt.setString(1, "John Doe");
//                pstmt.setString(2, "john.doe@example.com");
//                pstmt.executeUpdate();
//                System.out.println("Data inserted successfully!");
//            } catch (SQLException e) {
//                System.out.println("Error during insert operation: " + e.getMessage());
//            }
//
//        } catch (SQLException e) {
//            System.out.println("Error connecting to the database: " + e.getMessage());
//        }



    }

//    public static void createTable(Connection conn) {
//        // SQL query to create the table
//        String createTableSQL = "CREATE TABLE users ("
//                + "id INT AUTO_INCREMENT PRIMARY KEY, "
//                + "name VARCHAR(50), "
//                + "email VARCHAR(100) UNIQUE, "
//                + "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
//
//        // Try-with-resources to auto-close resources
//        try (Connection conn = DriverManager.getConnection(url, user, password);
//             Statement stmt = conn.createStatement()) {
//
//            // Execute the query
//            stmt.executeUpdate(createTableSQL);
//            System.out.println("Table 'users' created successfully!");
//
//        } catch (SQLException e) {
//            System.out.println("Error creating table: " + e.getMessage());
//        }
//    }
}