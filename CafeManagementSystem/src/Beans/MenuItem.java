/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Beans;

/**
 *
 * @author salilam
 */
public class MenuItem {
    private String name;
    private double price;
    private int stock;
    
    public MenuItem(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }
    
     /**
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }
    
    public void displayMenuItem() {
        System.out.println("Item: " + name);
        System.out.println("Price: $" + price);
        System.out.println("Stock: " + stock);
    }
    
     public void reduceStock(int quantity) {
        if (stock >= quantity) {
            stock -= quantity;
            System.out.println(quantity + " " + name + "(s) ordered.");
        } else {
            System.out.println("Insufficient stock for " + name + ".");
        }
    }

    public void restock(int quantity) {
        stock += quantity;
        System.out.println("Restocked " + quantity + " " + name + "(s).");
    }

}
