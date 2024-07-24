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
package com.shelson.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception for business errors.
 * <p>
 * This exception is thrown when a business logic error occurs in the application.
 * It is annotated with {@link ResponseStatus} to return a 400 Bad Request HTTP status
 * code when the exception is thrown.
 * </p>
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new BusinessException with the specified detail message.
     *
     * @param message the detail message
     */
    public BusinessException(String message) {
        super(message);
    }
}
