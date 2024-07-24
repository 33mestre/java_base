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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultExchange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Unit tests for the {@link ExchangeRateProcessor} class.
 * 
 * @version 0.6.3
 * @since 2024-07-24
 * 
 * @author Shelson Ferrari
 * 
 * @see com.shelson.application.processors.ExchangeRateProcessor
 * @see org.apache.camel.Exchange
 * @see org.apache.camel.impl.DefaultCamelContext
 * @see org.apache.camel.support.DefaultExchange
 * @see org.junit.jupiter.api.Test
 * @see org.junit.jupiter.api.BeforeEach
 */
public class ExchangeRateProcessorTest {

    private static final Logger logger = LoggerFactory.getLogger(ExchangeRateProcessorTest.class);

    private ExchangeRateProcessor processor;
    private Exchange exchange;

    /**
     * Sets up the test environment.
     */
    @BeforeEach
    public void setUp() {
        processor = new ExchangeRateProcessor();
        exchange = new DefaultExchange(new DefaultCamelContext());
        logger.info("Test environment set up");
    }

    /**
     * Tests the {@link ExchangeRateProcessor#process(Exchange)} method.
     * 
     * @throws Exception if any error occurs during processing
     */
    @Test
    public void testProcess() throws Exception {
        // Correctly formatted JSON string
        String json = "{\"rates\":{\"USD\":1.0,\"EUR\":0.85}}";
        exchange.getIn().setBody(json);

        logger.info("Processing exchange with body: {}", json);
        processor.process(exchange);

        @SuppressWarnings("unchecked")
        Map<String, Double> rates = exchange.getIn().getBody(Map.class);

        assertNotNull(rates, "Rates should not be null");
        assertEquals(1.0, rates.get("USD"), "USD rate should be 1.0");
        assertEquals(0.85, rates.get("EUR"), "EUR rate should be 0.85");

        logger.info("Processed rates: {}", rates);
    }
}
