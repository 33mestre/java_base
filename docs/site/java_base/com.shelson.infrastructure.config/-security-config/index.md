//[java_base](../../../index.md)/[com.shelson.infrastructure.config](../index.md)/[SecurityConfig](index.md)

# SecurityConfig

[JVM]\
@Configuration

@EnableWebSecurity

open class [SecurityConfig](index.md)

Security configuration for the Spring Security application. This class configures the security policies applied to HTTP requests, focusing on disabling features that may not be necessary for APIs or services that do not require state or session. 

Configured features:

- Disables CSRF protection, useful for REST APIs that use token-based authentication where the CSRF risk is minimal.
- Allows unrestricted access to all routes, ideal for public services or during initial development.
- Disables XSS protection, considering that content control can be done by more modern CSP policies.
- Implements a content security policy (CSP) to prevent loading untrusted resources.
- Disables HTTP basic authentication, favoring more secure authentication methods like JWT tokens.

Usage example:

```kotlin

http
  .csrf(csrf -> csrf.disable())
  .authorizeHttpRequests(authz -> authz.anyRequest().permitAll())
  .headers(headers -> headers
    .xssProtection(xss -> xss.disable())
    .contentSecurityPolicy(csp -> csp.policyDirectives("script-src 'self'; object-src 'none';")))
  .httpBasic(httpBasic -> httpBasic.disable());

```

This configuration ensures that the application is using up-to-date and recommended security practices, aligned with the latest versions and security practices of Spring Security.

## Constructors

| | |
|---|---|
| [SecurityConfig](-security-config.md) | [JVM]<br>constructor() |
