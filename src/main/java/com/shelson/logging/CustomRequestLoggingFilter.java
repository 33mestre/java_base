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
import java.util.TimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Custom filter to log details of HTTP requests.
 * <p>
 * This filter logs various details about each HTTP request and response,
 * including the request method, URI, protocol, response status, timestamp, user agent,
 * and the duration of the request processing. It is useful for monitoring and debugging purposes.
 * </p>
 * 
 * @version 0.6.3
 * @since 2024-07-24
 * 
 * @author Shelson Ferrari
 * 
 * @see jakarta.servlet.FilterChain
 * @see jakarta.servlet.FilterConfig
 * @see jakarta.servlet.http.HttpFilter
 * @see jakarta.servlet.http.HttpServletRequest
 * @see jakarta.servlet.http.HttpServletResponse
 * @see org.slf4j.Logger
 * @see org.slf4j.LoggerFactory
 */
public class CustomRequestLoggingFilter extends HttpFilter {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(CustomRequestLoggingFilter.class);

    /**
     * Initializes the filter.
     * <p>
     * This method is called by the servlet container to indicate to the filter that it is being
     * placed into service. The filter configuration object provides the filter with its
     * initialization parameters. Here, any necessary setup can be done.
     * </p>
     *
     * @param filterConfig The filter configuration.
     * @throws ServletException If an error occurs during initialization.
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("Initializing CustomRequestLoggingFilter");
        // Initialization logic if necessary
    }

    /**
     * Executes the filter, logging details of the HTTP request and response.
     * <p>
     * This method performs the actual logging. It captures the start time of the request,
     * allows the request to proceed through the filter chain, then logs the request details,
     * response status, and the time taken to process the request.
     * </p>
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

        // Execute the request
        chain.doFilter(request, response);

        long duration = System.currentTimeMillis() - startTime;

        // Date and time formatting
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss 'GMT'XXX");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-3"));
        String formattedDate = sdf.format(new Date());

        // Get the user agent
        String userAgent = request.getHeader("User-Agent");

        // Get the HTTP status code
        int statusCode = response.getStatus();

        // Build the log message
        String logMessage = String.format("HTTP %s %s %s | %d | %s | %s | %d ms",
                request.getMethod(),
                request.getRequestURI(),
                request.getProtocol(),
                statusCode,
                formattedDate,
                userAgent,
                duration);

        logger.info(logMessage);
    }

    /**
     * Destroys the filter, performing necessary cleanup.
     * <p>
     * This method is called by the servlet container to indicate to the filter that it is being
     * taken out of service. Here, any necessary cleanup can be done.
     * </p>
     */
    @Override
    public void destroy() {
        logger.info("Destroying CustomRequestLoggingFilter");
        // Cleanup logic if necessary
    }
}
