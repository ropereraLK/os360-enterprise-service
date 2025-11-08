package com.os360.enterprise.exception.validation;

import com.os360.enterprise.exception.ApplicationException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.Map;

/**
 * Exception thrown when a validation error occurs.
 * <p>
 * Typically used in the service or controller layer to indicate that
 * input data does not satisfy business rules or constraints.
 * </p>
 *
 * <p>Features:</p>
 * <ul>
 *     <li>Default HTTP status 400 Bad Request</li>
 *     <li>Optional metadata to include additional context (e.g., invalid field names)</li>
 *     <li>Integrates with GlobalExceptionHandler for consistent API error responses</li>
 * </ul>
 *
 * <p>Example usage:</p>
 * <pre>
 *     if (name == null || name.isBlank()) {
 *         throw new ValidationException("Name cannot be blank", Map.of("field", "name"));
 *     }
 * </pre>
 */
@Getter
public class ValidationException extends ApplicationException {

    /**
     * The HTTP status to return to the client. Always 400 Bad Request for validation errors.
     */
    private final HttpStatus status = HttpStatus.BAD_REQUEST;

    /**
     * Optional metadata providing additional context about the validation failure.
     * For example, field names or invalid values.
     */
    private final Map<String, Object> metadata;

    /**
     * Constructs a new ValidationException with the specified message and metadata.
     *
     * @param message  human-readable message describing the validation error
     * @param metadata optional additional context about the validation failure
     */
    public ValidationException(String message, Map<String, Object> metadata) {
        super(message);
        this.metadata = metadata != null ? metadata : Collections.emptyMap();
    }

    /**
     * Constructs a new ValidationException with the specified message.
     * No additional metadata is provided.
     *
     * @param message human-readable message describing the validation error
     */
    public ValidationException(String message) {
        this(message, Collections.emptyMap());
    }
}
