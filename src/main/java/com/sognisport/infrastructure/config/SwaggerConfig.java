/*
 * Copyright (c) 2024, Shelson Ferrari
 *
 * Licensed under the MIT License and the Apache License, Version 2.0 (the "Licenses"); you may not use this file except in
 * compliance with the Licenses. You may obtain a copy of the Licenses at
 *
 * MIT License:
 * https://opensource.org/licenses/MIT
 *
 * Apache License, Version 2.0:
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the Licenses is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See
 * the Licenses for the specific language governing permissions and limitations under the Licenses.
 */
package com.sognisport.infrastructure.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/**
 * Swagger configuration for API documentation using SpringDoc.
 * This class defines centralized configuration for the API documentation of a Spring Boot application,
 * utilizing SpringDoc OpenAPI 3 to generate dynamic and interactive RESTful API documentation.
 *
 * <p>Configurations are externalized through the application.properties file for easy maintenance and flexibility.
 * Properties are injected directly into the class fields using Spring Framework's {@link Value} annotation.</p>
 *
 * <p>Main configured features:</p>
 * <ul>
 *     <li>Grouping APIs by name, packages to scan, and specific paths.</li>
 *     <li>Customization of API information such as title, description, and version.</li>
 *     <li>Modular configuration that can be adjusted without code changes.</li>
 * </ul>
 *
 * <p>This configuration supports building robust API documentation and is ideal for applications
 * that require clarity and detail in their endpoint specifications.</p>
 */
@Configuration
@EnableWebSecurity
public class SwaggerConfig {

    private static final Logger logger = LoggerFactory.getLogger(SwaggerConfig.class);

    @Value("${swagger.group}")
    private String swaggerGroup;

    @Value("${swagger.packages-to-scan}")
    private String swaggerPackagesToScan;

    @Value("${swagger.paths-to-match}")
    private String swaggerPathsToMatch;

    @Value("${api.title}")
    private String apiTitle;

    @Value("${api.description}")
    private String apiDescription;

    @Value("${api.version}")
    private String apiVersion;

    /**
     * Configures and provides a GroupedOpenApi bean.
     * This bean groups specific endpoints under a common title to facilitate navigation
     * and organization in the Swagger UI.
     *
     * @return a {@link GroupedOpenApi} bean configured based on the defined properties.
     */
    @Bean
    GroupedOpenApi api() {
        logger.info("Configuring GroupedOpenApi for {}", swaggerGroup);
        return GroupedOpenApi.builder()
            .group(swaggerGroup)
            .packagesToScan(swaggerPackagesToScan)
            .pathsToMatch(swaggerPathsToMatch)
            .build();
    }

    /**
     * Configures and provides an OpenAPI bean.
     * This bean defines the general information of the API, such as title, description, and version,
     * which are presented in the documentation generated by the Swagger UI.
     *
     * @return an {@link OpenAPI} bean with the information defined in the properties.
     */
    @Bean
    OpenAPI customOpenAPI() {
        logger.info("Configuring OpenAPI with title: {}", apiTitle);
        return new OpenAPI()
            .info(new Info()
                .title(apiTitle)
                .description(apiDescription)
                .version(apiVersion));
    }
}
