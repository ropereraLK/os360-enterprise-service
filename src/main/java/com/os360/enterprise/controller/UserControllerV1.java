package com.os360.enterprise.controller;

import com.os360.enterprise.dto.UserCreateRequest;
import com.os360.enterprise.dto.UserResponse;
import com.os360.enterprise.entity.UserRoleAssignment;
import com.os360.enterprise.service.UserService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserControllerV1 {

    private final UserService userService;

    public UserControllerV1(UserService userService) {
        this.userService = userService;
    }

    // Create a new user
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserCreateRequest request) {
        UserResponse response = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Get user details by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable UUID id) {
        UserResponse response = userService.getUser(id);
        return ResponseEntity.ok(response);
    }

    // Assign a role to a user (optional expiration)
    @PostMapping("/{userId}/roles/{roleId}")
    public ResponseEntity<UserRoleAssignment> assignRoleToUser(
            @PathVariable UUID userId,
            @PathVariable UUID roleId,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime expiresAt,
            @RequestParam String assignedBy) {

        UserRoleAssignment assignment = userService.assignRole(userId, roleId, expiresAt, assignedBy);
        return ResponseEntity.status(HttpStatus.CREATED).body(assignment);
    }
}
