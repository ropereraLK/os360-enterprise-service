package com.os360.enterprise.dto;

public class AddressComMethod extends CommunicationMethod{
    /** ISO 3166-1 alpha-2 country code (e.g., "US", "LK") */
    private String countryCode;

    /** Administrative area: state, province, or region */
    private String administrativeArea;

    /** Locality: usually city, town, or municipality */
    private String locality;

    /** Dependent locality: neighborhood, village, or subdistrict */
    private String dependentLocality;

    /** Postcode or ZIP code */
    private String postalCode;

    /** Thoroughfare: street number and street name */
    private String thoroughfare;

    /** Premise: building name, apartment, suite, or floor */
    private String premise;

    /** Post office box number (if applicable) */
    private String postBox;

    /** Recipient or organization name (optional, if applicable) */
    private String recipient;

    /** Unstructured fallback lines (for non-standard addresses) */
    private String addressLine1;
    private String addressLine2;

    /** Latitude/Longitude for geolocation (optional) */
    private Double latitude;
    private Double longitude;


}
