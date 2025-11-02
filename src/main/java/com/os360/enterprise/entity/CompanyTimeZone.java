package com.os360.enterprise.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "company_time_zone")
public class CompanyTimeZone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(name = "time_zone", nullable = false)
    private String timeZone; // ISO ID like "Asia/Colombo"

    @Column(name = "is_default", nullable = false)
    private boolean isDefault;
}
