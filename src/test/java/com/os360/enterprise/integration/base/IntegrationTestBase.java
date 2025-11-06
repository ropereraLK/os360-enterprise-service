package com.os360.enterprise.integration.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import jakarta.transaction.Transactional;

/**
 * Base class for all integration tests.
 * Provides MockMvc, transactional rollback, and test profile setup.
 */
@ActiveProfiles("test")
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.yml")
@Transactional
@SpringBootTest
public abstract class IntegrationTestBase {

    /**
     * Nested configuration to define scan base packages and exclude unwanted auto-configurations.
     */
    @SpringBootApplication(scanBasePackages = "com.os360.enterprise",
            exclude = {ErrorMvcAutoConfiguration.class})
    static class TestConfig {
        // Only for context bootstrap, no beans defined here
    }

    @Autowired
    protected MockMvc mockMvc;

    /**
     * Optional helper method to clear a repository before tests.
     * Example usage in concrete IT: clearRepository(companyRepository);
     */
    protected <T> void clearRepository(org.springframework.data.repository.CrudRepository<T, ?> repository) {
        repository.deleteAll();
    }
}
