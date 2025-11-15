package com.os360.enterprise.service;

import com.os360.enterprise.dto.PersonCreateRequest;
import com.os360.enterprise.dto.PersonPatchRequest;
import com.os360.enterprise.dto.PersonResponse;
import com.os360.enterprise.dto.PersonUpdateRequest;
import   com.os360.enterprise.enumurations.PartyType ;
import com.os360.enterprise.entity.Person;
import com.os360.enterprise.enumurations.PartyType;
import com.os360.enterprise.exception.validation.*;
import com.os360.enterprise.exception.domain.EntityNotFoundException;
import com.os360.enterprise.mapper.PersonMapper;
import com.os360.enterprise.repository.PersonRepository;
import com.os360.enterprise.validator.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service layer for managing {@link Person} entities.
 * <p>
 * Responsibilities:
 * - Perform CRUD operations on Person entities.
 * - Apply business validation via {@link PersonValidator}.
 * - Map between entity and DTO using {@link PersonMapper}.
 * - Handle soft delete logic.
 */
@Service
@Transactional
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private PersonValidator personValidator;

    /**
     * Retrieves all persons (excluding soft-deleted ones).
     *
     * @return list of {@link PersonResponse}.
     */
    public List<Optional<PersonResponse>> getAll() {
        return personRepository.findAllByIsDeletedFalse()
                .stream()
                .map(personMapper::toResponse)
                .toList();
    }

    /**
     * Retrieves a person by ID.
     *
     * @param id UUID of the person.
     * @return Optional of {@link PersonResponse}.
     * @throws EntityNotFoundException if person is not found.
     */
    public Optional<PersonResponse> get(UUID id) {
        return personRepository.findByIdAndIsDeletedFalse(id)
                .map(personMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException(Person.class, id));
    }



    /**
     * Creates a new person entity.
     *
     * @param request creation request.
     * @return created {@link PersonResponse}.
     */
    public Optional<PersonResponse> create(PersonCreateRequest request) {
        personValidator.validateForCreate(request);

        Person person = personMapper.toEntity(request);
        person.setPartyType(PartyType.PERSON.name());
        person.setActive(true);
        person.setDeleted(false);
        person.setCreatedAt(OffsetDateTime.now());
        person.setVersion(0L);

        Person saved = personRepository.save(person);
        return personMapper.toResponse(saved);
    }

    /**
     * Updates an existing person entity (full update).
     *
     * @param id      person ID.
     * @param request update request.
     * @return updated {@link PersonResponse}.
     */
    public Optional<PersonResponse> update(UUID id, PersonUpdateRequest request) {
        Person existing = personRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new EntityNotFoundException(Person.class, id));

        personValidator.validateForUpdate(request, existing);

        personMapper.updateEntityFromRequest(request, existing);
        existing.setLastModifiedAt(OffsetDateTime.now());

        Person saved = personRepository.save(existing);
        return personMapper.toResponse(saved);
    }

    /**
     * Performs a partial update (PATCH) on an existing person.
     *
     * @param id      person ID.
     * @param request patch request.
     * @return updated {@link PersonResponse}.
     */
    public Optional<PersonResponse> patch(UUID id, PersonPatchRequest request) {
        Person existing = personRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new EntityNotFoundException(Person.class, id));

        personValidator.validateForPatch(request, existing);

        personMapper.patchEntityFromRequest(request, existing);
        existing.setLastModifiedAt(OffsetDateTime.now());

        Person saved = personRepository.save(existing);
        return  personMapper.toResponse(saved);
    }

    /**
     * Performs a soft delete on a person (marks as deleted).
     *
     * @param id person ID.
     */
    public void softDelete(UUID id) {
        Person existing = personRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new EntityNotFoundException(Person.class, id));

        existing.setDeleted(true);
        existing.setActive(false);
        existing.setDeletedAt(OffsetDateTime.now());

        personRepository.save(existing);
    }
}
