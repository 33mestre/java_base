//[sognisport](../../../index.md)/[com.sognisport.domain.service](../index.md)/[CurrencyConversionDomainService](index.md)/[performConversion](perform-conversion.md)

# performConversion

[JVM]\
open fun [performConversion](perform-conversion.md)(sourceCurrency: [Currency](../../com.sognisport.domain.model/-currency/index.md), targetCurrency: [Currency](../../com.sognisport.domain.model/-currency/index.md), rate: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)): [CurrencyConversion](../../com.sognisport.domain.model/-currency-conversion/index.md)

Performs currency conversion based on the provided rate and records the transaction. This function creates a new [CurrencyConversion](../../com.sognisport.domain.model/-currency-conversion/index.md) object, sets its properties, including the query date and time, and saves the object to the repository.

#### Return

A [CurrencyConversion](../../com.sognisport.domain.model/-currency-conversion/index.md) object containing the conversion details.

#### Parameters

JVM

| | |
|---|---|
| sourceCurrency | The source currency for the conversion. |
| targetCurrency | The target currency for the conversion. |
| rate | The conversion rate to be applied. |

#### Throws

| | |
|---|---|
| [IllegalArgumentException](https://docs.oracle.com/javase/8/docs/api/java/lang/IllegalArgumentException.html) | if the conversion rate is less than or equal to zero. |
