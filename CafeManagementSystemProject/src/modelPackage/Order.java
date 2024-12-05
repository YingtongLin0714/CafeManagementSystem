/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelPackage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author salilam
 */
public class Order {
    private int orderId;
    private Date orderDate;
    private double totalAmount;
    private List<OrderItem> orderItems;
    
    public Order(int orderId) {
        this.orderId = orderId;
        this.orderDate = new Date();
        this.orderItems = new ArrayList<>();
        this.totalAmount = 0.0;
    }
    
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    
    public Date getOrderDate() {
        return orderDate;
    }
    
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    
    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
    
    public void addItem(MenuItem item, int quantity) {
        OrderItem orderItem = new OrderItem(item, quantity);
        orderItems.add(orderItem);
        totalAmount += orderItem.calculateSubtotal();
    }
    
    public void removeItem(MenuItem item) {
        for (int i = 0; i < orderItems.size(); i++) {
            if (orderItems.get(i).getItem().equals(item)) {
                totalAmount -= orderItems.get(i).calculateSubtotal();
                orderItems.remove(i);
                break;
            }
        }
    }
    
    public void calculateTotal() {
        totalAmount = 0.0;
        for (OrderItem orderItem : orderItems) {
            totalAmount += orderItem.calculateSubtotal();
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order ID: ").append(orderId).append("\n");
        sb.append("Order Date: ").append(getOrderDate()).append("\n");
        sb.append("Order Items:\n");
        for (OrderItem orderItem : orderItems) {
            sb.append(" - ").append(orderItem).append("\n");
        }
        sb.append("Total Amount: $").append(totalAmount).append("\n");
        return sb.toString();
    }

}
