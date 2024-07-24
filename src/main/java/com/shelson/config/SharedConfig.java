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
package com.shelson.config;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for setting up a shared Camel {@link CamelContext}.
 * <p>
 * This class provides a {@link CamelContext} bean that is shared across the application.
 * It initializes and starts the Camel context.
 */
@Configuration
public class SharedConfig {

    /**
     * Creates and configures a {@link CamelContext} bean.
     * <p>
     * The {@link CamelContext} is initialized and started before being returned as a bean.
     * 
     * @return a {@link CamelContext} instance that is started and ready for use.
     * @throws Exception if there is an error starting the Camel context.
     */
    @Bean
    CamelContext camelContext() throws Exception {
        CamelContext camelContext = new DefaultCamelContext();
        camelContext.start();
        return camelContext;
    }
}
