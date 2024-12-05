/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelPackage;

/**
 *
 * @author salilam
 */
public class Inventory {
    private int itemId;
    private String itemName;
    private int quantity;
    private int threshold;
    
    public Inventory(int itemId, String itemName, int quantity, int threshold) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.threshold = threshold;
    }
    
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
    
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }
    
    public void updateStock(int newQuantity) {
        this.quantity = newQuantity;
    }
    
    public boolean isLowStock() {
        return quantity < threshold;
    }
    
    @Override
    public String toString() {
        return "Inventory Item: " + itemName +
                " | Quantity: " + quantity +
                " | Threshold: " + threshold +
                (isLowStock() ? " (Low Stock!)" : "");
    }
}
