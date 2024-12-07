package com.storemanagement.inventory;

import com.storemanagement.utils.Constants.Category;

public class Product
{
    int id;
    String name;
    Category category;
    double price;
    int quantity;

    public Product(int id, String name, Category category, double price, int quantity)
    {
        setId(id);
        setName(name);
        setCategory(category);
        setPrice(price);
        setQuantity(quantity);
    }

    // Overloaded Constructor without ID (for new products)
    public Product(String name, Category category, double price, int quantity)
    {
        setName(name);
        setCategory(category);
        setPrice(price);
        setQuantity(quantity);
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Category getCategory()
    {
        return category;
    }

    public void setCategory(Category category)
    {
        this.category = category;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    @Override
    public String toString()
    {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
