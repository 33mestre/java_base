/**
 * This package contains Camel routes for the currency conversion application.
 * The routes handle incoming requests for currency conversion, process them
 * using the CurrencyConversionService, and return the conversion results.
 *
 * <p>
 * The main route in this package is {@link com.shelson.application.routes.CurrencyConversionRoute},
 * which processes the conversion by extracting headers from the request, validating them,
 * and invoking the service to perform the conversion.
 * </p>
 *
 * @since 1.0
 * @version 1.0
 */
package com.shelson.application.routes;
