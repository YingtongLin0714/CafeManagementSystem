/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelPackage;

/**
 *
 * @author salilam
 */
public class Employee {
    private int employeeId;
    private String name;
    private Role role;
    private String phoneNumber;
    
    public Employee(int employeeId, String name, Role role, String phoneNumber) {
        this.employeeId = employeeId;
        this.name = name;
        this.role = role;
        this.phoneNumber = phoneNumber;
    }
    
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    @Override
    public String toString() {
        return "Employee ID: " + employeeId +
               ", Name: " + name +
               ", Role: " + role +
               ", Phone Number: " + phoneNumber;
    }
}
