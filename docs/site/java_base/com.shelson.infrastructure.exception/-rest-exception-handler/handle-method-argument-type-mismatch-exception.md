//[java_base](../../../index.md)/[com.shelson.infrastructure.exception](../index.md)/[RestExceptionHandler](index.md)/[handleMethodArgumentTypeMismatchException](handle-method-argument-type-mismatch-exception.md)

# handleMethodArgumentTypeMismatchException

[JVM]\

@ExceptionHandler(value = MethodArgumentTypeMismatchException::class)

fun [handleMethodArgumentTypeMismatchException](handle-method-argument-type-mismatch-exception.md)(ex: MethodArgumentTypeMismatchException, request: WebRequest): ResponseEntity&lt;[Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)&gt;

Handles method argument type mismatch exceptions.

#### Return

A ResponseEntity containing the error details and the HTTP status BAD_REQUEST.

#### Parameters

JVM

| | |
|---|---|
| ex | The exception thrown. |
| request | The web request object. |
