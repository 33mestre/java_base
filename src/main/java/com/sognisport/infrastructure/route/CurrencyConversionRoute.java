package com.sognisport.infrastructure.route;

import java.util.Map;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CurrencyConversionRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:fetchRate")
            .toD("https://api.exchangerate-api.com/v4/latest/${header.sourceCurrency}")
            .convertBodyTo(String.class)
            .unmarshal().json()
            .process(exchange -> {
                Map<String, Object> response = exchange.getIn().getBody(Map.class);
                Map<String, Double> rates = (Map<String, Double>) response.get("rates");
                exchange.getIn().setBody(rates);
            });
    }
}
