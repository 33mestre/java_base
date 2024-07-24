//[java_base](../../../index.md)/[com.shelson.application.controller](../index.md)/[HomeController](index.md)

# HomeController

[JVM]\
@Controller

open class [HomeController](index.md)

Controller responsible for the application's home page. This controller redirects root application requests to the Swagger UI interface, allowing API exploration. Example usage: 

```kotlin

// URL: /
// This will redirect to /swagger-ui.html

```

#### Author

Shelson Ferrari

#### Since

2024-07-24

## Constructors

| | |
|---|---|
| [HomeController](-home-controller.md) | [JVM]<br>constructor() |

## Functions

| Name | Summary |
|---|---|
| [home](home.md) | [JVM]<br>@GetMapping(value = &quot;/&quot;)<br>open fun [home](home.md)(): [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html)<br>Redirects the root request (&quot;/&quot;) to the Swagger UI interface. |
