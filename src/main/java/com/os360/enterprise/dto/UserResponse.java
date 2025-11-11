package com.os360.enterprise.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class UserResponse extends PersonResponse {

    private String username;
    private boolean enabled;
    private boolean accountLocked;
    private boolean accountExpired;
    private boolean credentialsExpired;

    // Active roles for this user
    private Set<String> roles;
}
