package com.os360.enterprise.controller;

import com.os360.enterprise.dto.OrgTimeZoneResponse;
import com.os360.enterprise.service.OrgTimeZoneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for organization time zones (OrgTimeZone).
 * <p>
 * Exposes endpoints to retrieve all active time zones, or get a time zone
 * by ID or by IANA zone code. All responses use OrgTimeZoneResponse DTOs.
 * </p>
 */
@RestController
@RequestMapping("/api/v1/timezones")
@Tag(name = "OrgTimeZone", description = "Operations related to organization time zones, Version 1.0")
public class OrgTimeZoneControllerV1 {

    @Autowired
    private OrgTimeZoneService orgTimeZoneService;

    /**
     * Retrieve all active time zones.
     *
     * @return List of OrgTimeZoneResponse
     */
    @GetMapping
    @Operation(summary = "Get all active time zones", description = "Returns all active organization time zones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Time zones retrieved successfully")
    })
    public ResponseEntity<List<OrgTimeZoneResponse>> getAllTimeZones() {
        return ResponseEntity.ok(orgTimeZoneService.getAllActiveTimeZones());
    }

    /**
     * Retrieve a time zone by its database ID.
     *
     * @param id Time zone primary key
     * @return OrgTimeZoneResponse
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get time zone by ID", description = "Returns a single time zone by database ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Time zone found"),
            @ApiResponse(responseCode = "404", description = "Time zone not found")
    })
    public ResponseEntity<OrgTimeZoneResponse> getTimeZoneById(@PathVariable int id) {
        return ResponseEntity.ok(orgTimeZoneService.getById(id));
    }

    /**
     * Retrieve a time zone by its IANA zone ID.
     *
     * @param zoneId Time zone string ID (e.g., "Asia/Kolkata")
     * @return OrgTimeZoneResponse
     */
    @GetMapping("/by-zone/{zoneId}")
    @Operation(summary = "Get time zone by zone ID", description = "Returns a single time zone by IANA zone ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Time zone found"),
            @ApiResponse(responseCode = "404", description = "Time zone not found")
    })
    public ResponseEntity<OrgTimeZoneResponse> getTimeZoneByZoneId(@PathVariable String zoneId) {
        return ResponseEntity.ok(orgTimeZoneService.getByZoneId(zoneId));
    }
}
