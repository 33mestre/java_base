//[java_base](../../../index.md)/[com.shelson.application.routes](../index.md)/[CurrencyConversionRoute](index.md)

# CurrencyConversionRoute

@Component

open class [CurrencyConversionRoute](index.md) : RouteBuilder

Camel route for handling currency conversion requests. This route takes the source currency, target currency, and amount as headers, processes the conversion using the [CurrencyConversionService](../../com.shelson.application.service/-currency-conversion-service/index.md), and returns the conversion details.

#### Since

2024-07-24

#### Author

Shelson Ferrari

#### See also

| |
|---|
| [CurrencyConversionDTO](../../com.shelson.application.dto/-currency-conversion-d-t-o/index.md) |
| [CurrencyConversionService](../../com.shelson.application.service/-currency-conversion-service/index.md) |
| [ExchangeRateProcessor](../../com.shelson.application.processors/-exchange-rate-processor/index.md) |
| ProducerTemplate |
| [Currency](../../com.shelson.domain.model/-currency/index.md) |

## Constructors

| | |
|---|---|
| [CurrencyConversionRoute](-currency-conversion-route.md) | [JVM]<br>constructor() |

## Properties

| Name | Summary |
|---|---|
| [camelContext](index.md#-2086042101%2FProperties%2F57259888) | [JVM]<br>open var [camelContext](index.md#-2086042101%2FProperties%2F57259888): CamelContext |
| [errorHandlerFactory](index.md#914481166%2FProperties%2F57259888) | [JVM]<br>open var [errorHandlerFactory](index.md#914481166%2FProperties%2F57259888): ErrorHandlerFactory |
| [HIGHEST](index.md#973210506%2FProperties%2F57259888) | [JVM]<br>val [HIGHEST](index.md#973210506%2FProperties%2F57259888): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = -2147483648 |
| [LOWEST](index.md#-1294669734%2FProperties%2F57259888) | [JVM]<br>val [LOWEST](index.md#-1294669734%2FProperties%2F57259888): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 2147482647 |
| [resource](index.md#559403412%2FProperties%2F57259888) | [JVM]<br>open var [resource](index.md#559403412%2FProperties%2F57259888): Resource |
| [restCollection](index.md#-1432259376%2FProperties%2F57259888) | [JVM]<br>open var [restCollection](index.md#-1432259376%2FProperties%2F57259888): RestsDefinition |
| [restConfiguration](index.md#-523105290%2FFunctions%2F57259888) | [JVM]<br>open val [restConfiguration](index.md#-523105290%2FFunctions%2F57259888): RestConfigurationDefinition |
| [routeCollection](index.md#1197605393%2FProperties%2F57259888) | [JVM]<br>open var [routeCollection](index.md#1197605393%2FProperties%2F57259888): RoutesDefinition |
| [routeTemplateCollection](index.md#1774930231%2FProperties%2F57259888) | [JVM]<br>open var [routeTemplateCollection](index.md#1774930231%2FProperties%2F57259888): RouteTemplatesDefinition |
| [templatedRouteCollection](index.md#1710808389%2FProperties%2F57259888) | [JVM]<br>open var [templatedRouteCollection](index.md#1710808389%2FProperties%2F57259888): TemplatedRoutesDefinition |

## Functions

| Name | Summary |
|---|---|
| [addLifecycleInterceptor](index.md#1177361426%2FFunctions%2F57259888) | [JVM]<br>open fun [addLifecycleInterceptor](index.md#1177361426%2FFunctions%2F57259888)(interceptor: RouteBuilderLifecycleStrategy) |
| [addRoutes](index.md#-1407676681%2FFunctions%2F57259888) | [JVM]<br>open fun [addRoutes](index.md#-1407676681%2FFunctions%2F57259888)(context: CamelContext, rbc: LambdaRouteBuilder) |
| [addRoutesToCamelContext](index.md#-477622462%2FFunctions%2F57259888) | [JVM]<br>open fun [addRoutesToCamelContext](index.md#-477622462%2FFunctions%2F57259888)(context: CamelContext) |
| [addTemplatedRoutesToCamelContext](index.md#409503872%2FFunctions%2F57259888) | [JVM]<br>open fun [addTemplatedRoutesToCamelContext](index.md#409503872%2FFunctions%2F57259888)(context: CamelContext) |
| [bindToRegistry](index.md#1046736068%2FFunctions%2F57259888) | [JVM]<br>open fun [bindToRegistry](index.md#1046736068%2FFunctions%2F57259888)(id: [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html), bean: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)) |
| [body](index.md#2000519074%2FFunctions%2F57259888) | [JVM]<br>open fun [body](index.md#2000519074%2FFunctions%2F57259888)(): ValueBuilder |
| [bodyAs](index.md#-10482226%2FFunctions%2F57259888) | [JVM]<br>open fun &lt;[T](index.md#-10482226%2FFunctions%2F57259888)&gt; [bodyAs](index.md#-10482226%2FFunctions%2F57259888)(type: [Class](https://docs.oracle.com/javase/8/docs/api/java/lang/Class.html)&lt;[T](index.md#-10482226%2FFunctions%2F57259888)&gt;): ValueBuilder |
| [configuration](index.md#2072164482%2FFunctions%2F57259888) | [JVM]<br>open fun [configuration](index.md#2072164482%2FFunctions%2F57259888)() |
| [configure](configure.md) | [JVM]<br>open fun [configure](configure.md)()<br>Configures the Camel route for currency conversion. |
| [configureRests](index.md#-1626191278%2FFunctions%2F57259888) | [JVM]<br>open fun [configureRests](index.md#-1626191278%2FFunctions%2F57259888)(context: CamelContext): RestsDefinition |
| [configureRoutes](index.md#540391667%2FFunctions%2F57259888) | [JVM]<br>open fun [configureRoutes](index.md#540391667%2FFunctions%2F57259888)(context: CamelContext): RoutesDefinition |
| [constant](index.md#-456412307%2FFunctions%2F57259888) | [JVM]<br>open fun [constant](index.md#-456412307%2FFunctions%2F57259888)(value: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)): ValueBuilder |
| [csimple](index.md#-182524708%2FFunctions%2F57259888) | [JVM]<br>open fun [csimple](index.md#-182524708%2FFunctions%2F57259888)(value: [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html)): ValueBuilder |
| [dataFormat](index.md#1619215233%2FFunctions%2F57259888) | [JVM]<br>open fun [dataFormat](index.md#1619215233%2FFunctions%2F57259888)(): DataFormatBuilderFactory |
| [datasonnet](index.md#1016575082%2FFunctions%2F57259888) | [JVM]<br>open fun [datasonnet](index.md#1016575082%2FFunctions%2F57259888)(value: [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html)): ValueBuilder |
| [deadLetterChannel](index.md#-1000256008%2FFunctions%2F57259888) | [JVM]<br>open fun [deadLetterChannel](index.md#-1000256008%2FFunctions%2F57259888)(deadLetterUri: [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html)): DeadLetterChannelBuilder |
| [defaultErrorHandler](index.md#76674547%2FFunctions%2F57259888) | [JVM]<br>open fun [defaultErrorHandler](index.md#76674547%2FFunctions%2F57259888)(): DefaultErrorHandlerBuilder |
| [endpoint](index.md#1090950026%2FFunctions%2F57259888) | [JVM]<br>open fun [endpoint](index.md#1090950026%2FFunctions%2F57259888)(uri: [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html)): Endpoint<br>open fun &lt;[T](index.md#-2134843277%2FFunctions%2F57259888) : Endpoint?&gt; [endpoint](index.md#-2134843277%2FFunctions%2F57259888)(uri: [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html), type: [Class](https://docs.oracle.com/javase/8/docs/api/java/lang/Class.html)&lt;[T](index.md#-2134843277%2FFunctions%2F57259888)&gt;): [T](index.md#-2134843277%2FFunctions%2F57259888) |
| [errorHandler](index.md#775322214%2FFunctions%2F57259888) | [JVM]<br>open fun [errorHandler](index.md#775322214%2FFunctions%2F57259888)(errorHandlerFactory: ErrorHandlerFactory) |
| [exceptionMessage](index.md#1892313804%2FFunctions%2F57259888) | [JVM]<br>open fun [exceptionMessage](index.md#1892313804%2FFunctions%2F57259888)(): ValueBuilder |
| [exchangeProperty](index.md#-868152217%2FFunctions%2F57259888) | [JVM]<br>open fun [exchangeProperty](index.md#-868152217%2FFunctions%2F57259888)(name: [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html)): ValueBuilder |
| [expression](index.md#-1291215920%2FFunctions%2F57259888) | [JVM]<br>open fun [expression](index.md#-1291215920%2FFunctions%2F57259888)(exp: Expression): ValueBuilder<br>open fun [expression](index.md#1632738570%2FFunctions%2F57259888)(): LanguageBuilderFactory |
| [from](index.md#1071389171%2FFunctions%2F57259888) | [JVM]<br>open fun [from](index.md#1071389171%2FFunctions%2F57259888)(uri: [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html)): RouteDefinition |
| [fromF](index.md#-1318016417%2FFunctions%2F57259888) | [JVM]<br>open fun [fromF](index.md#-1318016417%2FFunctions%2F57259888)(uri: [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html), args: [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)&gt;): RouteDefinition |
| [getContext](index.md#-357423509%2FFunctions%2F57259888) | [JVM]<br>open fun [getContext](index.md#-357423509%2FFunctions%2F57259888)(): CamelContext |
| [getOrder](index.md#-1682745526%2FFunctions%2F57259888) | [JVM]<br>open fun [getOrder](index.md#-1682745526%2FFunctions%2F57259888)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [hasErrorHandlerFactory](index.md#-2130955914%2FFunctions%2F57259888) | [JVM]<br>open fun [hasErrorHandlerFactory](index.md#-2130955914%2FFunctions%2F57259888)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [header](index.md#721455954%2FFunctions%2F57259888) | [JVM]<br>open fun [header](index.md#721455954%2FFunctions%2F57259888)(name: [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html)): ValueBuilder |
| [intercept](index.md#1355833270%2FFunctions%2F57259888) | [JVM]<br>open fun [intercept](index.md#1355833270%2FFunctions%2F57259888)(): InterceptDefinition |
| [interceptFrom](index.md#-1706285396%2FFunctions%2F57259888) | [JVM]<br>open fun [interceptFrom](index.md#-1706285396%2FFunctions%2F57259888)(): InterceptFromDefinition |
| [interceptSendToEndpoint](index.md#968595961%2FFunctions%2F57259888) | [JVM]<br>open fun [interceptSendToEndpoint](index.md#968595961%2FFunctions%2F57259888)(uri: [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html)): InterceptSendToEndpointDefinition |
| [joor](index.md#911869975%2FFunctions%2F57259888) | [JVM]<br>open fun [joor](index.md#911869975%2FFunctions%2F57259888)(value: [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html)): ValueBuilder |
| [jsonpath](index.md#-2096899310%2FFunctions%2F57259888) | [JVM]<br>open fun [jsonpath](index.md#-2096899310%2FFunctions%2F57259888)(value: [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html)): ValueBuilder |
| [jtaTransactionErrorHandler](index.md#1262750747%2FFunctions%2F57259888) | [JVM]<br>open fun [jtaTransactionErrorHandler](index.md#1262750747%2FFunctions%2F57259888)(): JtaTransactionErrorHandlerBuilder |
| [loadRoutesBuilder](index.md#1468717532%2FFunctions%2F57259888) | [JVM]<br>open fun [loadRoutesBuilder](index.md#1468717532%2FFunctions%2F57259888)(resource: Resource, consumer: ThrowingBiConsumer&lt;[Reader](https://docs.oracle.com/javase/8/docs/api/java/io/Reader.html), RouteBuilder, [Exception](https://docs.oracle.com/javase/8/docs/api/java/lang/Exception.html)&gt;): RouteBuilder |
| [method](index.md#1093260432%2FFunctions%2F57259888) | [JVM]<br>open fun [method](index.md#1093260432%2FFunctions%2F57259888)(beanOrBeanRef: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)): ValueBuilder |
| [noErrorHandler](index.md#1979043617%2FFunctions%2F57259888) | [JVM]<br>open fun [noErrorHandler](index.md#1979043617%2FFunctions%2F57259888)(): NoErrorHandlerBuilder |
| [onCompletion](index.md#1517605031%2FFunctions%2F57259888) | [JVM]<br>open fun [onCompletion](index.md#1517605031%2FFunctions%2F57259888)(): OnCompletionDefinition |
| [onException](index.md#-999242506%2FFunctions%2F57259888) | [JVM]<br>open fun [onException](index.md#-999242506%2FFunctions%2F57259888)(exception: [Class](https://docs.oracle.com/javase/8/docs/api/java/lang/Class.html)&lt;out [Throwable](https://docs.oracle.com/javase/8/docs/api/java/lang/Throwable.html)&gt;): OnExceptionDefinition |
| [property](index.md#-399563352%2FFunctions%2F57259888) | [JVM]<br>open fun [property](index.md#-399563352%2FFunctions%2F57259888)(key: [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html)): [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html) |
| [propertyInject](index.md#-501747770%2FFunctions%2F57259888) | [JVM]<br>open fun &lt;[T](index.md#-501747770%2FFunctions%2F57259888)&gt; [propertyInject](index.md#-501747770%2FFunctions%2F57259888)(key: [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html), type: [Class](https://docs.oracle.com/javase/8/docs/api/java/lang/Class.html)&lt;[T](index.md#-501747770%2FFunctions%2F57259888)&gt;): [T](index.md#-501747770%2FFunctions%2F57259888) |
| [regexReplaceAll](index.md#1711142490%2FFunctions%2F57259888) | [JVM]<br>open fun [regexReplaceAll](index.md#1711142490%2FFunctions%2F57259888)(content: Expression, regex: [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html), replacement: [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html)): ValueBuilder |
| [removeLifecycleInterceptor](index.md#1485851001%2FFunctions%2F57259888) | [JVM]<br>open fun [removeLifecycleInterceptor](index.md#1485851001%2FFunctions%2F57259888)(interceptor: RouteBuilderLifecycleStrategy) |
| [rest](index.md#-1411956978%2FFunctions%2F57259888) | [JVM]<br>open fun [rest](index.md#-1411956978%2FFunctions%2F57259888)(): RestDefinition |
| [restConfiguration](index.md#-523105290%2FFunctions%2F57259888) | [JVM]<br>open fun [restConfiguration](index.md#-523105290%2FFunctions%2F57259888)(): RestConfigurationDefinition |
| [routeTemplate](index.md#1347074096%2FFunctions%2F57259888) | [JVM]<br>open fun [routeTemplate](index.md#1347074096%2FFunctions%2F57259888)(id: [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html)): RouteTemplateDefinition |
| [simple](index.md#1684195789%2FFunctions%2F57259888) | [JVM]<br>open fun [simple](index.md#1684195789%2FFunctions%2F57259888)(value: [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html)): ValueBuilder |
| [simpleF](index.md#-1256307003%2FFunctions%2F57259888) | [JVM]<br>open fun [simpleF](index.md#-1256307003%2FFunctions%2F57259888)(format: [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html), values: [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)&gt;): ValueBuilder |
| [springTransactionErrorHandler](index.md#1326802019%2FFunctions%2F57259888) | [JVM]<br>open fun [springTransactionErrorHandler](index.md#1326802019%2FFunctions%2F57259888)(): SpringTransactionErrorHandlerBuilder |
| [systemProperty](index.md#833910683%2FFunctions%2F57259888) | [JVM]<br>open fun [systemProperty](index.md#833910683%2FFunctions%2F57259888)(name: [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html)): ValueBuilder |
| [templatedRoute](index.md#-1248818754%2FFunctions%2F57259888) | [JVM]<br>open fun [templatedRoute](index.md#-1248818754%2FFunctions%2F57259888)(routeTemplateId: [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html)): TemplatedRouteDefinition |
| [toString](index.md#1544915190%2FFunctions%2F57259888) | [JVM]<br>open fun [toString](index.md#1544915190%2FFunctions%2F57259888)(): [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html) |
| [transformer](index.md#-314949505%2FFunctions%2F57259888) | [JVM]<br>open fun [transformer](index.md#-314949505%2FFunctions%2F57259888)(): TransformerBuilder |
| [trySetCamelContext](index.md#-2046625498%2FFunctions%2F57259888) | [JVM]<br>open fun &lt;[T](index.md#-2046625498%2FFunctions%2F57259888)&gt; [trySetCamelContext](index.md#-2046625498%2FFunctions%2F57259888)(object: [T](index.md#-2046625498%2FFunctions%2F57259888), camelContext: CamelContext): [T](index.md#-2046625498%2FFunctions%2F57259888) |
| [trySetResource](index.md#-1750054341%2FFunctions%2F57259888) | [JVM]<br>open fun &lt;[T](index.md#-1750054341%2FFunctions%2F57259888)&gt; [trySetResource](index.md#-1750054341%2FFunctions%2F57259888)(object: [T](index.md#-1750054341%2FFunctions%2F57259888), resource: Resource): [T](index.md#-1750054341%2FFunctions%2F57259888) |
| [updateRoutesToCamelContext](index.md#-1778304512%2FFunctions%2F57259888) | [JVM]<br>open fun [updateRoutesToCamelContext](index.md#-1778304512%2FFunctions%2F57259888)(context: CamelContext): [Set](https://docs.oracle.com/javase/8/docs/api/java/util/Set.html)&lt;[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html)&gt; |
| [validator](index.md#-1664846042%2FFunctions%2F57259888) | [JVM]<br>open fun [validator](index.md#-1664846042%2FFunctions%2F57259888)(): ValidatorBuilder |
| [xpath](index.md#-1004004524%2FFunctions%2F57259888) | [JVM]<br>open fun [xpath](index.md#-1004004524%2FFunctions%2F57259888)(value: [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html)): ValueBuilder |
