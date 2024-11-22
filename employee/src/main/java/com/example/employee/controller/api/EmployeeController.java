package com.example.employee.controller.api;

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

import com.example.employee.dto.CreateOrUpdateEmployee;
import com.example.employee.dto.GetEmployeeResponse;
import com.example.employee.dto.GetEmployeesResponse;

public interface EmployeeController {
    @GetMapping("/employees")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetEmployeesResponse getAllEmployees();

    @GetMapping("/employees/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetEmployeeResponse getEmployeeById(@PathVariable UUID id);

    @GetMapping("/companies/{companyId}/employees")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetEmployeesResponse getEmployeesByCompanyId(@PathVariable UUID companyId);

    @PutMapping("/companies/{companyId}/employees")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    GetEmployeeResponse createEmployee(@PathVariable UUID companyId, @RequestBody CreateOrUpdateEmployee requestBody);

    @PatchMapping("/employees/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    GetEmployeeResponse updateEmployee(@PathVariable UUID id, @RequestBody CreateOrUpdateEmployee requestBody);

    @DeleteMapping("/employees/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteEmployee(@PathVariable UUID id);

}
