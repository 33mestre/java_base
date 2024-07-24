//[java_base](../../../index.md)/[com.shelson.application.service](../index.md)/[CurrencyConversionService](index.md)

# CurrencyConversionService

[JVM]\
@Service

open class [CurrencyConversionService](index.md)

Service class for handling currency conversion operations. This class uses a Camel route to fetch the latest exchange rates and performs conversions between different currencies.

## Constructors

| | |
|---|---|
| [CurrencyConversionService](-currency-conversion-service.md) | [JVM]<br>constructor() |

## Functions

| Name | Summary |
|---|---|
| [convertCurrency](convert-currency.md) | [JVM]<br>open fun [convertCurrency](convert-currency.md)(sourceCurrency: [Currency](../../com.shelson.domain.model/-currency/index.md), targetCurrency: [Currency](../../com.shelson.domain.model/-currency/index.md), amount: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)): [CurrencyConversionDTO](../../com.shelson.application.dto/-currency-conversion-d-t-o/index.md)<br>Converts the given amount from the source currency to the target currency. |
