package com.os360.enterprise.exception.domain;

import com.os360.enterprise.exception.ApplicationException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Map;

/**
 * Base exception class for the domain/entity layer.
 * <p>
 * All exceptions related to business rules, entity operations, or domain logic
 * should extend this class.
 * </p>
 *
 * <p>Examples:</p>
 * <ul>
 *     <li>Entity not found (e.g., CompanyNotFoundOperationException)</li>
 *     <li>Entity already deleted (e.g., CompanyAlreadyDeletedOperationException)</li>
 *     <li>Business rule violations</li>
 * </ul>
 *
 * <p>This class holds additional information useful for API responses:</p>
 * <ul>
 *     <li>{@code httpStatus} - the HTTP status code to be returned to the client</li>
 *     <li>{@code metadata} - optional additional information about the exception, such as entity ID or other context</li>
 * </ul>
 *
 * <p>By using this class, the GlobalExceptionHandler can automatically build
 * consistent API responses without requiring changes for each new domain exception.</p>
 */
@Getter
public abstract class DomainException extends ApplicationException {

    /**
     * The HTTP status code that should be returned when this exception occurs.
     */
    private final HttpStatus httpStatus;

    /**
     * Optional additional information that can be returned in the API response.
     * For example, entity IDs, field names, or any other context relevant to the exception.
     */
    private final Map<String, Object> metadata;

    /**
     * Constructs a new DomainException with the specified detail message, HTTP status, and metadata.
     *
     * @param message    a human-readable message describing the exception
     * @param httpStatus the HTTP status code to return to the client
     * @param metadata   optional metadata providing additional context for the exception
     */
    public DomainException(String message, HttpStatus httpStatus, Map<String, Object> metadata) {
        super(message);
        this.httpStatus = httpStatus;
        this.metadata = metadata;
    }
}
