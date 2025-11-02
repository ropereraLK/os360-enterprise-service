-- Enable pgcrypto for UUID generation (if not already enabled)
CREATE EXTENSION IF NOT EXISTS pgcrypto;

-- ================================
-- Table: party
-- ================================
CREATE TABLE party (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    party_type VARCHAR(50) NOT NULL,
    external_system VARCHAR(100),
    external_id VARCHAR(100),
    country_code CHAR(2),
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    created_by UUID NULL,
    last_modified_at TIMESTAMPTZ NULL,
    last_modified_by UUID NULL,
    deleted_at TIMESTAMPTZ NULL,
    deleted_by UUID NULL,
    version BIGINT NOT NULL DEFAULT 1
);

ALTER TABLE party
ADD CONSTRAINT party_uc_external_system UNIQUE (external_system, external_id);

CREATE INDEX party_idx_type ON party (party_type);
CREATE INDEX party_idx_external_system ON party (external_system, external_id);
CREATE INDEX party_idx_is_active ON party (is_active);

-- ================================
-- Table: company
-- ================================
CREATE TABLE company (
    id UUID PRIMARY KEY REFERENCES party (id) ON DELETE CASCADE,
    code VARCHAR(100) NOT NULL UNIQUE,
    name VARCHAR(300) NOT NULL,
    parent_company_id UUID NULL,
    logo_url TEXT,
    valid_from DATE NOT NULL DEFAULT DATE '1900-01-01',
    valid_to DATE NOT NULL DEFAULT DATE '9999-01-01',
    is_system_company BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT fk_company_parent FOREIGN KEY (parent_company_id)
        REFERENCES company (id) ON DELETE SET NULL
);

ALTER TABLE company
ADD CONSTRAINT company_chk_valid_dates
CHECK (valid_to IS NULL OR valid_to >= valid_from);

CREATE INDEX company_idx_code ON company (code);
CREATE INDEX company_idx_name ON company (name);

-- ================================
-- Table: company_time_zone
-- ================================
CREATE TABLE company_time_zone (
    id BIGSERIAL PRIMARY KEY,       -- auto-increment
    company_id UUID NOT NULL REFERENCES company(id) ON DELETE CASCADE,
    time_zone VARCHAR(100) NOT NULL,
    is_default BOOLEAN NOT NULL
);

-- Ensure only one default per company
CREATE UNIQUE INDEX company_tz_unique_default
ON company_time_zone (company_id)
WHERE is_default = TRUE;

CREATE INDEX company_tz_idx_company_id
ON company_time_zone (company_id);

-- ================================
-- Table: company_language
-- ================================
--CREATE TABLE company_language (
--    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
--    company_id UUID NOT NULL,
--    language_code VARCHAR(10) NOT NULL,
--    CONSTRAINT fk_cl_company FOREIGN KEY (company_id)
--        REFERENCES company (id) ON DELETE CASCADE
--);

-- ================================
-- Table: site
-- ================================
CREATE TABLE site (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    company_id UUID NOT NULL REFERENCES company(id) ON DELETE CASCADE,
    site_code VARCHAR(100) NOT NULL,
    site_name VARCHAR(300) NOT NULL,
    site_type VARCHAR(50) NOT NULL,
    company_time_zone_id BIGINT NOT NULL REFERENCES company_time_zone(id) ON DELETE RESTRICT,
    is_default BOOLEAN NOT NULL DEFAULT FALSE,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    created_by UUID,
    last_modified_at TIMESTAMPTZ,
    last_modified_by UUID,
    deleted_at TIMESTAMPTZ,
    deleted_by UUID,
    version BIGINT DEFAULT 1
);

-- Indexes
CREATE INDEX site_idx_company ON site(company_id);
CREATE INDEX site_idx_site_code ON site(site_code);