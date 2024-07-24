//[java_base](../../../index.md)/[com.shelson.application.processors](../index.md)/[ExchangeRateProcessor](index.md)

# ExchangeRateProcessor

@Component

open class [ExchangeRateProcessor](index.md) : Processor

Processor to handle exchange rates. This processor reads the exchange rate data from the message body, parses it, and sets the parsed rates back into the message body.

#### Since

2024-07-24

#### Author

Shelson Ferrari

#### See also

| |
|---|
| Processor |
| Exchange |
| ObjectMapper |
| TypeReference |
| Logger |
| LoggerFactory |
| [CurrencyConversionService](../../com.shelson.application.service/-currency-conversion-service/index.md) |
| [CurrencyConversionRoute](../../com.shelson.application.routes/-currency-conversion-route/index.md) |
| [CurrencyConversionDTO](../../com.shelson.application.dto/-currency-conversion-d-t-o/index.md) |
| [Currency](../../com.shelson.domain.model/-currency/index.md) |
| [CurrencyConversionRepository](../../com.shelson.domain.repository/-currency-conversion-repository/index.md) |
| [BusinessException](../../com.shelson.infrastructure.exception/-business-exception/index.md) |
| [ResourceNotFoundException](../../com.shelson.infrastructure.exception/-resource-not-found-exception/index.md) |

## Constructors

| | |
|---|---|
| [ExchangeRateProcessor](-exchange-rate-processor.md) | [JVM]<br>constructor() |

## Functions

| Name | Summary |
|---|---|
| [process](process.md) | [JVM]<br>open fun [process](process.md)(exchange: Exchange)<br>Processes the exchange to extract and set exchange rates. |
