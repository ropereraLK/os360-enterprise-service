package com.os360.enterprise.service;

import com.os360.enterprise.dto.CompanyCreateRequest;
import com.os360.enterprise.dto.CompanyResponse;
import com.os360.enterprise.entity.Company;
import com.os360.enterprise.repository.CompanyRepository;
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

    public CompanyResponse create(CompanyCreateRequest companyCreateRequest) {
        companyRepository.save(company);
        return companyResponse;
    }
}
