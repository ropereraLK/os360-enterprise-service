package com.os360.enterprise.testutils.builder;

import com.os360.enterprise.entity.Company;
import com.os360.enterprise.repository.CompanyRepository;

import java.time.LocalDate;
import java.util.Objects;

public class CompanyTestBuilder {
    private final String code;
    private final String name;
    private final String countryCode;

    private String logoUrl = "https://example.com/logo.png";
    private LocalDate validFrom = LocalDate.of(2025, 1, 1);
    private LocalDate validTo = LocalDate.of(2095, 12, 31);
    private Boolean isSystemCompany = false;


    private CompanyRepository repository;


    private CompanyTestBuilder(String code, String name, String countryCode) {
        if (Objects.isNull(code) || Objects.isNull(name) || Objects.isNull(countryCode)) {
            throw new IllegalArgumentException("code, name, and countryCode are mandatory");
        }
        this.code = code;
        this.name = name;
        this.countryCode = countryCode;
    }

    // 🔹 Static factory method - no `new` keyword needed
    public static CompanyTestBuilder builder(String code, String name, String countryCode) {
        return new CompanyTestBuilder(code, name, countryCode);
    }

    public CompanyTestBuilder withLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
        return this;
    }

    public CompanyTestBuilder withValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
        return this;
    }

    public CompanyTestBuilder withValidTo(LocalDate validTo) {
        this.validTo = validTo;
        return this;
    }

    public CompanyTestBuilder withSystemCompany(Boolean isSystemCompany) {
        this.isSystemCompany = isSystemCompany;
        return this;
    }

    public CompanyTestBuilder withRepository(CompanyRepository repository) {
        this.repository = repository;
        return this;
    }

    public Company build() {
        Company company = new Company();
        company.setCode(code);
        company.setName(name);
        company.setCountryCode(countryCode);
        company.setLogoUrl(logoUrl);
        company.setValidFrom(validFrom);
        company.setValidTo(validTo);
        company.setSystemCompany(isSystemCompany);
        return company;
    }

    public Company buildAndPersist() {
        if (repository == null) {
            throw new IllegalStateException("Repository must be set before calling buildAndPersist()");
        }
        return repository.save(build());
    }
}
