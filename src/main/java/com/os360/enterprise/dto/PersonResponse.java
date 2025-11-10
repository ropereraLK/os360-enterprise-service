package com.os360.enterprise.dto;

import com.os360.enterprise.enumurations.PersonTitle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Response DTO for {@link com.os360.enterprise.entity.Person}.
 * <p>
 * Contains fields exposed to clients or API consumers.
 * This class is used in GET, POST, PUT, PATCH responses.
 * </p>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonResponse {

    private UUID id;

    private String externalSystem;

    private String externalId;

    private String countryCode;

    private PersonTitle title;

    private String firstName;

    private String middleName;

    private String lastName;

    private String preferredName;

    private String gender;

    private LocalDate dateOfBirth;

    private String profileImageUrl;

    private boolean isActive;

    private boolean isDeleted;

    private OffsetDateTime createdAt;

    private UUID createdBy;

    private OffsetDateTime lastModifiedAt;

    private UUID lastModifiedBy;
}
