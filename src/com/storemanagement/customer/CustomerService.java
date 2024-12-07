package com.storemanagement.customer;

import com.storemanagement.utils.DatabaseConnection;
import com.storemanagement.utils.Constants.CustomerType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerService
{
    public boolean addCustomer(Customer customer) {
        String query = "INSERT INTO Customers (full_name, phone_number, customer_type) VALUES (?, ?, ?)";
        try {
            DatabaseConnection dbConnection = DatabaseConnection.getInstance();
            PreparedStatement stmt = dbConnection.prepareStatementWithGeneratedKeys(query);

            stmt.setString(1,customer.getFullName());
            stmt.setString(2,customer.getPhoneNumber());
            stmt.setString(3,customer.getType().toString());

            int rowsAffected = stmt.executeUpdate();
            // if customer is added successfully, update the customer object with the new ID
            if (rowsAffected > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    customer.setId(rs.getInt(1));
                }
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("Error adding customer: " + e.getMessage());
            return false;
        }
    }

    public boolean removeCustomer(int customerId) {
        String query = "DELETE FROM Customers WHERE id = ?";
        try {
            DatabaseConnection dbConnection = DatabaseConnection.getInstance();
            PreparedStatement stmt = dbConnection.prepareStatement(query);

            stmt.setInt(1, customerId);

            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Error removing customer: " + e.getMessage());
            return false;
        }
    }

    public List<Customer> listCustomers() {
        String query = "SELECT * FROM Customers";
        List<Customer> customers = new ArrayList<>();
        try {
            DatabaseConnection dbConnection = DatabaseConnection.getInstance();
            Statement stmt = dbConnection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getString("phone_number"),
                        CustomerType.valueOf(rs.getString("customer_type")),
                        null
                );
                customers.add(customer);
            }
        } catch (SQLException e) {
            System.out.println("Error listing customers: " + e.getMessage());
        }
        return customers;
    }

    public boolean updateCustomer(Customer customer) {
        String query = "UPDATE Customers SET full_name = ?, phone_number = ?, customer_type = ? WHERE id = ?";
        try {
            DatabaseConnection dbConnection = DatabaseConnection.getInstance();
            PreparedStatement stmt = dbConnection.prepareStatement(query);

            stmt.setString(1, customer.getFullName());
            stmt.setString(2, customer.getPhoneNumber());
            stmt.setString(3, customer.getType().toString());
            stmt.setInt(4, customer.getId());

            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Error updating customer: " + e.getMessage());
            return false;
        }
    }

    public Customer getCustomerById(int customerId) {
        String query = "SELECT * FROM Customers WHERE id = ?";
        try {
            DatabaseConnection dbConnection = DatabaseConnection.getInstance();
            PreparedStatement stmt = dbConnection.prepareStatement(query);

            stmt.setInt(1, customerId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Customer(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getString("phone_number"),
                        CustomerType.valueOf(rs.getString("customer_type")),
                        null
                );
            }
        } catch (SQLException e) {
            System.out.println("Error fetching customer: " + e.getMessage());
        }
        return null;
    }
}
