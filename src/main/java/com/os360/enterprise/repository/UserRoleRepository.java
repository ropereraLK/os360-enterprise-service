package com.os360.enterprise.repository;

import com.os360.enterprise.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UUID> {

    // Find role by name
    Optional<UserRole> findByName(String name);
}
