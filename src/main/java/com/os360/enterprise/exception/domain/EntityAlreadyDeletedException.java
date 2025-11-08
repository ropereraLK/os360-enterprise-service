package com.os360.enterprise.exception.domain;

import org.springframework.http.HttpStatus;

import java.util.Map;

/**
 * Generic domain exception thrown when an entity has already been deleted.
 * Supports any entity type and identifier.
 */
public class EntityAlreadyDeletedException extends DomainException {

    /**
     * Constructs a new EntityAlreadyDeletedException for the given entity type and identifier.
     *
     * @param entityType the class of the entity
     * @param id         the identifier of the entity (any type)
     * @param <T>        type of the identifier
     */
    public <T> EntityAlreadyDeletedException(Class<?> entityType, T id) {
        super(
                entityType.getSimpleName() + " has already been deleted: " + id,
                HttpStatus.GONE,  // 410 Gone is appropriate
                Map.of("entity", entityType.getSimpleName(), "id", id)
        );
    }
}
