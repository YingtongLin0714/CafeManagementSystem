/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoPackage;

import modelPackage.Inventory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author salilam
 */
public class InventoryDao {
    private Connection connection;
    
    public InventoryDao(Connection connection) {
        this.connection = connection;
    }
    
    public void addInventoryItem(Inventory inventory) throws SQLException {
        String query = "INSERT INTO Inventory (item_name, quantity, threshold) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, inventory.getItemName());
            stmt.setInt(2, inventory.getQuantity());
            stmt.setInt(3, inventory.getThreshold());
            stmt.executeUpdate();
        }
    }
    
    public List<Inventory> getAllInventoryItems() throws SQLException {
        List<Inventory> inventoryList = new ArrayList<>();
        String query = "SELECT * FROM Inventory";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                inventoryList.add(new Inventory(
                        rs.getInt("item_id"),
                        rs.getString("item_name"),
                        rs.getInt("quantity"),
                        rs.getInt("threshold")
                ));
            }
        }
        return inventoryList;
    }
    
    public Inventory getInventoryById(int itemId) throws SQLException {
        String query = "SELECT * FROM Inventory WHERE item_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, itemId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Inventory(
                            rs.getInt("item_id"),
                            rs.getString("item_name"),
                            rs.getInt("quantity"),
                            rs.getInt("threshold")
                    );
                } else {
                    return null; // Item not found
                }
            }
        }
    }
    
    public void updateInventory(Inventory inventory) throws SQLException {
        String query = "UPDATE Inventory SET quantity = ? WHERE item_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, inventory.getQuantity());
            stmt.setInt(2, inventory.getItemId());
            stmt.executeUpdate();
        }
    }
    
    public void deleteInventoryItem(int itemId) throws SQLException {
        String query = "DELETE FROM Inventory WHERE item_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, itemId);
            stmt.executeUpdate();
        }
    }
    
    public boolean isLowStock(int itemId) throws SQLException {
        Inventory inventory = getInventoryById(itemId);
        return inventory != null && inventory.isLowStock();
    }
}
