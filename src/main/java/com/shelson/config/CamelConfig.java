/*
 * Copyright (c) 2024, Shelson Ferrari
 *
 * Licensed under the MIT License and the Apache License, Version 2.0 (the "Licenses"); you may not use this file except in
 * compliance with the Licenses. You may obtain a copy of the Licenses at
 *
 * MIT License:
 * https://opensource.org/licenses/MIT
 *
 * Apache License, Version 2.0:
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the Licenses is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See
 * the Licenses for the specific language governing permissions and limitations under the Licenses.
 */
package com.shelson.config;

import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shelson.application.service.CurrencyConversionService;
import com.shelson.application.service.CurrencyConversionServiceApiResponse;

/**
 * Configuration class for Apache Camel routes.
 * <p>
 * This class sets up the Camel routes used for currency conversion and fetching exchange rates.
 * It defines how the data flows through the routes and how the exchange rates are fetched from the API.
 */
@Configuration
public class CamelConfig {

    @Autowired
    private CurrencyConversionService currencyConversionService;

    @Value("${currency.conversion.url}")
    private String currencyConversionUrl;

    /**
     * Configures the Camel routes.
     * 
     * @param camelContext the Camel context used to configure the routes.
     * @throws Exception if an error occurs while adding routes to the Camel context.
     */
    @Autowired
    public void configureCamelContext(CamelContext camelContext) throws Exception {
        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() {
                from("direct:convertCurrency")
                    .routeId("currencyConversionRoute")
                    .log("Processing currency conversion: source=${header.source}, target=${header.target}, amount=${header.amount}")
                    .bean(currencyConversionService, "convertCurrency(${header.source}, ${header.target}, ${header.amount})")
                    .log("Currency conversion completed: ${body}");

                from("direct:fetchRate")
                    .routeId("fetchRateRoute")
                    .log("Fetching exchange rate for ${header.sourceCurrency}")
                    .process(exchange -> {
                        String sourceCurrency = exchange.getIn().getHeader("sourceCurrency", String.class);
                        String apiUrl = String.format("%s%s", currencyConversionUrl, sourceCurrency);
                        exchange.getIn().setHeader("CamelHttpUri", apiUrl);
                    })
                    .toD("${header.CamelHttpUri}")
                    .convertBodyTo(String.class)
                    .process(exchange -> {
                        String response = exchange.getIn().getBody(String.class);
                        Map<String, Double> rates = new ObjectMapper().readValue(response, CurrencyConversionServiceApiResponse.class).getRates();
                        exchange.getIn().setBody(rates);
                    });
            }
        });
    }
}
