/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelPackage;

/**
 *
 * @author salilam
 */
public class OrderItem {
    
    private MenuItem item;
    private int quantity;
    private double subtotal;
    
    public OrderItem(MenuItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
        this.subtotal = calculateSubtotal();
    }
    
    public MenuItem getItem() {
        return item;
    }
    
    public void setItem(MenuItem item) {
        this.item = item;
        this.subtotal = calculateSubtotal();
    }
    
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.subtotal = calculateSubtotal();
    }
    
    public double getSubtotal() {
        return subtotal;
    }
    
    public double calculateSubtotal() {
        return item.getPrice() * quantity;
    }
    
    @Override
    public String toString() {
        return item.getName() + " x " + quantity + " = $" + String.format("%.2f", subtotal);
    }
}
