package com.os360.enterprise.service;

import com.os360.enterprise.dto.PersonCreateRequest;
import com.os360.enterprise.dto.PersonResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {
    public Optional<PersonResponse> get(UUID id) {
        return null;
    }

    public Optional<PersonResponse> create(@Valid PersonCreateRequest personCreateRequest) {
        return null;
    }
}
