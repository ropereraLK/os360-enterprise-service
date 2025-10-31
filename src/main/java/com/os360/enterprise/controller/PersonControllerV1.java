package com.os360.enterprise.controller;

import com.os360.enterprise.dto.PersonV1DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/persons")
public class PersonControllerV1 {

    @Autowired
    PersonV1DTO person;

    @GetMapping
    public PersonV1DTO getPerson(){
        return person;
    }


}
