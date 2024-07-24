//[java_base](../../../index.md)/[com.shelson.application.service](../index.md)/[CurrencyConversionService](index.md)/[convertCurrency](convert-currency.md)

# convertCurrency

[JVM]\
open fun [convertCurrency](convert-currency.md)(sourceCurrency: [Currency](../../com.shelson.domain.model/-currency/index.md), targetCurrency: [Currency](../../com.shelson.domain.model/-currency/index.md), amount: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)): [CurrencyConversionDTO](../../com.shelson.application.dto/-currency-conversion-d-t-o/index.md)

Converts the given amount from the source currency to the target currency.

#### Return

A [CurrencyConversionDTO](../../com.shelson.application.dto/-currency-conversion-d-t-o/index.md) containing the conversion details.

#### Parameters

JVM

| | |
|---|---|
| sourceCurrency | The source [Currency](../../com.shelson.domain.model/-currency/index.md) to convert from. |
| targetCurrency | The target [Currency](../../com.shelson.domain.model/-currency/index.md) to convert to. |
| amount | The amount to be converted. |

#### Throws

| | |
|---|---|
| [BusinessException](../../com.shelson.infrastructure.exception/-business-exception/index.md) | if sourceCurrency or targetCurrency is null, or if the amount is less than or equal to zero. |
| [ResourceNotFoundException](../../com.shelson.infrastructure.exception/-resource-not-found-exception/index.md) | if an error occurs while fetching the exchange rates. |
