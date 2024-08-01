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
 * Unless required by applicable law or agreed to in writing, software distributed under the Licenses é
 * distribuído em uma base "COMO ESTÁ", SEM GARANTIAS OU CONDIÇÕES DE QUALQUER TIPO, expressas ou implícitas. Veja
 * os Licenses para a linguagem específica que rege permissões e limitações sob os Licenses.
 */

package com.shelson.application.service;

import java.time.LocalDateTime;
import java.util.Map;

import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shelson.application.dto.CurrencyConversionDTO;
import com.shelson.domain.model.Currency;
import com.shelson.domain.model.CurrencyConversion;
import com.shelson.domain.repository.CurrencyConversionRepository;
import com.shelson.infrastructure.exception.BusinessException;
import com.shelson.infrastructure.exception.ResourceNotFoundException;

/**
 * Camel route for handling currency conversion requests.
 * This route takes the source currency, target currency, and amount as headers,
 * processes the conversion using the {@link CurrencyConversionService}, and returns the conversion details.
 * 
 * @version 0.6.3
 * @since 2024-07-24
 * 
 * @author Shelson Ferrari
 * 
 * @see com.shelson.application.dto.CurrencyConversionDTO
 * @see com.shelson.application.service.CurrencyConversionService
 * @see com.shelson.application.processors.ExchangeRateProcessor
 * @see org.apache.camel.ProducerTemplate
 * @see com.shelson.domain.model.Currency
 */
@Service
public class CurrencyConversionService {

    @Autowired
    private ProducerTemplate producerTemplate;

    @Autowired
    private CurrencyConversionRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(CurrencyConversionService.class);

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Converts the given amount from the source currency to the target currency.
     *
     * @param sourceCurrency The source {@link Currency} to convert from.
     * @param targetCurrency The target {@link Currency} to convert to.
     * @param amount The amount to be converted.
     * @return A {@link CurrencyConversionDTO} containing the conversion details.
     * @throws BusinessException if sourceCurrency or targetCurrency is null, or if the amount is less than or equal to zero.
     * @throws ResourceNotFoundException if an error occurs while fetching the exchange rates.
     */
    public CurrencyConversionDTO convertCurrency(Currency sourceCurrency, Currency targetCurrency, double amount) {
        logger.info("Starting currency conversion: source={}, target={}, amount={}", sourceCurrency, targetCurrency, amount);

        if (sourceCurrency == null || targetCurrency == null) {
            logger.error("Source or target currency is null");
            throw new BusinessException("Source and target currencies must not be null");
        }
        if (amount <= 0) {
            logger.error("Invalid amount: {}", amount);
            throw new BusinessException("Amount must be greater than zero");
        }

        Map<String, Double> rates = null;
        try {
            Object response = producerTemplate.requestBodyAndHeader("direct:fetchRate", null, "sourceCurrency", sourceCurrency.getCode());
            if (response != null) {
                rates = objectMapper.convertValue(response, new TypeReference<Map<String, Double>>() {});
                logger.info("Fetched exchange rates: {}", rates);
            }
        } catch (Exception ex) {
            logger.error("Error fetching exchange rates from API: {}", ex.getMessage());
            throw new ResourceNotFoundException("Error fetching exchange rates from API", ex);
        }

        if (rates == null || !rates.containsKey(targetCurrency.getCode())) {
            logger.error("Invalid or missing target currency rate: {}", targetCurrency);
            throw new BusinessException("Invalid or missing target currency rate");
        }

        Double rate = rates.get(targetCurrency.getCode());
        double convertedAmount = amount * rate;
        CurrencyConversion conversion = new CurrencyConversion(sourceCurrency, targetCurrency, rate, LocalDateTime.now());
        repository.save(conversion);
        logger.info("Currency conversion saved: {}", conversion);

        CurrencyConversionDTO result = new CurrencyConversionDTO(sourceCurrency, targetCurrency, rate, LocalDateTime.now(), amount, convertedAmount);
        logger.info("Conversion result: {}", result);
        return result;
    }
}
