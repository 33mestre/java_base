//[java_base](../../../index.md)/[com.shelson.application.service](../index.md)/[CurrencyConversionService](index.md)

# CurrencyConversionService

@Service

open class [CurrencyConversionService](index.md)

Camel route for handling currency conversion requests. This route takes the source currency, target currency, and amount as headers, processes the conversion using the [CurrencyConversionService](index.md), and returns the conversion details.

#### Since

2024-07-24

#### Author

Shelson Ferrari

#### See also

| |
|---|
| [CurrencyConversionDTO](../../com.shelson.application.dto/-currency-conversion-d-t-o/index.md) |
| [CurrencyConversionService](index.md) |
| [ExchangeRateProcessor](../../com.shelson.application.processors/-exchange-rate-processor/index.md) |
| ProducerTemplate |
| [Currency](../../com.shelson.domain.model/-currency/index.md) |

## Constructors

| | |
|---|---|
| [CurrencyConversionService](-currency-conversion-service.md) | [JVM]<br>constructor() |

## Functions

| Name | Summary |
|---|---|
| [convertCurrency](convert-currency.md) | [JVM]<br>open fun [convertCurrency](convert-currency.md)(sourceCurrency: [Currency](../../com.shelson.domain.model/-currency/index.md), targetCurrency: [Currency](../../com.shelson.domain.model/-currency/index.md), amount: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)): [CurrencyConversionDTO](../../com.shelson.application.dto/-currency-conversion-d-t-o/index.md)<br>Converts the given amount from the source currency to the target currency. |
