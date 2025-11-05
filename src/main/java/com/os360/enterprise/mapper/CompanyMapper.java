package com.os360.enterprise.mapper;

import com.os360.enterprise.dto.CompanyResponse;
import com.os360.enterprise.entity.Company;
import org.springframework.beans.BeanUtils;

import java.util.Optional;

public class CompanyMapper {

    public Optional<CompanyResponse> toResponse(Company entity) {
        CompanyResponse dto = new CompanyResponse();
        BeanUtils.copyProperties(entity, dto);

        return Optional.of(dto);
    }

}
