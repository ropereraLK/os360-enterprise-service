package com.os360.enterprise.entity;

import com.os360.enterprise.enumurations.PersonTitle;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Entity representing a person in the organization or external system.
 * <p>
 * Extends the {@link Party} base class to inherit common attributes such as
 * identifiers, audit information, and active/deleted flags.
 * <br>
 * This model can represent various human entities such as employees, customers,
 * vendors, or contacts within the system.
 * </p>
 *
 * <p><b>Inheritance Strategy:</b> JOINED — ensures normalized data storage across
 * parent and subclass tables. Each subclass (e.g., Person, Organization) has its
 * own table joined with the PARTY table via the shared primary key.</p>
 *
 * <p><b>Examples:</b></p>
 * <ul>
 *   <li>Employee profiles</li>
 *   <li>Customer or vendor contacts</li>
 *   <li>System users tied to HR modules</li>
 * </ul>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "person")
public class Person extends Party {

    /** The person's given name. */
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    /** The person’s middle name (optional). */
    @Column(name = "middle_name", length = 100)
    private String middleName;

    /** The person’s surname or family name. */
    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    /** Preferred or display name (optional). Used for informal or preferred addressing. */
    @Column(name = "preferred_name", length = 100)
    private String preferredName;

    /** Gender of the person. Example values: "Male", "Female", "Other". */
    @Column(name = "gender", length = 20)
    private String gender;

    /** Date of birth of the person (YYYY-MM-DD). */
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

//TODO
//    /** National identification or passport number (optional). */
//    @Column(name = "national_id", length = 50)
//    private String nationalId;
//
//    /** Primary email address for communication. */
//    @Column(name = "email", length = 150)
//    private String email;
//
//    /** Primary contact number for communication. */
//    @Column(name = "phone", length = 20)
//    private String phone;

    /** URL to the profile image or avatar of the person (optional). */
    @Column(name = "profile_image_url", length = 255)
    private String profileImageUrl;

    /** The person's title or honorific (e.g., Mr., Ms., Dr., Prof.). */
    @Column(name = "title", length = 20)
    private PersonTitle title;

}
