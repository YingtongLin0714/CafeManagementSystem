/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoPackage;

import modelPackage.MenuItem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author salilam
 */
public class MenuItemDao {
    private Connection connection;
    
    public MenuItemDao(Connection connection) {
        this.connection = connection;
    }
    
    public void addMenuItem(MenuItem menuItem) throws SQLException {
        String query = "INSERT INTO MenuItems (name, category, price) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, menuItem.getName());
            stmt.setString(2, menuItem.getCategory());
            stmt.setDouble(3, menuItem.getPrice());
            stmt.executeUpdate();
        }
    }
    
    public List<MenuItem> getAllMenuItems() throws SQLException {
        List<MenuItem> menuItems = new ArrayList<>();
        String query = "SELECT * FROM MenuItems";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                menuItems.add(new MenuItem(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("category"),
                    rs.getDouble("price")
                ));
            }
        }
        return menuItems;
    }
    
    public void updateMenuItemPrice(int id, double newPrice) throws SQLException {
        String query = "UPDATE MenuItems SET price = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDouble(1, newPrice);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }
    
    public void deleteMenuItem(int id) throws SQLException {
        String query = "DELETE FROM MenuItems WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
