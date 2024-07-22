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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * Interceptor for logging HTTP request details.
 * This interceptor logs the details of the request before, during, and after the handler processing.
 */
@Component
public class LoggingInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

    /**
     * Method executed before the handler processing.
     * Logs the called endpoint, HTTP method, and request parameters.
     *
     * @param request  The HttpServletRequest object that contains the client's request.
     * @param response The HttpServletResponse object that contains the server's response.
     * @param handler  The chosen handler to execute the request.
     * @return {@code true} to continue the execution chain; {@code false} to interrupt.
     * @throws Exception If an error occurs during pre-handling.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("Endpoint called: {}", request.getRequestURI());
        logger.info("HTTP method: {}", request.getMethod());

        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            logger.info("Parameter: {} = {}", paramName, request.getParameter(paramName));
        }

        return true;
    }

    /**
     * Method executed after the handler processing but before rendering the view.
     * Can be used to add logs or modify the ModelAndView before rendering.
     *
     * @param request      The HttpServletRequest object that contains the client's request.
     * @param response     The HttpServletResponse object that contains the server's response.
     * @param handler      The chosen handler to execute the request.
     * @param modelAndView The ModelAndView object that contains the view and model data.
     * @throws Exception If an error occurs during post-handling.
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("Handler executed: {}", handler);
        if (modelAndView != null) {
            logger.info("View to be rendered: {}", modelAndView.getViewName());
        }
    }

    /**
     * Method executed after the request processing is completed.
     * Can be used to log information or perform cleanup after the request completion.
     *
     * @param request  The HttpServletRequest object that contains the client's request.
     * @param response The HttpServletResponse object that contains the server's response.
     * @param handler  The chosen handler to execute the request.
     * @param ex       The exception thrown, if any.
     * @throws Exception If an error occurs after request completion.
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("Request to {} completed with status {}", request.getRequestURI(), response.getStatus());
        if (ex != null) {
            logger.error("Exception occurred: ", ex);
        }
    }
}
