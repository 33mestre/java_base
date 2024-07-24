//[java_base](../../../index.md)/[com.shelson.application.processors](../index.md)/[ExchangeRateProcessor](index.md)

# ExchangeRateProcessor

[JVM]\
@Component

open class [ExchangeRateProcessor](index.md) : Processor

Processor to handle exchange rates. This processor reads the exchange rate data from the message body, parses it, and sets the parsed rates back into the message body.

## Constructors

| | |
|---|---|
| [ExchangeRateProcessor](-exchange-rate-processor.md) | [JVM]<br>constructor() |

## Functions

| Name | Summary |
|---|---|
| [process](process.md) | [JVM]<br>open fun [process](process.md)(exchange: Exchange)<br>Processes the exchange to extract and set exchange rates. |
