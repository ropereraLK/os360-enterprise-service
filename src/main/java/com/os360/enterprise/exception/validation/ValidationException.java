package com.os360.enterprise.exception.validation;

import org.springframework.http.HttpStatus;
import    com.os360.enterprise.exception.*;

import java.util.Map;

/**
 * Exception representing validation errors in API requests or domain entities.
 * <p>
 * Can carry metadata about the invalid fields to provide more context to API consumers.
 * Typically results in HTTP 400 Bad Request responses.
 */
public class ValidationException extends ApplicationException {

    private final HttpStatus httpStatus;
    private final Map<String, Object> metadata;

    /**
     * Constructs a ValidationException with a message.
     *
     * @param message the human-readable error message
     */
    public ValidationException(String message) {
        super(message);
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.metadata = Map.of();
    }

    /**
     * Constructs a ValidationException with a message and single field metadata.
     *
     * @param message the human-readable error message
     * @param field   the field name that caused the validation failure
     * @param value   the value that was invalid (optional)
     */
    public ValidationException(String message, String field, Object value) {
        super(message);
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.metadata = Map.of(field, value);
    }

    /**
     * Constructs a ValidationException with a message and arbitrary metadata map.
     *
     * @param message  the human-readable error message
     * @param metadata a map containing field names and invalid values
     */
    public ValidationException(String message, Map<String, Object> metadata) {
        super(message);
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.metadata = metadata;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }
}
