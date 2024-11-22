package com.example.employee.controller.implementation;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.employee.controller.api.EmployeeController;
import com.example.employee.dto.CreateOrUpdateEmployee;
import com.example.employee.dto.GetEmployeeResponse;
import com.example.employee.dto.GetEmployeesResponse;
import com.example.employee.entity.Employee;
import com.example.employee.function.EmployeeToResponseFunction;
import com.example.employee.function.EmployeesToResponseFunction;
import com.example.employee.function.RequestToEmployeeFunction;
import com.example.employee.service.CompanyService;
import com.example.employee.service.EmployeeService;

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
        employeeService.findById(id)
                .map(employeeToResponseFunction)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        employeeService.deleteById(id);
    }

    @Override
    public GetEmployeesResponse getEmployeesByCompanyId(UUID companyId) {
        return employeesToResponseFunction.apply(employeeService.findAllByCompanyId(companyId));
    }

}
