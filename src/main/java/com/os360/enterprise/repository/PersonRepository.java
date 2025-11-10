package com.os360.enterprise.repository;

import com.os360.enterprise.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {
    List<Person> findAllByIsDeletedFalse();
    Optional<Person> findByIdAndIsDeletedFalse(UUID id);
}
