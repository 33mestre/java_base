//[java_base](../../index.md)/[com.shelson.application.routes](index.md)

# Package-level declarations

[JVM]\
This package contains Camel routes for the currency conversion application. The routes handle incoming requests for currency conversion, process them using the CurrencyConversionService, and return the conversion results. 

 The main route in this package is [com.shelson.application.routes.CurrencyConversionRoute](-currency-conversion-route/index.md), which processes the conversion by extracting headers from the request, validating them, and invoking the service to perform the conversion.

## Types

| Name | Summary |
|---|---|
| [CurrencyConversionRoute](-currency-conversion-route/index.md) | [JVM]<br>@Component<br>open class [CurrencyConversionRoute](-currency-conversion-route/index.md) : RouteBuilder<br>Camel route for handling currency conversion requests. |
