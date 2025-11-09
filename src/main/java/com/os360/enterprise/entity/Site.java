package com.os360.enterprise.entity;

import com.os360.enterprise.enumurations.SiteType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Entity representing a physical or logical site/office within a company.
 *
 * Stores core site information like code, name, type, default status, and active/deleted flags.
 * Tracks audit information including creation, modification, and deletion details.
 * Supports optimistic locking via the `version` field.
 *
 * Indexes:
 * - site_idx_company: for fast lookup of sites by company.
 * - site_idx_site_code: for quick lookup by site code.
 *
 * TODOs indicate planned relationships and attributes to be implemented later:
 * - Time zone per site (`siteTimeZone`), linked to `CompanyTimeZone`.
 * - Supported languages (`companyLanguages`) for each site.
 * - Communication methods (`communicationMethods`) list.
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "site",
        indexes = {
                @Index(name = "site_idx_company", columnList = "company_id"),
                @Index(name = "site_idx_site_code", columnList = "site_code")
        })
public class Site {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(name = "site_code", nullable = false)
    private String siteCode;

    @Column(name = "site_name", nullable = false, length = 300)
    private String siteName;

    @Enumerated(EnumType.STRING)  // store as string in DB
    @Column(name = "site_type", nullable = false)
    private SiteType siteType;

//TODO
//    @ManyToOne
//    @JoinColumn(name = "company_time_zone_id", nullable = false)
//    private CompanyTimeZone siteTimeZone;
//TODO
//    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
//    private Set<CompanyLanguage> companyLanguages;

// TODO
//    //Attributes
//    private List<CommunicationMethod> communicationMethods;

    @Column(name = "is_default", nullable = false)
    private boolean isDefault;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted;

    // AUDIT
    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt;

    @Column(name = "created_by")
    private UUID createdBy;

    @Column(name = "last_modified_at")
    private OffsetDateTime lastModifiedAt;

    @Column(name = "last_modified_by")
    private UUID lastModifiedBy;

    @Column(name = "deleted_at")
    private OffsetDateTime deletedAt;

    @Column(name = "deleted_by")
    private UUID deletedBy;

    @Column(name = "version", nullable = false)
    @Version
    private Long version;
}
