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

package com.shelson.application.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.camel.ProducerTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.shelson.application.service.CurrencyConversionService;
import com.shelson.domain.model.Currency;
import com.shelson.infrastructure.exception.BusinessException;

/**
 * Tests for {@link CurrencyConversionController}.
 */
@WebMvcTest(CurrencyConversionController.class)
public class CurrencyConversionControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(CurrencyConversionControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurrencyConversionService service;

    @MockBean
    private ProducerTemplate producerTemplate;

    @InjectMocks
    private CurrencyConversionController controller;

    @BeforeEach
    public void setUp() {
        logger.info("Setting up mocks for controller tests");
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    /**
     * Tests currency conversion with extra parameters.
     */
    @Test
    public void testConvertCurrencyWithExtraParameters() throws Exception {
        logger.info("Starting test for currency conversion with extra parameters");

        // Mock the service to throw a BusinessException when extra parameters are detected
        when(service.convertCurrency(any(Currency.class), any(Currency.class), any(Double.class)))
            .thenThrow(new BusinessException("Unexpected parameter: conversionType"));

        mockMvc.perform(get("/api/v1/conversions/convert")
                .param("source", "GBP")
                .param("target", "JPY")
                .param("amount", "200")
                .param("conversionType", "fast"))
            .andExpect(status().isBadRequest());

        logger.info("Test for currency conversion with extra parameters completed");
    }
}
