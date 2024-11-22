package com.example.employee.function;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.employee.dto.GetEmployeesResponse;
import com.example.employee.entity.Employee;

@Component
public class EmployeesToResponseFunction implements Function<List<Employee>, GetEmployeesResponse> {

    @Override
    public GetEmployeesResponse apply(List<Employee> employees) {
        return GetEmployeesResponse.builder()
                .employees(employees.stream()
                        .map(employee -> GetEmployeesResponse.Employee.builder()
                        .id(employee.getId())
                        .name(employee.getName())
                        .level(employee.getLevel())
                        .companyName(employee.getCompany().getName())
                        .build())
                        .collect(Collectors.toList())).build();
    }

}
