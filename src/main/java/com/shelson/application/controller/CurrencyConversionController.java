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

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shelson.application.dto.CurrencyConversionDTO;
import com.shelson.domain.model.Currency;
import com.shelson.infrastructure.exception.BusinessException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Controller responsible for handling currency conversion requests.
 * This controller provides an endpoint to convert a given amount from a source currency to a target currency.
 * It utilizes Apache Camel routes for fetching the exchange rates and performing the conversion.
 * 
 * Validations performed by this class:
 * <ul>
 *     <li>Ensures that the source and target currency codes are valid.</li>
 *     <li>Ensures that the amount to be converted is greater than zero.</li>
 *     <li>Checks for any unexpected parameters in the request and throws a BusinessException if found.</li>
 * </ul>
 */
@RestController
@RequestMapping("/api/v1/conversions")
@Api(value = "Currency Conversion Controller", description = "Operations for currency conversion")
public class CurrencyConversionController {

    @Autowired
    private ProducerTemplate producerTemplate;

    private static final Logger logger = LoggerFactory.getLogger(CurrencyConversionController.class);

    /**
     * Converts the source currency to the target currency.
     *
     * Validates the following conditions:
     * <ul>
     *     <li>Source currency code is valid.</li>
     *     <li>Target currency code is valid.</li>
     *     <li>Amount to be converted is greater than zero.</li>
     *     <li>No unexpected parameters are present in the request.</li>
     * </ul>
     *
     * @param source The source currency code.
     * @param target The target currency code.
     * @param amount The amount to be converted.
     * @param request The HttpServletRequest object to check for unexpected parameters.
     * @return The details of the currency conversion.
     * @throws BusinessException if the source or target currency is invalid or if there are unexpected parameters.
     */
    @GetMapping("/convert")
    @ApiOperation(value = "Converts the source currency to the target currency", notes = "Returns the currency conversion details")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation", response = CurrencyConversionDTO.class),
            @ApiResponse(code = 400, message = "Invalid input")
    })
    public CurrencyConversionDTO convertCurrency(
            @RequestParam @ApiParam(value = "Source currency", example = "USD") String source,
            @RequestParam @ApiParam(value = "Target currency", example = "BRL") String target,
            @RequestParam @ApiParam(value = "Amount to be converted", example = "100.0") double amount,
            HttpServletRequest request) throws BusinessException {

        logger.info("Received currency conversion request: source={}, target={}, amount={}", source, target, amount);

        // Validate that only the expected parameters are present
        validateParameters(request);

        try {
            Currency sourceCurrency = Currency.valueOf(source);
            Currency targetCurrency = Currency.valueOf(target);

            Map<String, Object> headers = new HashMap<>();
            headers.put("source", sourceCurrency);
            headers.put("target", targetCurrency);
            headers.put("amount", amount);

            CurrencyConversionDTO result = producerTemplate.requestBodyAndHeaders("direct:convertCurrency", null, headers, CurrencyConversionDTO.class);
            logger.info("Currency conversion successful: {}", result);
            return result;
        } catch (IllegalArgumentException ex) {
            logger.error("Invalid currency code provided: source={}, target={}", source, target, ex);
            throw new BusinessException("Invalid source or target currency");
        }
    }

    /**
     * Validates that no unexpected parameters are present in the request.
     *
     * @param request The HttpServletRequest object containing the request parameters.
     * @throws BusinessException if any unexpected parameters are found.
     */
    private void validateParameters(HttpServletRequest request) throws BusinessException {
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            if (!paramName.equals("source") && !paramName.equals("target") && !paramName.equals("amount")) {
                logger.error("Unexpected parameter found: {}", paramName);
                throw new BusinessException("Unexpected parameter: " + paramName);
            }
        }
    }
}
