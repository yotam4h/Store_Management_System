package com.storemanagement.admin;

import com.storemanagement.utils.Constants.EmployeeRole;

public class Employee {
    private int id;
    private String fullName;
    private String phoneNumber;
    private EmployeeRole role;
    private int branchId;

    // Parameterized Constructor
    public Employee(int id, String fullName, String phoneNumber, EmployeeRole role, int branchId) {
        setId(id);
        setFullName(fullName);
        setPhoneNumber(phoneNumber);
        setRole(role);
        setBranchId(branchId);
    }

    // Overloaded Constructor without ID (for new employees)
    public Employee(String fullName, String phoneNumber, EmployeeRole role, int branchId) {
        setFullName(fullName);
        setPhoneNumber(phoneNumber);
        setRole(role);
        setBranchId(branchId);
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public EmployeeRole getRole() {
        return role;
    }

    public int getBranchId() {
        return branchId;
    }

    // Setters (optional, for future updates)
    public void setId(int id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setRole(EmployeeRole role) {
        this.role = role;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    @Override
    public String toString()
    {
        return "Employee{" +
                "id=" + id +
                ", fullName='" + getFullName() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", employeeRole=" + getRole() +
                ", branchId=" + getBranchId() +
                '}';
    }

}
