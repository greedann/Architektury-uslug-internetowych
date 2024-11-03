package com.example.aui.function;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.example.aui.dto.GetCompanyResponse;
import com.example.aui.model.Company;

@Component
public class CompanyToResponseFunction implements Function<Company, GetCompanyResponse> {

    @Override
    public GetCompanyResponse apply(Company company) {
        return GetCompanyResponse.builder()
                .id(company.getId())
                .name(company.getName())
                .capitalization(company.getCapitalization())
                .employees(company.getEmployees().stream()
                        .map(employee -> GetCompanyResponse.Employee.builder()
                                .id(employee.getId())
                                .name(employee.getName())
                                .level(employee.getLevel())
                                .build())
                        .collect(java.util.stream.Collectors.toList()))
                .build();
    }

}
