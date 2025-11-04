package com.os360.enterprise.common;

public final class CommonUtils {

    private CommonUtils() {
        // Prevent instantiation
    }

    /**
     * Checks whether a string is null, empty, or contains only whitespace.
     *
     * @param value the string to check
     * @return true if the string is null, empty, or blank; false otherwise
     */
    public static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    /**
     * Checks whether a string is not null, not empty, and contains at least one non-whitespace character.
     *
     * @param value the string to check
     * @return true if the string is not null and not blank; false otherwise
     */
    public static boolean isNotBlank(String value) {
        return !isBlank(value);
    }

    /**
     * Checks that all given strings are NOT blank.
     * Returns true only if every argument is non-null and non-blank.
     *
     * Example:
     *   allNotBlank("A", "B", "C") → true
     *   allNotBlank("A", " ", "C") → false
     */
    public static boolean allNotBlank(String... values) {
        if (values == null || values.length == 0) {
            return false;
        }
        for (String value : values) {
            if (isBlank(value)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks whether any of the given strings are blank.
     * Returns true if at least one argument is null or blank.
     */
    public static boolean anyBlank(String... values) {
        if (values == null || values.length == 0) {
            return true;
        }
        for (String value : values) {
            if (isBlank(value)) {
                return true;
            }
        }
        return false;
    }

}
