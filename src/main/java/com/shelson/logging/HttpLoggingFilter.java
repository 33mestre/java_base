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
package com.shelson.logging;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HTTP filter for logging details of HTTP requests and responses.
 * This filter intercepts all HTTP requests, logging information such as the HTTP method,
 * destination URI, protocol, response status, user agent, and processing time.
 */
public class HttpLoggingFilter extends HttpFilter {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(HttpLoggingFilter.class);

    /**
     * Initializes the filter.
     *
     * @param filterConfig The filter configuration.
     * @throws ServletException If an error occurs during initialization.
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization logic, if necessary
        logger.info("HttpLoggingFilter initialized.");
    }

    /**
     * Executes the filter, logging details of the HTTP request and response.
     *
     * @param request  The HttpServletRequest object that contains the client request.
     * @param response The HttpServletResponse object that contains the server response.
     * @param chain    The FilterChain to pass the request and response to the next filter in the chain.
     * @throws IOException      If an I/O error occurs during processing.
     * @throws ServletException If a general servlet error occurs during processing.
     */
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        long startTime = System.currentTimeMillis();
        chain.doFilter(request, response);
        long duration = System.currentTimeMillis() - startTime;

        String method = request.getMethod();
        String endpoint = request.getRequestURI();
        String protocol = request.getProtocol();
        int status = response.getStatus();
        String userAgent = request.getHeader("User-Agent");
        String timestamp = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss Z").format(new Date());

        String logMessage = String.format("HTTP %d %s %s %s [%s] \"%s\" in %d ms",
                status, method, endpoint, protocol, timestamp, userAgent, duration);

        logger.info(logMessage); // Using logger instead of System.out.println
    }

    /**
     * Destroys the filter, performing necessary cleanup.
     */
    @Override
    public void destroy() {
        // Cleanup logic, if necessary
        logger.info("HttpLoggingFilter destroyed.");
    }
}
