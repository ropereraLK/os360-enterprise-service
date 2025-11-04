package com.os360.enterprise.validator;

import com.os360.enterprise.common.CommonUtils;
import com.os360.enterprise.dto.CompanyCreateRequest;
import com.os360.enterprise.entity.Company;
import com.os360.enterprise.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompanyValidator {

    @Autowired
    private CompanyRepository companyRepository;

    public Company validateCreate(CompanyCreateRequest companyCreateRequest) {
        Company company = new Company();
        //Validate for Duplicate Company Code
        if (!companyRepository.existsByCode(companyCreateRequest.getCode())) {
            company.setCode(companyCreateRequest.getCode());
        }

        String externalId = companyCreateRequest.getExternalId();
        String externalSystem = companyCreateRequest.getExternalSystem();

        if(CommonUtils.allNotBlank(externalId,externalSystem)){
            if (!companyRepository.existsByExternalSystemAndExternalId(
                    companyCreateRequest.getExternalSystem(),
                    companyCreateRequest.getExternalSystem())) {
                company.setCode(companyCreateRequest.getCode());
            }else {
                //Throw wxception1
                throw new RuntimeException();
            }

        }else{
            throw new RuntimeException();
        }



        return company;
    }
}
