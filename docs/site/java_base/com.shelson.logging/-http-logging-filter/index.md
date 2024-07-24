//[java_base](../../../index.md)/[com.shelson.logging](../index.md)/[HttpLoggingFilter](index.md)

# HttpLoggingFilter

open class [HttpLoggingFilter](index.md) : HttpFilter

HTTP filter for logging details of HTTP requests and responses. This filter intercepts all HTTP requests, logging information such as the HTTP method, destination URI, protocol, response status, user agent, and processing time.

#### Since

2024-07-24

#### Author

Shelson Ferrari

#### See also

| |
|---|
| FilterChain |
| FilterConfig |
| HttpFilter |
| HttpServletRequest |
| HttpServletResponse |
| Logger |
| LoggerFactory |

## Constructors

| | |
|---|---|
| [HttpLoggingFilter](-http-logging-filter.md) | [JVM]<br>constructor() |

## Functions

| Name | Summary |
|---|---|
| [destroy](destroy.md) | [JVM]<br>open fun [destroy](destroy.md)()<br>Destroys the filter, performing necessary cleanup. |
| [doFilter](../-custom-request-logging-filter/index.md#-1767447681%2FFunctions%2F57259888) | [JVM]<br>open fun [doFilter](../-custom-request-logging-filter/index.md#-1767447681%2FFunctions%2F57259888)(req: ServletRequest, res: ServletResponse, chain: FilterChain) |
| [getFilterConfig](../-custom-request-logging-filter/index.md#601366131%2FFunctions%2F57259888) | [JVM]<br>open fun [getFilterConfig](../-custom-request-logging-filter/index.md#601366131%2FFunctions%2F57259888)(): FilterConfig |
| [getFilterName](../-custom-request-logging-filter/index.md#-1016510134%2FFunctions%2F57259888) | [JVM]<br>open fun [getFilterName](../-custom-request-logging-filter/index.md#-1016510134%2FFunctions%2F57259888)(): [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html) |
| [getInitParameter](../-custom-request-logging-filter/index.md#-763123953%2FFunctions%2F57259888) | [JVM]<br>open fun [getInitParameter](../-custom-request-logging-filter/index.md#-763123953%2FFunctions%2F57259888)(name: [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html)): [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html) |
| [getInitParameterNames](../-custom-request-logging-filter/index.md#1300092574%2FFunctions%2F57259888) | [JVM]<br>open fun [getInitParameterNames](../-custom-request-logging-filter/index.md#1300092574%2FFunctions%2F57259888)(): [Enumeration](https://docs.oracle.com/javase/8/docs/api/java/util/Enumeration.html)&lt;[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html)&gt; |
| [getServletContext](../-custom-request-logging-filter/index.md#-893877853%2FFunctions%2F57259888) | [JVM]<br>open fun [getServletContext](../-custom-request-logging-filter/index.md#-893877853%2FFunctions%2F57259888)(): ServletContext |
| [init](init.md) | [JVM]<br>open fun [init](init.md)(filterConfig: FilterConfig)<br>Initializes the filter. |
