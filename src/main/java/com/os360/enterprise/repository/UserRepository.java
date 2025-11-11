package com.os360.enterprise.repository;

import com.os360.enterprise.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    // Find user by ID only if not deleted
    Optional<User> findByIdAndIsDeletedFalse(UUID id);

    // Find user by username (useful for login/authentication)
    Optional<User> findByUsername(String username);
}
