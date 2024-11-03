package com.example.aui.function;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.example.aui.dto.CreateOrUpdateCompany;
import com.example.aui.model.Company;

@Component
public class RequestToCompanyFunction implements Function<CreateOrUpdateCompany, Company> {

    @Override
    public Company apply(CreateOrUpdateCompany createOrUpdateCompany) {
        return Company.builder()
                .name(createOrUpdateCompany.getName())
                .capitalization(createOrUpdateCompany.getCapitalization())
                .employees(new java.util.ArrayList<>())
                .build();
    }

}
