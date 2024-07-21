//[sognisport](../../../index.md)/[com.sognisport.infrastructure.exception](../index.md)/[BusinessException](index.md)

# BusinessException

[JVM]\
@ResponseStatus(code = HttpStatus.BAD_REQUEST)

open class [BusinessException](index.md) : [RuntimeException](https://docs.oracle.com/javase/8/docs/api/java/lang/RuntimeException.html)

Custom exception for business errors.

## Constructors

| | |
|---|---|
| [BusinessException](-business-exception.md) | [JVM]<br>constructor(message: [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html)) |

## Properties

| Name | Summary |
|---|---|
| [cause](../-resource-not-found-exception/index.md#-1023347080%2FProperties%2F1511606574) | [JVM]<br>open val [cause](../-resource-not-found-exception/index.md#-1023347080%2FProperties%2F1511606574): [Throwable](https://docs.oracle.com/javase/8/docs/api/java/lang/Throwable.html) |
| [stackTrace](../-resource-not-found-exception/index.md#1573944892%2FProperties%2F1511606574) | [JVM]<br>open var [stackTrace](../-resource-not-found-exception/index.md#1573944892%2FProperties%2F1511606574): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[StackTraceElement](https://docs.oracle.com/javase/8/docs/api/java/lang/StackTraceElement.html)&gt; |

## Functions

| Name | Summary |
|---|---|
| [addSuppressed](../-resource-not-found-exception/index.md#-1898257014%2FFunctions%2F1511606574) | [JVM]<br>fun [addSuppressed](../-resource-not-found-exception/index.md#-1898257014%2FFunctions%2F1511606574)(exception: [Throwable](https://docs.oracle.com/javase/8/docs/api/java/lang/Throwable.html)) |
| [fillInStackTrace](../-resource-not-found-exception/index.md#-1207709164%2FFunctions%2F1511606574) | [JVM]<br>open fun [fillInStackTrace](../-resource-not-found-exception/index.md#-1207709164%2FFunctions%2F1511606574)(): [Throwable](https://docs.oracle.com/javase/8/docs/api/java/lang/Throwable.html) |
| [getLocalizedMessage](../-resource-not-found-exception/index.md#-2138642817%2FFunctions%2F1511606574) | [JVM]<br>open fun [getLocalizedMessage](../-resource-not-found-exception/index.md#-2138642817%2FFunctions%2F1511606574)(): [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html) |
| [getMessage](../-resource-not-found-exception/index.md#1068546184%2FFunctions%2F1511606574) | [JVM]<br>open fun [getMessage](../-resource-not-found-exception/index.md#1068546184%2FFunctions%2F1511606574)(): [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html) |
| [getSuppressed](../-resource-not-found-exception/index.md#1678506999%2FFunctions%2F1511606574) | [JVM]<br>fun [getSuppressed](../-resource-not-found-exception/index.md#1678506999%2FFunctions%2F1511606574)(): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[Throwable](https://docs.oracle.com/javase/8/docs/api/java/lang/Throwable.html)&gt; |
| [initCause](../-resource-not-found-exception/index.md#-104903378%2FFunctions%2F1511606574) | [JVM]<br>open fun [initCause](../-resource-not-found-exception/index.md#-104903378%2FFunctions%2F1511606574)(cause: [Throwable](https://docs.oracle.com/javase/8/docs/api/java/lang/Throwable.html)): [Throwable](https://docs.oracle.com/javase/8/docs/api/java/lang/Throwable.html) |
| [printStackTrace](../-resource-not-found-exception/index.md#-1357294889%2FFunctions%2F1511606574) | [JVM]<br>open fun [printStackTrace](../-resource-not-found-exception/index.md#-1357294889%2FFunctions%2F1511606574)() |
| [toString](../-resource-not-found-exception/index.md#1869833549%2FFunctions%2F1511606574) | [JVM]<br>open fun [toString](../-resource-not-found-exception/index.md#1869833549%2FFunctions%2F1511606574)(): [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html) |
