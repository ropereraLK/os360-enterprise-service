package com.os360.enterprise.exception.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Map;
import java.util.Set;

/**
 * Standardized API error response following RFC 7807 "Problem Details for HTTP APIs".
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProblemDetails(
        String type,        // URI reference that identifies the error type (can be generic or specific)
        String title,       // Short, human-readable summary of the error
        int status,         // HTTP status code
        String detail,      // Detailed error message
        String instance,    // URI identifying the specific occurrence of the problem (optional)
        String errorKey,    // unique identifier for this error
        Map<String, Object> metadata // Additional contextual info (safe for client)
) {
    // Static helper method to filter sensitive metadata
    public static Map<String, Object> filterSensitiveMetadata(Map<String, Object> metadata) {
        if (metadata == null) {
            return Map.of();
        }

        return metadata.entrySet().stream()
                .filter(entry -> isSafeKey(entry.getKey()))
                .collect(java.util.stream.Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private static boolean isSafeKey(String key) {
        Set<String> safeKeys = Set.of(
                "entity",
                "id",
                "field",
                "operation",
                "reason"
        );
        return safeKeys.contains(key);
    }
}
