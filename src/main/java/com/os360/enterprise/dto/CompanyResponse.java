package com.os360.enterprise.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.os360.enterprise.entity.Company;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyResponse {
    @Schema(description = "Company code", example = "OS360")
    private String code;

    @Schema(description = "Company name", example = "Open Suite 360")
    private String name;

    @Schema(description = "External system name", example = "SAP")
    private String externalSystem;

    @Schema(description = "External ID in the system", example = "12345")
    private String externalId;

    @Schema(description = "Parent company UUID", example = "d290f1ee-6c54-4b01-90e6-d701748f0851")
    private UUID parentCompanyId;

    @Schema(description = "Whether the company is active", example = "true")
    private boolean isActive;

    @Schema(description = "Company logo URL", example = "https://example.com/logo.png")
    private String logoUrl;

    @Schema(description = "Valid from date", example = "2025-11-05")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate validFrom;

    @Schema(description = "Valid to date", example = "2030-12-31")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate validTo;

    @Schema(description = "Whether this is the system company", example = "true")
    private boolean isSystemCompany;
}
