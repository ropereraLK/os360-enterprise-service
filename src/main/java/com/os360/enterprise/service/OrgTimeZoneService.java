package com.os360.enterprise.service;

import com.os360.enterprise.dto.OrgTimeZoneResponse;
import com.os360.enterprise.entity.OrgTimeZone;
import com.os360.enterprise.exception.domain.EntityNotFoundException;
import com.os360.enterprise.repository.OrgTimeZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for managing organization time zones.
 * <p>
 * Provides methods to:
 * - Retrieve all active time zones
 * - Retrieve a time zone by its ID
 * - Retrieve a time zone by its IANA zone code
 * <p>
 * Returns OrgTimeZoneResponse DTOs to avoid exposing internal entity details.
 */
@Service
public class OrgTimeZoneService {

    @Autowired
    private OrgTimeZoneRepository orgTimeZoneRepository;

    /**
     * Retrieve all active time zones and map them to DTOs.
     *
     * @return list of OrgTimeZoneResponse
     */
    public List<OrgTimeZoneResponse> getAllActiveTimeZones() {
        return orgTimeZoneRepository.findAllByIsActiveTrue()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    /**
     * Retrieve a time zone by its database ID and map to DTO.
     *
     * @param id primary key of the time zone
     * @return OrgTimeZoneResponse
     * @throws EntityNotFoundException if not found
     */
    public OrgTimeZoneResponse getById(int id) {
        OrgTimeZone tz = orgTimeZoneRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(OrgTimeZone.class, id));
        return toResponse(tz);
    }

    /**
     * Retrieve a time zone by its IANA zone ID and map to DTO.
     *
     * @param zoneId the IANA zone ID (e.g., "Asia/Kolkata")
     * @return OrgTimeZoneResponse
     * @throws EntityNotFoundException if not found
     */
    public OrgTimeZoneResponse getByZoneId(String zoneId) {
        OrgTimeZone tz = orgTimeZoneRepository.findByZoneId(zoneId)
                .orElseThrow(() -> new EntityNotFoundException(OrgTimeZone.class, zoneId));
        return toResponse(tz);
    }

    /**
     * Map OrgTimeZone entity to OrgTimeZoneResponse DTO.
     *
     * @param tz the entity
     * @return DTO with selected fields
     */
    private OrgTimeZoneResponse toResponse(OrgTimeZone tz) {
        return new OrgTimeZoneResponse(
                tz.getId(),
                tz.getZoneId(),
                tz.getCountryCode(),
                tz.getDisplayName(),
                tz.getUtcOffsetMinutes(),
                tz.getAbbreviation(),
                tz.getRegion(),
                tz.getDescription(),
                tz.getObservesDst(),
                tz.getDisplayOrder()
        );
    }
}
