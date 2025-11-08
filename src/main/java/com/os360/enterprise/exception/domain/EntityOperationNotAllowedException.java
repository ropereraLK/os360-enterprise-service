package com.os360.enterprise.exception.domain;

import org.springframework.http.HttpStatus;

import java.util.Map;

/**
 * Exception thrown when a business rule prevents an operation on an entity.
 * <p>
 * Examples: attempting to delete a company with active sites, updating a closed order, etc.
 * </p>
 */
public class EntityOperationNotAllowedException extends DomainException {

    /**
     * Constructs a new EntityOperationNotAllowedException for the given entity type and reason.
     *
     * @param entityType the class of the entity
     * @param entityId   the identifier of the entity (any type)
     * @param reason     the reason why the operation is not allowed
     */
    public EntityOperationNotAllowedException(Class<?> entityType, Object entityId, String reason) {
        super(
                entityType.getSimpleName() + " operation not allowed: " + reason,
                HttpStatus.FORBIDDEN,
                Map.of("entity", entityType.getSimpleName(), "id", entityId, "reason", reason)
        );
    }
}
