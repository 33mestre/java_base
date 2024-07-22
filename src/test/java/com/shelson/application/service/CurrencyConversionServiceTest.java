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
package com.shelson.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.shelson.application.dto.CurrencyConversionDTO;
import com.shelson.domain.model.Currency;
import com.shelson.domain.model.CurrencyConversion;
import com.shelson.domain.repository.CurrencyConversionRepository;
import com.shelson.infrastructure.exception.BusinessException;
import com.shelson.infrastructure.exception.ResourceNotFoundException;

/**
 * Testes para a classe {@link CurrencyConversionService}.
 */
@ExtendWith(MockitoExtension.class)
public class CurrencyConversionServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(CurrencyConversionServiceTest.class);

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private CurrencyConversionRepository repository;

    @InjectMocks
    private CurrencyConversionService service;

    /**
     * Testa a conversão de moeda com dados válidos.
     * @throws BusinessException 
     */ 
    @Test
    public void testConvertCurrency() throws BusinessException {
        logger.info("Iniciando teste para conversão de moeda com dados válidos");

        // Mock data
        Currency source = Currency.USD;
        Currency target = Currency.EUR;
        double rate = 0.85;
        double amount = 100.0;
        LocalDateTime now = LocalDateTime.now();

        // Mock response from API
        CurrencyConversionService.ApiResponse mockResponse = new CurrencyConversionService.ApiResponse();
        Map<String, Double> rates = new HashMap<>();
        rates.put(target.getCode(), rate);
        mockResponse.setRates(rates);
        when(restTemplate.getForObject(anyString(), eq(CurrencyConversionService.ApiResponse.class))).thenReturn(mockResponse);
        logger.debug("Mock response from API: {}", mockResponse);

        // Mock saving to repository
        CurrencyConversion savedConversion = new CurrencyConversion(source, target, rate, now);
        when(repository.save(any(CurrencyConversion.class))).thenReturn(savedConversion);
        logger.debug("Mock saving to repository: {}", savedConversion);

        // Perform the service method call
        CurrencyConversionDTO resultDTO = service.convertCurrency(source, target, amount);
        logger.info("Resultado da conversão: {}", resultDTO);

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
        logger.info("Teste para conversão de moeda com dados válidos concluído com sucesso");
    }

    /**
     * Testa a conversão de moeda com uma moeda de origem nula.
     */
    @Test
    public void testConvertCurrencyWithNullSource() {
        logger.info("Iniciando teste para conversão de moeda com moeda de origem nula");
        assertThatThrownBy(() -> service.convertCurrency(null, Currency.EUR, 100.0))
            .isInstanceOf(BusinessException.class)
            .hasMessageContaining("Source and target currencies must not be null");
        logger.info("Teste para conversão de moeda com moeda de origem nula concluído");
    }

    /**
     * Testa a conversão de moeda com uma moeda de destino nula.
     */
    @Test
    public void testConvertCurrencyWithNullTarget() {
        logger.info("Iniciando teste para conversão de moeda com moeda de destino nula");
        assertThatThrownBy(() -> service.convertCurrency(Currency.USD, null, 100.0))
            .isInstanceOf(BusinessException.class)
            .hasMessageContaining("Source and target currencies must not be null");
        logger.info("Teste para conversão de moeda com moeda de destino nula concluído");
    }

    /**
     * Testa a conversão de moeda com uma moeda inválida.
     */
    @Test
    public void testConvertCurrencyWithInvalidCurrency() {
        logger.info("Iniciando teste para conversão de moeda com moeda inválida");
        assertThatThrownBy(() -> service.convertCurrency(Currency.USD, Currency.valueOf("INVALID"), 100.0))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("No enum constant com.shelson.domain.model.Currency.INVALID");
        logger.info("Teste para conversão de moeda com moeda inválida concluído");
    }

    /**
     * Testa a conversão de moeda com um erro da API.
     */
    @Test
    public void testConvertCurrencyWithApiError() {
        logger.info("Iniciando teste para conversão de moeda com erro da API");
        when(restTemplate.getForObject(anyString(), eq(CurrencyConversionService.ApiResponse.class)))
            .thenThrow(HttpClientErrorException.class);

        assertThatThrownBy(() -> service.convertCurrency(Currency.USD, Currency.EUR, 100.0))
            .isInstanceOf(ResourceNotFoundException.class)
            .hasMessageContaining("Error fetching exchange rates from API");
        logger.info("Teste para conversão de moeda com erro da API concluído");
    }
}
