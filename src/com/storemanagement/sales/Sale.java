package com.storemanagement.sales;

import java.time.LocalDateTime;

public class Sale
{
    int saleId;
    int customerId;
    int productId;
    int branchId;
    int employeeId;
    int quantity;
    double totalAmount;
    LocalDateTime saleDate;

    public Sale(int saleId, int customerId, int productId, int branchId, int employeeId, int quantity, double totalAmount, LocalDateTime saleDate)
    {
        setSaleId(saleId);
        setCustomerId(customerId);
        setProductId(productId);
        setBranchId(branchId);
        setEmployeeId(employeeId);
        setQuantity(quantity);
        setTotalAmount(totalAmount);
        setSaleDate(saleDate);
    }

    public int getSaleId()
    {
        return saleId;
    }

    public int getCustomerId()
    {
        return customerId;
    }

    public int getProductId()
    {
        return productId;
    }

    public int getBranchId()
    {
        return branchId;
    }

    public int getEmployeeId()
    {
        return employeeId;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public double getTotalAmount()
    {
        return totalAmount;
    }

    public LocalDateTime getSaleDate()
    {
        return saleDate;
    }

    public void setSaleId(int saleId)
    {
        this.saleId = saleId;
    }

    public void setCustomerId(int customerId)
    {
        this.customerId = customerId;
    }

    public void setProductId(int productId)
    {
        this.productId = productId;
    }

    public void setBranchId(int branchId)
    {
        this.branchId = branchId;
    }

    public void setEmployeeId(int employeeId)
    {
        this.employeeId = employeeId;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public void setTotalAmount(double totalAmount)
    {
        this.totalAmount = totalAmount;
    }

    public void setSaleDate(LocalDateTime saleDate)
    {
        this.saleDate = saleDate;
    }

    public String toString()
    {
        return "Sale{" +
                "saleId=" + saleId +
                ", customerId=" + customerId +
                ", productId=" + productId +
                ", branchId=" + branchId +
                ", employeeId=" + employeeId +
                ", quantity=" + quantity +
                ", totalAmount=" + totalAmount +
                ", saleDate=" + saleDate +
                '}';
    }
}
