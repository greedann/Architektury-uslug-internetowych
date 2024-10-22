package com.example.lab2.component;

import com.example.lab2.model.Company;
import com.example.lab2.model.Employee;
import com.example.lab2.service.CompanyService;
import com.example.lab2.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

@Component
public class AppRunner implements CommandLineRunner {

    private final CompanyService companyService;
    private final EmployeeService employeeService;

    @Autowired
    public AppRunner(CompanyService companyService, EmployeeService employeeService) {
        this.companyService = companyService;
        this.employeeService = employeeService;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add company");
            System.out.println("2. Add employee");
            System.out.println("3. Show all companies");
            System.out.println("4. Show all employees");
            System.out.println("5. Show employees by company name");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter company name: ");
                    String companyName = scanner.nextLine();
                    Company company = new Company();
                    company.setName(companyName);
                    company.setId(UUID.randomUUID());
                    companyService.save(company);
                    break;
                case 2:
                    System.out.print("Enter employee name: ");
                    String employeeName = scanner.nextLine();
                    System.out.print("Enter company name: ");
                    String employeeCompanyName = scanner.nextLine();
                    Employee employee = new Employee();
                    employee.setName(employeeName);
                    employee.setCompany(companyService.findByName(employeeCompanyName).get(0));
                    employee.setId(UUID.randomUUID());
                    employeeService.save(employee);
                    break;
                case 3:
                    List<Company> companies = companyService.findAll();
                    for (Company c : companies) {
                        System.out.println(c);
                    }
                    break;
                case 4:
                    List<Employee> employees = employeeService.findAll();
                    for (Employee e : employees) {
                        System.out.println(e);
                    }
                    break;
                case 5:
                    System.out.print("Enter company name: ");
                    String companyNameForEmployees = scanner.nextLine();
                    List<Employee> employeesByCompany = employeeService.findByCompanyName(companyNameForEmployees);
                    for (Employee e : employeesByCompany) {
                        System.out.println(e);
                    }
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

}
