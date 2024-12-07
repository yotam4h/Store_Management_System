package com.storemanagement.auth;

import com.storemanagement.utils.Constants.EmployeeRole;

public class User {
    private int id;
    private String username;
    private String passwordHash;
    private EmployeeRole role;
    private int branchId;

    // Constructor
    public User(int id, String username, String passwordHash, EmployeeRole role, int branchId) {
        setId(id);
        setUsername(username);
        setPasswordHash(passwordHash);
        setRole(role);
        setBranchId(branchId);
    }

    // Overloaded Constructor (for new users without ID)
    public User(String username, String passwordHash, EmployeeRole role, int branchId) {
        setUsername(username);
        setPasswordHash(passwordHash);
        setRole(role);
        setBranchId(branchId);
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public EmployeeRole getRole() {
        return role;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setRole(EmployeeRole role) {
        this.role = role;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }
}
