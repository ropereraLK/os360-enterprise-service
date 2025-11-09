package com.os360.enterprise.controller;

import com.os360.enterprise.entity.OrgTimeZone;
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
 * REST controller for managing organization time zones.
 * Provides endpoints to:
 * - List all active time zones
 * - Get a time zone by its ID
 * - Get a time zone by its IANA zone ID
 */
@RestController
@RequestMapping("/api/v1/timezones")
@Tag(name = "TimeZone", description = "Operations related to organization time zones, Version 1.0")
public class OrgTimeZoneControllerV1 {

    @Autowired
    private OrgTimeZoneService orgTimeZoneService;

    /**
     * Get all active organization time zones.
     *
     * @return list of active OrgTimeZone entities
     */
    @GetMapping
    @Operation(summary = "Get all active time zones", description = "Returns a list of all active organization time zones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of time zones returned successfully")
    })
    public ResponseEntity<List<OrgTimeZone>> getAllTimeZones() {
        List<OrgTimeZone> timeZones = orgTimeZoneService.getAllActiveTimeZones();
        return ResponseEntity.ok(timeZones);
    }

    /**
     * Get a time zone by its database ID.
     *
     * @param id the primary key of the time zone
     * @return the OrgTimeZone entity
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get time zone by ID", description = "Returns a specific time zone by its database ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Time zone found"),
            @ApiResponse(responseCode = "404", description = "Time zone not found")
    })
    public ResponseEntity<OrgTimeZone> getTimeZoneById(@PathVariable int id) {
        OrgTimeZone timeZone = orgTimeZoneService.getById(id);
        return ResponseEntity.ok(timeZone);
    }

    /**
     * Get a time zone by its IANA zone ID.
     *
     * @param zoneId the IANA zone ID (e.g., "Asia/Kolkata")
     * @return the OrgTimeZone entity
     */
    @GetMapping("/code/{zoneId}")
    @Operation(summary = "Get time zone by zone ID", description = "Returns a specific time zone by its IANA zone ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Time zone found"),
            @ApiResponse(responseCode = "404", description = "Time zone not found")
    })
    public ResponseEntity<OrgTimeZone> getTimeZoneByZoneId(@PathVariable String zoneId) {
        OrgTimeZone timeZone = orgTimeZoneService.getByZoneId(zoneId);
        return ResponseEntity.ok(timeZone);
    }
}
