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

package com.shelson.application.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import io.swagger.v3.oas.annotations.Hidden;

/**
 * Controller responsible for the application's home page.
 * This controller redirects root application requests to the Swagger UI interface,
 * allowing API exploration.
 * 
 * Example usage:
 * <pre>
 * {@code
 * // URL: /
 * // This will redirect to /swagger-ui.html
 * }
 * </pre>
 * 
 * @author Shelson Ferrari
 * @version 0.6.3
 * @since 2024-07-24
 */
@Controller
@Hidden // Ignores this controller in Swagger documentation
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    /**
     * Redirects the root request ("/") to the Swagger UI interface.
     * This method captures requests to the application's root URL and redirects them to the Swagger UI.
     *
     * @return A string indicating the redirect URL to the Swagger UI.
     */
    @GetMapping("/")
    public String home() {
        logger.info("Redirecting to Swagger UI");
        return "redirect:/swagger-ui.html";
    }
}
