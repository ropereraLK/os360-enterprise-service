package com.os360.enterprise.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.AssertTrue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyCreateRequest {
    private UUID parentCompany;

    @NotBlank(message = "Code is mandatory")
    @Size(max = 100, message = "Code cannot exceed 100 characters")
    private String code;

    @NotBlank(message = "Name is mandatory")
    @Size(max = 300, message = "Name cannot exceed 300 characters")
    private String name;

    @NotBlank(message = "Country code is mandatory")
    @Size(min = 2, max = 2, message = "Country code must be 2 characters")
    private String countryCode; // mandatory

    @Size(max = 100, message = "External system cannot exceed 100 characters")
    private String externalSystem;

    @Size(max = 100, message = "External ID cannot exceed 100 characters")
    private String externalId;

    private String logoUrl;
    //TODO
    // private Set<Site> sites;
    // private Set<CompanyTimeZone> timeZones;
    private Boolean isSystemCompany;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate validFrom;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate validTo;

    // Cross-field validation: validTo >= validFrom
    @AssertTrue(message = "validTo must be after or equal to validFrom")
    public boolean isValidDates() {
        if (validFrom == null || validTo == null) {
            return true; // skip check if one of them is null
        }
        return !validTo.isBefore(validFrom);
    }
}
