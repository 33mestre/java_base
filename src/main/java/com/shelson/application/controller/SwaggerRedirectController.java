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
 * Controller responsible for redirecting to the Swagger UI interface.
 * This controller handles two different routes to redirect the user
 * to the Swagger UI.
 * 
 * @version 0.6.3
 * @since 2024-07-24
 * @author Shelson Ferrari
 */
@Controller
@Hidden
public class SwaggerRedirectController {

    private static final Logger logger = LoggerFactory.getLogger(SwaggerRedirectController.class);

    /**
     * Redirects the "/swagger-ui/" route to "/swagger-ui/index.html".
     * This method captures requests to the URL "/swagger-ui/" and redirects to the Swagger UI.
     *
     * @return A string indicating the redirect URL to the Swagger UI.
     */
    @GetMapping("/swagger-ui/")
    public String redirectToSwaggerUIWithSlash() {
        logger.info("Redirecting '/swagger-ui/' to '/swagger-ui/index.html'");
        return "redirect:/swagger-ui/index.html";
    }

    /**
     * Redirects the "/swagger-ui" route to "/swagger-ui/index.html".
     * This method captures requests to the URL "/swagger-ui" and redirects to the Swagger UI.
     *
     * @return A string indicating the redirect URL to the Swagger UI.
     */
    @GetMapping("/swagger-ui")
    public String redirectToSwaggerUIWithoutSlash() {
        logger.info("Redirecting '/swagger-ui' to '/swagger-ui/index.html'");
        return "redirect:/swagger-ui/index.html";
    }
}
