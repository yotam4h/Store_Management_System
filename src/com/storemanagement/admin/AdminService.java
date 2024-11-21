package com.storemanagement.admin;

import com.storemanagement.utils.DatabaseConnection;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class AdminService
{
    // TODO: Implement this method
    public void addEmployee(Employee employee)
    {
        // Add employee to database
        String query = "INSERT INTO employees (id, firstName, lastName, role, branchId) VALUES ('" +employee.getId() + "', '" + employee.getFirstName() + "', '" + employee.getLastName() + "', '" + employee.getRole() + "', " + employee.getBranchId() + ")";
        try {
            DatabaseConnection dbConnection = DatabaseConnection.getInstance();
            Connection connection = dbConnection.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
        }
    }

    // TODO: Implement this method
    public void removeEmployee(int employeeId)
    {
        // Remove employee from database
        String query = "DELETE FROM employees WHERE id = " + employeeId;
        try {
            DatabaseConnection dbConnection = DatabaseConnection.getInstance();
            Connection connection = dbConnection.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
        }
    }

    // TODO: Implement this method
    List<Employee> getEmployees()
    {
        List<Employee> employees = new ArrayList<>();
        // Get all employees from database
        String query = "SELECT * FROM employees";
        try {
            DatabaseConnection dbConnection = DatabaseConnection.getInstance();
            Connection connection = dbConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String role = resultSet.getString("role");
                int branchId = resultSet.getInt("branchId");
                Employee employee = new Employee(id, firstName, lastName, role, branchId);
                employees.add(employee);
            }
        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
        }

        return employees;
    }
}
