package com.os360.enterprise.service;

import com.os360.enterprise.dto.SiteCreateRequest;
import com.os360.enterprise.dto.SiteResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class SiteService {
    public Optional<SiteResponse> create(@Valid SiteCreateRequest siteCreateRequest) {
        return null;
    }

    public Optional<SiteResponse> get(UUID id) {
        return null;
    }
}
