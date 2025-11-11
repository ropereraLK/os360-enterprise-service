package com.os360.enterprise.repository;

import com.os360.enterprise.entity.UserRoleAssignment;
import com.os360.enterprise.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface UserRoleAssignmentRepository extends JpaRepository<UserRoleAssignment, UUID> {

    // Get all active role assignments for a user
    List<UserRoleAssignment> findByUserAndExpiresAtAfterOrExpiresAtIsNull(User user, OffsetDateTime now);

    // Optional: find all assignments for a specific role
    List<UserRoleAssignment> findByRoleId(UUID roleId);
}
