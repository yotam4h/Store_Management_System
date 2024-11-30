package com.storemanagement.admin;

import com.storemanagement.utils.DatabaseConnection;
import com.storemanagement.utils.Constants.EmployeeRole;
import com.storemanagement.models.*;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminService {

    // Method to add a new employee
    public boolean addEmployee(Employee employee) {

        String query = "INSERT INTO Employees (full_name, phone_number, role, branch_id) VALUES (?, ?, ?, ?)";
        try {
            DatabaseConnection dbConnection = DatabaseConnection.getInstance();
            PreparedStatement stmt = dbConnection.prepareStatementWithGeneratedKeys(query);

            stmt.setString(1, employee.getFullName());
            stmt.setString(2, employee.getPhoneNumber());
            stmt.setString(3, employee.getRole().toString());
            stmt.setInt(4, employee.getBranchId());

            int rowsAffected = stmt.executeUpdate();
            // if employee is added successfully, update the employee object with the new ID
             if (rowsAffected > 0) {
                 ResultSet rs = stmt.getGeneratedKeys();
                 if (rs.next()) {
                     employee.setId(rs.getInt(1));
                 }
                 return true;
             }
            return false;
        } catch (SQLException e) {
            System.out.println("Error adding employee: " + e.getMessage());
            return false;
        }
    }

    // Method to remove an employee by ID
    public boolean removeEmployee(int employeeId) {

        String query = "DELETE FROM Employees WHERE id = ?";
        try {
            DatabaseConnection dbConnection = DatabaseConnection.getInstance();
            PreparedStatement stmt = dbConnection.prepareStatement(query);

            stmt.setInt(1, employeeId);

            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Error removing employee: " + e.getMessage());
            return false;
        }
    }

    // Method to list all employees
    public List<Employee> listEmployees() {
        /*
        String query = "SELECT * FROM Employees";
        List<Employee> employees = new ArrayList<>();

        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Employee employee = new Employee(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getString("phone_number"),
                        EmployeeRole.valueOf(rs.getString("role")),
                        rs.getInt("branch_id")
                );
                employees.add(employee);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching employees: " + e.getMessage());
        }

        return employees;
        */

        String query = "SELECT * FROM Employees";
        List<Employee> employees = new ArrayList<>();

        try {
            DatabaseConnection dbConnection = DatabaseConnection.getInstance();
            Statement stmt = dbConnection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Employee employee = new Employee(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getString("phone_number"),
                        EmployeeRole.valueOf(rs.getString("role")),
                        rs.getInt("branch_id")
                );
                employees.add(employee);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching employees: " + e.getMessage());
        }

        return employees;
    }

    // Method to add a new branch
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

    // Method to remove a branch by ID
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

    // Method to list all branches
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
}
