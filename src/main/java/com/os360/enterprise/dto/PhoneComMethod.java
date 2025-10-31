package com.os360.enterprise.dto;

import com.os360.enterprise.enumurations.ContactContextType;
import com.os360.enterprise.enumurations.PhoneServiceType;

import java.util.Set;

public class PhoneComMethod extends CommunicationMethod{
    private String phoneNumber;
    private ContactContextType contextType;
    private String verificationToken;
    private boolean verified;
    private Set<PhoneServiceType> supportedChannels;
}
