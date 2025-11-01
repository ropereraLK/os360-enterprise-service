package com.os360.enterprise.entity;

public class EmailComMethod extends CommunicationMethod {
    private String emailAddress;

    //Optional field for label (e.g., "Work", "Personal", "Support").
    private String label;
    private boolean verified;
    private String verificationToken;


}
