package com.storemanagement.auth;

import com.storemanagement.utils.Constants.EmployeeRole;

public class User
{
    String username;
    String password;
    EmployeeRole employeeRole;
    int branchId;

    public User(String username, String password, EmployeeRole employeeRole, int branchId)
    {
        setUsername(username);
        setPassword(password);
        setRole(employeeRole);
        setBranchId(branchId);
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public EmployeeRole getRole()
    {
        return employeeRole;
    }

    public int getBranchId()
    {
        return branchId;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setRole(EmployeeRole employeeRole)
    {
        this.employeeRole = employeeRole;
    }

    public void setBranchId(int branchId)
    {
        this.branchId = branchId;
    }

    @Override
    public String toString()
    {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", employeeRole=" + employeeRole +
                ", branchId=" + branchId +
                '}';
    }
}
