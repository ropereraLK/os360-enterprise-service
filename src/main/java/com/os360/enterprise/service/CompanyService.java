package com.os360.enterprise.service;

import com.os360.enterprise.entity.Company;
import com.os360.enterprise.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    //TODD
    private void createCompany(){
        Company company = new Company();
        companyRepository.save(company);
    }


}
