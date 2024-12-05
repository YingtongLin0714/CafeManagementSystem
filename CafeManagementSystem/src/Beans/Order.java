/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Beans;

import java.util.HashMap;
/**
 *
 * @author salilam
 */
public class Order {
    private HashMap<MenuItem, Integer> items;
    private double totalPrice;
    private String status;
    
    public Order() {
        items = new HashMap<>();
        totalPrice = 0.0;
        status = "Pending";
    }
    
    public void addItem(MenuItem item, int quantity) {
        if (item.getStock() >= quantity) {
            items.put(item, quantity);
            totalPrice += item.getPrice() * quantity;
            item.reduceStock(quantity);
        } else {
            System.out.println("Not enough stock for " + item.getName());
        }
    }

    public void displayOrder() {
        System.out.println("Order Status: " + status);
        System.out.println("Items in the order:");
        for (MenuItem item : items.keySet()) {
            System.out.println(item.getName() + " (x" + items.get(item) + ")");
        }
        System.out.println("Total Price: $" + totalPrice);
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
