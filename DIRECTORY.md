## src
  * main
    * java
        * infrastructure
          * config
              * [WebConfig](https://github.com/33mestre/sognisport/tree/master/src/main/java/com/sognisport/infrastructure/config/WebConfig.java)
              * [SwaggerConfig](https://github.com/33mestre/sognisport/tree/master/src/main/java/com/sognisport/infrastructure/config/SwaggerConfig.java)
              * [SecurityConfig](https://github.com/33mestre/sognisport/tree/master/src/main/java/com/sognisport/infrastructure/config/SecurityConfig.java)
              * [RestTemplateConfig](https://github.com/33mestre/sognisport/tree/master/src/main/java/com/sognisport/infrastructure/config/RestTemplateConfig.java)
          * exception
              * [BusinessException](https://github.com/33mestre/sognisport/tree/master/src/main/java/com/sognisport/infrastructure/exception/BusinessException.java)
              * [ResourceNotFoundException](https://github.com/33mestre/sognisport/tree/master/src/main/java/com/sognisport/infrastructure/exception/ResourceNotFoundException.java)
          * [MethodValidationConfig](https://github.com/33mestre/sognisport/tree/master/src/main/java/com/sognisport/MethodValidationConfig.java)
          * [SognisportApplication](https://github.com/33mestre/sognisport/tree/master/src/main/java/com/sognisport/SognisportApplication.java)
        * application
          * controller
              * [HomeController](https://github.com/33mestre/sognisport/tree/master/src/main/java/com/sognisport/application/controller/HomeController.java)
              * [CurrencyConversionController](https://github.com/33mestre/sognisport/tree/master/src/main/java/com/sognisport/application/controller/CurrencyConversionController.java)
              * [SwaggerRedirectController](https://github.com/33mestre/sognisport/tree/master/src/main/java/com/sognisport/application/controller/SwaggerRedirectController.java)
          * service
              * [CurrencyConversionService](https://github.com/33mestre/sognisport/tree/master/src/main/java/com/sognisport/application/service/CurrencyConversionService.java)
          * dto
              * [CurrencyConversionDTO](https://github.com/33mestre/sognisport/tree/master/src/main/java/com/sognisport/application/dto/CurrencyConversionDTO.java)
        * domain
          * service
              * [CurrencyConversionDomainService](https://github.com/33mestre/sognisport/tree/master/src/main/java/com/sognisport/domain/service/CurrencyConversionDomainService.java)
          * model
              * [Currency](https://github.com/33mestre/sognisport/tree/master/src/main/java/com/sognisport/domain/model/Currency.java)
              * [CurrencyConversion](https://github.com/33mestre/sognisport/tree/master/src/main/java/com/sognisport/domain/model/CurrencyConversion.java)
          * repository
              * [CurrencyConversionRepository](https://github.com/33mestre/sognisport/tree/master/src/main/java/com/sognisport/domain/repository/CurrencyConversionRepository.java)
        * logging
            * [HttpLoggingFilter](https://github.com/33mestre/sognisport/tree/master/src/main/java/com/sognisport/logging/HttpLoggingFilter.java)
            * [CustomRequestLoggingFilter](https://github.com/33mestre/sognisport/tree/master/src/main/java/com/sognisport/logging/CustomRequestLoggingFilter.java)
            * [LoggingInterceptor](https://github.com/33mestre/sognisport/tree/master/src/main/java/com/sognisport/logging/LoggingInterceptor.java)
        * console
            * [CurrencyConverterConsoleApp](https://github.com/33mestre/sognisport/tree/master/src/main/java/com/sognisport/console/CurrencyConverterConsoleApp.java)


---

## Package Documentation

- [com.shelson.infrastructure.config](https://github.com/33mestre/sognisport/tree/master/src/main/java/com/sognisport/com/sognisport/infrastructure/config)
This package contains the configuration settings for the application infrastructure.

- [com.shelson.infrastructure.exception](https://github.com/33mestre/sognisport/tree/master/src/main/java/com/sognisport/com/sognisport/infrastructure/exception)
This package contains the exception classes for the application infrastructure.

- [com.shelson.application.controller](https://github.com/33mestre/sognisport/tree/master/src/main/java/com/sognisport/com/sognisport/application/controller)
This package contains the REST controllers for the application.  <p> Controllers are responsible for receiving HTTP requests, processing those requests, and returning HTTP responses. </p>

- [com.shelson.application.service](https://github.com/33mestre/sognisport/tree/master/src/main/java/com/sognisport/com/sognisport/application/service)
This package contains the services of the application, responsible for business logic.

- [com.shelson.application.dto](https://github.com/33mestre/sognisport/tree/master/src/main/java/com/sognisport/com/sognisport/application/dto)
This package contains the Data Transfer Objects (DTOs) used to transfer data between the layers of the application.

- [com.shelson.domain.service](https://github.com/33mestre/sognisport/tree/master/src/main/java/com/sognisport/com/sognisport/domain/service)
This package contains the domain services of the application.  <p> Domain services are responsible for business logic and for implementing the application's business rules. </p>

- [com.shelson.domain.model](https://github.com/33mestre/sognisport/tree/master/src/main/java/com/sognisport/com/sognisport/domain/model)
This package contains the domain model classes of the application.

- [com.shelson.domain.repository](https://github.com/33mestre/sognisport/tree/master/src/main/java/com/sognisport/com/sognisport/domain/repository)
This package contains the repositories for the application, responsible for communication with the database.

- [com.shelson.logging](https://github.com/33mestre/sognisport/tree/master/src/main/java/com/sognisport/com/sognisport/logging)
This package contains classes related to logging in the application.

- [com.shelson.console](https://github.com/33mestre/sognisport/tree/master/src/main/java/com/sognisport/com/sognisport/console)
This package contains the console application for currency conversion.

