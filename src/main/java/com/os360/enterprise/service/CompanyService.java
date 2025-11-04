package com.os360.enterprise.service;

import com.os360.enterprise.dto.CompanyCreateRequest;
import com.os360.enterprise.dto.CompanyResponse;
import com.os360.enterprise.entity.Company;
import com.os360.enterprise.repository.CompanyRepository;
import com.os360.enterprise.validator.CompanyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public CompanyResponse create(CompanyCreateRequest companyCreateRequest) {


        companyRepository.save(companyValidator.validateCreate(companyCreateRequest));
        return companyResponse;
    }
}
