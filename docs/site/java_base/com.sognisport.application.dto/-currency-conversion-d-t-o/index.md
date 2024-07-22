//[java_base](../../../index.md)/[com.sognisport.application.dto](../index.md)/[CurrencyConversionDTO](index.md)

# CurrencyConversionDTO

[JVM]\
open class [CurrencyConversionDTO](index.md)

DTO that represents details about the currency conversion.

## Constructors

| | |
|---|---|
| [CurrencyConversionDTO](-currency-conversion-d-t-o.md) | [JVM]<br>constructor()constructor(sourceCurrency: [Currency](../../com.sognisport.domain.model/-currency/index.md), targetCurrency: [Currency](../../com.sognisport.domain.model/-currency/index.md), conversionRate: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), queryDate: [LocalDateTime](https://docs.oracle.com/javase/8/docs/api/java/time/LocalDateTime.html), amount: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), convertedAmount: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [amount](amount.md) | [JVM]<br>@Positive(message = &quot;Amount must be greater than zero&quot;)<br>open var [amount](amount.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [conversionRate](conversion-rate.md) | [JVM]<br>@Positive(message = &quot;Conversion rate must be positive&quot;)<br>open var [conversionRate](conversion-rate.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [convertedAmount](converted-amount.md) | [JVM]<br>@Positive(message = &quot;Converted amount must be greater than zero&quot;)<br>open var [convertedAmount](converted-amount.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [queryDate](query-date.md) | [JVM]<br>@PastOrPresent(message = &quot;Query date cannot be in the future&quot;)<br>open var [queryDate](query-date.md): @PastOrPresent(message = &quot;Query date cannot be in the future&quot;)[LocalDateTime](https://docs.oracle.com/javase/8/docs/api/java/time/LocalDateTime.html) |
| [sourceCurrency](source-currency.md) | [JVM]<br>@NotNull(message = &quot;Source currency cannot be null&quot;)<br>open var [sourceCurrency](source-currency.md): @NotNull(message = &quot;Source currency cannot be null&quot;)[Currency](../../com.sognisport.domain.model/-currency/index.md) |
| [targetCurrency](target-currency.md) | [JVM]<br>@NotNull(message = &quot;Target currency cannot be null&quot;)<br>open var [targetCurrency](target-currency.md): @NotNull(message = &quot;Target currency cannot be null&quot;)[Currency](../../com.sognisport.domain.model/-currency/index.md) |

## Functions

| Name | Summary |
|---|---|
| [equals](equals.md) | [JVM]<br>open fun [equals](equals.md)(o: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](hash-code.md) | [JVM]<br>open fun [hashCode](hash-code.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](to-string.md) | [JVM]<br>open fun [toString](to-string.md)(): [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html) |
