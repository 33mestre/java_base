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
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * DTO that represents details about the currency conversion.
 * 
 * @version 0.6.3
 * @since 2024-07-24
 * @see com.shelson.domain.model.Currency
 * @see com.shelson.application.service.CurrencyConversionService
 * @see org.apache.camel.ProducerTemplate
 * @author Shelson Ferrari
 */
@ApiModel(description = "Details about the currency conversion")
public class CurrencyConversionDTO {

    @ApiModelProperty(value = "Source currency", example = "USD", required = true)
    @NotNull(message = "Source currency cannot be null")
    private Currency sourceCurrency;

    @ApiModelProperty(value = "Target currency", example = "BRL", required = true)
    @NotNull(message = "Target currency cannot be null")
    private Currency targetCurrency;

    @ApiModelProperty(value = "Conversion rate", example = "5.25", required = true)
    @Positive(message = "Conversion rate must be positive")
    private double conversionRate;

    @ApiModelProperty(value = "Query date and time", example = "2024-07-12T18:12:24", required = true)
    @PastOrPresent(message = "Query date cannot be in the future")
    private LocalDateTime queryDate;
    
    @ApiModelProperty(value = "Amount to be converted", example = "100.0", required = true)
    @Positive(message = "Amount must be greater than zero")
    private double amount;

    @ApiModelProperty(value = "Converted amount", example = "850.0", required = true)
    @Positive(message = "Converted amount must be greater than zero")
    private double convertedAmount;

    // Default constructor
    public CurrencyConversionDTO() {
    }

    // Constructor with all parameters
    public CurrencyConversionDTO(Currency sourceCurrency, Currency targetCurrency, double conversionRate, LocalDateTime queryDate, double amount, double convertedAmount) {
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
        this.conversionRate = conversionRate;
        this.queryDate = queryDate;
        this.amount = amount;
        this.convertedAmount = convertedAmount;
    }

    // Getters and Setters
    public Currency getSourceCurrency() {
        return sourceCurrency;
    }

    public void setSourceCurrency(Currency sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
    }

    public Currency getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(Currency targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public double getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(double conversionRate) {
        this.conversionRate = conversionRate;
    }

    public LocalDateTime getQueryDate() {
        return queryDate;
    }

    public void setQueryDate(LocalDateTime queryDate) {
        this.queryDate = queryDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getConvertedAmount() {
        return convertedAmount;
    }

    public void setConvertedAmount(double convertedAmount) {
        this.convertedAmount = convertedAmount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("sourceCurrency", sourceCurrency)
                .append("targetCurrency", targetCurrency)
                .append("conversionRate", conversionRate)
                .append("queryDate", queryDate)
                .append("amount", amount)
                .append("convertedAmount", convertedAmount)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CurrencyConversionDTO that = (CurrencyConversionDTO) o;

        return new EqualsBuilder()
                .append(conversionRate, that.conversionRate)
                .append(amount, that.amount)
                .append(convertedAmount, that.convertedAmount)
                .append(sourceCurrency, that.sourceCurrency)
                .append(targetCurrency, that.targetCurrency)
                .append(queryDate, that.queryDate)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(sourceCurrency)
                .append(targetCurrency)
                .append(conversionRate)
                .append(queryDate)
                .append(amount)
                .append(convertedAmount)
                .toHashCode();
    }
}
