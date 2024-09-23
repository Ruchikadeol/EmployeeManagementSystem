package service;

import model.Employee;
import java.util.ArrayList;
import java.util.Comparator;

public class EmployeeService {
    private ArrayList<Employee> employees;

    public EmployeeService() {
        this.employees = new ArrayList<>();
    }

    // Add Employee
    public boolean addEmployee(Employee employee) {
        if (findEmployeeById(employee.getId()) == null) {
            employees.add(employee);
            return true;
        }
        return false;  // Employee with the same ID exists
    }

    // List all Employees
    public ArrayList<Employee> listEmployees() {
        return employees;
    }

    // Update Employee
    public boolean updateEmployee(int id, String newName, String newDepartment) {
        Employee employee = findEmployeeById(id);
        if (employee != null) {
            employee.setName(newName);
            employee.setDepartment(newDepartment);
            return true;
        }
        return false;  // Employee not found
    }

    // Delete Employee
    public boolean deleteEmployee(int id) {
        Employee employee = findEmployeeById(id);
        if (employee != null) {
            employees.remove(employee);
            return true;
        }
        return false;  // Employee not found
    }

    // Search Employee by Name
    public ArrayList<Employee> searchEmployeeByName(String name) {
        ArrayList<Employee> result = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getName().equalsIgnoreCase(name)) {
                result.add(employee);
            }
        }
        return result;
    }

    // Sort Employees by ID
    public void sortEmployeesById() {
        employees.sort(Comparator.comparingInt(Employee::getId));
    }

    // Find Employee by ID
    public Employee findEmployeeById(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }
}
