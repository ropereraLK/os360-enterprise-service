package com.os360.enterprise.mapper;

import com.os360.enterprise.dto.UserCreateRequest;
import com.os360.enterprise.dto.UserResponse;
import com.os360.enterprise.entity.User;
import com.os360.enterprise.entity.UserRoleAssignment;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    /**
     * Maps a User entity to UserResponse DTO.
     * Only active role assignments (not expired) are included.
     */
    public UserResponse toResponse(User user) {
        if (user == null) return null;

        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEnabled(user.isEnabled());

        // Map active roles
        Set<String> activeRoles = user.getRoleAssignments().stream()
                .filter(r -> r.getExpiresAt() == null || r.getExpiresAt().isAfter(OffsetDateTime.now()))
                .map(r -> r.getRole().getName())
                .collect(Collectors.toSet());
        response.setRoles(activeRoles);

        // Map inherited Person fields
        response.setFirstName(user.getFirstName());
        response.setMiddleName(user.getMiddleName());
        response.setLastName(user.getLastName());
        response.setPreferredName(user.getPreferredName());
        response.setCountryCode(user.getCountryCode());
        response.setGender(user.getGender());
        response.setDateOfBirth(user.getDateOfBirth());
        response.setProfileImageUrl(user.getProfileImageUrl());

        return response;
    }

    /**
     * Maps UserCreateRequest DTO to User entity.
     * Password should be hashed in service layer before saving.
     */
    public User toEntity(UserCreateRequest request) {
        if (request == null) return null;

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPasswordHash(request.getPassword()); // hash in service before saving
        user.setFirstName(request.getFirstName());
        user.setMiddleName(request.getMiddleName());
        user.setLastName(request.getLastName());
        user.setPreferredName(request.getPreferredName());
        user.setCountryCode(request.getCountryCode());
        user.setGender(request.getGender());
        user.setDateOfBirth(request.getDateOfBirth());
        user.setProfileImageUrl(request.getProfileImageUrl());
        user.setEnabled(true);
        user.setAccountLocked(false);
        user.setAccountExpired(false);
        user.setCredentialsExpired(false);

        return user;
    }
}
