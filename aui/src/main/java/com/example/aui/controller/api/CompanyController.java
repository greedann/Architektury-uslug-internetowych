package com.example.aui.controller.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

import org.springframework.http.HttpStatus;

import com.example.aui.dto.CreateOrUpdateCompany;
import com.example.aui.dto.GetCompaniesResponse;
import com.example.aui.dto.GetCompanyResponse;

public interface CompanyController {
    @GetMapping("/companies")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetCompaniesResponse getAllCompanies();

    @GetMapping("/companies/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetCompanyResponse getCompanyById(@PathVariable UUID id);

    @PutMapping("/companies")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    GetCompanyResponse createCompany(@RequestBody CreateOrUpdateCompany requestBody);

    @PatchMapping("/companies/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    GetCompanyResponse updateCompany(@PathVariable UUID id, @RequestBody CreateOrUpdateCompany requestBody);

    @DeleteMapping("/companies/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCompany(@PathVariable UUID id);

}
