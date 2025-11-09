package com.os360.enterprise.controller;

import com.os360.enterprise.dto.CompanyCreateRequest;
import com.os360.enterprise.dto.CompanyPatchRequest;
import com.os360.enterprise.dto.CompanyResponse;
import com.os360.enterprise.dto.CompanyUpdateRequest;
import com.os360.enterprise.entity.Company;
import com.os360.enterprise.exception.domain.EntityNotFoundException;
import com.os360.enterprise.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


/**
 * REST controller for managing Company entities.
 * <p>
 * Provides CRUD operations (Create, Read, Update, Delete) for companies.
 * All endpoints are versioned under "/api/v1/companies".
 * <p>
 * Responsibilities:
 * - Delegate business logic to {@link CompanyService}.
 * - Handle HTTP requests and responses for company operations.
 * - Provide OpenAPI/Swagger documentation using annotations.
 * <p>
 * Note:
 * - PATCH endpoints support partial updates.
 * - DELETE endpoint performs a soft delete.
 */
@RestController
@RequestMapping("/api/v1/companies")
@Tag(name = "Company", description = "Operations related to companies, Version 1.0")
public class CompanyControllerV1 {

    @Autowired
    private CompanyService companyService;

    private CompanyResponse companyResponse;
    private List<CompanyResponse> companies;

    /**
     * Retrieves a list of companies based on provided criteria.
     *
     * @param companyCreateRequest The request body containing filter/search criteria.
     * @return List of {@link CompanyResponse} matching the criteria.
     */
    @GetMapping
    public ResponseEntity<List<CompanyResponse>> getCompanies(
            @RequestBody CompanyCreateRequest companyCreateRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(companies);
    }

    /**
     * Retrieves a single company by its unique ID.
     *
     * @param id UUID of the company to retrieve.
     * @return Optional containing {@link CompanyResponse} if found.
     * @throws EntityNotFoundException if no company exists with the given ID.
     */
    @Operation(summary = "Get a company by ID", description = "Returns the company details by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the company"),
            @ApiResponse(responseCode = "404", description = "Company not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Optional<CompanyResponse>> getCompany(
            @PathVariable UUID id) {

        return ResponseEntity.status(HttpStatus.OK).body(companyService.get(id));
    }

    /**
     * Creates a new company.
     *
     * @param companyCreateRequest Request body containing company details.
     * @return Optional containing the created {@link CompanyResponse}.
     * @throws IllegalArgumentException if creation fails due to validation or business rules.
     */
    @Operation(summary = "Create a company", description = "Returns the company details by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Company Created"),
            @ApiResponse(responseCode = "404", description = "Company Creation Failed")
    })
    @PostMapping
    public ResponseEntity<Optional<CompanyResponse>> createCompany(
            @RequestBody @Valid CompanyCreateRequest companyCreateRequest) {

        return ResponseEntity.status(HttpStatus.CREATED).body(companyService.create(companyCreateRequest));
    }

    /**
     * Updates an existing company with full replacement of provided fields.
     * <p>
     * All required fields in {@link CompanyUpdateRequest} must be provided.
     *
     * @param id UUID of the company to update.
     * @param companyUpdateRequest Request body containing updated company details.
     * @return Updated {@link CompanyResponse}.
     * @throws EntityNotFoundException if no company exists with the given ID.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CompanyResponse> updateCompany(
            @PathVariable UUID id,
            @RequestBody @Valid CompanyUpdateRequest companyUpdateRequest) {
        Optional<CompanyResponse> updated = companyService.update(id, companyUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(updated.orElseThrow(() -> new EntityNotFoundException(Company.class, id)));
    }

    /**
     * Partially updates an existing company.
     * <p>
     * Only non-null fields in {@link CompanyPatchRequest} will be updated.
     * This supports PATCH-style partial updates.
     *
     * @param id UUID of the company to patch.
     * @param companyPatchRequest Request body containing fields to update.
     * @return Updated {@link CompanyResponse}.
     * @throws EntityNotFoundException if no company exists with the given ID.
     */
    @PatchMapping("/{id}")
    @Operation(summary = "Patch company", description = "Updates partial fields of a company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Company updated successfully"),
            @ApiResponse(responseCode = "404", description = "Company not found")
    })
    public ResponseEntity<CompanyResponse> patchCompany(
            @PathVariable UUID id,
            @RequestBody CompanyPatchRequest companyPatchRequest) {

        Optional<CompanyResponse> updated = companyService.patch(id, companyPatchRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(updated.orElseThrow(() -> new EntityNotFoundException(Company.class, id)));
    }

    /**
     * Soft deletes a company by setting its deleted flags and timestamps.
     * <p>
     * Does not permanently remove the entity from the database.
     *
     * @param id UUID of the company to delete.
     * @return {@link ResponseEntity} with HTTP 204 (No Content) status.
     * @throws EntityNotFoundException if no company exists with the given ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteCompany(
            @PathVariable UUID id) {
        companyService.softDelete(id);
        return ResponseEntity.noContent().build();
    }
}

