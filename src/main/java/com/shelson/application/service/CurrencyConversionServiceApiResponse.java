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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Map;

/**
 * Represents the response from the currency conversion API.
 * 
 * This class is used to map the JSON response from the currency conversion API to a Java object.
 * The expected JSON should contain exchange rates.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyConversionServiceApiResponse {

    /**
     * Map containing exchange rates for different currencies.
     */
    private Map<String, Double> rates;

    /**
     * Gets the exchange rates.
     * 
     * @return a map containing the exchange rates, where the key is the currency code and the value is the exchange rate.
     */
    public Map<String, Double> getRates() {
        return rates;
    }

    /**
     * Sets the exchange rates.
     * 
     * @param rates a map containing the exchange rates, where the key is the currency code and the value is the exchange rate.
     */
    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }
}
