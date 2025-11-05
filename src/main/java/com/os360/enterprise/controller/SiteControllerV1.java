package com.os360.enterprise.controller;

import com.os360.enterprise.dto.SiteCreateRequest;
import com.os360.enterprise.dto.SiteResponse;
import com.os360.enterprise.service.SiteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/sites")
@Tag(name = "Company", description = "Operations related to Sites, Version 1.0")
public class SiteControllerV1 {
    @Autowired
    private SiteService siteService;

    private SiteResponse siteResponse;

    private List<SiteResponse> sites;

    @GetMapping
    public ResponseEntity<List<SiteResponse>> getSites(
            @RequestBody SiteCreateRequest siteCreateRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(sites);
    }

    @Operation(summary = "Get a site by ID", description = "Returns the site details by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the site"),
            @ApiResponse(responseCode = "404", description = "Site not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Optional<SiteResponse>> getSite(
            @PathVariable UUID id) {

        return ResponseEntity.status(HttpStatus.OK).body(siteService.get(id));
    }

    @Operation(summary = "Create a site", description = "Returns the site details by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Site Created"),
            @ApiResponse(responseCode = "404", description = "Site Creation Failed")
    })
    @PostMapping
    public ResponseEntity<Optional<SiteResponse>> createSite(
            @RequestBody @Valid SiteCreateRequest siteCreateRequest) {

        return ResponseEntity.status(HttpStatus.CREATED).body(siteService.create(siteCreateRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SiteResponse> updateSite(
            @PathVariable UUID id,
            @RequestBody @Valid SiteCreateRequest siteCreateRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(siteResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SiteResponse> patchSite(
            @PathVariable UUID id,
            @RequestBody SiteCreateRequest sitePatchRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(siteResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSite(
            @PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
