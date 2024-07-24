//[java_base](../../../index.md)/[com.shelson.infrastructure.exception](../index.md)/[RestExceptionHandler](index.md)/[handleBusinessException](handle-business-exception.md)

# handleBusinessException

[JVM]\

@ExceptionHandler(value = [BusinessException::class](../-business-exception/index.md))

fun [handleBusinessException](handle-business-exception.md)(ex: [BusinessException](../-business-exception/index.md), request: WebRequest): ResponseEntity&lt;[Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)&gt;

Handles exceptions of type [BusinessException](../-business-exception/index.md).

#### Return

A ResponseEntity containing the error details and the HTTP status BAD_REQUEST.

#### Parameters

JVM

| | |
|---|---|
| ex | The exception thrown. |
| request | The web request object. |
