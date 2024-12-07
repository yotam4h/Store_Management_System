package com.storemanagement.auth;

import com.storemanagement.utils.DatabaseConnection;
import com.storemanagement.utils.Constants.EmployeeRole;

import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class UserService
{
    public boolean addUser(User user)
    {
        String query = "INSERT INTO Users (username, password_hash, role, branch_id) VALUES (?, ?, ?, ?)";
        try {
            DatabaseConnection dbConnection = DatabaseConnection.getInstance();
            PreparedStatement stmt = dbConnection.prepareStatementWithGeneratedKeys(query);

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPasswordHash());
            stmt.setString(3, user.getRole().toString());
            stmt.setInt(4, user.getBranchId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    user.setId(rs.getInt(1));
                }
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.out.println("Error adding user: " + e.getMessage());
            return false;
        }
    }

    public boolean updateUser(User user)
    {
        String query = "UPDATE Users SET username = ?, password_hash = ?, role = ?, branch_id = ? WHERE id = ?";
        try {
            DatabaseConnection dbConnection = DatabaseConnection.getInstance();
            PreparedStatement stmt = dbConnection.prepareStatement(query);

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPasswordHash());
            stmt.setString(3, user.getRole().toString());
            stmt.setInt(4, user.getBranchId());
            stmt.setInt(5, user.getId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error updating user: " + e.getMessage());
            return false;
        }
    }

    public boolean removeUser(int userId)
    {
        String query = "DELETE FROM Users WHERE id = ?";
        try {
            DatabaseConnection dbConnection = DatabaseConnection.getInstance();
            PreparedStatement stmt = dbConnection.prepareStatement(query);

            stmt.setInt(1, userId);

            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Error removing user: " + e.getMessage());
            return false;
        }
    }

    public User getUserById(int userId)
    {
        String query = "SELECT * FROM Users WHERE id = ?";
        try {
            DatabaseConnection dbConnection = DatabaseConnection.getInstance();
            PreparedStatement stmt = dbConnection.prepareStatement(query);

            stmt.setInt(1, userId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password_hash"),
                        EmployeeRole.valueOf(rs.getString("role")),
                        rs.getInt("branch_id")
                );
            }
            return null;
        } catch (SQLException e) {
            System.out.println("Error fetching user: " + e.getMessage());
            return null;
        }
    }

    public List<User> listUsers()
    {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM Users";
        try {
            DatabaseConnection dbConnection = DatabaseConnection.getInstance();
            PreparedStatement stmt = dbConnection.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password_hash"),
                        EmployeeRole.valueOf(rs.getString("role")),
                        rs.getInt("branch_id")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching users: " + e.getMessage());
        }
        return users;
    }


}
