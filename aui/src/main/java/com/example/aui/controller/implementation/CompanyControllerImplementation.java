package com.example.aui.controller.implementation;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.aui.controller.api.CompanyController;
import com.example.aui.dto.CreateOrUpdateCompany;
import com.example.aui.dto.GetCompaniesResponse;
import com.example.aui.dto.GetCompanyResponse;
import com.example.aui.function.CompaniesToResponseFunction;
import com.example.aui.function.CompanyToResponseFunction;
import com.example.aui.function.RequestToCompanyFunction;
import com.example.aui.model.Company;
import com.example.aui.service.CompanyService;

import lombok.extern.java.Log;

@RestController
@Log
public class CompanyControllerImplementation implements CompanyController {
    private final CompanyService companyService;
    private final CompanyToResponseFunction companyToResponseFunction;
    private final CompaniesToResponseFunction companiesToResponseFunction;
    private final RequestToCompanyFunction requestToCompanyFunction;

    public CompanyControllerImplementation(CompanyService companyService,
            CompanyToResponseFunction companyToResponseFunction,
            CompaniesToResponseFunction companiesToResponseFunction,
            RequestToCompanyFunction requestToCompanyFunction) {
        this.companyService = companyService;
        this.companyToResponseFunction = companyToResponseFunction;
        this.companiesToResponseFunction = companiesToResponseFunction;
        this.requestToCompanyFunction = requestToCompanyFunction;
    }

    @Override
    public GetCompaniesResponse getAllCompanies() {
        return companiesToResponseFunction.apply(companyService.findAll());
    }

    @Override
    public GetCompanyResponse getCompanyById(UUID id) {
        return companyService.findById(id)
                .map(companyToResponseFunction)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public GetCompanyResponse createCompany(CreateOrUpdateCompany requestBody) {
        return companyToResponseFunction.apply(companyService.save(requestToCompanyFunction.apply(requestBody)));
    }

    @Override
    public GetCompanyResponse updateCompany(UUID id, CreateOrUpdateCompany requestBody) {
        Company company = companyService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        company.setName(requestBody.getName());
        company.setCapitalization(requestBody.getCapitalization());
        return companyToResponseFunction.apply(companyService.save(company));
    }

    @Override
    public void deleteCompany(UUID id) {
        try {
            companyService.deleteById(id);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
