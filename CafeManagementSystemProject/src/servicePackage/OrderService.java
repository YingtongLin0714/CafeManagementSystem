/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicePackage;

import daoPackage.MenuItemDao;
import daoPackage.OrderDao;
import modelPackage.Order;
import modelPackage.OrderItem;
import modelPackage.MenuItem;

import java.sql.Connection;
import java.util.List;

/**
 *
 * @author salilam
 */
public class OrderService {
    private OrderDao orderDao;
    private MenuItemDao menuItemDao;
    
    public OrderService(Connection connection) {
        this.orderDao = new OrderDao(connection);
        this.menuItemDao = new MenuItemDao(connection);
    }
    
    public void placeOrder(Order order) {
        // Validate the order
        if (order.getOrderItems().isEmpty()) {
            throw new IllegalArgumentException("Order must have at least one item");
        }
        order.calculateTotal();
        
        try {
            orderDao.saveOrder(order);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to place order");
        }
    }
    
    public List<Order> getAllOrders() {
        try {
            return orderDao.getAllOrders();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to retrieve orders");
        }
    }
}
