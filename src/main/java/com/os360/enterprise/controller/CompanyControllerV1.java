package com.os360.enterprise.controller;

import com.os360.enterprise.dto.CompanyCreateRequest;
import com.os360.enterprise.dto.CompanyResponse;
import com.os360.enterprise.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/v2/companies")
public class CompanyControllerV1 {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyResponse companyResponse;

    private List<CompanyResponse> companies;

    @GetMapping
    public ResponseEntity<List<CompanyResponse>> getCompanies(
            @RequestBody CompanyCreateRequest companyCreateRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(companies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> getCompany(
            @PathVariable UUID id) {

        return ResponseEntity.status(HttpStatus.OK).body(companyService.get(id));
    }

    @PostMapping
    public ResponseEntity<CompanyResponse> createCompany(
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
