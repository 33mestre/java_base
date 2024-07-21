//[sognisport](../../../index.md)/[com.sognisport.application.controller](../index.md)/[SwaggerRedirectController](index.md)/[redirectToSwaggerUIWithSlash](redirect-to-swagger-u-i-with-slash.md)

# redirectToSwaggerUIWithSlash

[JVM]\

@GetMapping(value = &quot;/swagger-ui/&quot;)

open fun [redirectToSwaggerUIWithSlash](redirect-to-swagger-u-i-with-slash.md)(): [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html)

Redirects the &quot;/swagger-ui/&quot; route to &quot;/swagger-ui/index.html&quot;. This method captures requests to the URL &quot;/swagger-ui/&quot; and redirects to the Swagger UI.

#### Return

A string indicating the redirect URL to the Swagger UI.
