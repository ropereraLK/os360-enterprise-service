package com.os360.enterprise.common;

import java.util.Arrays;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Utility class for validating ISO 3166-1 country codes.
 *
 * <p>This class supports both alpha-2 (two-letter) and alpha-3 (three-letter)
 * country code validation. It uses Java's built-in {@link Locale} data for
 * accuracy and compatibility.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 *     CountryCodeUtils.isValidAlpha2("US");  // true
 *     CountryCodeUtils.isValidAlpha3("LKA"); // true
 * </pre>
 *
 * @author Rohan Perera @ropereralk
 * @since 1.0.0
 */
public final class CountryUtils {
    private CountryUtils() {
        // Prevent instantiation
    }

    // alpha-2 two-letter codes (e.g. "US", "IN")
    private static final Set<String> ISO_ALPHA2_CODES =
            Arrays.stream(Locale.getISOCountries())
                    .map(s -> s.toUpperCase(Locale.ROOT))
                    .collect(Collectors.toUnmodifiableSet());

    /**
     * Validates whether the given country code is a valid ISO 3166-1 alpha-2 code.
     *
     * <p>Alpha-2 country codes are two-letter uppercase identifiers,
     * e.g., "US", "IN", "LK".</p>
     *
     * @param countryCode the 2-letter country code to validate (case-insensitive)
     * @return {@countryCode true} if the code is a valid ISO 3166-1 alpha-2 country code;
     * {@countryCode false} otherwise
     */
    public static boolean isValidAlpha2CountryCode(String countryCode) {
        if (countryCode == null || countryCode.isBlank() || countryCode.length() != 2) {
            return false;
        }
        return ISO_ALPHA2_CODES.contains(countryCode.toUpperCase(Locale.ROOT));
    }


    public static String getCountryName(String countryCode) {
        if (countryCode == null || countryCode.isBlank()) {
            return null;
        }

        String upper = countryCode.toUpperCase(Locale.ROOT);

        // alpha-2
        if (isValidAlpha2CountryCode(upper)) {
            return new Locale("", upper).getDisplayCountry(Locale.ENGLISH);
        }
        return null;
    }
}
