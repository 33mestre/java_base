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
package com.shelson.console;

import java.io.IOException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.shelson.domain.model.Currency;

/**
 * Console application for currency conversion.
 */
public class CurrencyConverterConsoleApp {

    private static final Logger logger = LoggerFactory.getLogger(CurrencyConverterConsoleApp.class);
    private static final List<String> currencies = Stream.of(Currency.values())
                                                        .map(Currency::getCode)
                                                        .collect(Collectors.toList());

    /**
     * Main method to start the application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        printHeader();

        Scanner scanner = new Scanner(System.in);

        try {
            // Prompt for source currency
            System.out.print("Enter the source currency (e.g., BRL): ");
            String sourceCurrency = scanner.nextLine().toUpperCase();
            if (sourceCurrency == null || sourceCurrency.length() != 3 || !currencies.contains(sourceCurrency)) {
                logger.error("Invalid source currency: {}", sourceCurrency);
                System.out.println("Invalid source currency.");
                return;
            }

            // Prompt for target currency
            System.out.print("Enter the target currency (e.g., USD): ");
            String targetCurrency = scanner.nextLine().toUpperCase();
            if (targetCurrency == null || targetCurrency.length() != 3 || !currencies.contains(targetCurrency)) {
                logger.error("Invalid target currency: {}", targetCurrency);
                System.out.println("Invalid target currency.");
                return;
            }

            // Prompt for amount to be converted
            System.out.print("Enter the amount to be converted: ");
            double amount = Double.parseDouble(scanner.nextLine());

            // Perform currency conversion
            JSONObject jsonResponse = convertCurrency(sourceCurrency, targetCurrency, amount);
            double convertedAmount = jsonResponse.getDouble("convertedAmount");
            double conversionRate = jsonResponse.getDouble("conversionRate");

            // Display results in the console
            System.out.println("Conversion rate: " + conversionRate);
            System.out.println("Converted amount: " + convertedAmount);

        } catch (NumberFormatException ex) {
            logger.error("Invalid amount entered.", ex);
            System.out.println("Please enter a valid amount.");
        } catch (ConnectException ex) {
            logger.error("Error during currency conversion. Connection refused.", ex);
            System.out.println("Error during currency conversion. Connection refused.");
        } catch (Exception ex) {
            logger.error("Error during currency conversion.", ex);
            System.out.println("Error: " + ex.getMessage());
        } finally {
            scanner.close();
        }
    }

    /**
     * Calls the currency conversion API and returns the JSON response.
     *
     * @param source The source currency.
     * @param target The target currency.
     * @param amount The amount to be converted.
     * @return A {@link JSONObject} containing the API response.
     * @throws IOException If an error occurs connecting to the API.
     */
    private static JSONObject convertCurrency(String source, String target, double amount) throws IOException {
        String apiUrl = "http://shelson-app:8080/api/v1/conversions/convert?source=" + source + "&target=" + target + "&amount=" + amount;
        logger.debug("Calling API: " + apiUrl);
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new IOException("API request error, response code: " + responseCode);
        }
        StringBuilder response = new StringBuilder();
        try (Scanner scanner = new Scanner(connection.getInputStream())) {
            while (scanner.hasNext()) {
                response.append(scanner.nextLine());
            }
        }
        return new JSONObject(response.toString());
    }

    /**
     * Prints the application header to the terminal.
     */
    private static void printHeader() {
        System.out.println("---------------------------------");
        System.out.println("    _____ __         __                    ______                          _");
        System.out.println("   / ___// /_  ___  / /________  ____     / ____/__  ______________ ______(_)");
        System.out.println("   \\__ \\/ __ \\/ _ \\/ / ___/ __ \\/ __ \\   / /_  / _ \\/ ___/ ___/ __ `/ ___/ /");
        System.out.println("  ___/ / / / /  __/ (__  ) /_/ / / / /  / __/ /  __/ /  / /  / /_/ / /  / / ");
        System.out.println(" /____/_/ /_/\\___/_/____/\\____/_/ /_/  /_/    \\___/_/  /_/   \\__,_/_/  /_/  ");
        System.out.println();
        System.out.println("---------------------------------");
        System.out.println("Currency Converter Console App");
        System.out.println("---------------------------------");
    }
}
