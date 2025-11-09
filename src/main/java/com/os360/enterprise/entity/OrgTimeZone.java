package com.os360.enterprise.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity representing a company or office time zone.
 * <p>
 * Stores standard IANA time zone ID, country, display information, and optional metadata.
 * Fully compatible with java.time.ZoneId for timestamp conversions.
 * </p>
 */
@Entity
@Table(name = "org_time_zone")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrgTimeZone {

    /** Primary key */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Official IANA Time Zone ID, e.g., "Asia/Kolkata".
     * This field is fully compatible with java.time.ZoneId.
     */
    @Column(name = "zone_id", unique = true, nullable = false, length = 50)
    private String zoneId;

    /**
     * ISO 3166-1 alpha-2 country code, e.g., "IN" for India.
     * Useful for filtering or grouping by country.
     */
    @Column(name = "country_code", length = 2)
    private String countryCode;

    /**
     * Friendly display name, e.g., "India Standard Time".
     * Can be used in dropdowns or UI.
     */
    @Column(name = "display_name", length = 100)
    private String displayName;

    /**
     * UTC offset in minutes.
     * Optional, can be used for sorting or quick calculations.
     */
    @Column(name = "utc_offset_minutes")
    private Integer utcOffsetMinutes;

    /** Short abbreviation for the time zone, e.g., "IST", "EST". Optional. */
    @Column(name = "abbreviation", length = 10)
    private String abbreviation;

    /** Region or continent, e.g., "Asia", "Europe". Optional, useful for grouping. */
    @Column(name = "region", length = 50)
    private String region;

    /** Notes or description for internal purposes */
    @Column(name = "description", length = 255)
    private String description;

    /** Indicates whether this time zone observes daylight saving time */
    @Column(name = "observes_dst")
    private Boolean observesDst;

    /** Sorting order for dropdowns or UI lists */
    @Column(name = "display_order")
    private Integer displayOrder;

    /** Indicates if this time zone is active/available for selection */
    @Column(name = "is_active", nullable = false)
    private boolean isActive;

}