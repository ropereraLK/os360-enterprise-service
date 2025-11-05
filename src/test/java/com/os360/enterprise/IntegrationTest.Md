# Integration Testing Guide for OS360 Enterprise Service

Integration tests ensure that **multiple layers of the application work together correctly**, including:

* Controller → Service → Repository → Database
* Business rule enforcement
* API responses and validations

This guide provides a detailed approach to writing, configuring, and running integration tests for the OS360 Enterprise Service.

---

## 1️⃣ Dependencies

Ensure the following dependencies are included in your `pom.xml` (or Gradle build):

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>

<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>test</scope>
</dependency>
```

* `spring-boot-starter-test` includes JUnit 5, Mockito, and AssertJ.
* H2 provides an **in-memory database** for testing without affecting production.

---

## 2️⃣ Test Configuration

Use a **test-specific profile** (e.g., `application-test.yml`) to configure the in-memory database:

```yaml
spring:
  datasource:
    url: jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1
    driverClassName: org.h2.Driver
    username: sa
    password:
  jpa:
    hibernate:
      ddl-auto: create-drop
    show-sql: true
```

---

## 3️⃣ Test Class Setup

Use `@SpringBootTest` and `@AutoConfigureMockMvc` for controller-level integration tests:

```java
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.yml")
@Transactional
public class CompanyControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CompanyRepository companyRepository;

    @BeforeEach
    void setUp() {
        companyRepository.deleteAll(); // Clean database before each test
    }
}
```

* `@Transactional` ensures **automatic rollback** after each test.
* `MockMvc` simulates HTTP requests without starting a real server.

---

## 4️⃣ Testing GET Endpoints

```java
@Test
void testGetCompany() throws Exception {
    Company company = new Company();
    company.setCode("OS360");
    company.setName("Open Suite 360");
    company.setIsActive(true);
    company.setIsSystemCompany(true);
    companyRepository.save(company);

    mockMvc.perform(get("/companies/{id}", company.getId())
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.code").value("OS360"))
        .andExpect(jsonPath("$.name").value("Open Suite 360"))
        .andExpect(jsonPath("$.isActive").value(true))
        .andExpect(jsonPath("$.isSystemCompany").value(true));
}
```

---

## 5️⃣ Testing POST Endpoints

```java
@Test
void testCreateCompany() throws Exception {
    String companyJson = """
        {
            "code": "OS361",
            "name": "Open Suite 361",
            "isActive": true,
            "isSystemCompany": false
        }
    """;

    mockMvc.perform(post("/companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(companyJson))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.code").value("OS361"))
        .andExpect(jsonPath("$.name").value("Open Suite 361"))
        .andExpect(jsonPath("$.isActive").value(true));
}
```

---

## 6️⃣ Testing Business Rules

### Only one system company allowed

```java
@Test
void testOnlyOneSystemCompanyAllowed() {
    Company sysCompany = new Company();
    sysCompany.setCode("SYS1");
    sysCompany.setIsSystemCompany(true);
    companyRepository.save(sysCompany);

    Company newCompany = new Company();
    newCompany.setCode("SYS2");
    newCompany.setIsSystemCompany(true);

    assertThrows(IllegalStateException.class, () -> {
        companyRepository.saveAndFlush(newCompany);
    });
}
```

### Parent company must exist

```java
@Test
void testParentCompanyMustExist() throws Exception {
    UUID nonExistentId = UUID.randomUUID();

    String json = """
        {
            "code": "OS362",
            "name": "Open Suite 362",
            "parentCompanyId": "%s"
        }
    """.formatted(nonExistentId);

    mockMvc.perform(post("/companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
        .andExpect(status().isBadRequest());
}
```

---

## 7️⃣ Best Practices

1. **Use an in-memory DB** (H2) for isolation from production.
2. **Clean DB** before each test (`deleteAll()`) to avoid interference.
3. **Use MockMvc** for controller-level tests.
4. **Test edge cases**: null values, invalid IDs, uniqueness constraints.
5. **Rollback after tests**: use `@Transactional` for automatic cleanup.
6. **Separate unit and integration tests** for clarity.
7. **Use `@TestPropertySource`** to switch configs for testing environment.

---

Integration testing ensures your **OS360 Enterprise Service** behaves correctly across all layers and enforces business rules reliably.
