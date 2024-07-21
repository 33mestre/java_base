//[sognisport](../../../index.md)/[com.sognisport.application.controller](../index.md)/[CurrencyConversionController](index.md)/[convertCurrency](convert-currency.md)

# convertCurrency

[JVM]\

@GetMapping(value = &quot;/convert&quot;)

open fun [convertCurrency](convert-currency.md)(@RequestParamsource: [Currency](../../com.sognisport.domain.model/-currency/index.md), @RequestParamtarget: [Currency](../../com.sognisport.domain.model/-currency/index.md), @RequestParamamount: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)): [CurrencyConversionDTO](../../com.sognisport.application.dto/-currency-conversion-d-t-o/index.md)

Converts the source currency to the target currency.

#### Return

A [CurrencyConversionDTO](../../com.sognisport.application.dto/-currency-conversion-d-t-o/index.md) containing the conversion details.

#### Parameters

JVM

| | |
|---|---|
| source | The source currency. Example: USD. |
| target | The target currency. Example: BRL. |
| amount | The amount to be converted. Example: 100.0. |

#### Throws

| |
|---|
| [BusinessException](../../com.sognisport.infrastructure.exception/-business-exception/index.md) |
