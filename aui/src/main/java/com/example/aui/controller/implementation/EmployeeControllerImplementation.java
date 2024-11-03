package com.example.aui.controller.implementation;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.aui.controller.api.EmployeeController;
import com.example.aui.dto.CreateOrUpdateEmployee;
import com.example.aui.dto.GetEmployeeResponse;
import com.example.aui.dto.GetEmployeesResponse;
import com.example.aui.function.EmployeeToResponseFunction;
import com.example.aui.function.EmployeesToResponseFunction;
import com.example.aui.function.RequestToEmployeeFunction;
import com.example.aui.model.Employee;
import com.example.aui.service.CompanyService;
import com.example.aui.service.EmployeeService;

import lombok.extern.java.Log;

@RestController
@Log
public class EmployeeControllerImplementation implements EmployeeController {
    private final EmployeeService employeeService;
    private final CompanyService companyService;
    private final EmployeeToResponseFunction employeeToResponseFunction;
    private final EmployeesToResponseFunction employeesToResponseFunction;
    private final RequestToEmployeeFunction requestToEmployeeFunction;

    public EmployeeControllerImplementation(EmployeeService employeeService,
            CompanyService companyService,
            EmployeeToResponseFunction employeeToResponseFunction,
            EmployeesToResponseFunction employeesToResponseFunction,
            RequestToEmployeeFunction requestToEmployeeFunction) {
        this.employeeService = employeeService;
        this.companyService = companyService;
        this.employeeToResponseFunction = employeeToResponseFunction;
        this.employeesToResponseFunction = employeesToResponseFunction;
        this.requestToEmployeeFunction = requestToEmployeeFunction;
    }

    @Override
    public GetEmployeesResponse getAllEmployees() {
        return employeesToResponseFunction.apply(employeeService.findAll());
    }

    @Override
    public GetEmployeeResponse getEmployeeById(UUID id) {
        return employeeService.findById(id)
                .map(employeeToResponseFunction)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public GetEmployeeResponse createEmployee(UUID companyId, CreateOrUpdateEmployee requestBody) {
        companyService.findById(companyId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Employee employee = requestToEmployeeFunction.apply(companyId, requestBody);

        return employeeToResponseFunction.apply(employeeService.save(employee));
    }

    @Override
    public GetEmployeeResponse updateEmployee(UUID id, CreateOrUpdateEmployee requestBody) {
        Employee employee = employeeService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        employee.setName(requestBody.getName());
        employee.setLevel(requestBody.getLevel());
        return employeeToResponseFunction.apply(employeeService.save(employee));
    }

    @Override
    public void deleteEmployee(UUID id) {
        employeeService.deleteById(id);
    }

    @Override
    public GetEmployeesResponse getEmployeesByCompanyId(UUID companyId) {
        return employeesToResponseFunction.apply(employeeService.findAllByCompanyId(companyId));
    }

}
