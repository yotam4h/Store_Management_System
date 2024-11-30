package com.storemanagement.admin;

import com.storemanagement.models.Employee;
import com.storemanagement.utils.DatabaseConnection;
import com.storemanagement.utils.Constants.EmployeeRole;

import org.junit.jupiter.api.*;
import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AdminServiceTest {

    private final AdminService adminService = new AdminService();

    @BeforeEach
    void cleanDatabase() {
        try {
            DatabaseConnection dbConnection = DatabaseConnection.getInstance();
            Connection connection = dbConnection.getConnection();
            Statement stmt = connection.createStatement();

            // Clear only the Employees table for testing purposes
            stmt.execute("DELETE FROM Employees");

        } catch (SQLException e) {
            System.out.println("Error cleaning database: " + e.getMessage());
        }

    }

    @Test
    void testAddEmployee() {
        Employee employee = new Employee("John Doe", "123-4567", EmployeeRole.MANAGER, 1);
        boolean isAdded = adminService.addEmployee(employee);

        assertTrue(isAdded, "Employee should be added successfully");

        // Verify in the database
        try {
            DatabaseConnection dbConnection = DatabaseConnection.getInstance();
            Statement stmt = dbConnection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Employees WHERE full_name = 'John Doe'");

            assertTrue(rs.next(), "Employee should exist in the database");
            assertEquals(employee.getFullName(), rs.getString("full_name"), "Employee name should match");
            assertEquals(employee.getId(), rs.getInt("id"), "Employee ID should match");

        } catch (SQLException e) {
            fail("Error verifying database: " + e.getMessage());
        }
    }

    @Test
    void testListEmployees() {
        // Add sample employees
        adminService.addEmployee(new Employee("Alice Johnson", "555-1234", EmployeeRole.ADMIN, 1));
        adminService.addEmployee(new Employee("Bob Smith", "555-5678", EmployeeRole.EMPLOYEE, 1));

        // Fetch employees
        List<Employee> employees = adminService.listEmployees();

        assertEquals(2, employees.size(), "There should be 2 employees in the database");
        assertEquals("Alice Johnson", employees.get(0).getFullName(), "First employee's name should match");
        assertEquals("Bob Smith", employees.get(1).getFullName(), "Second employee's name should match");
    }

    @Test
    void testRemoveEmployee() {
        // Add an employee to remove

        boolean isAdded = adminService.addEmployee(new Employee("Charlie Brown", "555-9999", EmployeeRole.MANAGER, 1));
        assertTrue(isAdded, "Employee should be added successfully");

        // Fetch the employee ID from the database
        int employeeId = adminService.listEmployees().get(0).getId();
        System.out.println("Employee ID: " + employeeId);

        // Remove the employee
        boolean isRemoved = adminService.removeEmployee(employeeId);

        assertTrue(isRemoved, "Employee should be removed successfully");

        // Verify the employee no longer exists
        try {
            DatabaseConnection dbConnection = DatabaseConnection.getInstance();
            Statement stmt = dbConnection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Employees WHERE id = " + employeeId);

            assertFalse(rs.next(), "Employee should not exist in the database");

        } catch (SQLException e) {
            fail("Error verifying database: " + e.getMessage());
        }
    }
}
