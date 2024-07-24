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

package com.shelson.application.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shelson.application.dto.CurrencyConversionDTO;
import com.shelson.application.service.CurrencyConversionService;
import com.shelson.domain.model.Currency;

/**
 * Camel route for handling currency conversion requests.
 * This route takes the source currency, target currency, and amount as headers,
 * processes the conversion using the {@link CurrencyConversionService}, and returns the conversion details.
 */
@Component
public class CurrencyConversionRoute extends RouteBuilder {

    @Autowired
    private CurrencyConversionService currencyConversionService;

    @Override
    public void configure() throws Exception {
        from("direct:convertCurrency")
            .routeId("currencyConversionRoute")
            .log("Processing currency conversion: source=${header.source}, target=${header.target}, amount=${header.amount}")
            .process(exchange -> {
                String source = exchange.getIn().getHeader("source", String.class);
                String target = exchange.getIn().getHeader("target", String.class);
                Double amount = exchange.getIn().getHeader("amount", Double.class);

                if (source == null || target == null || amount == null) {
                    throw new IllegalArgumentException("Source, target, and amount must be provided");
                }

                Currency sourceCurrency = Currency.valueOf(source);
                Currency targetCurrency = Currency.valueOf(target);

                CurrencyConversionDTO conversionDTO = currencyConversionService.convertCurrency(sourceCurrency, targetCurrency, amount);
                exchange.getIn().setBody(conversionDTO);
            })
            .log("Currency conversion completed: ${body}");
    }
}
