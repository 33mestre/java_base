//[sognisport](../../../index.md)/[com.sognisport.application.controller](../index.md)/[CurrencyConversionController](index.md)

# CurrencyConversionController

[JVM]\
@RestController

@RequestMapping(value = &quot;/api/v1/conversions&quot;)

open class [CurrencyConversionController](index.md)

Controller responsible for currency conversion operations. Provides endpoints for converting values between different currencies.

## Constructors

| | |
|---|---|
| [CurrencyConversionController](-currency-conversion-controller.md) | [JVM]<br>constructor() |

## Functions

| Name | Summary |
|---|---|
| [convertCurrency](convert-currency.md) | [JVM]<br>@GetMapping(value = &quot;/convert&quot;)<br>open fun [convertCurrency](convert-currency.md)(@RequestParamsource: [Currency](../../com.sognisport.domain.model/-currency/index.md), @RequestParamtarget: [Currency](../../com.sognisport.domain.model/-currency/index.md), @RequestParamamount: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)): [CurrencyConversionDTO](../../com.sognisport.application.dto/-currency-conversion-d-t-o/index.md)<br>Converts the source currency to the target currency. |
