package com.shelson.application.service;

import java.util.Map;

public class CurrencyConversionServiceApiResponse {
    private Map<String, Double> rates;

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }
}
