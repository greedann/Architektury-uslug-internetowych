package com.example.company.function;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.example.company.dto.CreateOrUpdateCompany;
import com.example.company.entity.Company;

@Component
public class RequestToCompanyFunction implements Function<CreateOrUpdateCompany, Company> {

    @Override
    public Company apply(CreateOrUpdateCompany createOrUpdateCompany) {
        return Company.builder()
                .name(createOrUpdateCompany.getName())
                .capitalization(createOrUpdateCompany.getCapitalization())
                .build();
    }

}
