package com.os360.enterprise.repository;

import com.os360.enterprise.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID> {

    Optional<Company> findByCode(String code);

    boolean existsByCode(String code);

    Optional<Company> findByExternalSystemAndExternalId(String externalSystem, String externalId);

    boolean existsByExternalSystemAndExternalId(String externalSystem, String externalId);

}
