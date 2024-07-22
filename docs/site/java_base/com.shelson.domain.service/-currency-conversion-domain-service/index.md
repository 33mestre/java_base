//[java_base](../../../index.md)/[com.shelson.domain.service](../index.md)/[CurrencyConversionDomainService](index.md)

# CurrencyConversionDomainService

[JVM]\
@Service

open class [CurrencyConversionDomainService](index.md)

Domain service to perform currency conversion operations. This service handles the business logic related to currency conversion and interaction with the conversion repository.

## Constructors

| | |
|---|---|
| [CurrencyConversionDomainService](-currency-conversion-domain-service.md) | [JVM]<br>constructor(conversionRepository: [CurrencyConversionRepository](../../com.shelson.domain.repository/-currency-conversion-repository/index.md))<br>Constructor that injects the currency conversion repository. |

## Functions

| Name | Summary |
|---|---|
| [performConversion](perform-conversion.md) | [JVM]<br>open fun [performConversion](perform-conversion.md)(sourceCurrency: [Currency](../../com.shelson.domain.model/-currency/index.md), targetCurrency: [Currency](../../com.shelson.domain.model/-currency/index.md), rate: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)): [CurrencyConversion](../../com.shelson.domain.model/-currency-conversion/index.md)<br>Performs currency conversion based on the provided rate and records the transaction. |
