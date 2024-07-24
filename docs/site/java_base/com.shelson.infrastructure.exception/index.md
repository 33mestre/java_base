//[java_base](../../index.md)/[com.shelson.infrastructure.exception](index.md)

# Package-level declarations

[JVM]\
This package contains the exception classes for the application infrastructure.

## Types

| Name | Summary |
|---|---|
| [BusinessException](-business-exception/index.md) | [JVM]<br>@ResponseStatus(code = HttpStatus.BAD_REQUEST)<br>open class [BusinessException](-business-exception/index.md) : [RuntimeException](https://docs.oracle.com/javase/8/docs/api/java/lang/RuntimeException.html)<br>Custom exception for business errors. |
| [ResourceNotFoundException](-resource-not-found-exception/index.md) | [JVM]<br>open class [ResourceNotFoundException](-resource-not-found-exception/index.md) : [RuntimeException](https://docs.oracle.com/javase/8/docs/api/java/lang/RuntimeException.html)<br>Exception thrown when a requested resource is not found. |
| [RestExceptionHandler](-rest-exception-handler/index.md) | [JVM]<br>@RestControllerAdvice<br>open class [RestExceptionHandler](-rest-exception-handler/index.md)<br>Global exception handler for the application. |
