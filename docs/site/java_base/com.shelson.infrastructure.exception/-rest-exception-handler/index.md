//[java_base](../../../index.md)/[com.shelson.infrastructure.exception](../index.md)/[RestExceptionHandler](index.md)

# RestExceptionHandler

@RestControllerAdvice

open class [RestExceptionHandler](index.md)

Global exception handler for the application. This class captures and handles specific and general exceptions thrown by the controllers. 

 This handler covers: 

- Resource not found exceptions, returning a 404 status.
- Business exceptions, returning a 400 status.
- Validation errors, returning a 400 status with field-specific messages.
- Argument type mismatch errors, returning a 400 status with details of the type conversion issue.
- General exceptions, returning a 500 status for uncaught errors.

 Each handler logs the error details using SLF4J and returns a structured JSON response with the error information.

#### Since

2024-07-24

#### Author

Shelson Ferrari

#### See also

| |
|---|
| ExceptionHandler |
| RestControllerAdvice |

## Constructors

| | |
|---|---|
| [RestExceptionHandler](-rest-exception-handler.md) | [JVM]<br>constructor() |

## Functions

| Name | Summary |
|---|---|
| [handleAllExceptions](handle-all-exceptions.md) | [JVM]<br>@ExceptionHandler(value = [Exception::class](https://docs.oracle.com/javase/8/docs/api/java/lang/Exception.html))<br>fun [handleAllExceptions](handle-all-exceptions.md)(ex: [Exception](https://docs.oracle.com/javase/8/docs/api/java/lang/Exception.html), request: WebRequest): ResponseEntity&lt;[Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)&gt;<br>Handles all uncaught exceptions. |
| [handleBusinessException](handle-business-exception.md) | [JVM]<br>@ExceptionHandler(value = [BusinessException::class](../-business-exception/index.md))<br>fun [handleBusinessException](handle-business-exception.md)(ex: [BusinessException](../-business-exception/index.md), request: WebRequest): ResponseEntity&lt;[Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)&gt;<br>Handles exceptions of type [BusinessException](../-business-exception/index.md). |
| [handleIllegalArgumentException](handle-illegal-argument-exception.md) | [JVM]<br>@ExceptionHandler(value = [IllegalArgumentException::class](https://docs.oracle.com/javase/8/docs/api/java/lang/IllegalArgumentException.html))<br>fun [handleIllegalArgumentException](handle-illegal-argument-exception.md)(ex: [IllegalArgumentException](https://docs.oracle.com/javase/8/docs/api/java/lang/IllegalArgumentException.html), request: WebRequest): ResponseEntity&lt;[Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)&gt;<br>Handles exceptions of type [IllegalArgumentException](https://docs.oracle.com/javase/8/docs/api/java/lang/IllegalArgumentException.html). |
| [handleMethodArgumentTypeMismatchException](handle-method-argument-type-mismatch-exception.md) | [JVM]<br>@ExceptionHandler(value = MethodArgumentTypeMismatchException::class)<br>fun [handleMethodArgumentTypeMismatchException](handle-method-argument-type-mismatch-exception.md)(ex: MethodArgumentTypeMismatchException, request: WebRequest): ResponseEntity&lt;[Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)&gt;<br>Handles method argument type mismatch exceptions. |
| [handleResourceNotFoundException](handle-resource-not-found-exception.md) | [JVM]<br>@ExceptionHandler(value = [ResourceNotFoundException::class](../-resource-not-found-exception/index.md))<br>fun [handleResourceNotFoundException](handle-resource-not-found-exception.md)(ex: [ResourceNotFoundException](../-resource-not-found-exception/index.md), request: WebRequest): ResponseEntity&lt;[Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)&gt;<br>Handles exceptions of type [ResourceNotFoundException](../-resource-not-found-exception/index.md). |
| [handleValidationExceptions](handle-validation-exceptions.md) | [JVM]<br>@ExceptionHandler(value = MethodArgumentNotValidException::class)<br>fun [handleValidationExceptions](handle-validation-exceptions.md)(ex: MethodArgumentNotValidException): ResponseEntity&lt;[Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)&gt;<br>Handles method argument validation exceptions. |
