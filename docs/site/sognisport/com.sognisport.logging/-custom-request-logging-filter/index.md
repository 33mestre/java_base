//[sognisport](../../../index.md)/[com.sognisport.logging](../index.md)/[CustomRequestLoggingFilter](index.md)

# CustomRequestLoggingFilter

[JVM]\
open class [CustomRequestLoggingFilter](index.md) : HttpFilter

Custom filter to log details of HTTP requests.

## Constructors

| | |
|---|---|
| [CustomRequestLoggingFilter](-custom-request-logging-filter.md) | [JVM]<br>constructor() |

## Properties

| Name | Summary |
|---|---|
| [filterConfig](index.md#1255613917%2FProperties%2F1511606574) | [JVM]<br>open val [filterConfig](index.md#1255613917%2FProperties%2F1511606574): FilterConfig |

## Functions

| Name | Summary |
|---|---|
| [destroy](destroy.md) | [JVM]<br>open fun [destroy](destroy.md)()<br>Destroys the filter, performing necessary cleanup. |
| [doFilter](index.md#-1767447681%2FFunctions%2F1511606574) | [JVM]<br>open fun [doFilter](index.md#-1767447681%2FFunctions%2F1511606574)(request: ServletRequest, response: ServletResponse, chain: FilterChain) |
| [getFilterName](index.md#-1016510134%2FFunctions%2F1511606574) | [JVM]<br>open fun [getFilterName](index.md#-1016510134%2FFunctions%2F1511606574)(): [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html) |
| [getInitParameter](index.md#-763123953%2FFunctions%2F1511606574) | [JVM]<br>open fun [getInitParameter](index.md#-763123953%2FFunctions%2F1511606574)(name: [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html)): [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html) |
| [getInitParameterNames](index.md#1300092574%2FFunctions%2F1511606574) | [JVM]<br>open fun [getInitParameterNames](index.md#1300092574%2FFunctions%2F1511606574)(): [Enumeration](https://docs.oracle.com/javase/8/docs/api/java/util/Enumeration.html)&lt;[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html)&gt; |
| [getServletContext](index.md#-893877853%2FFunctions%2F1511606574) | [JVM]<br>open fun [getServletContext](index.md#-893877853%2FFunctions%2F1511606574)(): ServletContext |
| [init](init.md) | [JVM]<br>open fun [init](init.md)(filterConfig: FilterConfig)<br>Initializes the filter. |
