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
 * Unless required by applicable law or agreed to in writing, software distributed under the Licenses é
 * distribuído em uma base "COMO ESTÁ", SEM GARANTIAS OU CONDIÇÕES DE QUALQUER TIPO, expressas ou implícitas. Veja
 * os Licenses para a linguagem específica que rege permissões e limitações sob os Licenses.
 */

package com.shelson.infrastructure.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * Global exception handler for the application.
 * This class captures and handles specific and general exceptions thrown by the controllers.
 * <p>
 * This handler covers:
 * <ul>
 *     <li>Resource not found exceptions, returning a 404 status.</li>
 *     <li>Business exceptions, returning a 400 status.</li>
 *     <li>Validation errors, returning a 400 status with field-specific messages.</li>
 *     <li>Argument type mismatch errors, returning a 400 status with details of the type conversion issue.</li>
 *     <li>General exceptions, returning a 500 status for uncaught errors.</li>
 * </ul>
 * <p>
 * Each handler logs the error details using SLF4J and returns a structured JSON response with the error information.
 * 
 * @version 0.6.3
 * @since 2024-07-24
 * 
 * @author Shelson Ferrari
 * 
 * @see org.springframework.web.bind.annotation.ExceptionHandler
 * @see org.springframework.web.bind.annotation.RestControllerAdvice
 */
@RestControllerAdvice
public class RestExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    /**
     * Handles exceptions of type {@link ResourceNotFoundException}.
     *
     * @param ex      The exception thrown.
     * @param request The web request object.
     * @return A {@link ResponseEntity} containing the error details and the HTTP status NOT_FOUND.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("timestamp", LocalDateTime.now());
        errorDetails.put("message", ex.getMessage());
        errorDetails.put("details", request.getDescription(false));

        logger.error("ResourceNotFoundException: {}", errorDetails);

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles exceptions of type {@link BusinessException}.
     *
     * @param ex      The exception thrown.
     * @param request The web request object.
     * @return A {@link ResponseEntity} containing the error details and the HTTP status BAD_REQUEST.
     */
    @ExceptionHandler(BusinessException.class)
    public final ResponseEntity<Object> handleBusinessException(BusinessException ex, WebRequest request) {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("timestamp", LocalDateTime.now());
        errorDetails.put("message", ex.getMessage());
        errorDetails.put("details", request.getDescription(false));

        logger.error("BusinessException: {}", errorDetails);

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles method argument validation exceptions.
     *
     * @param ex The exception thrown.
     * @return A {@link ResponseEntity} containing the error details and the HTTP status BAD_REQUEST.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("timestamp", LocalDateTime.now());
        errorDetails.put("errors", errors);

        logger.error("MethodArgumentNotValidException: {}", errorDetails);

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles exceptions of type {@link IllegalArgumentException}.
     *
     * @param ex      The exception thrown.
     * @param request The web request object.
     * @return A {@link ResponseEntity} containing the error details and the HTTP status BAD_REQUEST.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("timestamp", LocalDateTime.now());
        errorDetails.put("message", ex.getMessage());
        errorDetails.put("details", request.getDescription(false));

        logger.error("IllegalArgumentException: {}", errorDetails);

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles method argument type mismatch exceptions.
     *
     * @param ex      The exception thrown.
     * @param request The web request object.
     * @return A {@link ResponseEntity} containing the error details and the HTTP status BAD_REQUEST.
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public final ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, WebRequest request) {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("timestamp", LocalDateTime.now());
        errorDetails.put("message", "Failed to convert value of type '" + ex.getValue() + "' to required type '" + ex.getRequiredType().getSimpleName() + "'");
        errorDetails.put("details", request.getDescription(false));

        logger.error("MethodArgumentTypeMismatchException: {}", errorDetails);

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles all uncaught exceptions.
     *
     * @param ex      The exception thrown.
     * @param request The web request object.
     * @return A {@link ResponseEntity} containing the error details and the HTTP status INTERNAL_SERVER_ERROR.
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("timestamp", LocalDateTime.now());
        errorDetails.put("message", ex.getMessage());
        errorDetails.put("details", request.getDescription(false));

        logger.error("Exception: {}", errorDetails);

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
