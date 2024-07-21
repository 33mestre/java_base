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
package com.sognisport.application.service;

import java.time.LocalDateTime;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.sognisport.application.dto.CurrencyConversionDTO;
import com.sognisport.domain.model.Currency;
import com.sognisport.domain.model.CurrencyConversion;
import com.sognisport.domain.repository.CurrencyConversionRepository;
import com.sognisport.infrastructure.exception.BusinessException;
import com.sognisport.infrastructure.exception.ResourceNotFoundException;

/**
 * Service responsible for currency conversion operations.
 */
@Service
public class CurrencyConversionService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CurrencyConversionRepository repository;
    
    private static final Logger logger = LoggerFactory.getLogger(CurrencyConversionService.class);

    /**
     * Converts an amount from a source currency to a target currency.
     *
     * @param sourceCurrency The source currency.
     * @param targetCurrency The target currency.
     * @param amount The amount to be converted.
     * @return A {@link CurrencyConversionDTO} containing the conversion details.
     * @throws BusinessException if the source or target currency is null, or if the amount is less than or equal to zero.
     * @throws ResourceNotFoundException if an error occurs when fetching exchange rates from the API, or if the target currency is invalid.
     */
    public CurrencyConversionDTO convertCurrency(Currency sourceCurrency, Currency targetCurrency, double amount) throws BusinessException {
        if (sourceCurrency == null || targetCurrency == null) {
            throw new BusinessException("Source and target currencies must not be null");
        }

        if (amount <= 0) {
            throw new BusinessException("Amount must be greater than zero");
        }

        logger.info("Starting currency conversion from {} to {}", sourceCurrency, targetCurrency);

        String apiUrl = "https://api.exchangerate-api.com/v4/latest/" + sourceCurrency.getCode();
        ApiResponse response;
        try {
            response = restTemplate.getForObject(apiUrl, ApiResponse.class);
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            throw new ResourceNotFoundException("Error fetching exchange rates from API: " + ex.getMessage());
        }

        if (response == null || response.getRates() == null) {
            throw new ResourceNotFoundException("Unable to fetch exchange rates from API");
        }

        Double rate = response.getRates().get(targetCurrency.getCode());

        if (rate == null) {
            throw new ResourceNotFoundException("Invalid target currency");
        }

        double convertedAmount = amount * rate;
        CurrencyConversion conversion = new CurrencyConversion(sourceCurrency, targetCurrency, rate, LocalDateTime.now());
        repository.save(conversion);

        logger.info("Conversion completed successfully");

        return new CurrencyConversionDTO(sourceCurrency, targetCurrency, rate, LocalDateTime.now(), amount, convertedAmount);
    }

    /**
     * Inner class representing the exchange rates API response.
     */
    public static class ApiResponse {
        private Map<String, Double> rates;

        /**
         * Gets the exchange rates.
         * 
         * @return A map where the keys are currency codes and the values are exchange rates.
         */
        public Map<String, Double> getRates() {
            return rates;
        }

        /**
         * Sets the exchange rates.
         * 
         * @param rates A map where the keys are currency codes and the values are exchange rates.
         */
        public void setRates(Map<String, Double> rates) {
            this.rates = rates;
        }
    }
}
