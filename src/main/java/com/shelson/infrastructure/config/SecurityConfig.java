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
package com.shelson.infrastructure.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration for the Spring Security application.
 * This class configures the security policies applied to HTTP requests,
 * focusing on disabling features that may not be necessary for APIs or services
 * that do not require state or session.
 * 
 * <p>Configured features:</p>
 * <ul>
 *   <li>Disables CSRF protection, useful for REST APIs that use token-based authentication where the CSRF risk is minimal.</li>
 *   <li>Allows unrestricted access to all routes, ideal for public services or during initial development.</li>
 *   <li>Disables XSS protection, considering that content control can be done by more modern CSP policies.</li>
 *   <li>Implements a content security policy (CSP) to prevent loading untrusted resources.</li>
 *   <li>Disables HTTP basic authentication, favoring more secure authentication methods like JWT tokens.</li>
 * </ul>
 * 
 * <p>Usage example:</p>
 * <pre>
 * {@code
 * http
 *   .csrf(csrf -> csrf.disable())
 *   .authorizeHttpRequests(authz -> authz.anyRequest().permitAll())
 *   .headers(headers -> headers
 *     .xssProtection(xss -> xss.disable())
 *     .contentSecurityPolicy(csp -> csp.policyDirectives("script-src 'self'; object-src 'none';")))
 *   .httpBasic(httpBasic -> httpBasic.disable());
 * }
 * </pre>
 *
 * <p>This configuration ensures that the application is using up-to-date and recommended security practices,
 * aligned with the latest versions and security practices of Spring Security.</p>
 * 
 * @version 0.6.3
 * @since 2024-07-24
 * 
 * @author Shelson Ferrari
 * 
 * @see org.springframework.security.config.annotation.web.builders.HttpSecurity
 * @see org.springframework.security.web.SecurityFilterChain
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    /**
     * Configures the security filter chain for HTTP.
     * 
     * @param http The HttpSecurity object to configure HTTP request security.
     * @return The configured security filter chain.
     * @throws Exception If an error occurs while configuring security.
     */
    @Bean
     SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        logger.info("Configuring security settings...");

        http.csrf(csrf -> csrf.disable());
        logger.info("CSRF protection disabled");

        http.authorizeHttpRequests(authz -> authz.anyRequest().permitAll());
        logger.info("All requests are permitted without authentication");

        http.headers(headers -> {
            headers.xssProtection(xss -> xss.disable());
            headers.contentSecurityPolicy(csp -> csp.policyDirectives("script-src 'self'; object-src 'none';"));
        });
        logger.info("Security headers configured");

        http.httpBasic(httpBasic -> httpBasic.disable());
        logger.info("HTTP Basic authentication disabled");

        logger.info("Security settings configured successfully");
        return http.build();
    }
}
