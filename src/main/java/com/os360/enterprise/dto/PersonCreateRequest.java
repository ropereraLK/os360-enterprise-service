package com.os360.enterprise.dto;

import com.os360.enterprise.enumurations.PersonTitle;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Request DTO for creating a new {@link com.os360.enterprise.entity.Person}.
 * <p>
 * Used in POST operations to capture user input when registering or adding
 * a new person (e.g., employee, customer, or contact).
 * </p>
 *
 * <p><b>Validation Rules:</b></p>
 * <ul>
 *   <li>{@code firstName} and {@code lastName} are required.</li>
 *   <li>String lengths are constrained to avoid data overflow.</li>
 *   <li>{@code dateOfBirth}, if provided, must be in the past.</li>
 * </ul>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonCreateRequest {

    /** Optional external system reference (integration support). */
    private String externalSystem;

    /** Optional external ID reference (e.g., ID from HR system). */
    private String externalId;

    /** ISO 2-letter country code (e.g., "US", "LK"). */
    @Size(max = 2, message = "Country code must be 2 characters")
    private String countryCode;

    /** The person's title or honorific (e.g., Mr., Ms., Dr., Prof.). */
    private PersonTitle title;

    /** Given name (required). */
    @NotBlank(message = "First name is required")
    @Size(max = 100, message = "First name must not exceed 100 characters")
    private String firstName;

    /** Middle name (optional). */
    @Size(max = 100, message = "Middle name must not exceed 100 characters")
    private String middleName;

    /** Surname or family name (required). */
    @NotBlank(message = "Last name is required")
    @Size(max = 100, message = "Last name must not exceed 100 characters")
    private String lastName;

    /** Preferred or display name (optional). */
    @Size(max = 100, message = "Preferred name must not exceed 100 characters")
    private String preferredName;

    /** Gender (optional). Example values: "Male", "Female", "Other". */
    @Size(max = 20, message = "Gender must not exceed 20 characters")
    private String gender;

    /** Date of birth (optional, must be in the past). */
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    /** URL to the profile image or avatar (optional). */
    @Size(max = 255, message = "Profile image URL must not exceed 255 characters")
    private String profileImageUrl;

    /** Indicates if this person is active. Defaults to true. */
    private boolean isActive = true;

    /** Optional company association for linked profiles (if applicable). */
    private UUID companyId;
}
