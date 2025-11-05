package com.os360.enterprise.controller;

import com.os360.enterprise.dto.PersonCreateRequest;
import com.os360.enterprise.dto.PersonResponse;
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

@RestController
@RequestMapping("/api/v1/persons")
@Tag(name = "Person", description = "Operations related to persons, Version 1.0")
public class PersonControllerV1 {

    @Autowired
    private PersonService personService;

    private PersonResponse personResponse;

    private List<PersonResponse> persons;

    @GetMapping
    public ResponseEntity<List<PersonResponse>> getPersons(
            @RequestBody PersonCreateRequest personCreateRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(persons);
    }

    @Operation(summary = "Get a person by ID", description = "Returns the person details by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the person"),
            @ApiResponse(responseCode = "404", description = "Person not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Optional<PersonResponse>> getPerson(
            @PathVariable UUID id) {

        return ResponseEntity.status(HttpStatus.OK).body(personService.get(id));
    }

    @Operation(summary = "Create a person", description = "Returns the person details by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Person Created"),
            @ApiResponse(responseCode = "404", description = "Person Creation Failed")
    })
    @PostMapping
    public ResponseEntity<Optional<PersonResponse>> createPerson(
            @RequestBody @Valid PersonCreateRequest personCreateRequest) {

        return ResponseEntity.status(HttpStatus.CREATED).body(personService.create(personCreateRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonResponse> updatePerson(
            @PathVariable UUID id,
            @RequestBody @Valid PersonCreateRequest personCreateRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(personResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PersonResponse> patchPerson(
            @PathVariable UUID id,
            @RequestBody PersonCreateRequest personPatchRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(personResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(
            @PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
