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
 * Unless required by applicable law or agreed to in writing, software distribuído sob os Licenses é
 * distribuído em uma base "COMO ESTÁ", SEM GARANTIAS OU CONDIÇÕES DE QUALQUER TIPO, expressas ou implícitas. Veja
 * os Licenses para a linguagem específica que rege permissões e limitações sob os Licenses.
 */

package com.shelson.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.apache.camel.ProducerTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shelson.application.dto.CurrencyConversionDTO;
import com.shelson.domain.model.Currency;
import com.shelson.domain.model.CurrencyConversion;
import com.shelson.domain.repository.CurrencyConversionRepository;
import com.shelson.infrastructure.exception.BusinessException;
import com.shelson.infrastructure.exception.ResourceNotFoundException;

/**
 * Tests for {@link CurrencyConversionService}.
 */
@ExtendWith(MockitoExtension.class)
public class CurrencyConversionServiceTest {

    @Mock
    private ProducerTemplate producerTemplate;

    @Mock
    private CurrencyConversionRepository repository;

    @InjectMocks
    private CurrencyConversionService service;

    private static final Logger logger = LoggerFactory.getLogger(CurrencyConversionServiceTest.class);

    @BeforeEach
    public void setUp() {
        logger.info("Setting up mocks for tests");
    }

    /**
     * Tests currency conversion with valid data.
     * @throws BusinessException if a business error occurs
     */
    @Test
    public void testConvertCurrency() throws BusinessException {
        logger.info("Starting test for valid currency conversion");

        // Mock data
        Currency source = Currency.USD;
        Currency target = Currency.EUR;
        double rate = 0.85;
        double amount = 100.0;
        LocalDateTime now = LocalDateTime.now();

        // Mock response from API
        Map<String, Double> rates = new HashMap<>();
        rates.put(target.getCode(), rate);
        when(producerTemplate.requestBodyAndHeader(eq("direct:fetchRate"), eq(null), eq("sourceCurrency"), eq(source.getCode())))
            .thenReturn(rates);

        // Mock saving to repository
        CurrencyConversion savedConversion = new CurrencyConversion(source, target, rate, now);
        when(repository.save(any(CurrencyConversion.class))).thenReturn(savedConversion);

        // Perform the service method call
        CurrencyConversionDTO resultDTO = service.convertCurrency(source, target, amount);
        logger.info("Conversion result: {}", resultDTO);

        // Assert results
        assertThat(resultDTO.getSourceCurrency()).isEqualTo(source);
        assertThat(resultDTO.getTargetCurrency()).isEqualTo(target);
        assertThat(resultDTO.getConversionRate()).isEqualTo(rate);

        // Compare year, month, day, hour, minute, second fields, ignoring nanos
        LocalDateTime expectedQueryDate = now.withNano(0);
        LocalDateTime actualQueryDate = resultDTO.getQueryDate().withNano(0);
        assertThat(actualQueryDate).isEqualTo(expectedQueryDate);

        // Verify repository method was called
        verify(repository).save(any(CurrencyConversion.class));
        logger.info("Test for valid currency conversion completed successfully");
    }

    /**
     * Tests currency conversion with null source currency.
     */
    @Test
    public void testConvertCurrencyWithNullSource() {
        logger.info("Starting test for currency conversion with null source");
        assertThatThrownBy(() -> service.convertCurrency(null, Currency.EUR, 100.0))
            .isInstanceOf(BusinessException.class)
            .hasMessageContaining("Source and target currencies must not be null");
        logger.info("Test for currency conversion with null source completed");
    }

    /**
     * Tests currency conversion with null target currency.
     */
    @Test
    public void testConvertCurrencyWithNullTarget() {
        logger.info("Starting test for currency conversion with null target");
        assertThatThrownBy(() -> service.convertCurrency(Currency.USD, null, 100.0))
            .isInstanceOf(BusinessException.class)
            .hasMessageContaining("Source and target currencies must not be null");
        logger.info("Test for currency conversion with null target completed");
    }

    /**
     * Tests currency conversion with an invalid currency.
     */
    @Test
    public void testConvertCurrencyWithInvalidCurrency() {
        logger.info("Starting test for currency conversion with invalid currency");
        assertThatThrownBy(() -> service.convertCurrency(Currency.USD, Currency.valueOf("INVALID"), 100.0))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("No enum constant com.shelson.domain.model.Currency.INVALID");
        logger.info("Test for currency conversion with invalid currency completed");
    }

    /**
     * Tests currency conversion with an API error.
     */
    @Test
    public void testConvertCurrencyWithApiError() {
        logger.info("Starting test for currency conversion with API error");
        when(producerTemplate.requestBodyAndHeader(eq("direct:fetchRate"), eq(null), eq("sourceCurrency"), eq(Currency.USD.getCode())))
            .thenThrow(RuntimeException.class);

        assertThatThrownBy(() -> service.convertCurrency(Currency.USD, Currency.EUR, 100.0))
            .isInstanceOf(ResourceNotFoundException.class)
            .hasMessageContaining("Error fetching exchange rates from API");
        logger.info("Test for currency conversion with API error completed");
    }
}
