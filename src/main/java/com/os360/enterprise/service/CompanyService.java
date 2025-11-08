package com.os360.enterprise.service;

import com.os360.enterprise.dto.CompanyCreateRequest;
import com.os360.enterprise.dto.CompanyResponse;
import com.os360.enterprise.entity.Company;
import com.os360.enterprise.entityUtils.EntityPatcher;
import CompanyAlreadyDeletedOperationException;
import CompanyNotFoundOperationException;
import com.os360.enterprise.exception.domain.EntityAlreadyDeletedException;
import com.os360.enterprise.mapper.CompanyMapper;
import com.os360.enterprise.repository.CompanyRepository;
import com.os360.enterprise.validator.CompanyValidator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyValidator companyValidator;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    EntityPatcher entityPatcher;

    public Optional<CompanyResponse> create(CompanyCreateRequest companyCreateRequest) {

        Company company = companyRepository.save(companyValidator.validateCreate(companyCreateRequest));
        return companyMapper.toResponse(company);
    }

    public Optional<CompanyResponse> get(UUID id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Company not found: " + id));

        return companyMapper.toResponse(company);
    }

    public void softDelete(UUID id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Company.class, id));

        if (company.isDeleted()) {
            throw new EntityAlreadyDeletedException(Company.class, id);
        }

        //Add deletion details
        company.setDeleted(true);
        company.setDeletedAt(OffsetDateTime.now());

        //TODO Change to a proper user
        company.setDeletedBy(UUID.fromString("00000000-0000-0000-0000-000000000000"));

//        CompanyPatchRequest companyPatchRequest = new CompanyPatchRequest();
//        companyPatchRequest.setDeleted(true);
//        entityPatcher.patchEntity(company, companyPatchRequest);
        companyRepository.save(company);

    }
}
