/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoPackage;

import modelPackage.Employee;
import modelPackage.Role;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author salilam
 */
public class EmployeeDao {
    private Connection connection;
    
    public EmployeeDao(Connection connection) {
        this.connection = connection;
    }
    
    public void addEmployee(Employee employee) throws SQLException {
        String query = "INSERT INTO Employees (name, role, phoneNumber) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, employee.getName());
            stmt.setString(2, employee.getRole().name()); // Store the role as a string
            stmt.setString(3, employee.getPhoneNumber());
            stmt.executeUpdate();
        }
    }
    
    public Employee getEmployeeById(int employeeId) throws SQLException {
        String query = "SELECT * FROM Employees WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, employeeId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        Role.valueOf(rs.getString("role")),
                        rs.getString("phoneNumber")
                    );
                } else {
                    return null;
                }
            }
        }
    }
    
    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM Employees";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                employees.add(new Employee(
                    rs.getInt("id"),
                    rs.getString("name"),
                    Role.valueOf(rs.getString("role")), // Convert the role string to Role enum
                    rs.getString("phoneNumber")
                ));
            }
        }
        return employees;
    }
    
    public void updateEmployeePhoneNumber(int id, String newPhoneNumber) throws SQLException {
        String query = "UPDATE Employees SET phoneNumber = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, newPhoneNumber);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }
    
    public void deleteEmployee(int id) throws SQLException {
        String query = "DELETE FROM Employees WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
