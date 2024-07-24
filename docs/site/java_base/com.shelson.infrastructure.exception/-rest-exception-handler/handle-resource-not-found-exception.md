//[java_base](../../../index.md)/[com.shelson.infrastructure.exception](../index.md)/[RestExceptionHandler](index.md)/[handleResourceNotFoundException](handle-resource-not-found-exception.md)

# handleResourceNotFoundException

[JVM]\

@ExceptionHandler(value = [ResourceNotFoundException::class](../-resource-not-found-exception/index.md))

fun [handleResourceNotFoundException](handle-resource-not-found-exception.md)(ex: [ResourceNotFoundException](../-resource-not-found-exception/index.md), request: WebRequest): ResponseEntity&lt;[Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)&gt;

Handles exceptions of type [ResourceNotFoundException](../-resource-not-found-exception/index.md).

#### Return

A ResponseEntity containing the error details and the HTTP status NOT_FOUND.

#### Parameters

JVM

| | |
|---|---|
| ex | The exception thrown. |
| request | The web request object. |
