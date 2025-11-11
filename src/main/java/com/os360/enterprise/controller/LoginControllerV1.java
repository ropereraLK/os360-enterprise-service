

package com.os360.enterprise.controller;

import com.os360.enterprise.dto.LoginRequest;
import com.os360.enterprise.dto.LoginResponse;
import com.os360.enterprise.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class LoginControllerV1 {

    private final AuthService authService;

    public LoginControllerV1(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = authService.authenticate(request);
        return ResponseEntity.ok(response);
    }
}
