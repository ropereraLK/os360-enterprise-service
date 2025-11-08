package com.os360.enterprise.exception;

import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.UUID;

/**
 * Exception thrown when a Company entity is not found in the system.
 * <p>
 * This is a domain-level exception that indicates a business rule violation:
 * the requested company does not exist in the database.
 * </p>
 *
 * <p>
 * Extends {@link DomainException}, so it carries:
 * <ul>
 *     <li>{@code httpStatus} - HTTP 404 Not Found</li>
 *     <li>{@code metadata} - includes the {@code companyId} for client reference</li>
 * </ul>
 * </p>
 *
 * <p>
 * Usage:
 * <pre>
 *     Company company = companyRepository.findById(id)
 *         .orElseThrow(() -> new CompanyNotFoundOperationException(id));
 * </pre>
 * </p>
 */
public class CompanyNotFoundOperationException extends DomainException {

    /**
     * Constructs a new {@code CompanyNotFoundOperationException} with the given company ID.
     *
     * @param id the UUID of the company that was not found
     */
    public CompanyNotFoundOperationException(UUID id) {
        super(
                "Company not found: " + id,            // Human-readable error message
                HttpStatus.NOT_FOUND,                  // HTTP status for API response
                Map.of("companyId", id)               // Metadata to include in API response
        );
    }
}
