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
package com.shelson.swing;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.*;
import org.json.JSONObject;
import com.formdev.flatlaf.FlatDarkLaf;
import com.shelson.domain.model.Currency;

/**
 * Swing application for currency conversion.
 */
public class CurrencyConverterSwingApp {

    private JFrame frame;
    private JComboBox<String> sourceCurrencyComboBox;
    private JComboBox<String> targetCurrencyComboBox;
    private JTextField inputAmountField;
    private JTextField outputAmountField;
    private JTextField conversionRateField;
    private JLabel statusLabel;

    private static final List<String> currencies = Stream.of(Currency.values())
                                                        .map(Currency::getCode)
                                                        .collect(Collectors.toList());

    /**
     * Constructor for the CurrencyConverterSwingApp.
     */
    public CurrencyConverterSwingApp() {
        initialize();
    }

    /**
     * Initializes the graphical interface of the application.
     */
    private void initialize() {
        // Set the look and feel
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }

        frame = new JFrame("Currency Converter");
        frame.setBounds(100, 100, 450, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setLocationRelativeTo(null); // Center the window on the screen

        // Add the Help menu
        if (!System.getProperty("os.name").toLowerCase().contains("mac")) {
            JMenuBar menuBar = new JMenuBar();
            JMenu helpMenu = new JMenu("Help");
            JMenuItem aboutItem = new JMenuItem("About");
            aboutItem.addActionListener(e -> showAboutDialog());
            helpMenu.add(aboutItem);
            menuBar.add(helpMenu);
            frame.setJMenuBar(menuBar);
        }

        JLabel lblEntrada = new JLabel("<html><b>Input Data:</b></html>");
        lblEntrada.setBounds(10, 10, 150, 25);
        frame.getContentPane().add(lblEntrada);

        JLabel lblSourceCurrency = new JLabel("Source Currency:");
        lblSourceCurrency.setBounds(10, 40, 120, 25);
        frame.getContentPane().add(lblSourceCurrency);

        sourceCurrencyComboBox = new JComboBox<>(currencies.toArray(new String[0]));
        sourceCurrencyComboBox.setBounds(140, 40, 120, 25);
        sourceCurrencyComboBox.setSelectedItem("BRL"); // Set BRL as the default value
        frame.getContentPane().add(sourceCurrencyComboBox);

        JLabel lblTargetCurrency = new JLabel("Target Currency:");
        lblTargetCurrency.setBounds(10, 75, 120, 25);
        frame.getContentPane().add(lblTargetCurrency);

        targetCurrencyComboBox = new JComboBox<>(currencies.toArray(new String[0]));
        targetCurrencyComboBox.setBounds(140, 75, 120, 25);
        targetCurrencyComboBox.setSelectedItem("USD"); // Set USD as the default value
        frame.getContentPane().add(targetCurrencyComboBox);

        JButton btnInvert = new JButton("Invert Currencies");
        btnInvert.setBounds(280, 55, 140, 25);
        frame.getContentPane().add(btnInvert);

        btnInvert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String sourceCurrency = (String) sourceCurrencyComboBox.getSelectedItem();
                String targetCurrency = (String) targetCurrencyComboBox.getSelectedItem();
                sourceCurrencyComboBox.setSelectedItem(targetCurrency);
                targetCurrencyComboBox.setSelectedItem(sourceCurrency);
                inputAmountField.setText("");
                outputAmountField.setText("");
                conversionRateField.setText("");
                inputAmountField.requestFocus();
            }
        });

        JLabel lblInputAmount = new JLabel("Input Amount:");
        lblInputAmount.setBounds(10, 110, 120, 25);
        frame.getContentPane().add(lblInputAmount);

        inputAmountField = new JTextField();
        inputAmountField.setBounds(140, 110, 120, 25);
        frame.getContentPane().add(inputAmountField);
        inputAmountField.setColumns(10);

        // Add a KeyListener to the input field
        inputAmountField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    convert();
                }
            }
        });

        JSeparator separator = new JSeparator();
        separator.setBounds(10, 145, 410, 2);
        frame.getContentPane().add(separator);

        JLabel lblSaida = new JLabel("<html><b>Output Data:</b></html>");
        lblSaida.setBounds(10, 160, 150, 25);
        frame.getContentPane().add(lblSaida);

        JLabel lblConversionRate = new JLabel("Conversion Rate:");
        lblConversionRate.setBounds(10, 190, 120, 25);
        frame.getContentPane().add(lblConversionRate);

        conversionRateField = new JTextField();
        conversionRateField.setBounds(140, 190, 120, 25);
        frame.getContentPane().add(conversionRateField);
        conversionRateField.setColumns(10);
        conversionRateField.setEditable(false);

        JLabel lblOutputAmount = new JLabel("Converted Amount:");
        lblOutputAmount.setBounds(10, 225, 120, 25);
        frame.getContentPane().add(lblOutputAmount);

        outputAmountField = new JTextField();
        outputAmountField.setBounds(140, 225, 120, 25);
        frame.getContentPane().add(outputAmountField);
        outputAmountField.setColumns(10);
        outputAmountField.setEditable(false);

        JButton btnConvert = new JButton("Convert");
        btnConvert.setBounds(280, 190, 120, 25);
        frame.getContentPane().add(btnConvert);

        JButton btnClear = new JButton("Clear");
        btnClear.setBounds(280, 225, 120, 25);
        frame.getContentPane().add(btnClear);

        btnConvert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                convert();
            }
        });

        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });

        // Set focus on the input field when the application starts
        EventQueue.invokeLater(() -> inputAmountField.requestFocus());

        // Add the status bar
        statusLabel = new JLabel(" ");
        statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
        statusLabel.setBounds(10, 260, 410, 25);
        frame.getContentPane().add(statusLabel);

        // Configure integration with macOS menu
        if (System.getProperty("os.name").toLowerCase().contains("mac")) {
            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.APP_ABOUT)) {
                desktop.setAboutHandler(e -> showAboutDialog());
            }
            if (desktop.isSupported(Desktop.Action.APP_QUIT_HANDLER)) {
                desktop.setQuitHandler((e, response) -> {
                    frame.dispose();
                    response.performQuit();
                });
            }
        }
    }

    /**
     * Performs the currency conversion by calling the API and updates the UI fields.
     */
    private void convert() {
        String sourceCurrency = (String) sourceCurrencyComboBox.getSelectedItem();
        String targetCurrency = (String) targetCurrencyComboBox.getSelectedItem();
        String inputAmount = inputAmountField.getText();
        if (sourceCurrency != null && targetCurrency != null && !inputAmount.isEmpty()) {
            try {
                double amount = Double.parseDouble(inputAmount);
                JSONObject jsonResponse = convertCurrency(sourceCurrency, targetCurrency, amount);
                double convertedAmount = jsonResponse.getDouble("convertedAmount");
                double conversionRate = jsonResponse.getDouble("conversionRate");
                outputAmountField.setText(String.valueOf(convertedAmount));
                conversionRateField.setText(String.valueOf(conversionRate));
                inputAmountField.requestFocus();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid amount.");
                inputAmountField.requestFocus();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
                inputAmountField.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Please fill in all fields.");
            inputAmountField.requestFocus();
        }
    }

    /**
     * Clears the UI fields.
     */
    private void clearFields() {
        sourceCurrencyComboBox.setSelectedItem("BRL");
        targetCurrencyComboBox.setSelectedItem("USD");
        inputAmountField.setText("");
        outputAmountField.setText("");
        conversionRateField.setText("");
        inputAmountField.requestFocus();
    }

    /**
     * Calls the currency conversion API and returns the JSON response.
     *
     * @param source The source currency.
     * @param target The target currency.
     * @param amount The amount to be converted.
     * @return A {@link JSONObject} containing the API response.
     * @throws IOException If an error occurs while connecting to the API.
     */
    private JSONObject convertCurrency(String source, String target, double amount) throws IOException {
        String apiUrl = "http://localhost:8080/api/v1/conversions/convert?source=" + source + "&target=" + target + "&amount=" + amount;
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new IOException("API request error, response code: " + responseCode);
        }
        Scanner scanner = new Scanner(url.openStream());
        String response = scanner.useDelimiter("\\Z").next();
        scanner.close();
        return new JSONObject(response);
    }

    /**
     * Displays the "About" dialog with application information.
     */
    private void showAboutDialog() {
        JOptionPane.showMessageDialog(frame,
                "Currency Converter\nProject for currency conversion using an external API.",
                "About", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Main method to start the application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        // Set the application name on macOS
        System.setProperty("apple.awt.application.name", "Currency Converter");
        System.setProperty("apple.laf.useScreenMenuBar", "true");

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CurrencyConverterSwingApp window = new CurrencyConverterSwingApp();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}