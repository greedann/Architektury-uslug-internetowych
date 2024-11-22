package com.example.company.function;

import com.example.company.entity.Company;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.company.dto.GetCompaniesResponse;

@Component
public class CompaniesToResponseFunction implements Function<List<Company>, GetCompaniesResponse> {

    @Override
    public GetCompaniesResponse apply(List<Company> companies) {
        return GetCompaniesResponse.builder()
                .companies(companies.stream()
                        .map(company -> GetCompaniesResponse.Company.builder()
                        .id(company.getId())
                        .name(company.getName())
                        .capitalization(company.getCapitalization())
                        .build())
                        .collect(Collectors.toList())).build();
    }

}
