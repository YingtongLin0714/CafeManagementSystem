/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;
import java.util.ArrayList;
import Beans.Employee;
import Beans.MenuItem;
import Beans.Order;

/**
 *
 * @author salilam
 */
public class Management {
    private ArrayList<Employee> employees;
    private ArrayList<MenuItem> menu;
    private ArrayList<Order> orders;
    
    public Management() {
        employees = new ArrayList<>();
        menu = new ArrayList<>();
        orders = new ArrayList<>();
    }
    
    public void addEmployee(String name, String role, String phoneNumber) {
        Employee employee = new Employee(name, role, phoneNumber);
        employees.add(employee);
    }

    public void displayEmployees() {
        for (Employee employee : employees) {
            employee.displayEmployee();
            System.out.println();
        }
    }
    
    public void addMenuItem(String name, double price, int stock) {
        MenuItem item = new MenuItem(name, price, stock);
        menu.add(item);
    }

    public void displayMenu() {
        for (MenuItem item : menu) {
            item.displayMenuItem();
            System.out.println();
        }
    }
    
    public void restockMenuItem(String itemName, int quantity) {
        for (MenuItem item : menu) {
            if (item.getName().equals(itemName)) {
                item.restock(quantity);
                return;
            }
        }
        System.out.println("Item " + itemName + " not found.");
    }
    
    public void createOrder() {
        Order order = new Order();
        orders.add(order);
    }

    public void addOrderItem(int orderIndex, int menuIndex, int quantity) {
        if (orderIndex < orders.size() && menuIndex < menu.size()) {
            orders.get(orderIndex).addItem(menu.get(menuIndex), quantity);
        }
    }

    public void displayOrders() {
        for (Order order : orders) {
            order.displayOrder();
            System.out.println();
        }
    }
}
