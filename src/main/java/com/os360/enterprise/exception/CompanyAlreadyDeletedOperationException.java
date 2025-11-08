package com.os360.enterprise.exception;

import org.springframework.http.HttpStatus;
import java.util.Map;
import java.util.UUID;

/**
 * Exception thrown when a Company entity is already marked as deleted.
 * <p>
 * This is a domain-level exception indicating a business rule violation:
 * the requested operation cannot be performed because the company is already deleted.
 * </p>
 *
 * <p>
 * Extends {@link DomainException}, so it carries:
 * <ul>
 *     <li>{@code httpStatus} - HTTP 410 Gone</li>
 *     <li>{@code metadata} - includes the {@code companyId} for client reference</li>
 * </ul>
 * </p>
 *
 * <p>
 * Usage:
 * <pre>
 *     if (company.isDeleted()) {
 *         throw new CompanyAlreadyDeletedOperationException(company.getId());
 *     }
 * </pre>
 * </p>
 */
public class CompanyAlreadyDeletedOperationException extends DomainException {

    /**
     * Constructs a new {@code CompanyAlreadyDeletedOperationException} with the given company ID.
     *
     * @param id the UUID of the company that is already deleted
     */
    public CompanyAlreadyDeletedOperationException(UUID id) {
        super(
                "Company already deleted: " + id,       // Human-readable error message
                HttpStatus.GONE,                        // HTTP status for API response
                Map.of("companyId", id)                // Metadata to include in API response
        );
    }
}
