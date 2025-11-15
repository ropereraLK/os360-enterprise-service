package com.os360.enterprise.validator;

import com.os360.enterprise.dto.SiteCreateRequest;
import com.os360.enterprise.dto.SiteUpdateRequest;
import com.os360.enterprise.entity.Company;
import com.os360.enterprise.entity.Site;

import com.os360.enterprise.exception.validation.*;
import com.os360.enterprise.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

/**
 * Validator for Site entities.
 * <p>
 * Ensures business rules are respected before creating or updating Site entities.
 */
@Component
public class SiteValidator {

    @Autowired
    private CompanyRepository companyRepository;

    /**
     * Validates a SiteCreateRequest before creating a new Site entity.
     *
     * @param request the site creation request DTO
     * @return a new Site entity ready for persistence
     * @throws ValidationException if validation fails
     */
    public Site validateCreate(SiteCreateRequest request) {
        validateCommonFields(request);

        // Validate company exists
        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new ValidationException(
                        "Company not found for the site",
                        "companyId",
                        request.getCompanyId()
                ));

        Site site = new Site();
        site.setCompany(company);
//        site.setSiteCode(request.getSiteCode());
//        site.setSiteName(request.getSiteName());
        site.setSiteType(request.getSiteType());
        site.setDefault(request.isDefault());
        site.setActive(true);
        site.setDeleted(false);

        return site;
    }

    /**
     * Validates a SiteUpdateRequest before updating an existing Site entity.
     *
     * @param existingSite the existing Site entity
     * @param request      the update request DTO
     * @return the existing Site entity updated with validated fields
     * @throws ValidationException if validation fails
     */
    public Site validateUpdate(Site existingSite, SiteUpdateRequest request) {
        validateCommonFields(request);

        if (request.getCompanyId() != null &&
                !Objects.equals(existingSite.getCompany().getId(), request.getCompanyId())) {
            // Validate company exists if changed
            Company company = companyRepository.findById(request.getCompanyId())
                    .orElseThrow(() -> new ValidationException(
                            "Company not found for the site",
                            "companyId",
                            request.getCompanyId()
                    ));
            existingSite.setCompany(company);
        }

        if (request.getSiteCode() != null) existingSite.setSiteCode(request.getSiteCode());
        if (request.getSiteName() != null) existingSite.setSiteName(request.getSiteName());
        if (request.getSiteType() != null) existingSite.setSiteType(request.getSiteType());
        existingSite.setDefault(request.isDefault());
        existingSite.setActive(request.isActive());

        return existingSite;
    }

    /**
     * Common validation logic for site fields.
     *
     * @param request the request DTO
     */
    private void validateCommonFields(Object request) {
        if (request == null) {
            throw new ValidationException("Request cannot be null");
        }

        if (request instanceof SiteCreateRequest createRequest) {
//            if (createRequest.getSiteCode() == null || createRequest.getSiteCode().isBlank()) {
//                throw new ValidationException("Site code is mandatory", "siteCode", null);
//            }
//            if (createRequest.getSiteName() == null || createRequest.getSiteName().isBlank()) {
//                throw new ValidationException("Site name is mandatory", "siteName", null);
//            }
            if (createRequest.getSiteType() == null) {
                throw new ValidationException("Site type is mandatory", "siteType", null);
            }
        }

        if (request instanceof SiteUpdateRequest updateRequest) {
            if (updateRequest.getSiteName() != null && updateRequest.getSiteName().isBlank()) {
                throw new ValidationException("Site name cannot be blank", "siteName", null);
            }
        }
    }
}
