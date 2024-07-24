package com.shelson.application.routes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.shelson.application.dto.CurrencyConversionDTO;

@CamelSpringBootTest
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CurrencyConversionRouteTest {

    @Autowired
    private ProducerTemplate producerTemplate;

    @Autowired
    private CamelContext camelContext;

    @Test
    public void testCurrencyConversionRoute() throws Exception {
        camelContext.start();

        Map<String, Object> headers = new HashMap<>();
        headers.put("source", "USD");
        headers.put("target", "EUR");
        headers.put("amount", 100.0);

        CurrencyConversionDTO result = producerTemplate.requestBodyAndHeaders("direct:convertCurrency", null, headers, CurrencyConversionDTO.class);
        assertNotNull(result);
        assertEquals("USD", result.getSourceCurrency().getCode());
        assertEquals("EUR", result.getTargetCurrency().getCode());

        camelContext.stop();
    }
}
