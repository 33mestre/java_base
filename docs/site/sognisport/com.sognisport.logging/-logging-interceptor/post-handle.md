//[sognisport](../../../index.md)/[com.sognisport.logging](../index.md)/[LoggingInterceptor](index.md)/[postHandle](post-handle.md)

# postHandle

[JVM]\
open fun [postHandle](post-handle.md)(request: HttpServletRequest, response: HttpServletResponse, handler: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html), modelAndView: ModelAndView)

Method executed after the handler processing but before rendering the view. Can be used to add logs or modify the ModelAndView before rendering.

#### Parameters

JVM

| | |
|---|---|
| request | The HttpServletRequest object that contains the client's request. |
| response | The HttpServletResponse object that contains the server's response. |
| handler | The chosen handler to execute the request. |
| modelAndView | The ModelAndView object that contains the view and model data. |

#### Throws

| | |
|---|---|
| [Exception](https://docs.oracle.com/javase/8/docs/api/java/lang/Exception.html) | If an error occurs during post-handling. |
