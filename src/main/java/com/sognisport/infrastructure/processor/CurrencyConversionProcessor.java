package com.sognisport.infrastructure.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CurrencyConversionProcessor implements Processor {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void process(Exchange exchange) throws Exception {
        String response = exchange.getIn().getBody(String.class);
        JsonNode jsonNode = objectMapper.readTree(response);
        exchange.getIn().setBody(jsonNode);
    }
}
