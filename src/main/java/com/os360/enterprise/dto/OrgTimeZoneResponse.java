package com.os360.enterprise.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO representing organization time zone info for API responses.
 * Exposes only the relevant fields to the client.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrgTimeZoneResponse {

    /** Primary key of the time zone */
    private int id;

    /** Official IANA Time Zone ID, e.g., "Asia/Kolkata" */
    private String zoneId;

    /** ISO 3166-1 alpha-2 country code, e.g., "IN" */
    private String countryCode;

    /** Friendly display name for the time zone, e.g., "India Standard Time" */
    private String displayName;

    /** UTC offset in minutes, e.g., 330 for IST */
    private Integer utcOffsetMinutes;

    /** Short abbreviation, e.g., "IST" */
    private String abbreviation;

    /** Region or continent, e.g., "Asia", "Europe" */
    private String region;

    /** Notes or description for internal purposes */
    private String description;

    /** Indicates whether this time zone observes daylight saving time */
    private Boolean observesDst;

    /** Sorting order for dropdowns or UI lists */
    private Integer displayOrder;
}
