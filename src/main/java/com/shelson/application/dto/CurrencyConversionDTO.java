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
package com.shelson.application.dto;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import com.shelson.domain.model.Currency;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Record that represents details about the currency conversion.
 * 
 * This `record` is a concise way to model immutable data in Java. It automatically generates 
 * constructors, accessors, `equals`, `hashCode`, and `toString` methods, reducing boilerplate code 
 * and making the data model more readable and maintainable.
 * 
 * @version 0.9.2
 * @since 2024-07-24
 * @see com.shelson.domain.model.Currency
 * @see com.shelson.application.service.CurrencyConversionService
 * @see org.apache.camel.ProducerTemplate
 * @author Shelson Ferrari
 */

@ApiModel(description = "Details about the currency conversion")
public record CurrencyConversionDTO(
    
    @ApiModelProperty(value = "Source currency", example = "USD", required = true)
    @NotNull(message = "Source currency cannot be null")
    Currency sourceCurrency,

    @ApiModelProperty(value = "Target currency", example = "BRL", required = true)
    @NotNull(message = "Target currency cannot be null")
    Currency targetCurrency,

    @ApiModelProperty(value = "Conversion rate", example = "5.25", required = true)
    @Positive(message = "Conversion rate must be positive")
    double conversionRate,

    @ApiModelProperty(value = "Query date and time", example = "2024-07-12T18:12:24", required = true)
    @PastOrPresent(message = "Query date cannot be in the future")
    LocalDateTime queryDate,

    @ApiModelProperty(value = "Amount to be converted", example = "100.0", required = true)
    @Positive(message = "Amount must be greater than zero")
    double amount,

    @ApiModelProperty(value = "Converted amount", example = "850.0", required = true)
    @Positive(message = "Converted amount must be greater than zero")
    double convertedAmount
) {}
