package main;

import model.Employee;
import service.EmployeeService;

import java.util.Scanner;

public class EmployeeManagementSystem {
    private static EmployeeService employeeService = new EmployeeService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            // Display Menu
            System.out.println("\nEmployee Management System");
            System.out.println("1. Add Employee");
            System.out.println("2. List Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Search Employee by Name");
            System.out.println("6. Sort Employees by ID");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Menu choices
            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    listEmployees();
                    break;
                case 3:
                    updateEmployee();
                    break;
                case 4:
                    deleteEmployee();
                    break;
                case 5:
                    searchEmployeeByName();
                    break;
                case 6:
                    sortEmployeesById();
                    break;
                case 7:
                    exitConfirmation();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addEmployee() {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (employeeService.findEmployeeById(id) != null) {
            System.out.println("Employee with ID " + id + " already exists.");
            return;
        }

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Department: ");
        String department = scanner.nextLine();

        Employee employee = new Employee(id, name, department);
        if (employeeService.addEmployee(employee)) {
            System.out.println("Employee added successfully.");
        } else {
            System.out.println("Failed to add employee.");
        }
    }

    private static void listEmployees() {
        var employees = employeeService.listEmployees();
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            for (Employee employee : employees) {
                System.out.println(employee);
            }
        }
    }

    private static void updateEmployee() {
        System.out.print("Enter ID of employee to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Employee employee = employeeService.findEmployeeById(id);
        if (employee != null) {
            System.out.print("Enter new Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter new Department: ");
            String department = scanner.nextLine();

            if (employeeService.updateEmployee(id, name, department)) {
                System.out.println("Employee updated successfully.");
            } else {
                System.out.println("Failed to update employee.");
            }
        } else {
            System.out.println("Employee with ID " + id + " not found.");
        }
    }

    private static void deleteEmployee() {
        System.out.print("Enter ID of employee to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (employeeService.deleteEmployee(id)) {
            System.out.println("Employee deleted successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    private static void searchEmployeeByName() {
        System.out.print("Enter Name of employee to search: ");
        String name = scanner.nextLine();

        var results = employeeService.searchEmployeeByName(name);
        if (results.isEmpty()) {
            System.out.println("No employees found with the name " + name);
        } else {
            for (Employee employee : results) {
                System.out.println(employee);
            }
        }
    }

    private static void sortEmployeesById() {
        employeeService.sortEmployeesById();
        System.out.println("Employees sorted by ID.");
        listEmployees();
    }

    private static void exitConfirmation() {
        System.out.print("Are you sure you want to exit? (yes/no): ");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("yes")) {
            System.out.println("Exiting... Goodbye!");
        } else {
            System.out.println("Returning to the menu...");
        }
    }
}
