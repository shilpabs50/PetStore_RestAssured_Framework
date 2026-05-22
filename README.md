# PetStore Rest Assured Automation Framework

## Overview

This project is an enterprise-style API automation framework developed for the Swagger PetStore application using:

- Java
- Rest Assured
- TestNG
- Maven
- Docker
- GitHub Actions
- Allure Reporting

The framework is designed with scalability, reusability, maintainability, and CI/CD integration in mind.

It supports:
- API functional testing
- Data-driven testing
- Environment-based execution
- Parallel execution
- Dockerized execution
- Dynamic GitHub Actions workflow execution
- Allure reporting

---

# Tech Stack

| Technology | Purpose |
|---|---|
| Java | Programming Language |
| Rest Assured | API Automation |
| TestNG | Test Execution Framework |
| Maven | Build Management |
| Jackson | POJO Serialization/Deserialization |
| Log4j2 | Logging |
| Allure Report | Reporting |
| Docker | Containerized Execution |
| GitHub Actions | CI/CD Pipeline |

---

# Framework Features

- Builder Pattern Request Creation
- POJO Serialization & Deserialization
- Dynamic Environment Handling
- TestNG Group Execution
- Parallel Test Execution
- Reusable API Utilities
- Centralized Configuration Management
- Docker Support
- Dynamic GitHub Actions Workflow
- Allure Reporting Integration
- Negative Workflow Testing
- Request/Response Logging
- Thread-Safe Execution Support

---


# Environment Execution

Framework supports multiple environments:

- QA
- STG


Environment selection is dynamic using:

```bash
-Denv=qa
```

Example:

```bash
mvn clean test -Denv=stg
```

---

# Group Execution

Tests are organized using TestNG groups.

Example groups:
- smoke
- regression
- pet
- store

Example execution:

```bash
mvn clean test -Dgroups=smoke
```

---

# Running Tests Locally

## Run All Tests

```bash
mvn clean test
```

## Run Smoke Tests

```bash
mvn clean test -Dgroups=smoke
```

## Run Against STG

```bash
mvn clean test -Denv=stg
```

## Run Specific Group in Specific Environment

```bash
mvn clean test -Dgroups=regression -Denv=qa
```

---

# Docker Execution

## Build Docker Image

```bash
docker build -t petstore-framework .
```

## Run Tests Inside Docker

```bash
docker run --rm \
-e ENV=qa \
-e GROUP="-Dgroups=smoke" \
petstore-framework
```

---

# GitHub Actions Integration

Framework supports dynamic workflow execution using GitHub Actions.

User can select:
- Environment
- Test Group

during manual workflow execution.

Supported environments:
- qa
- stg


Supported groups:
- smoke
- regression
- pet
- store

---

# Allure Reporting

Generate Allure report:

```bash
allure serve target/allure-results
```

Generated reports include:
- Test execution summary
- Passed/Failed tests
- API request/response logs
- Execution timeline

---

# Sample Test

```java
@Test(groups = {"smoke"})
public void createPetTest() {

    PetRequest request =
            PetRequest.builder()
                    .id(101)
                    .name("Tom")
                    .status("available")
                    .build();

    Response response =
            PetClient.createPet(request);

    Assert.assertEquals(response.getStatusCode(), 200);
}
```

---

# CI/CD Pipeline Features

- Dynamic workflow dispatch
- Dockerized execution
- Artifact upload support
- Allure report publishing
- Environment-based execution
- Group-based execution

---

# Future Enhancements

- ThreadLocal implementation
- Jenkins Integration
- Kubernetes Execution
- Slack Notifications


---

# Author

PetStore Rest Assured Automation Framework developed for enterprise-level API automation learning and implementation.