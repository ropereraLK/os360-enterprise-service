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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SiteCreateRequest {
    @NotNull(message = "Company ID is required")
    private UUID companyId;

    @NotBlank(message = "Site code is required")
    @Size(max = 100, message = "Site code must not exceed 100 characters")
    private String code;

    @NotBlank(message = "Site name is required")
    @Size(max = 300, message = "Site name must not exceed 300 characters")
    private String name;

    @NotNull(message = "Site type is required")
    private SiteType siteType;

    private boolean isDefault;




}
