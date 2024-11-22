package com.example.employee.function;

import org.springframework.stereotype.Component;
import java.util.function.Function;

import com.example.employee.dto.GetEmployeeResponse;
import com.example.employee.entity.Employee;

@Component
public class EmployeeToResponseFunction implements Function<Employee, GetEmployeeResponse> {

    @Override
    public GetEmployeeResponse apply(Employee employee) {
        return GetEmployeeResponse.builder()
                .id(employee.getId())
                .name(employee.getName())
                .level(employee.getLevel())
                .company(employee.getCompany() != null ? GetEmployeeResponse.Company.builder()
                        .id(employee.getCompany().getId())
                        .name(employee.getCompany().getName())
                        .capitalization(employee.getCompany().getCapitalization())
                        .build() : null)
                .build();
    }

}
