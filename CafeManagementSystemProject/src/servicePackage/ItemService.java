/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicePackage;

import daoPackage.MenuItemDao;
import modelPackage.MenuItem;

import java.sql.Connection;
import java.util.List;

/**
 *
 * @author salilam
 */
public class ItemService {
    private MenuItemDao menuItemDao;

    public ItemService(Connection connection) {
        this.menuItemDao = new MenuItemDao(connection);
    }
    
    public List<MenuItem> getAllMenuItems() {
        try {
            return menuItemDao.getAllMenuItems();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to retrieve menu items");
        }
    }
    
    public void addMenuItem(MenuItem menuItem) {
        if (menuItem.getPrice() <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }
        try {
            menuItemDao.addMenuItem(menuItem);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to add menu item");
        }
    }
}
