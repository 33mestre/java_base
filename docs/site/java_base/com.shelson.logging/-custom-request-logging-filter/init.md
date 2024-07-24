//[java_base](../../../index.md)/[com.shelson.logging](../index.md)/[CustomRequestLoggingFilter](index.md)/[init](init.md)

# init

[JVM]\
open fun [init](init.md)(filterConfig: FilterConfig)

Initializes the filter. 

 This method is called by the servlet container to indicate to the filter that it is being placed into service. The filter configuration object provides the filter with its initialization parameters. Here, any necessary setup can be done. 

#### Parameters

JVM

| | |
|---|---|
| filterConfig | The filter configuration. |

#### Throws

| | |
|---|---|
| ServletException | If an error occurs during initialization. |
