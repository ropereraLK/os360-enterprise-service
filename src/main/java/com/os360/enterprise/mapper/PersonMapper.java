package com.os360.enterprise.mapper;

import com.os360.enterprise.dto.PersonCreateRequest;
import com.os360.enterprise.dto.PersonPatchRequest;
import com.os360.enterprise.dto.PersonResponse;
import com.os360.enterprise.dto.PersonUpdateRequest;
import com.os360.enterprise.entity.Person;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

/**
 * Mapper class to convert between {@link Person} entity and DTOs.
 * <p>
 * Provides methods for creating responses and mapping input requests.
 * Keeps entity-to-DTO transformations centralized.
 * </p>
 */
@Component
public class PersonMapper {

    /**
     * Maps a {@link Person} entity to a {@link PersonResponse} DTO.
     *
     * @param person the Person entity
     * @return Optional containing the PersonResponse if person is not null
     */
    public Optional<PersonResponse> toResponse(Person person) {
        if (person == null) {
            return Optional.empty();
        }

        PersonResponse response = new PersonResponse();
        response.setId(person.getId());
        response.setExternalSystem(person.getExternalSystem());
        response.setExternalId(person.getExternalId());
        response.setCountryCode(person.getCountryCode());
        response.setTitle(person.getTitle());
        response.setFirstName(person.getFirstName());
        response.setMiddleName(person.getMiddleName());
        response.setLastName(person.getLastName());
        response.setPreferredName(person.getPreferredName());
        response.setGender(person.getGender());
        response.setDateOfBirth(person.getDateOfBirth());
        response.setProfileImageUrl(person.getProfileImageUrl());
        response.setActive(person.isActive());
        response.setDeleted(person.isDeleted());
        response.setCreatedAt(person.getCreatedAt());
        response.setCreatedBy(person.getCreatedBy());
        response.setLastModifiedAt(person.getLastModifiedAt());
        response.setLastModifiedBy(person.getLastModifiedBy());

        return Optional.of(response);
    }

    /**
     * Maps a {@link PersonCreateRequest} DTO to a {@link Person} entity.
     * <p>
     * Does not set audit fields (createdAt, createdBy), which should be
     * handled in the service layer.
     * </p>
     *
     * @param request the PersonCreateRequest
     * @return the new Person entity
     */
    public Person toEntity(PersonCreateRequest request) {
        if (request == null) {
            return null;
        }

        Person person = new Person();
        person.setExternalSystem(request.getExternalSystem());
        person.setExternalId(request.getExternalId());
        person.setCountryCode(request.getCountryCode());
        person.setTitle(request.getTitle());
        person.setFirstName(request.getFirstName());
        person.setMiddleName(request.getMiddleName());
        person.setLastName(request.getLastName());
        person.setPreferredName(request.getPreferredName());
        person.setGender(request.getGender());
        person.setDateOfBirth(request.getDateOfBirth());
        person.setProfileImageUrl(request.getProfileImageUrl());
        person.setActive(request.isActive());

        return person;
    }

    /**
     * Updates an existing {@link Person} entity from a {@link PersonUpdateRequest}.
     * <p>
     * Only non-null fields in the request will overwrite existing values.
     * </p>
     *
     * @param request  the update request
     * @param existing the existing Person entity to modify
     */
    public void updateEntityFromRequest(PersonUpdateRequest request, Person existing) {
        if (request == null || existing == null) return;

        if (request.getExternalSystem() != null) existing.setExternalSystem(request.getExternalSystem());
        if (request.getExternalId() != null) existing.setExternalId(request.getExternalId());
        if (request.getCountryCode() != null) existing.setCountryCode(request.getCountryCode());
        if (request.getTitle() != null) existing.setTitle(request.getTitle());
        if (request.getFirstName() != null) existing.setFirstName(request.getFirstName());
        if (request.getMiddleName() != null) existing.setMiddleName(request.getMiddleName());
        if (request.getLastName() != null) existing.setLastName(request.getLastName());
        if (request.getPreferredName() != null) existing.setPreferredName(request.getPreferredName());
        if (request.getGender() != null) existing.setGender(request.getGender());
        if (request.getDateOfBirth() != null) existing.setDateOfBirth(request.getDateOfBirth());
        if (request.getProfileImageUrl() != null) existing.setProfileImageUrl(request.getProfileImageUrl());
    }

    /**
     * Applies a partial update (PATCH) to an existing {@link Person} entity
     * using non-null fields from the {@link PersonPatchRequest}.
     *
     * @param request  the patch request with updated fields
     * @param existing the existing Person entity to modify
     */
    public void patchEntityFromRequest(PersonPatchRequest request, Person existing) {
        if (request == null || existing == null) return;

        if (request.getExternalSystem() != null) existing.setExternalSystem(request.getExternalSystem());
        if (request.getExternalId() != null) existing.setExternalId(request.getExternalId());
        if (request.getCountryCode() != null) existing.setCountryCode(request.getCountryCode());
        if (request.getTitle() != null) existing.setTitle(request.getTitle());
        if (request.getFirstName() != null) existing.setFirstName(request.getFirstName());
        if (request.getMiddleName() != null) existing.setMiddleName(request.getMiddleName());
        if (request.getLastName() != null) existing.setLastName(request.getLastName());
        if (request.getPreferredName() != null) existing.setPreferredName(request.getPreferredName());
        if (request.getGender() != null) existing.setGender(request.getGender());
        if (request.getDateOfBirth() != null) existing.setDateOfBirth(request.getDateOfBirth());
        if (request.getProfileImageUrl() != null) existing.setProfileImageUrl(request.getProfileImageUrl());
        //if (request.getActive() != null) existing.setActive(request.getActive());
    }


}
