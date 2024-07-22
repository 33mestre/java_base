//[java_base](../../../index.md)/[com.sognisport.infrastructure.config](../index.md)/[WebConfig](index.md)

# WebConfig

[JVM]\
@Configuration

open class [WebConfig](index.md) : WebMvcConfigurer

Web MVC configuration for the application. Includes configuration for CORS, interceptors, and filters.

## Constructors

| | |
|---|---|
| [WebConfig](-web-config.md) | [JVM]<br>constructor() |

## Functions

| Name | Summary |
|---|---|
| [addArgumentResolvers](index.md#35807762%2FFunctions%2F57259888) | [JVM]<br>open fun [addArgumentResolvers](index.md#35807762%2FFunctions%2F57259888)(resolvers: [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html)&lt;HandlerMethodArgumentResolver&gt;) |
| [addCorsMappings](add-cors-mappings.md) | [JVM]<br>open fun [addCorsMappings](add-cors-mappings.md)(registry: CorsRegistry)<br>Configures the CORS mappings. |
| [addFormatters](index.md#1367850811%2FFunctions%2F57259888) | [JVM]<br>open fun [addFormatters](index.md#1367850811%2FFunctions%2F57259888)(registry: FormatterRegistry) |
| [addInterceptors](add-interceptors.md) | [JVM]<br>open fun [addInterceptors](add-interceptors.md)(registry: InterceptorRegistry)<br>Adds the logging interceptor to the interceptor registry. |
| [addResourceHandlers](add-resource-handlers.md) | [JVM]<br>open fun [addResourceHandlers](add-resource-handlers.md)(registry: ResourceHandlerRegistry) |
| [addReturnValueHandlers](index.md#-972151650%2FFunctions%2F57259888) | [JVM]<br>open fun [addReturnValueHandlers](index.md#-972151650%2FFunctions%2F57259888)(handlers: [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html)&lt;HandlerMethodReturnValueHandler&gt;) |
| [addViewControllers](index.md#-1862175630%2FFunctions%2F57259888) | [JVM]<br>open fun [addViewControllers](index.md#-1862175630%2FFunctions%2F57259888)(registry: ViewControllerRegistry) |
| [configureAsyncSupport](index.md#279135143%2FFunctions%2F57259888) | [JVM]<br>open fun [configureAsyncSupport](index.md#279135143%2FFunctions%2F57259888)(configurer: AsyncSupportConfigurer) |
| [configureContentNegotiation](index.md#927185469%2FFunctions%2F57259888) | [JVM]<br>open fun [configureContentNegotiation](index.md#927185469%2FFunctions%2F57259888)(configurer: ContentNegotiationConfigurer) |
| [configureDefaultServletHandling](index.md#-216514656%2FFunctions%2F57259888) | [JVM]<br>open fun [configureDefaultServletHandling](index.md#-216514656%2FFunctions%2F57259888)(configurer: DefaultServletHandlerConfigurer) |
| [configureHandlerExceptionResolvers](index.md#1355792817%2FFunctions%2F57259888) | [JVM]<br>open fun [configureHandlerExceptionResolvers](index.md#1355792817%2FFunctions%2F57259888)(resolvers: [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html)&lt;HandlerExceptionResolver&gt;) |
| [configureMessageConverters](index.md#-1351779689%2FFunctions%2F57259888) | [JVM]<br>open fun [configureMessageConverters](index.md#-1351779689%2FFunctions%2F57259888)(converters: [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html)&lt;HttpMessageConverter&lt;out [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)&gt;&gt;) |
| [configurePathMatch](index.md#808521551%2FFunctions%2F57259888) | [JVM]<br>open fun [configurePathMatch](index.md#808521551%2FFunctions%2F57259888)(configurer: PathMatchConfigurer) |
| [configureViewResolvers](index.md#-2110810761%2FFunctions%2F57259888) | [JVM]<br>open fun [configureViewResolvers](index.md#-2110810761%2FFunctions%2F57259888)(registry: ViewResolverRegistry) |
| [extendHandlerExceptionResolvers](index.md#-616794493%2FFunctions%2F57259888) | [JVM]<br>open fun [extendHandlerExceptionResolvers](index.md#-616794493%2FFunctions%2F57259888)(resolvers: [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html)&lt;HandlerExceptionResolver&gt;) |
| [extendMessageConverters](index.md#-479283991%2FFunctions%2F57259888) | [JVM]<br>open fun [extendMessageConverters](index.md#-479283991%2FFunctions%2F57259888)(converters: [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html)&lt;HttpMessageConverter&lt;out [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)&gt;&gt;) |
| [getMessageCodesResolver](index.md#-1737501503%2FFunctions%2F57259888) | [JVM]<br>open fun [getMessageCodesResolver](index.md#-1737501503%2FFunctions%2F57259888)(): MessageCodesResolver |
| [getValidator](index.md#1116410210%2FFunctions%2F57259888) | [JVM]<br>open fun [getValidator](index.md#1116410210%2FFunctions%2F57259888)(): Validator |
