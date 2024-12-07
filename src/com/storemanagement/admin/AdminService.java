package com.storemanagement.admin;

import com.storemanagement.branch.Branch;
import com.storemanagement.utils.DatabaseConnection;
import com.storemanagement.utils.Constants.EmployeeRole;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminService {

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

    public List<Employee> listEmployees() {

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

    public boolean updateEmployee(Employee employee) {
        String query = "UPDATE Employees SET full_name = ?, phone_number = ?, role = ?, branch_id = ? WHERE id = ?";
        try {
            DatabaseConnection dbConnection = DatabaseConnection.getInstance();
            PreparedStatement stmt = dbConnection.prepareStatement(query);

            stmt.setString(1, employee.getFullName());
            stmt.setString(2, employee.getPhoneNumber());
            stmt.setString(3, employee.getRole().toString());
            stmt.setInt(4, employee.getBranchId());
            stmt.setInt(5, employee.getId());

            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Error updating employee: " + e.getMessage());
            return false;
        }
    }

    public Employee getEmployeeById(int employeeId) {
        String query = "SELECT * FROM Employees WHERE id = ?";
        try {
            DatabaseConnection dbConnection = DatabaseConnection.getInstance();
            PreparedStatement stmt = dbConnection.prepareStatement(query);

            stmt.setInt(1, employeeId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Employee(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getString("phone_number"),
                        EmployeeRole.valueOf(rs.getString("role")),
                        rs.getInt("branch_id")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error fetching employee: " + e.getMessage());
        }
        return null;
    }
}
