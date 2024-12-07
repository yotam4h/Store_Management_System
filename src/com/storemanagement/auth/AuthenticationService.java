package com.storemanagement.auth;

import com.storemanagement.utils.DatabaseConnection;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.HashMap;
import java.util.UUID;

public class AuthenticationService {

    // In-memory store for active sessions (for simplicity)
    private static final HashMap<Integer, String> activeSessions = new HashMap<>();

    public boolean login(String username, String password) {
        String query = "SELECT id, password_hash FROM Users WHERE username = ?";

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("id");
                String storedHash = rs.getString("password_hash");

                // Validate password hash
                if (validatePassword(password, storedHash)) {
                    // If authentication is successful, create a session token
                    String sessionToken = generateSessionToken(userId);
                    activeSessions.put(userId, sessionToken);
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error during login: " + e.getMessage());
        }
        return false; // Authentication failed
    }

    public boolean logout(int userId) {
        if (activeSessions.containsKey(userId)) {
            activeSessions.remove(userId);
            return true;
        }
        return false;
    }

    public boolean validateSession(String sessionToken) {
        return activeSessions.containsValue(sessionToken);
    }

    public boolean isUserLoggedIn(int userId) {
        return activeSessions.containsKey(userId);
    }

    private String generateSessionToken(int userId) {
        // Generate a random UUID for the session token
        String sessionToken = UUID.randomUUID().toString();
        activeSessions.put(userId, sessionToken);
        return sessionToken;
    }

    private boolean validatePassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
