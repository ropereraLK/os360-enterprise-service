package com.os360.enterprise.service;

import com.os360.enterprise.dto.CompanyCreateRequest;
import com.os360.enterprise.dto.CompanyResponse;
import com.os360.enterprise.entity.Company;
import com.os360.enterprise.mapper.CompanyMapper;
import com.os360.enterprise.repository.CompanyRepository;
import com.os360.enterprise.validator.CompanyValidator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private Company company;

    @Autowired
    private CompanyResponse companyResponse;

    @Autowired
    private CompanyValidator companyValidator;

    @Autowired
    private CompanyMapper companyMapper;

    public CompanyResponse create(CompanyCreateRequest companyCreateRequest) {
        companyRepository.save(companyValidator.validateCreate(companyCreateRequest));
        return companyResponse;
    }

    public Optional<CompanyResponse> get(UUID id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Company not found: " + id));

        return companyMapper.toResponse(company);
    }
}
