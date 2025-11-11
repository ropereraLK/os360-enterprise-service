package com.os360.enterprise.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    private String token;        // JWT or session token
    private String username;
    private String displayName;
    private boolean accountLocked;
    private boolean enabled;
}
