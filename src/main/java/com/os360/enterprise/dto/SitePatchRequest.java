package com.os360.enterprise.dto;

import com.os360.enterprise.enumurations.SiteType;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SitePatchRequest {

    private UUID companyId;

    @Size(max = 100, message = "Site code must not exceed 100 characters")
    private String siteCode;

    @Size(max = 300, message = "Site name must not exceed 300 characters")
    private String siteName;

    private SiteType siteType;

    private boolean isDefault;

    private boolean isActive;
}
