package com.os360.enterprise.dto;

import com.os360.enterprise.enumurations.SiteType;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
public class SiteResponse {

    private UUID id;

    // Reference to the company this site belongs to
    private UUID companyId;

    private String code;        // siteCode
    private String name;        // siteName
    private SiteType siteType;  // enum

    private boolean isDefault;
    private boolean isActive;
    private boolean isDeleted;

    // Audit fields
    private OffsetDateTime createdAt;
    private String createdBy;
    private OffsetDateTime lastModifiedAt;
    private String lastModifiedBy;
}
