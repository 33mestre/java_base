//[java_base](../../../index.md)/[com.shelson.application.controller](../index.md)/[CurrencyConversionController](index.md)/[convertCurrency](convert-currency.md)

# convertCurrency

[JVM]\

@GetMapping(value = &quot;/convert&quot;)

open fun [convertCurrency](convert-currency.md)(@RequestParamsource: [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html), @RequestParamtarget: [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html), @RequestParamamount: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), request: HttpServletRequest): [CurrencyConversionDTO](../../com.shelson.application.dto/-currency-conversion-d-t-o/index.md)

Converts the source currency to the target currency. Validates the following conditions: 

- Source currency code is valid.
- Target currency code is valid.
- Amount to be converted is greater than zero.
- No unexpected parameters are present in the request.

#### Return

The details of the currency conversion.

#### Parameters

JVM

| | |
|---|---|
| source | The source currency code. |
| target | The target currency code. |
| amount | The amount to be converted. |
| request | The HttpServletRequest object to check for unexpected parameters. |

#### Throws

| | |
|---|---|
| [BusinessException](../../com.shelson.infrastructure.exception/-business-exception/index.md) | if the source or target currency is invalid or if there are unexpected parameters. |
