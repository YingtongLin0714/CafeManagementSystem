/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoPackage;

import modelPackage.Order;
import modelPackage.OrderItem;
import modelPackage.MenuItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author salilam
 */
public class OrderDao {
    private Connection connection;
    
    public OrderDao(Connection connection) {
        this.connection = connection;
    }
    
    public void saveOrder(Order order) throws SQLException {
        String insertOrderQuery = "INSERT INTO Orders (order_id, total, order_time) VALUES (?, ?, ?)";
        String insertOrderItemQuery = "INSERT INTO OrderItems (order_id, item_id, quantity) VALUES (?, ?, ?)";

        try (PreparedStatement orderStmt = connection.prepareStatement(insertOrderQuery);
             PreparedStatement orderItemStmt = connection.prepareStatement(insertOrderItemQuery)) {

            orderStmt.setInt(1, order.getOrderId());
            orderStmt.setDouble(2, order.getTotalAmount());
            orderStmt.setTimestamp(3, new Timestamp(order.getOrderDate().getTime()));
            orderStmt.executeUpdate();
            
            for (OrderItem orderItem : order.getOrderItems()) {
                orderItemStmt.setInt(1, order.getOrderId());
                orderItemStmt.setInt(2, orderItem.getItem().getId());
                orderItemStmt.setInt(3, orderItem.getQuantity());
                orderItemStmt.addBatch();
            }
            orderItemStmt.executeBatch();
        }
    }
    
    public List<Order> getAllOrders() throws SQLException {
        List<Order> orders = new ArrayList<>();
        String selectOrderQuery = "SELECT * FROM Orders";
        String selectOrderItemsQuery = "SELECT * FROM OrderItems WHERE order_id = ?";

        try (Statement orderStmt = connection.createStatement();
             ResultSet orderRs = orderStmt.executeQuery(selectOrderQuery)) {

            while (orderRs.next()) {
                int orderId = orderRs.getInt("order_id");
                double total = orderRs.getDouble("total");
                Timestamp orderTime = orderRs.getTimestamp("order_time");

                Order order = new Order(orderId);
                order.setTotalAmount(total);
                order.setOrderDate(orderTime);
                
                try (PreparedStatement orderItemsStmt = connection.prepareStatement(selectOrderItemsQuery)) {
                    orderItemsStmt.setInt(1, orderId);
                    try (ResultSet orderItemsRs = orderItemsStmt.executeQuery()) {
                        while (orderItemsRs.next()) {
                            int itemId = orderItemsRs.getInt("item_id");
                            int quantity = orderItemsRs.getInt("quantity");
                            // Mock menu item for simplicity
                            MenuItem menuItem = new MenuItem(itemId, "Unknown", "Unknown", 0.0);
                            order.addItem(menuItem, quantity);
                        }
                    }
                }
                orders.add(order);
            }
        }
        return orders;
    }
    
    public void deleteOrder(int orderId) throws SQLException {
        String deleteOrderItemsQuery = "DELETE FROM OrderItems WHERE order_id = ?";
        String deleteOrderQuery = "DELETE FROM Orders WHERE order_id = ?";

        try (PreparedStatement orderItemsStmt = connection.prepareStatement(deleteOrderItemsQuery);
             PreparedStatement orderStmt = connection.prepareStatement(deleteOrderQuery)) {

            orderItemsStmt.setInt(1, orderId);
            orderItemsStmt.executeUpdate();

            orderStmt.setInt(1, orderId);
            orderStmt.executeUpdate();
        }
    }
                
}
