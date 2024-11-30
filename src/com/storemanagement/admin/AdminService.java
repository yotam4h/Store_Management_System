package com.storemanagement.admin;

import com.storemanagement.utils.DatabaseConnection;
import com.storemanagement.utils.Constants.EmployeeRole;
import com.storemanagement.models.Employee;

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
}
