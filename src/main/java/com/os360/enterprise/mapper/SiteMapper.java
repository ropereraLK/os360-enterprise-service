package com.os360.enterprise.mapper;

import com.os360.enterprise.dto.SiteCreateRequest;
import com.os360.enterprise.dto.SiteResponse;
import com.os360.enterprise.entity.Site;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SiteMapper {

    /**
     * Maps a Site entity to SiteResponse DTO.
     *
     * @param site the Site entity
     * @return Optional containing SiteResponse if site is not null
     */
    public Optional<SiteResponse> toResponse(Site site) {
        if (site == null) {
            return Optional.empty();
        }

        SiteResponse response = new SiteResponse();
        response.setId(site.getId());
        response.setCompanyId(site.getCompany() != null ? site.getCompany().getId() : null);
        response.setCode(site.getSiteCode());
        response.setName(site.getSiteName());
        response.setSiteType(site.getSiteType() != null ? site.getSiteType().name() : null);
        response.setDefault(site.isDefault());
        response.setActive(site.isActive());


        response.setCreatedAt(site.getCreatedAt());
        response.setCreatedBy(site.getCreatedBy() != null ? site.getCreatedBy().toString() : null);
        response.setLastModifiedAt(site.getLastModifiedAt());
        response.setLastModifiedBy(site.getLastModifiedBy() != null ? site.getLastModifiedBy().toString() : null);

        return Optional.of(response);
    }

    /**
     * Maps a SiteCreateRequest DTO to Site entity.
     *
     * @param request the SiteCreateRequest
     * @return new Site entity
     */
    public Site toEntity(SiteCreateRequest request) {
        if (request == null) return null;

        Site site = new Site();
        site.setSiteCode(request.getCode());
        site.setSiteName(request.getName());
        site.setSiteType(request.getSiteType());
        site.setDefault(request.isDefault());

        site.setDeleted(false);

        // company should be set in the service layer
        return site;
    }

    /**
     * Updates an existing Site entity from a SiteCreateRequest (for PUT/UPDATE)
     * Only non-null fields in request overwrite existing values.
     *
     * @param request  the SiteCreateRequest
     * @param existing the existing Site entity
     */
    public void updateEntityFromRequest(SiteCreateRequest request, Site existing) {
        if (request == null || existing == null) return;

        if (request.getCode() != null) existing.setSiteCode(request.getCode());
        if (request.getName() != null) existing.setSiteName(request.getName());
        if (request.getSiteType() != null) existing.setSiteType(request.getSiteType());
        existing.setDefault(request.isDefault());
    }
}
