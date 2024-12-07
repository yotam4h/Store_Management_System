package com.storemanagement.branch;

import com.storemanagement.utils.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BranchService
{
    public boolean addBranch(Branch branch) {
        String query = "INSERT INTO Branches (branch_name, branch_address, branch_phone) VALUES (?, ?, ?)";
        try {
            DatabaseConnection dbConnection = DatabaseConnection.getInstance();
            PreparedStatement stmt = dbConnection.prepareStatementWithGeneratedKeys(query);

            stmt.setString(1, branch.getBranchName());
            stmt.setString(2, branch.getBranchAddress());
            stmt.setString(3, branch.getBranchPhone());

            int rowsAffected = stmt.executeUpdate();
            // if branch is added successfully, update the branch object with the new ID
            if (rowsAffected > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    branch.setBranchId(rs.getInt(1));
                }
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.out.println("Error adding branch: " + e.getMessage());
            return false;
        }
    }

    public boolean removeBranch(int branchId) {
        String query = "DELETE FROM Branches WHERE branch_id = ?";
        try {
            DatabaseConnection dbConnection = DatabaseConnection.getInstance();
            PreparedStatement stmt = dbConnection.prepareStatement(query);

            stmt.setInt(1, branchId);

            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Error removing branch: " + e.getMessage());
            return false;
        }
    }

    public List<Branch> listBranches() {
        String query = "SELECT * FROM Branches";
        List<Branch> branches = new ArrayList<>();

        try {
            DatabaseConnection dbConnection = DatabaseConnection.getInstance();
            Statement stmt = dbConnection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Branch branch = new Branch(
                        rs.getInt("branch_id"),
                        rs.getString("branch_name"),
                        rs.getString("branch_address"),
                        rs.getString("branch_phone")
                );
                branches.add(branch);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching branches: " + e.getMessage());
        }

        return branches;
    }

    public boolean updateBranch(Branch branch) {
        String query = "UPDATE Branches SET branch_name = ?, branch_address = ?, branch_phone = ? WHERE branch_id = ?";
        try {
            DatabaseConnection dbConnection = DatabaseConnection.getInstance();
            PreparedStatement stmt = dbConnection.prepareStatement(query);

            stmt.setString(1, branch.getBranchName());
            stmt.setString(2, branch.getBranchAddress());
            stmt.setString(3, branch.getBranchPhone());
            stmt.setInt(4, branch.getBranchId());

            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Error updating branch: " + e.getMessage());
            return false;
        }
    }

    public Branch getBranchById(int branchId) {
        String query = "SELECT * FROM Branches WHERE branch_id = ?";
        try {
            DatabaseConnection dbConnection = DatabaseConnection.getInstance();
            PreparedStatement stmt = dbConnection.prepareStatement(query);

            stmt.setInt(1, branchId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Branch(
                        rs.getInt("branch_id"),
                        rs.getString("branch_name"),
                        rs.getString("branch_address"),
                        rs.getString("branch_phone")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error fetching branch: " + e.getMessage());
        }
        return null;
    }
}
