package org.currency.service;

import org.currency.model.CurrencyRate;
import org.currency.view.UserInterface;
import java.util.List;

public class MainLogicClass {

    private final ConverterClass converter;
    private final UserInterface userInterface;
    private final List<CurrencyRate> exchangeRates;

    public MainLogicClass(ConverterClass converter, UserInterface userInterface, List<CurrencyRate> exchangeRates) {
        this.converter = converter;
        this.userInterface = userInterface;
        this.exchangeRates = exchangeRates;
    }

    public void startCurrencyConverter() {
        try {
            userInterface.print("Enter the amount to exchange: ");
            double amount = Double.parseDouble(userInterface.getByUser());

            userInterface.print("Enter the code of the currency to exchange to (e.g., EUR): ");
            String currencyCode = userInterface.getByUser();

            double exchangeRate = getExchangeRate(currencyCode, exchangeRates);
            double exchangedAmount = converter.convertCurrency(amount, exchangeRate);
            userInterface.print(String.format("You can buy %.2f %s for %.2f RUB\n", exchangedAmount, currencyCode, amount));
        } catch (Exception e) {
            userInterface.print("An error occurred while reading exchange rates: " + e.getMessage());
        }
    }

    private double getExchangeRate(String currencyCode, List<CurrencyRate> exchangeRates) {
        for (CurrencyRate rate : exchangeRates) {
            if (rate.getName().equalsIgnoreCase(currencyCode)) {
                return rate.getRate();
            }
        }
        throw new IllegalArgumentException("Exchange rate not found for currency code: " + currencyCode);
    }
}
