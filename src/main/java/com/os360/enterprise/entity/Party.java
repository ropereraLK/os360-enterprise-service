package com.os360.enterprise.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "party")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Party {
    //KEYS
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "external_system")
    private String externalSystem;

    @Column(name = "external_id")
    private String externalId;

    //DATA
    @Column(name = "party_type", nullable = false)
    private String partyType;

    @Column(name = "country_code", length = 2)
    private String countryCode;// ISO Country Code 2 Letters

    //TODO
    //Addresses, emails and phones are handled in CommunicationMethod
    //    @OneToMany(mappedBy = "party")
    //    private Set<CommunicationMethod> communicationMethods;

//    @OneToMany(mappedBy = "party")
//    private Set<Document> documents;

    //VALIDATIONS
    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted;

    // AUDIT
    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt;

    @Column(name = "created_by")
    private UUID createdBy;

    @Column(name = "last_modified_at")
    private OffsetDateTime lastModifiedAt;

    @Column(name = "last_modified_by")
    private UUID lastModifiedBy;

    @Column(name = "deleted_at")
    private OffsetDateTime deletedAt;

    @Column(name = "deleted_by")
    private UUID deletedBy;

    @Column(name = "version", nullable = false)
    @Version
    private Long version;

}
