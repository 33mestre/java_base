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

package com.shelson.application.processors;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Processor to handle exchange rates.
 * This processor reads the exchange rate data from the message body,
 * parses it, and sets the parsed rates back into the message body.
 */
@Component
public class ExchangeRateProcessor implements Processor {
    private static final Logger logger = LoggerFactory.getLogger(ExchangeRateProcessor.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Processes the exchange to extract and set exchange rates.
     *
     * @param exchange The Camel exchange containing the message
     * @throws Exception If an error occurs during processing
     */
    @Override
    public void process(Exchange exchange) throws Exception {
        logger.info("Processing exchange to extract rates");
        String response = exchange.getIn().getBody(String.class);
        Map<String, Map<String, Double>> ratesResponse = objectMapper.readValue(response, new TypeReference<Map<String, Map<String, Double>>>() {});
        Map<String, Double> rates = ratesResponse.get("rates");
        exchange.getIn().setBody(rates);
        logger.info("Exchange rates extracted and set in the message body");
    }
}
