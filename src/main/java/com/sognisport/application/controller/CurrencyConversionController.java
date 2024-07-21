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
package com.sognisport.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sognisport.application.dto.CurrencyConversionDTO;
import com.sognisport.application.service.CurrencyConversionService;
import com.sognisport.domain.model.Currency;
import com.sognisport.infrastructure.exception.BusinessException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Controller responsible for currency conversion operations.
 * Provides endpoints for converting values between different currencies.
 */
@RestController
@RequestMapping("/api/v1/conversions")
@Api(value = "Currency Conversion Controller", description = "Currency conversion operations")
public class CurrencyConversionController {

    @Autowired
    private CurrencyConversionService currencyConversionService;

    /**
     * Converts the source currency to the target currency.
     * 
     * @param source The source currency. Example: USD.
     * @param target The target currency. Example: BRL.
     * @param amount The amount to be converted. Example: 100.0.
     * @return A {@link CurrencyConversionDTO} containing the conversion details.
     * @throws BusinessException 
     */
    @GetMapping("/convert")
    @ApiOperation(value = "Converts the source currency to the target currency", notes = "Returns the currency conversion details")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation", response = CurrencyConversionDTO.class),
            @ApiResponse(code = 400, message = "Invalid input")
    })
    public CurrencyConversionDTO convertCurrency(
            @RequestParam @ApiParam(value = "Source currency", example = "USD") Currency source, 
            @RequestParam @ApiParam(value = "Target currency", example = "BRL") Currency target,
            @RequestParam @ApiParam(value = "Amount to be converted", example = "100.0") double amount) throws BusinessException {
        return currencyConversionService.convertCurrency(source, target, amount);
    }
}
