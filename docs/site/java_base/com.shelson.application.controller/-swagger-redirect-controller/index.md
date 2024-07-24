//[java_base](../../../index.md)/[com.shelson.application.controller](../index.md)/[SwaggerRedirectController](index.md)

# SwaggerRedirectController

[JVM]\
@Controller

open class [SwaggerRedirectController](index.md)

Controller responsible for redirecting to the Swagger UI interface. This controller handles two different routes to redirect the user to the Swagger UI.

#### Since

2024-07-24

#### Author

Shelson Ferrari

## Constructors

| | |
|---|---|
| [SwaggerRedirectController](-swagger-redirect-controller.md) | [JVM]<br>constructor() |

## Functions

| Name | Summary |
|---|---|
| [redirectToSwaggerUIWithoutSlash](redirect-to-swagger-u-i-without-slash.md) | [JVM]<br>@GetMapping(value = &quot;/swagger-ui&quot;)<br>open fun [redirectToSwaggerUIWithoutSlash](redirect-to-swagger-u-i-without-slash.md)(): [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html)<br>Redirects the &quot;/swagger-ui&quot; route to &quot;/swagger-ui/index.html&quot;. |
| [redirectToSwaggerUIWithSlash](redirect-to-swagger-u-i-with-slash.md) | [JVM]<br>@GetMapping(value = &quot;/swagger-ui/&quot;)<br>open fun [redirectToSwaggerUIWithSlash](redirect-to-swagger-u-i-with-slash.md)(): [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html)<br>Redirects the &quot;/swagger-ui/&quot; route to &quot;/swagger-ui/index.html&quot;. |
