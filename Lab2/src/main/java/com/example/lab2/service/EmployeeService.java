package com.example.lab2.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lab2.model.Company;
import com.example.lab2.model.Employee;
import com.example.lab2.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
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
        employeeRepository.deleteById(id);
    }
}