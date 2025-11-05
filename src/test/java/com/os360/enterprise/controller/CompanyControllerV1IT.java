package com.os360.enterprise.controller;

import com.os360.enterprise.repository.CompanyRepository;
import com.os360.enterprise.service.CompanyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import jakarta.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.yml")
@Transactional
@SpringBootTest
class CompanyControllerV1IT {

    @SpringBootApplication(scanBasePackages = {"com.os360.enterprise"},
            exclude = {ErrorMvcAutoConfiguration.class})
    static class TestConfig {
        // Nested config to exclude ErrorMvcAutoConfiguration
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CompanyRepository companyRepository;

    @BeforeEach
    void setUp() {
        companyRepository.deleteAll();
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
}
