package com.os360.enterprise.controller;

import com.os360.enterprise.dto.SiteCreateRequest;
import com.os360.enterprise.dto.SitePatchRequest;
import com.os360.enterprise.dto.SiteResponse;
import com.os360.enterprise.dto.SiteUpdateRequest;
import com.os360.enterprise.entity.Site;
import com.os360.enterprise.exception.domain.EntityNotFoundException;
import com.os360.enterprise.service.SiteService;
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
 * REST controller for managing Site entities.
 * <p>
 * Provides CRUD operations (Create, Read, Update, Delete) for sites.
 * All endpoints are versioned under "/api/v1/sites".
 * <p>
 * Responsibilities:
 * - Delegate business logic to {@link SiteService}.
 * - Handle HTTP requests and responses for site operations.
 * - Provide OpenAPI/Swagger documentation using annotations.
 * <p>
 * Note:
 * - PATCH endpoints support partial updates.
 * - DELETE endpoint performs a soft delete.
 */
@RestController
@RequestMapping("/api/v1/sites")
@Tag(name = "Site", description = "Operations related to company sites, Version 1.0")
public class SiteControllerV1 {

    @Autowired
    private SiteService siteService;

    private SiteResponse siteResponse;
    private List<SiteResponse> sites;

    /**
     * Retrieves a list of sites for a company or based on filter criteria.
     *
     * @param siteCreateRequest Optional filter criteria.
     * @return List of {@link SiteResponse} matching the criteria.
     */
    @GetMapping
    public ResponseEntity<List<SiteResponse>> getSites(
            @RequestBody(required = false) SiteCreateRequest siteCreateRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(sites);
    }

    /**
     * Retrieves a single site by its unique ID.
     *
     * @param id UUID of the site to retrieve.
     * @return Optional containing {@link SiteResponse} if found.
     * @throws EntityNotFoundException if no site exists with the given ID.
     */
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

    /**
     * Creates a new site.
     *
     * @param siteCreateRequest Request body containing site details.
     * @return Optional containing the created {@link SiteResponse}.
     */
    @Operation(summary = "Create a site", description = "Creates a new site for a company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Site Created"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @PostMapping
    public ResponseEntity<Optional<SiteResponse>> createSite(
            @RequestBody @Valid SiteCreateRequest siteCreateRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(siteService.create(siteCreateRequest));
    }

    /**
     * Updates an existing site with full replacement of provided fields.
     *
     * @param id UUID of the site to update.
     * @param siteUpdateRequest Request body containing updated site details.
     * @return Updated {@link SiteResponse}.
     * @throws EntityNotFoundException if no site exists with the given ID.
     */
    @PutMapping("/{id}")
    public ResponseEntity<SiteResponse> updateSite(
            @PathVariable UUID id,
            @RequestBody @Valid SiteUpdateRequest siteUpdateRequest) {
        Optional<SiteResponse> updated = siteService.update(id, siteUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(updated.orElseThrow(() -> new EntityNotFoundException(Site.class, id)));
    }

    /**
     * Partially updates an existing site.
     * Only non-null fields in {@link SitePatchRequest} will be updated.
     *
     * @param id UUID of the site to patch.
     * @param sitePatchRequest Request body containing fields to update.
     * @return Updated {@link SiteResponse}.
     * @throws EntityNotFoundException if no site exists with the given ID.
     */
    @PatchMapping("/{id}")
    @Operation(summary = "Patch site", description = "Updates partial fields of a site")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Site updated successfully"),
            @ApiResponse(responseCode = "404", description = "Site not found")
    })
    public ResponseEntity<SiteResponse> patchSite(
            @PathVariable UUID id,
            @RequestBody SitePatchRequest sitePatchRequest) {

        Optional<SiteResponse> updated = siteService.patch(id, sitePatchRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(updated.orElseThrow(() -> new EntityNotFoundException(Site.class, id)));
    }

    /**
     * Soft deletes a site by setting its deleted flags and timestamps.
     *
     * @param id UUID of the site to delete.
     * @return {@link ResponseEntity} with HTTP 204 (No Content) status.
     * @throws EntityNotFoundException if no site exists with the given ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSite(
            @PathVariable UUID id) {
        siteService.softDelete(id);
        return ResponseEntity.noContent().build();
    }
}
