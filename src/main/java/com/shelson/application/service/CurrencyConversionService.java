package com.shelson.application.service;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

import com.shelson.application.dto.CurrencyConversionDTO;
import com.shelson.domain.model.Currency;
import com.shelson.domain.model.CurrencyConversion;
import com.shelson.domain.repository.CurrencyConversionRepository;
import com.shelson.infrastructure.exception.BusinessException;
import com.shelson.infrastructure.exception.ResourceNotFoundException;

@Service
public class CurrencyConversionService {

    @Autowired
    private ProducerTemplate producerTemplate;

    @Autowired
    private CurrencyConversionRepository repository;

    public CurrencyConversionDTO convertCurrency(Currency sourceCurrency, Currency targetCurrency, double amount) throws BusinessException {
        if (sourceCurrency == null || targetCurrency == null) {
            throw new BusinessException("Source and target currencies must not be null");
        }

        if (amount <= 0) {
            throw new BusinessException("Amount must be greater than zero");
        }

        Map<String, Object> headers = Map.of(
                "sourceCurrency", sourceCurrency.getCode(),
                "targetCurrency", targetCurrency.getCode()
        );

        Map<String, Double> rates;
        try {
            rates = producerTemplate.requestBodyAndHeaders("direct:fetchRate", null, headers, Map.class);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Unable to fetch exchange rates from API");
        }

        if (rates == null || rates.isEmpty()) {
            throw new ResourceNotFoundException("Unable to fetch exchange rates from API");
        }

        Double rate = rates.get(targetCurrency.getCode());

        if (rate == null) {
            throw new ResourceNotFoundException("Invalid target currency");
        }

        double convertedAmount = amount * rate;
        CurrencyConversion conversion = new CurrencyConversion(sourceCurrency, targetCurrency, rate, LocalDateTime.now());
        repository.save(conversion);

        return new CurrencyConversionDTO(sourceCurrency, targetCurrency, rate, LocalDateTime.now(), amount, convertedAmount);
    }
}
