//[java_base](../../../index.md)/[com.shelson.infrastructure.exception](../index.md)/[RestExceptionHandler](index.md)/[handleIllegalArgumentException](handle-illegal-argument-exception.md)

# handleIllegalArgumentException

[JVM]\

@ExceptionHandler(value = [IllegalArgumentException::class](https://docs.oracle.com/javase/8/docs/api/java/lang/IllegalArgumentException.html))

fun [handleIllegalArgumentException](handle-illegal-argument-exception.md)(ex: [IllegalArgumentException](https://docs.oracle.com/javase/8/docs/api/java/lang/IllegalArgumentException.html), request: WebRequest): ResponseEntity&lt;[Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)&gt;

Handles exceptions of type [IllegalArgumentException](https://docs.oracle.com/javase/8/docs/api/java/lang/IllegalArgumentException.html).

#### Return

A ResponseEntity containing the error details and the HTTP status BAD_REQUEST.

#### Parameters

JVM

| | |
|---|---|
| ex | The exception thrown. |
| request | The web request object. |
