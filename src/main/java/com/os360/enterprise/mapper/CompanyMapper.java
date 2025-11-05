package com.os360.enterprise.mapper;

import com.os360.enterprise.dto.CompanyResponse;
import com.os360.enterprise.entity.Company;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CompanyMapper {

    public Optional<CompanyResponse> toResponse(Company entity) {
        CompanyResponse dto = new CompanyResponse();
        BeanUtils.copyProperties(entity, dto);
        return Optional.of(dto);
    }

}
