package com.os360.enterprise.exception;
import org.springframework.http.HttpStatus;

/**
 * Base exception class for the entire application.
 * <p>
 * All other exceptions (domain, validation, API) should extend this class.
 * Provides a common root type for exception handling and categorization.
 * </p>
 */
public abstract class ApplicationException extends RuntimeException {

    /**
     * Constructs a new ApplicationException with the specified detail message.
     *
     * @param message the detail message
     */
    public ApplicationException(String message) {
        super(message);
    }

    /**
     * Constructs a new ApplicationException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause   the cause of the exception
     */
    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
