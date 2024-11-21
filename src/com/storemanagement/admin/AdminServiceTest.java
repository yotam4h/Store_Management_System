package com.storemanagement.admin;

import com.storemanagement.utils.Constants;
import com.storemanagement.utils.DatabaseConnection;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.sql.Connection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdminServiceTest
{
    AdminService adminService = new AdminService();
    Employee employee = new Employee(1, "John", "Doe", Constants.EmployeeRole.ADMIN.toString(), 1);

    @BeforeAll
    static void setUp()
    {
        // Get the database connection
        DatabaseConnection dbConnection = DatabaseConnection.getInstance();
        Connection connection = dbConnection.getConnection();
    }

    @AfterAll
    static void tearDown()
    {
        // Close the database connection
        DatabaseConnection dbConnection = DatabaseConnection.getInstance();
        dbConnection.closeConnection();
    }

    @org.junit.jupiter.api.Test
    void addEmployee()
    {
        adminService.addEmployee(employee);
    }

    @org.junit.jupiter.api.Test
    void removeEmployee()
    {
        adminService.removeEmployee(employee.getId());
    }

    @org.junit.jupiter.api.Test
    void getEmployees()
    {
        List<Employee> employees = adminService.getEmployees();
        assertNotNull(employees);
        System.out.println("ID\tFirstName\tLastName\tRole\tBranchID");
        while(employees.iterator().hasNext())
        {
            Employee employee = employees.iterator().next();
            assertNotNull(employee);
            System.out.println(employee.getId() + "\t" + employee.getFirstName() + "\t\t" + employee.getLastName() + "\t\t\t" + employee.getRole() + "\t" + employee.getBranchId());
            employees.remove(employee);
        }
    }
}