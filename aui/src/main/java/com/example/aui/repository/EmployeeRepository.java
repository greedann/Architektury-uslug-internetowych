package com.example.aui.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.aui.model.Company;
import com.example.aui.model.Employee;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    List<Employee> findByCompanyName(String name);

    List<Employee> findAllByCompany(Company company);
}
