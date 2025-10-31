package com.os360.enterprise.dto;

import com.os360.enterprise.enumurations.PartyType;

import java.util.List;
import java.util.UUID;

public abstract class  Party {
    //Keys
    private UUID id;
    private PartyType partyType;

    private String externalSys;
    private String externalId;



}
