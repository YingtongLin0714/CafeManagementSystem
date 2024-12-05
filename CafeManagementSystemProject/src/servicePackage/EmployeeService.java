/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicePackage;

import daoPackage.EmployeeDao;
import modelPackage.Employee;
import modelPackage.Role;

import java.sql.Connection;
import java.util.List;

/**
 *
 * @author salilam
 */
public class EmployeeService {
    private EmployeeDao employeeDao;
    
    public EmployeeService(Connection connection) {
        this.employeeDao = new EmployeeDao(connection);
    }
    
    public void addEmployee(Employee employee) {

        if (employee.getName() == null || employee.getName().isEmpty()) {
            throw new IllegalArgumentException("Employee name cannot be null or empty.");
        }
        if (employee.getPhoneNumber() == null || !employee.getPhoneNumber().matches("\\d{3}-\\d{3}-\\d{4}")) {
            throw new IllegalArgumentException("Invalid phone number format. Expected: XXX-XXX-XXXX.");
        }

        try {
            employeeDao.addEmployee(employee);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to add employee.");
        }
    }
    
    public List<Employee> getAllEmployees() {
        try {
            return employeeDao.getAllEmployees();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to retrieve employees.");
        }
    }
    
    public void updateEmployeePhoneNumber(int employeeId, String newPhoneNumber) {
        if (!newPhoneNumber.matches("\\d{3}-\\d{3}-\\d{4}")) {
            throw new IllegalArgumentException("Invalid phone number format. Expected: XXX-XXX-XXXX.");
        }

        try {
            Employee employee = employeeDao.getEmployeeById(employeeId);
            if (employee == null) {
                throw new IllegalArgumentException("Employee with ID " + employeeId + " does not exist.");
            }

            employee.setPhoneNumber(newPhoneNumber);
            employeeDao.updateEmployeePhoneNumber(employeeId, newPhoneNumber);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update employee phone number.");
        }
    }
    
    public void deleteEmployee(int employeeId) {
        try {
            employeeDao.deleteEmployee(employeeId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to delete employee.");
        }
    }
}
