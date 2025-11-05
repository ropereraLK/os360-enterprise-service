package com.os360.enterprise.service;

import com.os360.enterprise.dto.CompanyCreateRequest;
import com.os360.enterprise.dto.CompanyResponse;
import com.os360.enterprise.entity.Company;
import com.os360.enterprise.mapper.CompanyMapper;
import com.os360.enterprise.repository.CompanyRepository;
import com.os360.enterprise.validator.CompanyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public CompanyResponse get(UUID id) {
        return companyMapper.toResponse(companyRepository.findById(id));
    }
}
