package com.storemanagement.admin;

import com.storemanagement.utils.Constants.EmployeeRole;

public class Employee
{
    int id;
    String firstName;
    String lastName;
    EmployeeRole employeeRole;
    int branchId;

    public Employee(int id, String firstName, String lastName, EmployeeRole employeeRole, int branchId)
    {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setRole(employeeRole);
        setBranchId(branchId);
    }

    public int getId()
    {
        return id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public EmployeeRole getRole()
    {
        return employeeRole;
    }

    public int getBranchId()
    {
        return branchId;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
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
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", employeeRole=" + employeeRole +
                ", branchId=" + branchId +
                '}';
    }

}
