package com.os360.enterprise.controller;

import com.os360.enterprise.dto.CompanyCreateRequest;
import com.os360.enterprise.dto.CompanyResponse;
import com.os360.enterprise.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/companies")
@Tag(name = "Company", description = "Operations related to companies, Version 1.0")
public class CompanyControllerV1 {

    @Autowired
    private CompanyService companyService;


    private CompanyResponse companyResponse;

    private List<CompanyResponse> companies;

    @GetMapping
    public ResponseEntity<List<CompanyResponse>> getCompanies(
            @RequestBody CompanyCreateRequest companyCreateRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(companies);
    }

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

    @Operation(summary = "Create a company", description = "Returns the company details by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Company Created"),
            @ApiResponse(responseCode = "404", description = "Company Creation Failed")
    })
    @PostMapping
    public ResponseEntity<Optional<CompanyResponse>> createCompany(
            @RequestBody CompanyCreateRequest companyCreateRequest) {

        return ResponseEntity.status(HttpStatus.CREATED).body(companyService.create(companyCreateRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyResponse> updateCompany(
            @PathVariable UUID id,
            @RequestBody CompanyCreateRequest companyCreateRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(companyResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CompanyResponse> patchCompany(
            @PathVariable UUID id,
            @RequestBody CompanyCreateRequest companyPatchRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(companyResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteCompany(
            @PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
