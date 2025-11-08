package com.os360.enterprise.exception;

import com.os360.enterprise.exception.domain.DomainException;
import com.os360.enterprise.exception.validation.ValidationException;
import com.os360.enterprise.exception.http.HttpException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler for the application.
 * <p>
 * Catches exceptions thrown from services or controllers and returns
 * structured API responses.
 * </p>
 *
 * Handles:
 * <ul>
 *     <li>DomainException – entity/business rule exceptions</li>
 *     <li>ValidationException – validation failures</li>
 *     <li>HttpException – controller-specific HTTP exceptions</li>
 *     <li>ApplicationException – generic application exceptions</li>
 *     <li>Exception – any other uncaught exceptions</li>
 * </ul>
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles all domain/entity exceptions.
     */
    @ExceptionHandler(DomainException.class)
    public ResponseEntity<Map<String, Object>> handleDomainException(DomainException ex) {
        return buildResponse(
                ex.getMessage(),
                ex.getHttpStatus().value(),
                ex.getHttpStatus().getReasonPhrase(),
                ex.getMetadata()
        );
    }

    /**
     * Handles all validation exceptions.
     */
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(ValidationException ex) {
        Map<String, Object> metadata = ex.getMetadata(); // Assume getMetadata() exists in ValidationException
        return buildResponse(
                ex.getMessage(),
                400,
                "Bad Request",
                metadata
        );
    }

    /**
     * Handles HTTP/controller-specific exceptions.
     */
    @ExceptionHandler(HttpException.class)
    public ResponseEntity<Map<String, Object>> handleHttpException(HttpException ex) {
        return buildResponse(
                ex.getMessage(),
                ex.getStatus().value(),
                ex.getStatus().getReasonPhrase(),
                ex.getMetadata() // optional metadata
        );
    }

    /**
     * Handles generic application exceptions.
     */
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<Map<String, Object>> handleApplicationException(ApplicationException ex) {
        return buildResponse(
                ex.getMessage(),
                500,
                "Internal Server Error",
                null
        );
    }

    /**
     * Handles any uncaught exceptions.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleOtherExceptions(Exception ex) {
        return buildResponse(
                ex.getMessage(),
                500,
                "Internal Server Error",
                null
        );
    }

    /**
     * Builds a consistent JSON API response for exceptions.
     */
    private ResponseEntity<Map<String, Object>> buildResponse(String message, int status, String error, Map<String, Object> metadata) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", OffsetDateTime.now());
        body.put("status", status);
        body.put("error", error);
        body.put("message", message);
        if (metadata != null && !metadata.isEmpty()) {
            body.put("metadata", metadata);
        }
        return ResponseEntity.status(status).body(body);
    }
}
