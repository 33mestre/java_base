//[java_base](../../../index.md)/[com.shelson.config](../index.md)/[CamelConfig](index.md)

# CamelConfig

[JVM]\
@Configuration

open class [CamelConfig](index.md)

Configuration class for Apache Camel routes. 

 This class sets up the Camel routes used for currency conversion and fetching exchange rates. It defines how the data flows through the routes and how the exchange rates are fetched from the API.

## Constructors

| | |
|---|---|
| [CamelConfig](-camel-config.md) | [JVM]<br>constructor() |

## Functions

| Name | Summary |
|---|---|
| [configureCamelContext](configure-camel-context.md) | [JVM]<br>@Autowired<br>open fun [configureCamelContext](configure-camel-context.md)(camelContext: CamelContext)<br>Configures the Camel routes. |
