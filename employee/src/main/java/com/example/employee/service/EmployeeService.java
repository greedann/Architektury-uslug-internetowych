package com.example.employee.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.example.employee.entity.Company;
import com.example.employee.entity.Employee;
import com.example.employee.repository.CompanyRepository;
import com.example.employee.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;

    public EmployeeService(EmployeeRepository employeeRepository, CompanyRepository companyRepository) {
        this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
    }

    @Transactional
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Transactional
    public Optional<Employee> findById(UUID id) {
        return employeeRepository.findById(id);
    }

    @Transactional
    public List<Employee> findByCompanyName(String name) {
        return employeeRepository.findByCompanyName(name);
    }

    @Transactional
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Transactional
    public void deleteById(UUID id) {
        // employeeRepository.deleteById(id);
        this.employeeRepository.findById(id).ifPresentOrElse(this.employeeRepository::delete, () -> {
            throw new RuntimeException("Employee not found");
        });
    }

    @Transactional
    public List<Employee> findAllByCompanyId(UUID companyId) {
        System.out.println("------------" + companyRepository.findAll());
        Company company = companyRepository.findById(companyId).orElseThrow();
        return employeeRepository.findAllByCompany(company);
    }
}