//[java_base](../../../index.md)/[com.shelson.domain.model](../index.md)/[CurrencyConversion](index.md)

# CurrencyConversion

[JVM]\
@Entity

open class [CurrencyConversion](index.md)

Entity that represents a currency conversion.

## Constructors

| | |
|---|---|
| [CurrencyConversion](-currency-conversion.md) | [JVM]<br>constructor()<br>Default constructor.<br>constructor(sourceCurrency: [Currency](../-currency/index.md), targetCurrency: [Currency](../-currency/index.md), conversionRate: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), queryDate: [LocalDateTime](https://docs.oracle.com/javase/8/docs/api/java/time/LocalDateTime.html))<br>Constructor with parameters. |

## Properties

| Name | Summary |
|---|---|
| [conversionRate](conversion-rate.md) | [JVM]<br>open var [conversionRate](conversion-rate.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [id](id.md) | [JVM]<br>open var [id](id.md): [Long](https://docs.oracle.com/javase/8/docs/api/java/lang/Long.html) |
| [queryDate](query-date.md) | [JVM]<br>open var [queryDate](query-date.md): [LocalDateTime](https://docs.oracle.com/javase/8/docs/api/java/time/LocalDateTime.html) |
| [sourceCurrency](source-currency.md) | [JVM]<br>open var [sourceCurrency](source-currency.md): [Currency](../-currency/index.md) |
| [targetCurrency](target-currency.md) | [JVM]<br>open var [targetCurrency](target-currency.md): [Currency](../-currency/index.md) |

## Functions

| Name | Summary |
|---|---|
| [equals](equals.md) | [JVM]<br>open fun [equals](equals.md)(o: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Checks if two instances of CurrencyConversion are equal. |
| [hashCode](hash-code.md) | [JVM]<br>open fun [hashCode](hash-code.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Computes the hash code of the instance. |
| [toString](to-string.md) | [JVM]<br>open fun [toString](to-string.md)(): [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html)<br>Returns a string representation of the currency conversion. |
