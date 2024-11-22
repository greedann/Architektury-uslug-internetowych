package com.example.company.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.example.company.entity.Company;
import com.example.company.event.api.CompanyEventRepository;
import com.example.company.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyEventRepository companyEventRepository;

    public CompanyService(CompanyRepository companyRepository, CompanyEventRepository companyEventRepository) {
        this.companyRepository = companyRepository;
        this.companyEventRepository = companyEventRepository;
    }

    @Transactional
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Transactional
    public Optional<Company> findById(UUID id) {
        return companyRepository.findById(id);
    }

    @Transactional
    public List<Company> findByName(String name) {
        return companyRepository.findByName(name);
    }

    @Transactional
    public Company save(Company company) {
        companyEventRepository.create(company.getId());
        return companyRepository.save(company);
    }

    @Transactional
    public void deleteById(UUID id) {
        companyRepository.deleteById(id);
        companyEventRepository.delete(id);
    }
}