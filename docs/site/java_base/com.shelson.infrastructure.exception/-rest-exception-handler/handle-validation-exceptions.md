//[java_base](../../../index.md)/[com.shelson.infrastructure.exception](../index.md)/[RestExceptionHandler](index.md)/[handleValidationExceptions](handle-validation-exceptions.md)

# handleValidationExceptions

[JVM]\

@ExceptionHandler(value = MethodArgumentNotValidException::class)

fun [handleValidationExceptions](handle-validation-exceptions.md)(ex: MethodArgumentNotValidException): ResponseEntity&lt;[Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)&gt;

Handles method argument validation exceptions.

#### Return

A ResponseEntity containing the error details and the HTTP status BAD_REQUEST.

#### Parameters

JVM

| | |
|---|---|
| ex | The exception thrown. |
