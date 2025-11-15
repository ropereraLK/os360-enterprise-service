package com.os360.enterprise.validator;

import com.os360.enterprise.dto.*;
import com.os360.enterprise.entity.Person;
import com.os360.enterprise.exception.validation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class PersonValidator {

    public void validateForCreate(PersonCreateRequest request) {
        if (!StringUtils.hasText(request.getFirstName())) {
            throw new ValidationException("First name is required");
        }
        if (!StringUtils.hasText(request.getLastName())) {
            throw new ValidationException("Last name is required");
        }
    }

    public void validateForUpdate(PersonUpdateRequest request, Person existing) {
        if (!StringUtils.hasText(request.getFirstName())) {
            throw new ValidationException("First name cannot be blank");
        }
    }

    public void validateForPatch(PersonPatchRequest request, Person existing) {
        // Example: no validation errors if everything is optional
    }
}
