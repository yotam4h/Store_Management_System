package com.storemanagement.chat;

import com.storemanagement.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChatService {

    // Method to start a new chat session
    public ChatSession startChat(int fromUserId, int toUserId) {
        String query = "INSERT INTO ChatSessions (from_user_id, to_user_id, start_time) VALUES (?, ?, ?)";
        Timestamp startTime = new Timestamp(System.currentTimeMillis());

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, fromUserId);
            stmt.setInt(2, toUserId);
            stmt.setTimestamp(3, startTime);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                // Retrieve the generated ID for the new chat session
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    int chatSessionId = rs.getInt(1);
                    return new ChatSession(fromUserId, toUserId, startTime); // Return the new ChatSession object
                }
            }
        } catch (SQLException e) {
            System.out.println("Error starting chat: " + e.getMessage());
        }
        return null;
    }

    // Method to send a message in a chat session
    public boolean sendMessage(int senderId, int chatSessionId, String messageText) {
        String query = "INSERT INTO ChatMessages (sender_id, chat_session_id, message_text, timestamp) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            stmt.setInt(1, senderId);
            stmt.setInt(2, chatSessionId);
            stmt.setString(3, messageText);
            stmt.setTimestamp(4, timestamp);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error sending message: " + e.getMessage());
        }
        return false;
    }

    // Method to get all messages for a chat session
    public List<ChatMessage> getChatHistory(int chatSessionId) {
        String query = "SELECT * FROM ChatMessages WHERE chat_session_id = ? ORDER BY timestamp ASC";
        List<ChatMessage> messages = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, chatSessionId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int messageId = rs.getInt("id");
                int senderId = rs.getInt("sender_id");
                String messageText = rs.getString("message_text");
                Timestamp timestamp = rs.getTimestamp("timestamp");

                messages.add(new ChatMessage(senderId, chatSessionId, messageText, timestamp));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving chat history: " + e.getMessage());
        }

        return messages;
    }

    // Method to end a chat session
    public boolean endChat(int chatSessionId) {
        String query = "UPDATE ChatSessions SET end_time = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            Timestamp endTime = new Timestamp(System.currentTimeMillis());
            stmt.setTimestamp(1, endTime);
            stmt.setInt(2, chatSessionId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error ending chat session: " + e.getMessage());
        }
        return false;
    }
}
