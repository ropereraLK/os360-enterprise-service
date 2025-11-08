package com.os360.enterprise.service;

import com.os360.enterprise.dto.CompanyCreateRequest;
import com.os360.enterprise.dto.CompanyResponse;
import com.os360.enterprise.dto.CompanyPatchRequest;
import com.os360.enterprise.entity.Company;
import com.os360.enterprise.entityUtils.EntityPatcher;
import com.os360.enterprise.mapper.CompanyMapper;
import com.os360.enterprise.repository.CompanyRepository;
import com.os360.enterprise.validator.CompanyValidator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public ResponseEntity<Void> softDelete(UUID id) {
        // Step 1: Load the existing entity
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Company not found: " + id));

        // already deleted?
        if (company.isDeleted()) {
            return ResponseEntity.status(HttpStatus.GONE).build();
        }

        company.setDeleted(true);
        company.setDeletedAt(OffsetDateTime.now());

        //TODO Change to a proper user
        company.setDeletedBy(UUID.fromString("00000000-0000-0000-0000-000000000000"));

//        CompanyPatchRequest companyPatchRequest = new CompanyPatchRequest();
//        companyPatchRequest.setDeleted(true);
//        entityPatcher.patchEntity(company, companyPatchRequest);
        companyRepository.save(company);

        return ResponseEntity.noContent().build();
    }
    }
}
