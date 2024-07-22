//[java_base](../../../index.md)/[com.sognisport.logging](../index.md)/[LoggingInterceptor](index.md)/[preHandle](pre-handle.md)

# preHandle

[JVM]\
open fun [preHandle](pre-handle.md)(request: HttpServletRequest, response: HttpServletResponse, handler: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

Method executed before the handler processing. Logs the called endpoint, HTTP method, and request parameters.

#### Return

`true` to continue the execution chain; `false` to interrupt.

#### Parameters

JVM

| | |
|---|---|
| request | The HttpServletRequest object that contains the client's request. |
| response | The HttpServletResponse object that contains the server's response. |
| handler | The chosen handler to execute the request. |

#### Throws

| | |
|---|---|
| [Exception](https://docs.oracle.com/javase/8/docs/api/java/lang/Exception.html) | If an error occurs during pre-handling. |
