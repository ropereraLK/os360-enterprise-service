package com.os360.enterprise.dto;

import com.os360.enterprise.entity.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyResponse {
    private String code;
    private String name;
    private String externalSystem;
    private String externalId;
    private Company parentCompany;
    private boolean isActive;
    private String logoUrl;
    private LocalDate validFrom;
    private LocalDate validTo;
    private boolean isSystemCompany;
}
