//[java_base](../../../index.md)/[com.shelson.application.service](../index.md)/[CurrencyConversionServiceApiResponse](index.md)

# CurrencyConversionServiceApiResponse

open class [CurrencyConversionServiceApiResponse](index.md)

Represents the response from the currency conversion API. This class is used to map the JSON response from the currency conversion API to a Java object. The expected JSON should contain exchange rates.

#### Since

2024-07-24

#### Author

Shelson Ferrari

#### See also

| |
|---|
| [CurrencyConversionService](../-currency-conversion-service/index.md) |
| ProducerTemplate |
| JsonIgnoreProperties |

## Constructors

| | |
|---|---|
| [CurrencyConversionServiceApiResponse](-currency-conversion-service-api-response.md) | [JVM]<br>constructor() |

## Properties

| Name | Summary |
|---|---|
| [rates](rates.md) | [JVM]<br>open var [rates](rates.md): [Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html)&lt;[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html), [Double](https://docs.oracle.com/javase/8/docs/api/java/lang/Double.html)&gt;<br>Map containing exchange rates for different currencies. |
