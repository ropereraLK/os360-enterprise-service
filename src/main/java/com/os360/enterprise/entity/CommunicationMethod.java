package com.os360.enterprise.entity;

import com.os360.enterprise.enumurations.CommunicationType;

public abstract class CommunicationMethod {

    private Long id;
    private Party party;
    private CommunicationType type;
    private boolean isDefault;
    private boolean isActive;



}
