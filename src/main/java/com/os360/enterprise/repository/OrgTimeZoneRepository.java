package com.os360.enterprise.repository;

import com.os360.enterprise.entity.OrgTimeZone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for OrgTimeZone entity.
 * Provides only the required methods:
 * - Get all time zones
 * - Get by ID
 * - Get by zone code
 */
@Repository
public interface OrgTimeZoneRepository extends JpaRepository<OrgTimeZone, Integer> {
    /**
     * Find a time zone by its IANA zone ID (e.g., "Asia/Kolkata").
     *
     * @param zoneId the IANA time zone ID
     * @return optional OrgTimeZone
     */
    Optional<OrgTimeZone> findByZoneId(String zoneId);

    /**
     * Get all active time zones.
     * Can be filtered later if needed.
     *
     * @return list of OrgTimeZone
     */
    List<OrgTimeZone> findAllByIsActiveTrue();
}
