package com.example.employee.event.implementation;

import com.example.employee.event.api.EmployeeEventRepository;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class EmployeeEventRepositoryImplementation implements EmployeeEventRepository {
    private final RestTemplate restTemplate;

    @Autowired
    public EmployeeEventRepositoryImplementation(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void create(UUID companyId) {
        restTemplate.put("/employees/{companyId}", null, companyId);
    }

    @Override
    public void delete(UUID companyId) {
        restTemplate.delete("/employees/{companyId}", companyId);
    }

}
