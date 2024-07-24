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
 * Service class for handling currency conversion operations.
 * This class uses a Camel route to fetch the latest exchange rates and performs
 * conversions between different currencies.
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
            throw new ResourceNotFoundException("Error fetching exchange rates from API: " + ex.getMessage());
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
