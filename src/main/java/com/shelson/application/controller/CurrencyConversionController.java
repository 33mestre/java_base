package com.shelson.application.controller;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shelson.application.dto.CurrencyConversionDTO;
import com.shelson.domain.model.Currency;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/conversions")
@Api(value = "Currency Conversion Controller", description = "Currency conversion operations")
public class CurrencyConversionController {

    @Autowired
    private ProducerTemplate producerTemplate;

    @GetMapping("/convert")
    @ApiOperation(value = "Converts the source currency to the target currency", notes = "Returns the currency conversion details")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation", response = CurrencyConversionDTO.class),
            @ApiResponse(code = 400, message = "Invalid input")
    })
    public CurrencyConversionDTO convertCurrency(
            @RequestParam @ApiParam(value = "Source currency", example = "USD") Currency source, 
            @RequestParam @ApiParam(value = "Target currency", example = "BRL") Currency target,
            @RequestParam @ApiParam(value = "Amount to be converted", example = "100.0") double amount) {

        Map<String, Object> headers = new HashMap<>();
        headers.put("sourceCurrency", source);
        headers.put("targetCurrency", target);

        return producerTemplate.requestBodyAndHeaders("direct:convertCurrency", amount, headers, CurrencyConversionDTO.class);
    }
}
