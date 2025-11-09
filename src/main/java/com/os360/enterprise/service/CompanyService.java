package com.os360.enterprise.service;

import com.os360.enterprise.dto.CompanyCreateRequest;
import com.os360.enterprise.dto.CompanyPatchRequest;
import com.os360.enterprise.dto.CompanyResponse;
import com.os360.enterprise.dto.CompanyUpdateRequest;
import com.os360.enterprise.entity.Company;
import com.os360.enterprise.entityUtils.EntityPatcher;
import com.os360.enterprise.exception.domain.EntityAlreadyDeletedException;
import com.os360.enterprise.mapper.CompanyMapper;
import com.os360.enterprise.repository.CompanyRepository;
import com.os360.enterprise.validator.CompanyValidator;
import com.os360.enterprise.exception.domain.EntityNotFoundException;
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
                .orElseThrow(() -> new EntityNotFoundException(Company.class, id));
        return companyMapper.toResponse(company);
    }

    /**
     * Performs a soft delete operation on the entity identified by the given ID.
     * <p>
     * Instead of permanently removing the entity from the database, this method
     * marks it as deleted (e.g., by setting a flag or status). This ensures that
     * the data remains available for auditing or recovery purposes.
     *
     * @param id the unique identifier of the entity to be soft deleted
     */
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

    /**
     * Updates an existing company identified by the given ID with the provided details.
     * <p>
     * Retrieves the company, applies the updates, and persists changes.
     * Throws {@link com.os360.enterprise.exception.domain.EntityNotFoundException}
     * if the company does not exist.
     *
     * @param id            the unique identifier of the company to update
     * @param updateRequest the DTO containing updated company details
     * @return the updated company as a response DTO
     */
    public Optional<CompanyResponse>  update(UUID id, CompanyUpdateRequest updateRequest) {
        Company existingCompany = companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Company.class, id));

        if (updateRequest.getName() != null) existingCompany.setName(updateRequest.getName());
        if (updateRequest.getCountryCode() != null) existingCompany.setCountryCode(updateRequest.getCountryCode());
        if (updateRequest.getExternalSystem() != null) existingCompany.setExternalSystem(updateRequest.getExternalSystem());
        if (updateRequest.getExternalId() != null) existingCompany.setExternalId(updateRequest.getExternalId());
        if (updateRequest.getLogoUrl() != null) existingCompany.setLogoUrl(updateRequest.getLogoUrl());
        existingCompany.setSystemCompany(updateRequest.isSystemCompany());
        if (updateRequest.getValidFrom() != null) existingCompany.setValidFrom(updateRequest.getValidFrom());
        if (updateRequest.getValidTo() != null) existingCompany.setValidTo(updateRequest.getValidTo());

        Company updatedCompany = companyRepository.save(existingCompany);
        return companyMapper.toResponse( updatedCompany);
    }

    /**
     * Updates an existing company identified by the given ID with the provided details.
     * <p>
     * Retrieves the company, applies the updates, and persists changes.
     * Throws {@link com.os360.enterprise.exception.domain.EntityNotFoundException}
     * if the company does not exist.
     *
     * @param id                    the unique identifier of the company to update
     * @param companyPatchRequest   the DTO containing updated company details
     * @return the updated company as a response DTO
     */
    public Optional<CompanyResponse>  patch(UUID id, CompanyPatchRequest companyPatchRequest) {
        Company existingCompany = companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Company.class, id));

        if (companyPatchRequest.getName() != null) existingCompany.setName(companyPatchRequest.getName());
        if (companyPatchRequest.getCountryCode() != null) existingCompany.setCountryCode(companyPatchRequest.getCountryCode());
        if (companyPatchRequest.getExternalSystem() != null) existingCompany.setExternalSystem(companyPatchRequest.getExternalSystem());
        if (companyPatchRequest.getExternalId() != null) existingCompany.setExternalId(companyPatchRequest.getExternalId());
        if (companyPatchRequest.getLogoUrl() != null) existingCompany.setLogoUrl(companyPatchRequest.getLogoUrl());
        existingCompany.setSystemCompany(companyPatchRequest.isSystemCompany());
        if (companyPatchRequest.getValidFrom() != null) existingCompany.setValidFrom(companyPatchRequest.getValidFrom());
        if (companyPatchRequest.getValidTo() != null) existingCompany.setValidTo(companyPatchRequest.getValidTo());

        Company updatedCompany = companyRepository.save(existingCompany);
        return companyMapper.toResponse( updatedCompany);

    }

}
