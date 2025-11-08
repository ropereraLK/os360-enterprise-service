package com.os360.enterprise.exception.domain;

import org.springframework.http.HttpStatus;

import java.util.Map;

/**
 * Exception thrown when attempting to create an entity that already exists.
 * <p>
 * Can be used for any entity type with a unique constraint (e.g., company code, email, etc.).
 * </p>
 */
public class EntityAlreadyExistsException extends DomainException {

    /**
     * Constructs a new EntityAlreadyExistsException for the given entity type and conflicting value.
     *
     * @param entityType     the class of the entity
     * @param idOrUniqueField the identifier or unique field that caused the conflict (any type)
     */
    public EntityAlreadyExistsException(Class<?> entityType, Object idOrUniqueField) {
        super(
                entityType.getSimpleName() + " already exists: " + idOrUniqueField,
                HttpStatus.CONFLICT,
                Map.of("entity", entityType.getSimpleName(), "value", idOrUniqueField)
        );
    }
}
