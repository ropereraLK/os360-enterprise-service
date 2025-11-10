package com.os360.enterprise.controller;

import com.os360.enterprise.dto.PersonCreateRequest;
import com.os360.enterprise.dto.PersonPatchRequest;
import com.os360.enterprise.dto.PersonResponse;
import com.os360.enterprise.dto.PersonUpdateRequest;
import com.os360.enterprise.entity.Person;
import com.os360.enterprise.exception.domain.EntityNotFoundException;
import com.os360.enterprise.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * REST controller for managing Person entities.
 * <p>
 * Provides CRUD operations (Create, Read, Update, Delete) for people (persons)
 * extending from {@link com.os360.enterprise.entity.Party}.
 * <p>
 * Responsibilities:
 * - Delegate business logic to {@link PersonService}.
 * - Handle HTTP requests and responses for person operations.
 * - Provide OpenAPI/Swagger documentation using annotations.
 * <p>
 * Note:
 * - PATCH endpoints support partial updates.
 * - DELETE endpoint performs a soft delete (logical removal).
 */
@RestController
@RequestMapping("/api/v1/persons")
@Tag(name = "Person", description = "Operations related to people (Person entities), Version 1.0")
public class PersonControllerV1 {

    @Autowired
    private PersonService personService;

    /**
     * Retrieves a list of people based on provided criteria (optional filtering/search).
     *
     * @return List of {@link PersonResponse} representing available people.
     */
    @GetMapping
    @Operation(summary = "Get all persons", description = "Retrieves a list of all persons or filtered results")
    public ResponseEntity<List<PersonResponse>> getPersons() {
        List<PersonResponse> persons = personService.getAll();
        return ResponseEntity.ok(persons);
    }

    /**
     * Retrieves a single person by their unique ID.
     *
     * @param id UUID of the person to retrieve.
     * @return Optional containing {@link PersonResponse} if found.
     * @throws EntityNotFoundException if no person exists with the given ID.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get person by ID", description = "Returns the person details by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person found"),
            @ApiResponse(responseCode = "404", description = "Person not found")
    })
    public ResponseEntity<Optional<PersonResponse>> getPerson(@PathVariable UUID id) {
        return ResponseEntity.ok(personService.get(id));
    }

    /**
     * Creates a new person entity.
     *
     * @param createRequest Request body containing person details.
     * @return Optional containing created {@link PersonResponse}.
     * @throws IllegalArgumentException if validation fails.
     */
    @PostMapping
    @Operation(summary = "Create person", description = "Creates a new person entity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Person created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data")
    })
    public ResponseEntity<Optional<PersonResponse>> createPerson(
            @Valid @RequestBody PersonCreateRequest createRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.create(createRequest));
    }

    /**
     * Fully updates an existing person (replaces all fields).
     *
     * @param id UUID of the person to update.
     * @param updateRequest Request body containing updated fields.
     * @return Updated {@link PersonResponse}.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update person", description = "Replaces all fields of a person")
    public ResponseEntity<PersonResponse> updatePerson(
            @PathVariable UUID id,
            @Valid @RequestBody PersonUpdateRequest updateRequest) {

        Optional<PersonResponse> updated = personService.update(id, updateRequest);
        return ResponseEntity.ok(
                updated.orElseThrow(() -> new EntityNotFoundException(Person.class, id))
        );
    }

    /**
     * Partially updates an existing person (only non-null fields are updated).
     *
     * @param id UUID of the person to patch.
     * @param patchRequest Request body with partial update fields.
     * @return Updated {@link PersonResponse}.
     */
    @PatchMapping("/{id}")
    @Operation(summary = "Patch person", description = "Updates partial fields of a person")
    public ResponseEntity<PersonResponse> patchPerson(
            @PathVariable UUID id,
            @RequestBody PersonPatchRequest patchRequest) {

        Optional<PersonResponse> updated = personService.patch(id, patchRequest);
        return ResponseEntity.ok(
                updated.orElseThrow(() -> new EntityNotFoundException(Person.class, id))
        );
    }

    /**
     * Performs a soft delete on a person by setting deleted flags and timestamps.
     *
     * @param id UUID of the person to delete.
     * @return 204 No Content.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete person", description = "Performs a soft delete on a person")
    public ResponseEntity<Void> deletePerson(@PathVariable UUID id) {
        personService.softDelete(id);
        return ResponseEntity.noContent().build();
    }
}
