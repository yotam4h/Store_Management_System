package com.storemanagement.customer;

import java.sql.Timestamp;

public class PurchaseHistory {
    private String productName;
    private int quantity;
    private Timestamp saleDate;
    private double totalPrice;

    // Constructor
    public PurchaseHistory(String productName, int quantity, Timestamp saleDate, double totalPrice) {
        this.productName = productName;
        this.quantity = quantity;
        this.saleDate = saleDate;
        this.totalPrice = totalPrice;
    }

    // Getters and Setters
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Timestamp getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Timestamp saleDate) {
        this.saleDate = saleDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "PurchaseHistory{" +
                "productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", saleDate=" + saleDate +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
