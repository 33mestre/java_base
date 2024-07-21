//[sognisport](../../../index.md)/[com.sognisport.logging](../index.md)/[LoggingInterceptor](index.md)

# LoggingInterceptor

[JVM]\
@Component

open class [LoggingInterceptor](index.md) : HandlerInterceptor

Interceptor for logging HTTP request details. This interceptor logs the details of the request before, during, and after the handler processing.

## Constructors

| | |
|---|---|
| [LoggingInterceptor](-logging-interceptor.md) | [JVM]<br>constructor() |

## Functions

| Name | Summary |
|---|---|
| [afterCompletion](after-completion.md) | [JVM]<br>open fun [afterCompletion](after-completion.md)(request: HttpServletRequest, response: HttpServletResponse, handler: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html), ex: [Exception](https://docs.oracle.com/javase/8/docs/api/java/lang/Exception.html))<br>Method executed after the request processing is completed. |
| [postHandle](post-handle.md) | [JVM]<br>open fun [postHandle](post-handle.md)(request: HttpServletRequest, response: HttpServletResponse, handler: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html), modelAndView: ModelAndView)<br>Method executed after the handler processing but before rendering the view. |
| [preHandle](pre-handle.md) | [JVM]<br>open fun [preHandle](pre-handle.md)(request: HttpServletRequest, response: HttpServletResponse, handler: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Method executed before the handler processing. |
