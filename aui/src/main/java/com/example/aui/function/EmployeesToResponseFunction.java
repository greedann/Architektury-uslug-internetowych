package com.example.aui.function;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.aui.dto.GetEmployeesResponse;
import com.example.aui.model.Employee;

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
