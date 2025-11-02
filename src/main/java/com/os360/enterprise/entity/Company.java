package com.os360.enterprise.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "company")
public class Company extends Party {

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "name", nullable = false, length = 300)
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_company_id")
    private Company parentCompany;

    @Column(name = "logo_url")
    private String logoUrl;

    // Relationships
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private Set<Site> Sites;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private Set<CompanyTimeZone> timeZones;
//TODO
//    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
//    private Set<CompanyLanguage> companyLanguages;

    //Validations
    @Column(name = "valid_from", nullable = false)
    private LocalDate validFrom;

    @Column(name = "valid_to", nullable = false)
    private LocalDate validTo;

    @Column(name = "is_system_company", nullable = false)
    private boolean isSystemCompany;
}
