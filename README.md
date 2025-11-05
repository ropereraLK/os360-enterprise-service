# Open Suite 360 Enterprise Service

Open Suite 360 Enterprise Service is a **Spring Boot 3.x backend application** that manages companies and related HR functionalities. It provides REST APIs with Swagger/OpenAPI documentation, robust validations, and full testing support.

---

## Table of Contents

* [Project Overview](#project-overview)
* [Features](#features)
* [Technologies](#technologies)
* [Setup](#setup)
* [API Documentation](#api-documentation)
* [Data Transfer Objects (DTOs)](#data-transfer-objects-dtos)
* [Validation Rules](#validation-rules)
* [Testing](#testing)
* [Database](#database)
* [Security](#security)
* [Contribution](#contribution)
* [License](#license)

---

## Project Overview

The project provides:

* CRUD operations for companies
* Validation for unique system company and parent company existence
* Active/inactive status management
* Validity dates (`validFrom`, `validTo`)
* Integration with external systems

---

## Features

* Create, read, update, and delete companies
* Parent company relationship management
* Single system company validation (`isSystemCompany`)
* REST API endpoints documented via Swagger
* Unit and integration testing
* DTO mapping and conversion from entity to API response

---

## Technologies

* Java 17
* Spring Boot 3.x
* Spring Data JPA
* PostgreSQL (production) / H2 (tests)
* Springdoc OpenAPI / Swagger
* Lombok
* Maven

---

## Setup

1. Clone the repository:

```bash
git clone https://github.com/ropereraLK/os360-enterprise-service.git
cd os360-enterprise-service
```

2. Configure database in `application.yml` or `application.properties`.

3. Build and run the application:

```bash
./mvnw clean install
./mvnw spring-boot:run
```

---

## API Documentation

Swagger UI is available at:

```
http://localhost:8080/swagger-ui.html
```

OpenAPI JSON:

```
http://localhost:8080/v3/api-docs
```

Example endpoint:

```http
GET /companies/{id}
```

---

## Data Transfer Objects (DTOs)

### `CompanyResponse`

* `code` – Company code (String)
* `name` – Company name (String)
* `externalSystem` – External system name (String)
* `externalId` – External ID in the system (String)
* `parentCompanyId` – Parent company UUID (UUID)
* `isActive` – Active flag (boolean)
* `logoUrl` – Company logo URL (String)
* `validFrom` – Start date of validity (LocalDate)
* `validTo` – End date of validity (LocalDate)
* `isSystemCompany` – Indicates if this is the system company (boolean)

---

## Validation Rules

* Only **one company** can have `isSystemCompany = true`.
* `parentCompanyId` must exist if provided.
* `validFrom` defaults to `LocalDate.MIN` if not provided.
* Mandatory fields: `code`, `name`.

---

## Testing

### Unit Tests

* Test individual components (services, mappers, validators)
* Use **JUnit 5**, **Mockito**, **AssertJ**
* Examples: `CompanyServiceTest`, `CompanyMapperTest`

### Integration Tests

* Test **controller → service → repository → database** flow
* Use **MockMvc** for HTTP requests
* Use **H2 in-memory DB** for isolation
* Test business rules (e.g., system company uniqueness, parent company existence)
* Rollback transactions after each test using `@Transactional`
* Example: `CompanyControllerIT`

---

## Database

* PostgreSQL (production)
* H2 in-memory DB (tests)
* Managed via Spring Data JPA

---

## Security

* Secure Swagger UI for internal use
* Validate all API inputs
* Monitor dependencies for vulnerabilities (e.g., CVEs in Springdoc/OpenAPI)
* Follow best practices for API access and authentication

---

## Contribution

1. Fork the repository
2. Create a feature branch
3. Implement features or bug fixes
4. Run unit and integration tests
5. Submit a pull request

---

## License

MIT License
