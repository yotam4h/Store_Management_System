package com.storemanagement.customer;

import com.storemanagement.sales.Sale;
import com.storemanagement.utils.Constants.CustomerType;

import java.util.List;

public class Customer
{
    int id;
    String fullName;
    String phoneNumber;
    CustomerType type;
    List<Sale> purchaseHistory;

    public Customer(int id, String fullName, String phoneNumber, CustomerType type, List<Sale> purchaseHistory)
    {
        setId(id);
        setFullName(fullName);
        setPhoneNumber(phoneNumber);
        setType(type);
        setPurchaseHistory(purchaseHistory);
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getFullName()
    {
        return fullName;
    }

    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public CustomerType getType()
    {
        return type;
    }

    public void setType(CustomerType type)
    {
        this.type = type;
    }

    public List<Sale> getPurchaseHistory()
    {
        return purchaseHistory;
    }

    public void setPurchaseHistory(List<Sale> purchaseHistory)
    {
        this.purchaseHistory = purchaseHistory;
    }

    public void addSale(Sale sale)
    {
        purchaseHistory.add(sale);
    }

    public void removeSale(Sale sale)
    {
        purchaseHistory.remove(sale);
    }

    public void updateSale(Sale sale)
    {
        // Update sale in purchase history
    }

    public void printPurchaseHistory()
    {
        for (Sale sale : purchaseHistory)
        {
            System.out.println(sale);
        }
    }

    @Override
    public String toString()
    {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + fullName + '\'' +
                ", type=" + type +
                ", purchaseHistory=" + purchaseHistory +
                '}';
    }
}