package com.shelson.application.dto;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import com.shelson.domain.model.Currency;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * DTO that represents details about the currency conversion.
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
        return "CurrencyConversionDTO{" +
                "sourceCurrency=" + sourceCurrency +
                ", targetCurrency=" + targetCurrency +
                ", conversionRate=" + conversionRate +
                ", queryDate=" + queryDate +
                ", amount=" + amount +
                ", convertedAmount=" + convertedAmount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CurrencyConversionDTO that = (CurrencyConversionDTO) o;

        if (Double.compare(that.conversionRate, conversionRate) != 0) return false;
        if (Double.compare(that.amount, amount) != 0) return false;
        if (Double.compare(that.convertedAmount, convertedAmount) != 0) return false;
        if (!sourceCurrency.equals(that.sourceCurrency)) return false;
        if (!targetCurrency.equals(that.targetCurrency)) return false;
        return queryDate.equals(that.queryDate);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = sourceCurrency.hashCode();
        result = 31 * result + targetCurrency.hashCode();
        temp = Double.doubleToLongBits(conversionRate);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + queryDate.hashCode();
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(convertedAmount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
