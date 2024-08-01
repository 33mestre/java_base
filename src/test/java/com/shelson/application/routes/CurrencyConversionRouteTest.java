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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.shelson.application.dto.CurrencyConversionDTO;

/**
 * Unit tests for the {@link CurrencyConversionRoute} class.
 * 
 * @version 0.6.3
 * @since 2024-07-24
 * 
 * @author Shelson Ferrari
 * 
 * @see com.shelson.application.routes.CurrencyConversionRoute
 * @see org.apache.camel.CamelContext
 * @see org.apache.camel.ProducerTemplate
 * @see org.junit.jupiter.api.Test
 * @see org.springframework.boot.test.context.SpringBootTest
 */
@CamelSpringBootTest
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CurrencyConversionRouteTest {

    private static final Logger logger = LoggerFactory.getLogger(CurrencyConversionRouteTest.class);

    @Autowired
    private ProducerTemplate producerTemplate;

    @Autowired
    private CamelContext camelContext;

    /**
     * Tests the currency conversion route.
     * 
     * @throws Exception if an error occurs during route execution
     */
    @Test
    public void testCurrencyConversionRoute() throws Exception {
        logger.info("Starting test for currency conversion route");

        camelContext.start();
        logger.info("Camel context started");

        Map<String, Object> headers = new HashMap<>();
        headers.put("source", "USD");
        headers.put("target", "EUR");
        headers.put("amount", 100.0);

        logger.info("Sending request to convertCurrency route with headers: {}", headers);
        CurrencyConversionDTO result = producerTemplate.requestBodyAndHeaders("direct:convertCurrency", null, headers, CurrencyConversionDTO.class);

        assertNotNull(result, "The result should not be null");
        logger.info("Result received: {}", result);

        assertEquals("USD", result.sourceCurrency().getCode(), "The source currency should be USD");
        assertEquals("EUR", result.targetCurrency().getCode(), "The target currency should be EUR");

        camelContext.stop();
        logger.info("Camel context stopped");

        logger.info("Test for currency conversion route completed successfully");
    }
}
