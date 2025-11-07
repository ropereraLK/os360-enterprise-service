package com.os360.enterprise.integration.controller;

import com.os360.enterprise.entity.Company;
import com.os360.enterprise.integration.base.IntegrationTestBase;
import com.os360.enterprise.repository.CompanyRepository;
import com.os360.enterprise.testutils.builder.CompanyTestBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import jakarta.transaction.Transactional;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.yml")
@Transactional
@SpringBootTest
class CompanyControllerV1IT extends IntegrationTestBase {

    @Autowired
    private CompanyRepository companyRepository;

    @BeforeEach
    void setUp() {
        clearRepository(companyRepository);
    }

    @Test
    void testCreateCompany() throws Exception {
        String json = """
                {
                    "code": "OS361",
                    "name": "Open Suite 361",
                    "countryCode": "US",
                    "logoUrl": "https://example.com/logo.png",
                    "validFrom": "2025-11-01",
                    "validTo": "2095-12-31"
                }
                """;

        mockMvc.perform(post("/api/v1/companies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.code").value("OS361"))
                .andExpect(jsonPath("$.name").value("Open Suite 361"))
                // .andExpect(jsonPath("$.countryCode").value("US"))
                .andExpect(jsonPath("$.logoUrl").value("https://example.com/logo.png"))
                .andExpect(jsonPath("$.validFrom").value("2025-11-01"))
                .andExpect(jsonPath("$.validTo").value("2095-12-31")
                        //.andExpect(jsonPath("$.isActive").value(true)
                );
    }

    @Test
    void testCreateCompanyError() throws Exception {
        String json = """
                {
                    "name": "Open Suite 361",
                    "countryCode": "US",
                    "logoUrl": "https://example.com/logo.png",
                    "validFrom": "2025-11-01",
                    "validTo": "2095-12-31"
                }
                """;

        mockMvc.perform(post("/api/v1/companies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest()
                );
    }

    @Test
    void testGetCompanyById() throws Exception {
        // Arrange: create and persist a company using your new builder
        Company savedCompany = CompanyTestBuilder
                .builder("OS361", "Open Suite 361", "US")
                .withRepository(companyRepository)
                .buildAndPersist();

        // Act, Assert
        mockMvc.perform(get("/api/v1/companies/{id}", savedCompany.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                // .andExpect(jsonPath("$.id").value(savedCompany.getId().toString()))
                .andExpect(jsonPath("$.code").value("OS361"))
                .andExpect(jsonPath("$.name").value("Open Suite 361"));
                // .andExpect(jsonPath("$.countryCode").value("US"))
                // .andExpect(jsonPath("$.isSystemCompany").value(false));
    }

//    @Test
//    void testGetCompany_NotFound() throws Exception {
//        // given: random UUID that doesn’t exist
//        UUID randomId = UUID.randomUUID();
//
//        // when + then
//        mockMvc.perform(get("/api/v1/companies/{id}", randomId)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound())
//                .andExpect(jsonPath("$.error").value("Company not found"))
//                .andExpect(jsonPath("$.status").value(404))
//                .andExpect(jsonPath("$.path").value("/api/v1/companies/" + randomId));
//    }
}
