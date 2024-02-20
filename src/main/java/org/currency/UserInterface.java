package org.currency;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    public static void showMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Currency Converter Menu:");
            System.out.println("1. Convert Currency");
            System.out.println("2. Display Currencies");
            System.out.println("3. Add Currency");
            System.out.println("4. Modify Currency");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                switch (choice) {
                    case 1:
                        performCurrencyConversion();
                        break;
                    case 2:
                        displayCurrencies();
                        break;
                    case 3:
                        addCurrency();
                        break;
                    case 4:
                        modifyCurrency();
                        break;
                    case 5:
                        System.out.println("Exiting Currency Converter. Goodbye!");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number from the menu.");
                scanner.nextLine();
            }
        }
    }

    private static void performCurrencyConversion() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the amount to exchange (in RUB): ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Enter the currency code to exchange to (USD, EUR): ");
        String currencyCode = scanner.nextLine().toUpperCase();

        CurrencyConverter.currencyConverter(amount, currencyCode);
    }

    private static void displayCurrencies() {
        try {
            JSONObject exchangeRates = FileHandler.loadExchangeRatesFromFile();
            List<String> currencies = FileHandler.showCurrenciesWithRates(exchangeRates);
            System.out.println("Available currencies:");
            for (String currency : currencies) {
                System.out.println(currency.toUpperCase());
            }

        } catch (IOException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void addCurrency() {
        Scanner scanner = new Scanner(System.in);

        try {
            JSONObject exchangeRates = FileHandler.loadExchangeRatesFromFile();

            System.out.print("Enter the currency code to add: ");
            String currencyCode = scanner.nextLine().toUpperCase();

            System.out.print("Enter the exchange rate for " + currencyCode + ": ");
            double exchangeRate = scanner.nextDouble();
            scanner.nextLine(); // Consume newline character

            FileHandler.addCurrency(exchangeRates, currencyCode, exchangeRate);
            System.out.println(currencyCode + " added successfully.");
        } catch (IOException | ParseException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void modifyCurrency() {
        Scanner scanner = new Scanner(System.in);
        try {
            JSONObject exchangeRates = FileHandler.loadExchangeRatesFromFile();

            System.out.print("Enter the currency code to modify: ");
            String currencyCode = scanner.nextLine().toUpperCase();

            System.out.print("Enter the new exchange rate for " + currencyCode + ": ");
            double newExchangeRate = scanner.nextDouble();
            scanner.nextLine();

            FileHandler.modifyCurrency(exchangeRates, currencyCode, newExchangeRate);
            System.out.println(currencyCode + " modified successfully.");
        } catch (IOException | ParseException | IllegalArgumentException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
























}
