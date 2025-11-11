package com.os360.enterprise.service;

import com.os360.enterprise.dto.UserCreateRequest;
import com.os360.enterprise.dto.UserResponse;
import com.os360.enterprise.entity.User;
import com.os360.enterprise.entity.UserRole;
import com.os360.enterprise.entity.UserRoleAssignment;
import com.os360.enterprise.exception.domain.EntityNotFoundException;
import com.os360.enterprise.mapper.UserMapper;
import com.os360.enterprise.repository.UserRepository;
import com.os360.enterprise.repository.UserRoleRepository;
import com.os360.enterprise.repository.UserRoleAssignmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserRoleAssignmentRepository userRoleAssignmentRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository,
                       UserRoleRepository userRoleRepository,
                       UserRoleAssignmentRepository userRoleAssignmentRepository,
                       UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.userRoleAssignmentRepository = userRoleAssignmentRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    public UserResponse createUser(UserCreateRequest request) {
        User user = userMapper.toEntity(request);
        userRepository.save(user);
        return userMapper.toResponse(user);
    }

    @Transactional(readOnly = true)
    public UserResponse getUser(UUID userId) {
        User user = userRepository.findByIdAndIsDeletedFalse(userId)
                .orElseThrow(() -> new EntityNotFoundException(User.class, userId));
        return userMapper.toResponse(user);
    }

    @Transactional
    public UserRoleAssignment assignRole(UUID userId, UUID roleId, OffsetDateTime expiresAt, String assignedBy) {
        User user = userRepository.findByIdAndIsDeletedFalse(userId)
                .orElseThrow(() -> new EntityNotFoundException(User.class, userId));
        UserRole role = userRoleRepository.findById(roleId)
                .orElseThrow(() -> new EntityNotFoundException(UserRole.class, roleId));

        UserRoleAssignment assignment = new UserRoleAssignment();
        assignment.setUser(user);
        assignment.setRole(role);
        assignment.setAssignedAt(OffsetDateTime.now());
        assignment.setExpiresAt(expiresAt);
        assignment.setAssignedBy(assignedBy);

        userRoleAssignmentRepository.save(assignment);

        return assignment;
    }
}
