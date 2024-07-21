//[sognisport](../../../index.md)/[com.sognisport.logging](../index.md)/[LoggingInterceptor](index.md)/[afterCompletion](after-completion.md)

# afterCompletion

[JVM]\
open fun [afterCompletion](after-completion.md)(request: HttpServletRequest, response: HttpServletResponse, handler: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html), ex: [Exception](https://docs.oracle.com/javase/8/docs/api/java/lang/Exception.html))

Method executed after the request processing is completed. Can be used to log information or perform cleanup after the request completion.

#### Parameters

JVM

| | |
|---|---|
| request | The HttpServletRequest object that contains the client's request. |
| response | The HttpServletResponse object that contains the server's response. |
| handler | The chosen handler to execute the request. |
| ex | The exception thrown, if any. |

#### Throws

| | |
|---|---|
| [Exception](https://docs.oracle.com/javase/8/docs/api/java/lang/Exception.html) | If an error occurs after request completion. |
