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
package com.shelson.domain.model;

import java.time.LocalDateTime;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity that represents a currency conversion.
 * 
 * @version 0.6.3
 * @since 2024-07-24
 * 
 * @author Shelson Ferrari
 * 
 * @see java.time.LocalDateTime
 * @see com.shelson.domain.model.Currency
 */
@Entity
@Table(name = "CURRENCY_CONVERSIONS")
public class CurrencyConversion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "source_currency")
    private Currency sourceCurrency;

    @Enumerated(EnumType.STRING)
    @Column(name = "target_currency")
    private Currency targetCurrency;

    @Column(name = "conversion_rate")
    private double conversionRate;

    @Column(name = "query_date")
    private LocalDateTime queryDate;

    /**
     * Default constructor.
     */
    public CurrencyConversion() {
    }

    /**
     * Constructor with parameters.
     *
     * @param sourceCurrency The source currency.
     * @param targetCurrency The target currency.
     * @param conversionRate The conversion rate.
     * @param queryDate      The date and time of the query.
     */
    public CurrencyConversion(Currency sourceCurrency, Currency targetCurrency, double conversionRate, LocalDateTime queryDate) {
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
        this.conversionRate = conversionRate;
        this.queryDate = queryDate;
    }

    /**
     * Gets the conversion ID.
     *
     * @return The conversion ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the conversion ID.
     *
     * @param id The conversion ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the source currency.
     *
     * @return The source currency.
     */
    public Currency getSourceCurrency() {
        return sourceCurrency;
    }

    /**
     * Sets the source currency.
     *
     * @param sourceCurrency The source currency.
     */
    public void setSourceCurrency(Currency sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
    }

    /**
     * Gets the target currency.
     *
     * @return The target currency.
     */
    public Currency getTargetCurrency() {
        return targetCurrency;
    }

    /**
     * Sets the target currency.
     *
     * @param targetCurrency The target currency.
     */
    public void setTargetCurrency(Currency targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    /**
     * Gets the conversion rate.
     *
     * @return The conversion rate.
     */
    public double getConversionRate() {
        return conversionRate;
    }

    /**
     * Sets the conversion rate.
     *
     * @param conversionRate The conversion rate.
     */
    public void setConversionRate(double conversionRate) {
        this.conversionRate = conversionRate;
    }

    /**
     * Gets the query date and time.
     *
     * @return The query date and time.
     */
    public LocalDateTime getQueryDate() {
        return queryDate;
    }

    /**
     * Sets the query date and time.
     *
     * @param queryDate The query date and time.
     */
    public void setQueryDate(LocalDateTime queryDate) {
        this.queryDate = queryDate;
    }

    /**
     * Returns a string representation of the currency conversion.
     *
     * @return A string representing the currency conversion.
     */
    @Override
    public String toString() {
      return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
              .append("id", id)
              .append("sourceCurrency", sourceCurrency)
              .append("targetCurrency", targetCurrency)
              .append("conversionRate", conversionRate)
              .append("queryDate", queryDate)
              .toString();
  }

    /**
     * Checks if two instances of CurrencyConversion are equal.
     *
     * @param o The object to be compared.
     * @return {@code true} if the objects are equal; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      CurrencyConversion that = (CurrencyConversion) o;

      return new EqualsBuilder()
              .append(conversionRate, that.conversionRate)
              .append(id, that.id)
              .append(sourceCurrency, that.sourceCurrency)
              .append(targetCurrency, that.targetCurrency)
              .append(queryDate, that.queryDate)
              .isEquals();
  }

    /**
     * Computes the hash code of the instance.
     *
     * @return The hash code of the instance.
     */
    @Override
    public int hashCode() {
      return new HashCodeBuilder(17, 37)
              .append(id)
              .append(sourceCurrency)
              .append(targetCurrency)
              .append(conversionRate)
              .append(queryDate)
              .toHashCode();
  }
}
