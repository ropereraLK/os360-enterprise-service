package com.os360.enterprise.validator;

import com.os360.enterprise.common.CommonUtils;
import com.os360.enterprise.common.CountryUtils;
import com.os360.enterprise.dto.CompanyCreateRequest;
import com.os360.enterprise.entity.Company;
import com.os360.enterprise.enumurations.PartyType;
import com.os360.enterprise.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Component
public class CompanyValidator {

    @Autowired
    private CompanyRepository companyRepository;

    public Company validateCreate(CompanyCreateRequest companyCreateRequest) {
        Company company = new Company();

        //Validate Parent Company
        UUID parentId = companyCreateRequest.getParentCompany();
        if (parentId != null) {
            Company parentCompany = companyRepository.findById(parentId)
                    .orElseThrow(() -> new IllegalArgumentException("Parent company not found: " + parentId));
            company.setParentCompany(parentCompany);
        }

        //Validate companyCode
        if (!companyRepository.existsByCode(companyCreateRequest.getCode())) {
            company.setCode(companyCreateRequest.getCode());
        } else {
            throw new RuntimeException();
        }

        //Validate countryCode
        if (CountryUtils.isValidAlpha2CountryCode(companyCreateRequest.getCountryCode())) {
            company.setCountryCode(companyCreateRequest.getCountryCode());
        } else {
            throw new RuntimeException();
        }

        String externalId = companyCreateRequest.getExternalId();
        String externalSystem = companyCreateRequest.getExternalSystem();

        //Validate externalId and externalSystem
        if (CommonUtils.allNotBlank(externalId, externalSystem)) {
            if (!companyRepository.existsByExternalSystemAndExternalId(
                    externalSystem,
                    externalId)) {
                company.setExternalSystem(externalSystem);
                company.setExternalId(externalId);
            } else {
                //Throw Exception
                throw new RuntimeException();
            }

        }

        //Validate isSystemCompany
        if (companyCreateRequest.isSystemCompany())
            if (!companyRepository.existsByIsSystemCompanyTrue()) {
                company.setSystemCompany(true);
            } else {
                throw new RuntimeException();
            }
        else {
            company.setSystemCompany(false);
        }

        company.setValidFrom(
                Optional.ofNullable(companyCreateRequest.getValidFrom())
                        .orElse(LocalDate.of(1900, 1, 1))
        );

        company.setValidTo(
                Optional.ofNullable(companyCreateRequest.getValidTo())
                        .orElse(LocalDate.of(9999, 1, 1))
        );

        company.setActive(true);
        company.setDeleted(false);
        company.setPartyType(String.valueOf(PartyType.COMPANY));

        company.setName(companyCreateRequest.getName());
        company.setLogoUrl(companyCreateRequest.getLogoUrl());

        return company;
    }
}
