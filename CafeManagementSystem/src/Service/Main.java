/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;
import java.util.Scanner;
import Beans.Employee;
import Beans.MenuItem;
import Beans.Order;

/**
 *
 * @author salilam
 */
public class Main {
    public static void main(String[] args) {
        Management management = new Management();
        
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            // Display the main menu
            System.out.println("--- Café Management System ---");
            System.out.println("1. Manage Employees");
            System.out.println("2. Manage Inventory");
            System.out.println("3. Manage Orders");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    manageEmployees(management, scanner);
                    break;
                case 2:
                    manageInventory(management, scanner);
                    break;
                case 3:
                    manageOrders(management, scanner);
                    break;
                case 4:
                    System.out.println("Exiting the system...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please choose a valid number.");
            }
        }
    }

    // Method to manage employees
    public static void manageEmployees(Management management, Scanner scanner) {
        while (true) {
            System.out.println("--- Manage Employees ---");
            System.out.println("1. Add Employee");
            System.out.println("2. Display Employees");
            System.out.println("3. Back to Main Menu");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter employee name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter employee role: ");
                    String role = scanner.nextLine();
                    System.out.print("Enter employee phone number: ");
                    String phoneNumber = scanner.nextLine();
                    management.addEmployee(name, role, phoneNumber);
                    break;
                case 2:
                    management.displayEmployees();
                    break;
                case 3:
                    return;  // Go back to the main menu
                default:
                    System.out.println("Invalid option. Please choose a valid number.");
            }
        }
    }

    // Method to manage inventory
    public static void manageInventory(Management management, Scanner scanner) {
        while (true) {
            System.out.println("--- Manage Inventory ---");
            System.out.println("1. Add Menu Item");
            System.out.println("2. Display Menu");
            System.out.println("3. Restock Item");
            System.out.println("4. Back to Main Menu");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter item name: ");
                    String itemName = scanner.nextLine();
                    System.out.print("Enter item price: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter item stock: ");
                    int stock = scanner.nextInt();
                    management.addMenuItem(itemName, price, stock);
                    break;
                case 2:
                    management.displayMenu();
                    break;
                case 3:
                    System.out.print("Enter item name to restock: ");
                    scanner.nextLine();  // consume newline character
                    String restockItem = scanner.nextLine();
                    System.out.print("Enter quantity to restock: ");
                    int restockQuantity = scanner.nextInt();
                    management.restockMenuItem(restockItem, restockQuantity);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid option. Please choose a valid number.");
            }
        }
    }

    public static void manageOrders(Management management, Scanner scanner) {
        while (true) {
            System.out.println("--- Manage Orders ---");
            System.out.println("1. Create Order");
            System.out.println("2. Display Orders");
            System.out.println("3. Back to Main Menu");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume the newline character

            switch (choice) {
                case 1:
                    management.createOrder();
                    while (true) {
                        System.out.print("Enter menu item index to add to order (or -1 to finish): ");
                        int itemIndex = scanner.nextInt();
                        if (itemIndex == -1) {
                            break;
                        }
                        System.out.print("Enter quantity: ");
                        int quantity = scanner.nextInt();
                        management.addOrderItem(0, itemIndex, quantity);
                    }
                    break;
                case 2:
                    management.displayOrders();
                    break;
                case 3:
                    return;  // Go back to the main menu
                default:
                    System.out.println("Invalid option. Please choose a valid number.");
            }
        }
    }
}
