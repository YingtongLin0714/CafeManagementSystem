/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicePackage;

import daoPackage.InventoryDao;
import modelPackage.Inventory;

import java.sql.Connection;
import java.util.List;

/**
 *
 * @author salilam
 */
public class InventoryService {
    private InventoryDao inventoryDao;
    
    public InventoryService(Connection connection) {
        this.inventoryDao = new InventoryDao(connection);
    }
    
    public List<Inventory> getAllInventoryItems() {
        try {
            return inventoryDao.getAllInventoryItems();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to retrieve inventory items");
        }
    }
    
    public void restockItem(int itemId, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Restock quantity must be greater than 0");
        }
        try {
            Inventory inventory = inventoryDao.getInventoryById(itemId);
            inventory.setQuantity(inventory.getQuantity() + quantity);
            inventoryDao.updateInventory(inventory);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to restock item");
        }
    }
    
     public boolean isLowStock(int itemId) {
        try {
            Inventory inventory = inventoryDao.getInventoryById(itemId);
            return inventory.isLowStock();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to check stock status");
        }
    }
}
