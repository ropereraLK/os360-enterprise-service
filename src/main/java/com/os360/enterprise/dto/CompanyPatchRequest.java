package com.os360.enterprise.dto;

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
public class CompanyPatchRequest {

    private String name;

    private String externalSystem;

    private String externalId;

    private UUID parentCompanyId;

    private boolean isActive;

    private String logoUrl;

    private LocalDate validFrom;

    private LocalDate validTo;

    private boolean isSystemCompany;

    private boolean isDeleted;
}
