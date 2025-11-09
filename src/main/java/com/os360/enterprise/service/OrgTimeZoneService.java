package com.os360.enterprise.service;

import com.os360.enterprise.entity.OrgTimeZone;
import com.os360.enterprise.exception.domain.EntityNotFoundException;
import com.os360.enterprise.repository.OrgTimeZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for managing organization time zones.
 * Provides methods to:
 * - Retrieve all active time zones
 * - Retrieve a time zone by its ID
 * - Retrieve a time zone by its IANA zone code
 */
@Service
public class OrgTimeZoneService {

    @Autowired
    private OrgTimeZoneRepository orgTimeZoneRepository;

    /**
     * Retrieve all active time zones.
     *
     * @return list of active OrgTimeZone entities
     */
    public List<OrgTimeZone> getAllActiveTimeZones() {
        return orgTimeZoneRepository.findAllByIsActiveTrue();
    }

    /**
     * Retrieve a time zone by its database ID.
     *
     * @param id the primary key of the time zone
     * @return the OrgTimeZone entity
     * @throws EntityNotFoundException if the time zone is not found
     */
    public OrgTimeZone getById(int id) {
        return orgTimeZoneRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(OrgTimeZone.class, id));
    }

    /**
     * Retrieve a time zone by its IANA zone ID (e.g., "Asia/Kolkata").
     *
     * @param zoneId the IANA time zone ID
     * @return the OrgTimeZone entity
     * @throws EntityNotFoundException if the time zone is not found
     */
    public OrgTimeZone getByZoneId(String zoneId) {
        return orgTimeZoneRepository.findByZoneId(zoneId)
                .orElseThrow(() -> new EntityNotFoundException(OrgTimeZone.class, zoneId));
    }
}
