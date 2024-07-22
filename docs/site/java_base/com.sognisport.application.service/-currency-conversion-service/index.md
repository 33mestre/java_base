//[java_base](../../../index.md)/[com.sognisport.application.service](../index.md)/[CurrencyConversionService](index.md)

# CurrencyConversionService

[JVM]\
@Service

open class [CurrencyConversionService](index.md)

Service responsible for currency conversion operations.

## Constructors

| | |
|---|---|
| [CurrencyConversionService](-currency-conversion-service.md) | [JVM]<br>constructor() |

## Types

| Name | Summary |
|---|---|
| [ApiResponse](-api-response/index.md) | [JVM]<br>open class [ApiResponse](-api-response/index.md)<br>Inner class representing the exchange rates API response. |

## Functions

| Name | Summary |
|---|---|
| [convertCurrency](convert-currency.md) | [JVM]<br>open fun [convertCurrency](convert-currency.md)(sourceCurrency: [Currency](../../com.sognisport.domain.model/-currency/index.md), targetCurrency: [Currency](../../com.sognisport.domain.model/-currency/index.md), amount: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)): [CurrencyConversionDTO](../../com.sognisport.application.dto/-currency-conversion-d-t-o/index.md)<br>Converts an amount from a source currency to a target currency. |
