package com.os360.enterprise.dto;

import com.os360.enterprise.enumurations.SiteType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * DTO used for performing a full update (HTTP PUT) of an existing
 * {@link com.os360.enterprise.entity.Site}.
 * <p>
 * This class represents a complete update payload where all fields are expected
 * to be provided, replacing the existing values of the target {@code Site} entity.
 * <p>
 * Example use cases:
 * <ul>
 *     <li>Updating all details of a site such as name, code, and type.</li>
 *     <li>Reassigning a site to another company.</li>
 *     <li>Changing the active or default status of a site.</li>
 * </ul>
 * <p>
 * Validation:
 * <ul>
 *     <li>String length constraints align with the underlying entity definitions.</li>
 *     <li>All key fields must be provided for a successful update.</li>
 * </ul>
 *
 * @author
 * OpenSuite360
 * @since 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SiteUpdateRequest {

    /**
     * The unique identifier of the company to which this site belongs.
     * <p>
     * Required — cannot be null during a full update.
     */
    @NotNull(message = "Company ID is required")
    private UUID companyId;

    /**
     * A unique code identifying the site within the company.
     * <p>
     * Required — up to 100 characters.
     */
    @NotBlank(message = "Site code is required")
    @Size(max = 100, message = "Site code must not exceed 100 characters")
    private String siteCode;

    /**
     * The human-readable name of the site (e.g., “Main Factory” or “Head Office”).
     * <p>
     * Required — up to 300 characters.
     */
    @NotBlank(message = "Site name is required")
    @Size(max = 300, message = "Site name must not exceed 300 characters")
    private String siteName;

    /**
     * Type or classification of the site (e.g., OFFICE, WAREHOUSE, PLANT).
     * <p>
     * Required — must be a valid {@link SiteType} enum value.
     */
    @NotNull(message = "Site type is required")
    private SiteType siteType;

    /**
     * Indicates whether this site is the default site of the associated company.
     */
    private boolean isDefault;

    /**
     * Indicates whether this site is currently active.
     */
    private boolean isActive;
}