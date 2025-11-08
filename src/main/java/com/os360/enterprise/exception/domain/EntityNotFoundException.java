package com.os360.enterprise.exception.domain;

import org.springframework.http.HttpStatus;

import java.util.Map;

/**
 * Generic domain exception thrown when an entity is not found in the system.
 * <p>
 * Supports any type of entity identifier (UUID, Long, int, String, etc.).
 * Carries optional metadata for richer context.
 * </p>
 */
public class EntityNotFoundException extends DomainException {

    /**
     * Constructs a new EntityNotFoundException for a given entity type and identifier.
     *
     * @param entityType the class of the entity that was not found
     * @param id         the identifier of the entity (any type)
     * @param <T>        type of the identifier
     */
    public <T> EntityNotFoundException(Class<?> entityType, Object id) {
        super(
                entityType.getSimpleName() + " not found: " + id,
                HttpStatus.NOT_FOUND,
                Map.of("entity", entityType.getSimpleName(), "id", id)
        );
    }
}
