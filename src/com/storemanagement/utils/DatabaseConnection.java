package com.storemanagement.utils;

import java.sql.*;
import io.github.cdimascio.dotenv.Dotenv;


public class DatabaseConnection {
    // Singleton instance
    private static DatabaseConnection instance;
    private Connection connection;

    // Load environment variables
    private final Dotenv dotenv = Dotenv.configure().directory("./").load();

    // Database credentials
    private final String URL = dotenv.get("DB_URL");
    private final String USER = dotenv.get("DB_USER");
    private final String PASSWORD = dotenv.get("DB_PASSWORD");

    // Private constructor to prevent instantiation
    private DatabaseConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
            throw new RuntimeException("Database connection failed");
        }
    }

    // Public method to provide the Singleton instance
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }

    // Method to get the database connection
    public Connection getConnection() {
        return connection;
    }

    // Close the connection (optional)
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.out.println("Error closing database connection: " + e.getMessage());
        }
    }


    // Execute a query
    public void executeQuery(String query) {
    try (Statement stmt = connection.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {

        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();

        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(",  ");
                String columnValue = rs.getString(i);
                System.out.print(rsmd.getColumnName(i) + ": " + columnValue);
            }
            System.out.println();
        }

        System.out.println("Query executed successfully!");
        } catch (SQLException e) {
        System.out.println("Error executing query: " + e.getMessage());
        }
    }

    public void executeFile(String path) {
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("RUNSCRIPT FROM '" + path + "'");
            System.out.println("File executed successfully!");
        } catch (SQLException e) {
            System.out.println("Error executing file: " + e.getMessage());
        }
    }

    public void createTables() {
        executeFile("src/com/storemanagement/utils/create_tables.sql");
    }

    // prepare statement
    public PreparedStatement prepareStatement(String query) throws SQLException {
        try {
            return connection.prepareStatement(query);
        } catch (SQLException e) {
            throw e;
        }
    }

    // prepare statement with generated keys
    public PreparedStatement prepareStatementWithGeneratedKeys(String query) throws SQLException {
        try {
            return connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            throw e;
        }
    }

    // Create statement
    public Statement createStatement() throws SQLException {
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            throw e;
        }
    }




}
