//[java_base](../../../index.md)/[com.shelson.infrastructure.config](../index.md)/[SwaggerConfig](index.md)

# SwaggerConfig

@Configuration

@EnableWebSecurity

open class [SwaggerConfig](index.md)

Swagger configuration for API documentation using SpringDoc. This class defines centralized configuration for the API documentation of a Spring Boot application, utilizing SpringDoc OpenAPI 3 to generate dynamic and interactive RESTful API documentation. 

Configurations are externalized through the application.properties file for easy maintenance and flexibility. Properties are injected directly into the class fields using Spring Framework's Value annotation.

Main configured features:

- Grouping APIs by name, packages to scan, and specific paths.
- Customization of API information such as title, description, and version.
- Modular configuration that can be adjusted without code changes.

This configuration supports building robust API documentation and is ideal for applications that require clarity and detail in their endpoint specifications.

#### Since

2024-07-24

#### Author

Shelson Ferrari

#### See also

| |
|---|
| GroupedOpenApi |
| OpenAPI |
| EnableWebSecurity |

## Constructors

| | |
|---|---|
| [SwaggerConfig](-swagger-config.md) | [JVM]<br>constructor() |
