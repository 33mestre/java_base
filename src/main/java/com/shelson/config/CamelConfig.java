package com.shelson.config;

import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamelConfig {

    @Bean
    public CamelContext camelContext() {
        CamelContext camelContext = new DefaultCamelContext();
        try {
            camelContext.start();  // Inicia o CamelContext
        } catch (Exception e) {
            e.printStackTrace();
        }
        return camelContext;
    }

    @Bean
    public ProducerTemplate producerTemplate(CamelContext camelContext) {
        return camelContext.createProducerTemplate();
    }

    @Bean
    public RouteBuilder routeBuilder() {
        return new RouteBuilder() {
            @Override
            public void configure() {
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
        };
    }
}
