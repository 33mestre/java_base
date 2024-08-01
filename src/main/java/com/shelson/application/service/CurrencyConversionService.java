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
 * Service responsible for handling currency conversion requests using Apache Camel.
 * This service takes the source currency, target currency, and amount as parameters,
 * performs the currency conversion, and returns the conversion details as a DTO.
 * 
 * <p>This service uses Apache Camel's {@link ProducerTemplate} to request exchange rates and
 * interacts with the {@link CurrencyConversionRepository} to persist conversion records.
 * 
 * @version 0.6.3
 * @since 2024-07-24
 * 
 * @author Shelson Ferrari
 * 
 * @see com.shelson.application.dto.CurrencyConversionDTO
 * @see com.shelson.domain.model.Currency
 * @see com.shelson.domain.model.CurrencyConversion
 * @see com.shelson.domain.repository.CurrencyConversionRepository
 * @see com.shelson.infrastructure.exception.BusinessException
 * @see com.shelson.infrastructure.exception.ResourceNotFoundException
 * @see org.apache.camel.ProducerTemplate
 */
@Service
public class CurrencyConversionService {

    private final ProducerTemplate producerTemplate;
    private final CurrencyConversionRepository repository;
    private final ObjectMapper objectMapper;

    private static final Logger logger = LoggerFactory.getLogger(CurrencyConversionService.class);

    /**
     * Constructs a {@code CurrencyConversionService} with the specified dependencies.
     * 
     * @param producerTemplate The Apache Camel {@link ProducerTemplate} used to request exchange rates.
     * @param repository The {@link CurrencyConversionRepository} used to save currency conversion records.
     * @param objectMapper The {@link ObjectMapper} used to parse and convert JSON data.
     */
    public CurrencyConversionService(ProducerTemplate producerTemplate, 
                                     CurrencyConversionRepository repository, 
                                     ObjectMapper objectMapper) {
        this.producerTemplate = producerTemplate;
        this.repository = repository;
        this.objectMapper = objectMapper;
    }
    
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

        validateInputs(sourceCurrency, targetCurrency, amount);
        Map<String, Double> rates = fetchExchangeRates(sourceCurrency);

        Double rate = getRateForTargetCurrency(rates, targetCurrency);
        double convertedAmount = calculateConvertedAmount(amount, rate);

        CurrencyConversion conversion = saveCurrencyConversion(sourceCurrency, targetCurrency, rate);
        return buildCurrencyConversionDTO(sourceCurrency, targetCurrency, amount, convertedAmount, conversion);
    }

    /**
     * Validates the input parameters for currency conversion.
     * 
     * @param sourceCurrency The source {@link Currency} to convert from.
     * @param targetCurrency The target {@link Currency} to convert to.
     * @param amount The amount to be converted.
     * @throws BusinessException if sourceCurrency or targetCurrency is null, or if the amount is less than or equal to zero.
     */
    private void validateInputs(Currency sourceCurrency, Currency targetCurrency, double amount) {
        if (sourceCurrency == null || targetCurrency == null) {
            logger.error("Source or target currency is null");
            throw new BusinessException("Source and target currencies must not be null");
        }
        if (amount <= 0) {
            logger.error("Invalid amount: {}", amount);
            throw new BusinessException("Amount must be greater than zero");
        }
    }

    /**
     * Fetches exchange rates from an external service.
     * 
     * @param sourceCurrency The source {@link Currency} for which the exchange rates are requested.
     * @return A {@link Map} containing the exchange rates, where the keys are currency codes and the values are rates.
     * @throws ResourceNotFoundException if an error occurs while fetching the exchange rates.
     */
    private Map<String, Double> fetchExchangeRates(Currency sourceCurrency) {
        try {
            Object response = producerTemplate.requestBodyAndHeader("direct:fetchRate", null, "sourceCurrency", sourceCurrency.getCode());
            if (response != null) {
                return objectMapper.convertValue(response, new TypeReference<Map<String, Double>>() {});
            }
        } catch (Exception ex) {
            logger.error("Error fetching exchange rates from API: {}", ex.getMessage());
            throw new ResourceNotFoundException("Error fetching exchange rates from API", ex);
        }
        return null;
    }

    /**
     * Retrieves the exchange rate for the target currency from the fetched rates.
     * 
     * @param rates A {@link Map} containing the exchange rates.
     * @param targetCurrency The target {@link Currency} for which the rate is needed.
     * @return The exchange rate for the target currency.
     * @throws BusinessException if the target currency rate is invalid or missing.
     */
    private Double getRateForTargetCurrency(Map<String, Double> rates, Currency targetCurrency) {
        if (rates == null || !rates.containsKey(targetCurrency.getCode())) {
            logger.error("Invalid or missing target currency rate: {}", targetCurrency);
            throw new BusinessException("Invalid or missing target currency rate");
        }
        return rates.get(targetCurrency.getCode());
    }

    /**
     * Calculates the converted amount based on the given amount and exchange rate.
     * 
     * @param amount The amount to be converted.
     * @param rate The exchange rate to be used for conversion.
     * @return The converted amount.
     */
    private double calculateConvertedAmount(double amount, Double rate) {
        return amount * rate;
    }

    /**
     * Saves the currency conversion record to the repository.
     * 
     * @param sourceCurrency The source {@link Currency}.
     * @param targetCurrency The target {@link Currency}.
     * @param rate The exchange rate used for conversion.
     * @return The {@link CurrencyConversion} record that was saved.
     */
    private CurrencyConversion saveCurrencyConversion(Currency sourceCurrency, Currency targetCurrency, Double rate) {
        CurrencyConversion conversion = new CurrencyConversion(sourceCurrency, targetCurrency, rate, LocalDateTime.now());
        repository.save(conversion);
        logger.info("Currency conversion saved: {}", conversion);
        return conversion;
    }

    /**
     * Builds a {@link CurrencyConversionDTO} with the conversion details.
     * 
     * @param sourceCurrency The source {@link Currency}.
     * @param targetCurrency The target {@link Currency}.
     * @param amount The amount to be converted.
     * @param convertedAmount The result of the conversion.
     * @param conversion The {@link CurrencyConversion} record used for creating the DTO.
     * @return A {@link CurrencyConversionDTO} containing the conversion details.
     */
    private CurrencyConversionDTO buildCurrencyConversionDTO(Currency sourceCurrency, Currency targetCurrency, double amount, double convertedAmount, CurrencyConversion conversion) {
        CurrencyConversionDTO result = new CurrencyConversionDTO(sourceCurrency, targetCurrency, conversion.getConversionRate(), conversion.getQueryDate(), amount, convertedAmount);
        logger.info("Conversion result: {}", result);
        return result;
    }
}
