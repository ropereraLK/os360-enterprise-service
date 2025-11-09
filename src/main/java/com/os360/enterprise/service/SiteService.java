package com.os360.enterprise.service;

import com.os360.enterprise.dto.SiteCreateRequest;
import com.os360.enterprise.dto.SitePatchRequest;
import com.os360.enterprise.dto.SiteResponse;
import com.os360.enterprise.dto.SiteUpdateRequest;
import com.os360.enterprise.entity.Site;
import com.os360.enterprise.entityUtils.EntityPatcher;
import com.os360.enterprise.exception.domain.EntityAlreadyDeletedException;
import com.os360.enterprise.exception.domain.EntityNotFoundException;
import com.os360.enterprise.mapper.SiteMapper;
import com.os360.enterprise.repository.SiteRepository;
import com.os360.enterprise.validator.SiteValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class SiteService {

    @Autowired
    private SiteRepository siteRepository;

    @Autowired
    private SiteValidator siteValidator;

    @Autowired
    private SiteMapper siteMapper;

    @Autowired
    private EntityPatcher entityPatcher;

    /**
     * Creates a new site.
     *
     * @param siteCreateRequest the site creation request DTO
     * @return Optional containing the created SiteResponse
     */
    public Optional<SiteResponse> create(SiteCreateRequest siteCreateRequest) {
        Site site = siteRepository.save(siteValidator.validateCreate(siteCreateRequest));
        return siteMapper.toResponse(site);
    }

    /**
     * Retrieves a site by its ID.
     *
     * @param id UUID of the site
     * @return Optional containing the SiteResponse
     * @throws EntityNotFoundException if the site does not exist
     */
    public Optional<SiteResponse> get(UUID id) {
        Site site = siteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Site.class, id));
        return siteMapper.toResponse(site);
    }

    /**
     * Soft deletes a site.
     *
     * @param id UUID of the site
     * @throws EntityNotFoundException if the site does not exist
     * @throws EntityAlreadyDeletedException if the site is already deleted
     */
    public void softDelete(UUID id) {
        Site site = siteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Site.class, id));

        if (site.isDeleted()) {
            throw new EntityAlreadyDeletedException(Site.class, id);
        }

        site.setDeleted(true);
        site.setDeletedAt(OffsetDateTime.now());
        site.setDeletedBy(UUID.fromString("00000000-0000-0000-0000-000000000000")); // TODO: replace with actual user
        siteRepository.save(site);
    }

    /**
     * Fully updates a site.
     *
     * @param id UUID of the site
     * @param updateRequest DTO containing updated values
     * @return Optional containing the updated SiteResponse
     */
    public Optional<SiteResponse> update(UUID id, SiteUpdateRequest updateRequest) {
        Site existingSite = siteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Site.class, id));

        if (updateRequest.getSiteCode() != null) existingSite.setSiteCode(updateRequest.getSiteCode());
        if (updateRequest.getSiteName() != null) existingSite.setSiteName(updateRequest.getSiteName());
        if (updateRequest.getSiteType() != null) existingSite.setSiteType(updateRequest.getSiteType());
        existingSite.setDefault(updateRequest.isDefault());
        existingSite.setActive(updateRequest.isActive());

        Site updatedSite = siteRepository.save(existingSite);
        return siteMapper.toResponse(updatedSite);
    }

    /**
     * Partially updates a site.
     *
     * @param id UUID of the site
     * @param sitePatchRequest DTO containing patch values
     * @return Optional containing the updated SiteResponse
     */
    public Optional<SiteResponse> patch(UUID id, SitePatchRequest sitePatchRequest) {
        Site existingSite = siteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Site.class, id));

        if (sitePatchRequest.getSiteCode() != null) existingSite.setSiteCode(sitePatchRequest.getSiteCode());
        if (sitePatchRequest.getSiteName() != null) existingSite.setSiteName(sitePatchRequest.getSiteName());
        if (sitePatchRequest.getSiteType() != null) existingSite.setSiteType(sitePatchRequest.getSiteType());
        existingSite.setDefault(sitePatchRequest.isDefault());
        existingSite.setActive(sitePatchRequest.isActive());

        Site updatedSite = siteRepository.save(existingSite);
        return siteMapper.toResponse(updatedSite);
    }
}
