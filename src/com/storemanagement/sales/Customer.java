package com.storemanagement.sales;

import com.storemanagement.utils.Constants.CustomerType;

import java.util.List;

public class Customer
{
    int id;
    String firstName;
    String lastName;
    CustomerType type;
    List<Sale> purchaseHistory;

    public Customer(int id, String firstName, String lastName, CustomerType type, List<Sale> purchaseHistory)
    {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
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

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
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
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", type=" + type +
                ", purchaseHistory=" + purchaseHistory +
                '}';
    }
}
