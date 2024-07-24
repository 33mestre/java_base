//[java_base](../../../index.md)/[com.shelson.config](../index.md)/[CamelConfig](index.md)

# CamelConfig

@Configuration

open class [CamelConfig](index.md)

Configuration class for Apache Camel routes. 

 This class sets up the Camel routes used for currency conversion and fetching exchange rates. It defines how the data flows through the routes and how the exchange rates are fetched from the API.

#### Since

2024-07-24

#### Author

Shelson Ferrari

#### See also

| |
|---|
| [CurrencyConversionService](../../com.shelson.application.service/-currency-conversion-service/index.md) |
| [CurrencyConversionServiceApiResponse](../../com.shelson.application.service/-currency-conversion-service-api-response/index.md) |
| CamelContext |
| RouteBuilder |
| Configuration |

## Constructors

| | |
|---|---|
| [CamelConfig](-camel-config.md) | [JVM]<br>constructor() |

## Functions

| Name | Summary |
|---|---|
| [configureCamelContext](configure-camel-context.md) | [JVM]<br>@Autowired<br>open fun [configureCamelContext](configure-camel-context.md)(camelContext: CamelContext)<br>Configures the Camel routes. |
