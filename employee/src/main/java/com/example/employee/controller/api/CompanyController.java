package com.example.employee.controller.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

import org.springframework.http.HttpStatus;

public interface CompanyController {
    @PutMapping("/companies/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    void createCompany(@PathVariable UUID id);


    @DeleteMapping("/companies/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCompany(@PathVariable UUID id);

}
