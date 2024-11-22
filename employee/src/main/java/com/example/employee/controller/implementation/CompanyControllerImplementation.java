package com.example.employee.controller.implementation;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.employee.controller.api.CompanyController;
import com.example.employee.entity.Company;
import com.example.employee.service.CompanyService;

@RestController
public class CompanyControllerImplementation implements CompanyController {
    private final CompanyService companyService;

    public CompanyControllerImplementation(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Override
    public void createCompany(UUID id) {
        Company company = Company.builder().id(id).build();
        System.out.println(company);
        companyService.save(company);
    }

    @Override
    public void deleteCompany(UUID id) {
        try {
            companyService.deleteById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found");
        }
    }
}
