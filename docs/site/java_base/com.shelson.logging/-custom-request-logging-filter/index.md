//[java_base](../../../index.md)/[com.shelson.logging](../index.md)/[CustomRequestLoggingFilter](index.md)

# CustomRequestLoggingFilter

[JVM]\
open class [CustomRequestLoggingFilter](index.md) : HttpFilter

Custom filter to log details of HTTP requests. 

 This filter logs various details about each HTTP request and response, including the request method, URI, protocol, response status, timestamp, user agent, and the duration of the request processing. It is useful for monitoring and debugging purposes.

## Constructors

| | |
|---|---|
| [CustomRequestLoggingFilter](-custom-request-logging-filter.md) | [JVM]<br>constructor() |

## Functions

| Name | Summary |
|---|---|
| [destroy](destroy.md) | [JVM]<br>open fun [destroy](destroy.md)()<br>Destroys the filter, performing necessary cleanup. |
| [doFilter](index.md#-1767447681%2FFunctions%2F57259888) | [JVM]<br>open fun [doFilter](index.md#-1767447681%2FFunctions%2F57259888)(req: ServletRequest, res: ServletResponse, chain: FilterChain) |
| [getFilterConfig](index.md#601366131%2FFunctions%2F57259888) | [JVM]<br>open fun [getFilterConfig](index.md#601366131%2FFunctions%2F57259888)(): FilterConfig |
| [getFilterName](index.md#-1016510134%2FFunctions%2F57259888) | [JVM]<br>open fun [getFilterName](index.md#-1016510134%2FFunctions%2F57259888)(): [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html) |
| [getInitParameter](index.md#-763123953%2FFunctions%2F57259888) | [JVM]<br>open fun [getInitParameter](index.md#-763123953%2FFunctions%2F57259888)(name: [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html)): [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html) |
| [getInitParameterNames](index.md#1300092574%2FFunctions%2F57259888) | [JVM]<br>open fun [getInitParameterNames](index.md#1300092574%2FFunctions%2F57259888)(): [Enumeration](https://docs.oracle.com/javase/8/docs/api/java/util/Enumeration.html)&lt;[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html)&gt; |
| [getServletContext](index.md#-893877853%2FFunctions%2F57259888) | [JVM]<br>open fun [getServletContext](index.md#-893877853%2FFunctions%2F57259888)(): ServletContext |
| [init](init.md) | [JVM]<br>open fun [init](init.md)(filterConfig: FilterConfig)<br>Initializes the filter. |
