## src
  * main
    * java
        * config
            * [CamelConfig](https://github.com/33mestre/java_base/tree/master/src/main/java/com/shelson/config/CamelConfig.java)
            * [MethodValidationConfig](https://github.com/33mestre/java_base/tree/master/src/main/java/com/shelson/config/MethodValidationConfig.java)
            * [SharedConfig](https://github.com/33mestre/java_base/tree/master/src/main/java/com/shelson/config/SharedConfig.java)
            * [ProducerConfig](https://github.com/33mestre/java_base/tree/master/src/main/java/com/shelson/config/ProducerConfig.java)
        * infrastructure
          * config
              * [WebConfig](https://github.com/33mestre/java_base/tree/master/src/main/java/com/shelson/infrastructure/config/WebConfig.java)
              * [SwaggerConfig](https://github.com/33mestre/java_base/tree/master/src/main/java/com/shelson/infrastructure/config/SwaggerConfig.java)
              * [SecurityConfig](https://github.com/33mestre/java_base/tree/master/src/main/java/com/shelson/infrastructure/config/SecurityConfig.java)
              * [RestTemplateConfig](https://github.com/33mestre/java_base/tree/master/src/main/java/com/shelson/infrastructure/config/RestTemplateConfig.java)
          * exception
              * [RestExceptionHandler](https://github.com/33mestre/java_base/tree/master/src/main/java/com/shelson/infrastructure/exception/RestExceptionHandler.java)
              * [BusinessException](https://github.com/33mestre/java_base/tree/master/src/main/java/com/shelson/infrastructure/exception/BusinessException.java)
              * [ResourceNotFoundException](https://github.com/33mestre/java_base/tree/master/src/main/java/com/shelson/infrastructure/exception/ResourceNotFoundException.java)
          * [ShelsonApplication](https://github.com/33mestre/java_base/tree/master/src/main/java/com/shelson/ShelsonApplication.java)
        * application
          * controller
              * [HomeController](https://github.com/33mestre/java_base/tree/master/src/main/java/com/shelson/application/controller/HomeController.java)
              * [CurrencyConversionController](https://github.com/33mestre/java_base/tree/master/src/main/java/com/shelson/application/controller/CurrencyConversionController.java)
              * [SwaggerRedirectController](https://github.com/33mestre/java_base/tree/master/src/main/java/com/shelson/application/controller/SwaggerRedirectController.java)
          * service
              * [CurrencyConversionService](https://github.com/33mestre/java_base/tree/master/src/main/java/com/shelson/application/service/CurrencyConversionService.java)
              * [CurrencyConversionServiceApiResponse](https://github.com/33mestre/java_base/tree/master/src/main/java/com/shelson/application/service/CurrencyConversionServiceApiResponse.java)
          * processors
              * [ExchangeRateProcessor](https://github.com/33mestre/java_base/tree/master/src/main/java/com/shelson/application/processors/ExchangeRateProcessor.java)
          * dto
              * [CurrencyConversionDTO](https://github.com/33mestre/java_base/tree/master/src/main/java/com/shelson/application/dto/CurrencyConversionDTO.java)
          * routes
              * [CurrencyConversionRoute](https://github.com/33mestre/java_base/tree/master/src/main/java/com/shelson/application/routes/CurrencyConversionRoute.java)
        * domain
          * model
              * [Currency](https://github.com/33mestre/java_base/tree/master/src/main/java/com/shelson/domain/model/Currency.java)
              * [CurrencyConversion](https://github.com/33mestre/java_base/tree/master/src/main/java/com/shelson/domain/model/CurrencyConversion.java)
          * repository
              * [CurrencyConversionRepository](https://github.com/33mestre/java_base/tree/master/src/main/java/com/shelson/domain/repository/CurrencyConversionRepository.java)
        * logging
            * [HttpLoggingFilter](https://github.com/33mestre/java_base/tree/master/src/main/java/com/shelson/logging/HttpLoggingFilter.java)
            * [CustomRequestLoggingFilter](https://github.com/33mestre/java_base/tree/master/src/main/java/com/shelson/logging/CustomRequestLoggingFilter.java)
            * [LoggingInterceptor](https://github.com/33mestre/java_base/tree/master/src/main/java/com/shelson/logging/LoggingInterceptor.java)
        * console
            * [CurrencyConverterConsoleApp](https://github.com/33mestre/java_base/tree/master/src/main/java/com/shelson/console/CurrencyConverterConsoleApp.java)


---

## Package Documentation

- [com.shelson.config](https://github.com/33mestre/java_base/tree/master/src/main/java/com/shelson/com/shelson/config)
This package contains the configuration classes for the application. It includes configuration for Camel routes, shared application settings, and any other necessary configurations.

- [com.shelson.infrastructure.config](https://github.com/33mestre/java_base/tree/master/src/main/java/com/shelson/com/shelson/infrastructure/config)
This package contains the configuration settings for the application infrastructure.

- [com.shelson.infrastructure.exception](https://github.com/33mestre/java_base/tree/master/src/main/java/com/shelson/com/shelson/infrastructure/exception)
This package contains the exception classes for the application infrastructure.

- [com.shelson.application.controller](https://github.com/33mestre/java_base/tree/master/src/main/java/com/shelson/com/shelson/application/controller)
This package contains the REST controllers for the application.  <p> Controllers are responsible for receiving HTTP requests, processing those requests, and returning HTTP responses. </p>

- [com.shelson.application.service](https://github.com/33mestre/java_base/tree/master/src/main/java/com/shelson/com/shelson/application/service)
This package contains the services of the application, responsible for business logic.

- [com.shelson.application.dto](https://github.com/33mestre/java_base/tree/master/src/main/java/com/shelson/com/shelson/application/dto)
This package contains the Data Transfer Objects (DTOs) used to transfer data between the layers of the application.

- [com.shelson.application.routes](https://github.com/33mestre/java_base/tree/master/src/main/java/com/shelson/com/shelson/application/routes)
This package contains Camel routes for the currency conversion application. The routes handle incoming requests for currency conversion, process them using the CurrencyConversionService, and return the conversion results.  <p> The main route in this package is {@link com.shelson.application.routes.CurrencyConversionRoute}, which processes the conversion by extracting headers from the request, validating them, and invoking the service to perform the conversion. </p>

- [com.shelson.domain.model](https://github.com/33mestre/java_base/tree/master/src/main/java/com/shelson/com/shelson/domain/model)
This package contains the domain model classes of the application.

- [com.shelson.domain.repository](https://github.com/33mestre/java_base/tree/master/src/main/java/com/shelson/com/shelson/domain/repository)
This package contains the repositories for the application, responsible for communication with the database.

- [com.shelson.logging](https://github.com/33mestre/java_base/tree/master/src/main/java/com/shelson/com/shelson/logging)
This package contains classes related to logging in the application.

- [com.shelson.console](https://github.com/33mestre/java_base/tree/master/src/main/java/com/shelson/com/shelson/console)
This package contains the console application for currency conversion.


## Wiki Menu

[Home Page](/README.md)

### 1. Introduction to the Project
- [Overview](https://github.com/33mestre/java_base/wiki/overview): Presentation of the project, highlighting its purpose and the context in which it is embedded.
- [Project Objectives](https://github.com/33mestre/java_base/wiki/project-objectives): Enumeration of the main objectives that the project aims to achieve.
- [Scope and Functionalities](https://github.com/33mestre/java_base/wiki/scope-and-functionalities): Description of the main functionalities offered by the project and its scope of operation.

### 2. Configuration and Installation
- [Initial Configurations](https://github.com/33mestre/java_base/wiki/initial-configurations): Steps required to set up the development or production environment.
- [Installation Instructions](https://github.com/33mestre/java_base/wiki/installation-instructions): Detailed procedures for installing the project in different environments.
- [Docker Configuration](https://github.com/33mestre/java_base/wiki/docker-configuration): Specifications on how to configure and use Docker for the project.

### 3. Project Structure
- **Folder Structure**
- [Project Architecture](/ARCHITECTURE.md): Explanation of the architecture used, including design patterns and technical decisions.

### 4. Development
- [Apache Camel Integration](https://github.com/33mestre/java_base/wiki/apache-camel-integration): Guide on integrating Apache Camel into the project, including configuration and usage.
- [Development Flow](https://github.com/33mestre/java_base/wiki/development-flow): Description of the development process adopted, including planning, coding, and review stages.
- [Contributors and Authors](/AUTHORS.md): Recognition of the contributors to the project.
- [Contributions](/CONTRIBUTING.md): Guidelines on how to contribute to the project, including code standards and pull request requirements, tips and best practices.
- [Code of Conduct](/CODE_OF_CONDUCT.md): Behavioral guidelines expected for the project community.

### 5. API and Documentation
- [OpenAPI Specification](https://github.com/33mestre/java_base/wiki/openapi-specification): Details about the OpenAPI specification used to document the API endpoints.
- [API Documentation with Swagger](https://github.com/33mestre/java_base/wiki/api-documentation-with-swagger): Information on how to access and use the interactive API documentation generated by Swagger.
- [Javadoc Documentation](https://github.com/33mestre/java_base/wiki/javadoc-documentation): Information on the Javadoc documentation generated for the project.

### 6. Endpoints and Database
- [Endpoint Description](https://github.com/33mestre/java_base/wiki/endpoint-description): Details of the available API endpoints, including methods, parameters, and usage examples.
- [Database Management](https://github.com/33mestre/java_base/wiki/database-management): Strategies and practices for efficient management of the database used by the project.

### 7. Testing
- [Testing Strategies](https://github.com/33mestre/java_base/wiki/testing-strategies): Approach and methods used to test the software, including unit, integration, and E2E tests.
- [Testing Tools](https://github.com/33mestre/java_base/wiki/testing-tools): Description of the testing tools used in the project and how to configure them.

### 8. CI/CD and Automations
- [CI/CD Pipeline](https://github.com/33mestre/java_base/wiki/ci-cd-pipeline): Explanation of the continuous integration and delivery pipeline, detailing each stage and its function.
- [Automations and Artifact Generation](https://github.com/33mestre/java_base/wiki/automations-and-artifact-generation): Description of the automations incorporated into the CI/CD, including documentation generation and build artifacts.

### 9. Configuration Files
- [.gitignore, .editorconfig and project.properties](https://github.com/33mestre/java_base/wiki/gitignore-and-editorconfig-and-project-properties): Utility of these files to maintain code consistency and quality.
- [Maven Wrapper and application.properties](https://github.com/33mestre/java_base/wiki/maven-wrapper-and-application-properties): Explanation of how these files help standardize the development environment.
- [.env File and Travis CI Settings](https://github.com/33mestre/java_base/wiki/env-file-and-travis-ci-settings): Use of these files to configure the environment and CI integrations.

### 10. Best Practices
- [Code Standards and Security](https://github.com/33mestre/java_base/wiki/code-standards-and-security): Guidelines for maintaining code quality and security.
- [Monitoring and Logging Practices](https://github.com/33mestre/java_base/wiki/monitoring-and-logging-practices): Recommended techniques for monitoring and logging in the project.

### 11. Legal and Licensing
- [Licensing](https://github.com/33mestre/java_base/wiki/licensing): Information about the rights and restrictions associated with the use of the software.
- [Terms of Use](https://github.com/33mestre/java_base/wiki/terms-of-use): Information about the terms and conditions for using the software.

### 12. Projections and Innovations
- **Future Plans**.
- [Improvement Proposals](https://github.com/33mestre/java_base/wiki/improvement-proposals): Space for the community to suggest and debate improvements and innovations.

### 13. Attachments and Useful Links
- [External Links and References](https://github.com/33mestre/java_base/wiki/external-links-and-references): Additional resources and external documentation relevant to the project.

### 14. Security
- [Security Policy](/SECURITY.md): Details on the supported versions, reporting vulnerabilities, and general security practices.
