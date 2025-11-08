package com.os360.enterprise.exception.http;

import com.os360.enterprise.exception.ApplicationException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Map;

/**
 * Base exception class for HTTP/controller-specific errors.
 * <p>
 * This exception is intended to be thrown directly in controllers when
 * a specific HTTP error needs to be returned.
 * </p>
 *
 * <p>Features:</p>
 * <ul>
 *     <li>Includes HTTP status to return to the client</li>
 *     <li>Optional metadata for additional context in the API response</li>
 *     <li>Integrates with GlobalExceptionHandler for consistent error responses</li>
 * </ul>
 *
 * <p>Example usage:</p>
 * <pre>
 *     if (someConditionFails) {
 *         throw new HttpException("Custom error message", HttpStatus.BAD_REQUEST, Map.of("field", "value"));
 *     }
 * </pre>
 */
@Getter
public class HttpException extends ApplicationException {

    /**
     * The HTTP status to return to the client.
     */
    private final HttpStatus status;

    /**
     * Optional additional metadata to include in the API response.
     */
    private final Map<String, Object> metadata;

    /**
     * Constructs a new HttpException with the specified message, status, and metadata.
     *
     * @param message  human-readable message describing the error
     * @param status   HTTP status code to return
     * @param metadata optional additional context
     */
    public HttpException(String message, HttpStatus status, Map<String, Object> metadata) {
        super(message);
        this.status = status;
        this.metadata = metadata;
    }

    /**
     * Constructs a new HttpException with the specified message and status.
     * No additional metadata is provided.
     *
     * @param message human-readable message describing the error
     * @param status  HTTP status code to return
     */
    public HttpException(String message, HttpStatus status) {
        this(message, status, null);
    }
}
