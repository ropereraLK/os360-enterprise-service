package com.os360.hr.contoller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/hr")
@Tag(name = "Company", description = "Operations related to companies, Version 1.0")
public class HrContoller {

    @GetMapping("/")
    public String getCompanies(
            ) {
        return "Hi";
    }

}

