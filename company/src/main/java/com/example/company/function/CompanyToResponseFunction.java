package com.example.company.function;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.example.company.dto.GetCompanyResponse;
import com.example.company.entity.Company;

@Component
public class CompanyToResponseFunction implements Function<Company, GetCompanyResponse> {

    @Override
    public GetCompanyResponse apply(Company company) {
        return GetCompanyResponse.builder()
                .id(company.getId())
                .name(company.getName())
                .capitalization(company.getCapitalization())
                .build();
    }

}
