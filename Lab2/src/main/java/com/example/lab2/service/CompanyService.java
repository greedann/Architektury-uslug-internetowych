package com.example.lab2.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lab2.model.Company;
import com.example.lab2.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
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
        return companyRepository.save(company);
    }

    @Transactional
    public void deleteById(UUID id) {
        companyRepository.deleteById(id);
    }
}