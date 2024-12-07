package com.storemanagement.customer;

import com.storemanagement.utils.Constants.CustomerType;

import java.util.List;

public class Customer {
    private int id;
    private String fullName;
    private String phoneNumber;
    private CustomerType customerType; // 'NEW', 'RETURNING', 'VIP'
    private List<PurchaseHistory> purchaseHistory; // New list of purchases

    // Constructor
    public Customer(int id, String fullName, String phoneNumber, CustomerType customerType, List<PurchaseHistory> purchaseHistory) {
        this.id = id;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.customerType = customerType;
        this.purchaseHistory = purchaseHistory; // Set the purchase history
    }

    // Overloaded Constructor without ID (for new customers)
    public Customer(String fullName, String phoneNumber, CustomerType customerType, List<PurchaseHistory> purchaseHistory) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.customerType = customerType;
        this.purchaseHistory = purchaseHistory; // Set the purchase history
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public CustomerType getType() {
        return customerType;
    }

    public void setType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public List<PurchaseHistory> getPurchaseHistory() {
        return purchaseHistory;
    }

    public void setPurchaseHistory(List<PurchaseHistory> purchaseHistory) {
        this.purchaseHistory = purchaseHistory;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", customerType='" + customerType + '\'' +
                ", purchaseHistory=" + purchaseHistory + // Include purchase history in the string representation
                '}';
    }
}
