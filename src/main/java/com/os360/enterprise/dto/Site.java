package com.os360.enterprise.dto;

import com.os360.enterprise.enumurations.SiteType;

import java.util.List;
import java.util.UUID;

public class Site {
    private UUID id;

    private String externalSys;
    private String externalId;

    private Company company;

    private SiteType siteType;

    //Attributes
    private List<CommunicationMethod> communicationMethods;

    private boolean isDefault;

    private boolean isActive;
}
