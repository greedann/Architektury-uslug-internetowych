package com.example.company.event.implementation;

import com.example.company.event.api.CompanyEventRepository;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class CompanyEventRepositoryImplementation implements CompanyEventRepository {
    private final RestTemplate restTemplate;

    @Autowired
    public CompanyEventRepositoryImplementation(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void create(UUID companyId) {
        restTemplate.put("/companies/{companyId}", null, companyId);
    }

    @Override
    public void delete(UUID companyId) {
        restTemplate.delete("/companies/{companyId}", companyId);
    }

}
