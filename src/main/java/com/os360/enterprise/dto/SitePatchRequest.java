package com.os360.enterprise.dto;

import com.os360.enterprise.enumurations.SiteType;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * DTO used for partially updating an existing {@link com.os360.enterprise.entity.Site}.
 * <p>
 * This class supports HTTP PATCH operations, allowing selective updates
 * of the {@code Site} entity. Only non-null fields provided in this request
 * will be considered for updating.
 * <p>
 * Example use cases:
 * <ul>
 *     <li>Activating or deactivating a site.</li>
 *     <li>Renaming a site or changing its type.</li>
 *     <li>Updating the site’s associated company.</li>
 * </ul>
 * <p>
 * Validation:
 * <ul>
 *     <li>String fields have size limits consistent with entity constraints.</li>
 *     <li>No mandatory fields are required — this DTO supports partial updates.</li>
 * </ul>
 *
 * @author OpenSuite360
 * @since 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SitePatchRequest {

    /**
     * The unique identifier of the company to which this site belongs.
     * <p>
     * Optional — only used if the site is being reassigned to another company.
     */
    private UUID companyId;

    /**
     * A short alphanumeric code uniquely identifying the site within a company.
     * <p>
     * Optional — limited to 100 characters.
     */
    @Size(max = 100, message = "Site code must not exceed 100 characters")
    private String siteCode;

    /**
     * The human-readable name of the site (e.g., "Colombo Main Branch").
     * <p>
     * Optional — limited to 300 characters.
     */
    @Size(max = 300, message = "Site name must not exceed 300 characters")
    private String siteName;

    /**
     * Type or classification of the site (e.g., OFFICE, FACTORY, WAREHOUSE).
     * <p>
     * Optional — represented as an enumeration.
     */
    private SiteType siteType;

    /**
     * Indicates whether this site is the default site for the company.
     * <p>
     * Optional — defaults to {@code false} if not provided.
     */
    private boolean isDefault;

    /**
     * Indicates whether the site is currently active.
     * <p>
     * Optional — defaults to {@code true} if not provided.
     */
    private boolean isActive;
}
