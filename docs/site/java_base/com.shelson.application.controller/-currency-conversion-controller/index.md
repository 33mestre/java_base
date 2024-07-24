//[java_base](../../../index.md)/[com.shelson.application.controller](../index.md)/[CurrencyConversionController](index.md)

# CurrencyConversionController

[JVM]\
@RestController

@RequestMapping(value = &quot;/api/v1/conversions&quot;)

open class [CurrencyConversionController](index.md)

Controller responsible for handling currency conversion requests. This controller provides an endpoint to convert a given amount from a source currency to a target currency. It utilizes Apache Camel routes for fetching the exchange rates and performing the conversion. Validations performed by this class: 

- Ensures that the source and target currency codes are valid.
- Ensures that the amount to be converted is greater than zero.
- Checks for any unexpected parameters in the request and throws a BusinessException if found.

## Constructors

| | |
|---|---|
| [CurrencyConversionController](-currency-conversion-controller.md) | [JVM]<br>constructor() |

## Functions

| Name | Summary |
|---|---|
| [convertCurrency](convert-currency.md) | [JVM]<br>@GetMapping(value = &quot;/convert&quot;)<br>open fun [convertCurrency](convert-currency.md)(@RequestParamsource: [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html), @RequestParamtarget: [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html), @RequestParamamount: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), request: HttpServletRequest): [CurrencyConversionDTO](../../com.shelson.application.dto/-currency-conversion-d-t-o/index.md)<br>Converts the source currency to the target currency. |
