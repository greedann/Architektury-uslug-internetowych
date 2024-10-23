package com.example.lab2.util;

import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import java.util.UUID;

import com.example.lab2.model.Company;
import com.example.lab2.model.Employee;
import com.example.lab2.service.CompanyService;
import com.example.lab2.service.EmployeeService;

@Component
public class DataInitializer {

    private final CompanyService companyService;
    private final EmployeeService employeeService;

    public DataInitializer(CompanyService companyService, EmployeeService employeeService) {
        this.companyService = companyService;
        this.employeeService = employeeService;
    }

    @PostConstruct
    public void initData() {
        if (companyService.findAll().size() > 0) {
            return;
        }
        Company company1 = new Company();
        company1.setId(UUID.randomUUID());
        company1.setCapitalization(15);
        company1.setName("Company1");
        companyService.save(company1);

        Company company2 = new Company();
        company2.setId(UUID.randomUUID());
        company2.setCapitalization(10);
        company2.setName("Company2");
        companyService.save(company2);

        Company company3 = new Company();
        company3.setId(UUID.randomUUID());
        company3.setCapitalization(25);
        company3.setName("Company3");
        companyService.save(company3);

        Employee employee1 = new Employee();
        employee1.setCompany(company1);
        employee1.setId(UUID.randomUUID());
        employee1.setLevel(2);
        employee1.setName("employee1");
        employeeService.save(employee1);

        Employee employee2 = new Employee();
        employee2.setCompany(company1);
        employee2.setId(UUID.randomUUID());
        employee2.setLevel(2);
        employee2.setName("employee2");
        employeeService.save(employee2);

    }
}
