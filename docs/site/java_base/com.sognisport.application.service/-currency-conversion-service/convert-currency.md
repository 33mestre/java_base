//[java_base](../../../index.md)/[com.sognisport.application.service](../index.md)/[CurrencyConversionService](index.md)/[convertCurrency](convert-currency.md)

# convertCurrency

[JVM]\
open fun [convertCurrency](convert-currency.md)(sourceCurrency: [Currency](../../com.sognisport.domain.model/-currency/index.md), targetCurrency: [Currency](../../com.sognisport.domain.model/-currency/index.md), amount: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)): [CurrencyConversionDTO](../../com.sognisport.application.dto/-currency-conversion-d-t-o/index.md)

Converts an amount from a source currency to a target currency.

#### Return

A [CurrencyConversionDTO](../../com.sognisport.application.dto/-currency-conversion-d-t-o/index.md) containing the conversion details.

#### Parameters

JVM

| | |
|---|---|
| sourceCurrency | The source currency. |
| targetCurrency | The target currency. |
| amount | The amount to be converted. |

#### Throws

| | |
|---|---|
| [BusinessException](../../com.sognisport.infrastructure.exception/-business-exception/index.md) | if the source or target currency is null, or if the amount is less than or equal to zero. |
| [ResourceNotFoundException](../../com.sognisport.infrastructure.exception/-resource-not-found-exception/index.md) | if an error occurs when fetching exchange rates from the API, or if the target currency is invalid. |
