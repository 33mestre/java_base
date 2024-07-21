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
package com.sognisport.domain.service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sognisport.domain.model.Currency;
import com.sognisport.domain.model.CurrencyConversion;
import com.sognisport.domain.repository.CurrencyConversionRepository;

/**
 * Domain service to perform currency conversion operations.
 * This service handles the business logic related to currency conversion and interaction with the conversion repository.
 */
@Service
public class CurrencyConversionDomainService {

    private static final Logger logger = LoggerFactory.getLogger(CurrencyConversionDomainService.class);
    private final CurrencyConversionRepository conversionRepository;

    /**
     * Constructor that injects the currency conversion repository.
     *
     * @param conversionRepository The currency conversion repository.
     */
    public CurrencyConversionDomainService(CurrencyConversionRepository conversionRepository) {
        this.conversionRepository = conversionRepository;
    }

    /**
     * Performs currency conversion based on the provided rate and records the transaction.
     * This function creates a new {@link CurrencyConversion} object, sets its properties,
     * including the query date and time, and saves the object to the repository.
     *
     * @param sourceCurrency The source currency for the conversion.
     * @param targetCurrency The target currency for the conversion.
     * @param rate           The conversion rate to be applied.
     * @return A {@link CurrencyConversion} object containing the conversion details.
     * @throws IllegalArgumentException if the conversion rate is less than or equal to zero.
     */
    public CurrencyConversion performConversion(Currency sourceCurrency, Currency targetCurrency, double rate) {
        logger.info("Starting currency conversion: {} to {} with rate {}", sourceCurrency, targetCurrency, rate);

        if (rate <= 0) {
            logger.error("Invalid conversion rate: {}", rate);
            throw new IllegalArgumentException("Conversion rate must be greater than zero.");
        }

        // Create a new CurrencyConversion object
        CurrencyConversion conversion = new CurrencyConversion();
        // Set the source currency
        conversion.setSourceCurrency(sourceCurrency);
        // Set the target currency
        conversion.setTargetCurrency(targetCurrency);
        // Set the conversion rate
        conversion.setConversionRate(rate);
        // Set the query date and time
        conversion.setQueryDate(LocalDateTime.now());

        // Save the conversion object to the repository and return the saved object
        CurrencyConversion savedConversion = conversionRepository.save(conversion);

        logger.info("Currency conversion completed: {}", savedConversion);
        return savedConversion;
    }
}
