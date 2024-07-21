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
package com.sognisport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

/**
 * Configuration for method validation.
 * This class sets up a bean for method validation using Bean Validation.
 */
@Configuration
public class MethodValidationConfig {

    private static final Logger logger = LoggerFactory.getLogger(MethodValidationConfig.class);

    /**
     * Creates a {@link MethodValidationPostProcessor} bean.
     * This bean enables validation of methods annotated with Bean Validation.
     *
     * @return A configured {@link MethodValidationPostProcessor}.
     */
    @Bean
    MethodValidationPostProcessor methodValidationPostProcessor() {
        logger.info("Creating MethodValidationPostProcessor bean for method validation");
        return new MethodValidationPostProcessor();
    }
}
