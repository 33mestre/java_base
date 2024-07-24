//[java_base](../../../index.md)/[com.shelson.infrastructure.exception](../index.md)/[RestExceptionHandler](index.md)/[handleAllExceptions](handle-all-exceptions.md)

# handleAllExceptions

[JVM]\

@ExceptionHandler(value = [Exception::class](https://docs.oracle.com/javase/8/docs/api/java/lang/Exception.html))

fun [handleAllExceptions](handle-all-exceptions.md)(ex: [Exception](https://docs.oracle.com/javase/8/docs/api/java/lang/Exception.html), request: WebRequest): ResponseEntity&lt;[Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)&gt;

Handles all uncaught exceptions.

#### Return

A ResponseEntity containing the error details and the HTTP status INTERNAL_SERVER_ERROR.

#### Parameters

JVM

| | |
|---|---|
| ex | The exception thrown. |
| request | The web request object. |
